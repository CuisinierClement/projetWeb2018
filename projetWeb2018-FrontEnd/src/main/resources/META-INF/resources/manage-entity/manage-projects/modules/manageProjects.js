//define([ 'require', 'module', '{angular}/angular', '{w20-core}/modules/ui', '{angular-animate}/angular-animate' ], function(require, module, angular) {
define(
        [ 'require', 'module', '{angular}/angular', '{w20-core}/modules/ui', '{w20-core}/modules/notifications', '{angular-animate}/angular-animate',
                '{angular-bootstrap}/ui-bootstrap-tpls', '{manage}/libext/datepickerPopup/index', '{w20-components}/modules/select',
                '{manage-users}/modules/manageUsers' ],
        function(require, module, angular) {

            var manageServiceProjectManagement = angular.module('ProjectsManagement', [ 'ngAnimate', 'ui.bootstrap', 'ui.grid', 'ui.grid.selection',
                    'manageServices', 'UserManagement' ]), config = module && module.config() || {};

            manageServiceProjectManagement.factory('ProjectService', [ '$resource', function($resource) {
                var Project = $resource(require.toUrl(config.apiUrl + 'projects/:projectId'), {}, {
                    'update' : {
                        method : 'PUT'
                    }
                });

                return {
                    // allPaginatedProjects : function(pageIndex, pageSize,
                    // success,
                    // error) {
                    // return Project.query({
                    // pageIndex : pageIndex - 1,
                    // pageSize : pageSize
                    // }, success, error);
                    // },
                    allPaginatedProjects : function(success, error) {
                        return Project.query(success, error);
                    },
                    searchPaginatedProjects : function(searchString, pageIndex, pageSize, success, error) {
                        return Project.query({
                            searchString : searchString,
                            pageIndex : pageIndex - 1,
                            pageSize : pageSize
                        }, success, error);
                    },
                    project : function(name, success, error) {
                        return Project.get({
                            projectId : name
                        }, success, error);
                    },
                    deleteProject : function(project, success, error) {
                        project.$delete({
                            projectId : project.name
                        }, success, error);
                    },
                    addProject : function(project, success, error) {
                        var newProject = new Project();

                        newProject.name = project.name;
                        newProject.deliverables = project.deliverables;
                        newProject.responsable = project.responsable;

                        newProject.$save(success, error);
                    },
                    updateProject : function(project, success, error) {
                        project.$update({
                            projectId : project.name
                        }, success, error);
                    }
                };
            } ]);

            /**
             * AngularJS default filter with the following expression: "person
             * in people | filter: {name: $select.search, age: $select.search}"
             * performs an AND between 'name: $select.search' and 'age:
             * $select.search'. We want to perform an OR.
             */
            manageServiceProjectManagement.filter('propsFilter', function() {
                return function(items, props) {
                    var out = [];

                    if (angular.isArray(items)) {
                        var keys = Object.keys(props);

                        items.forEach(function(item) {
                            var itemMatches = false;

                            for (var i = 0; i < keys.length; i++) {
                                var prop = keys[i];
                                var text = props[prop].toLowerCase();
                                if (item[prop].toString().toLowerCase().indexOf(text) !== -1) {
                                    itemMatches = true;
                                    break;
                                }
                            }

                            if (itemMatches) {
                                out.push(item);
                            }
                        });
                    } else {
                        // Let the output be the input untouched
                        out = items;
                    }

                    return out;
                };
            });

            // TRY to change date locale....
            // manageServiceProjectManagement.config(function($mdDateLocaleProvider)
            // {
            // $mdDateLocaleProvider.formatDate = function(date) {
            // return moment(date).format('YYYY-MM-DD');
            // };
            // });

            manageServiceProjectManagement
                    .controller(
                            'ProjectsManagementController',
                            [
                                    '$scope',
                                    '$timeout',
                                    '$location',
                                    '$http',
                                    '$q',
                                    'CultureService',
                                    'ProjectService',
                                    'UserService',
                                    'ManagingServices',
                                    function($scope, $timeout, $location, $http, $q, cultureService, projectService, userService, managingServices) {

                                        /** * For open Deliverables ** */
                                        $scope.open = true;

                                        $scope.isOpen = function() {
                                            $scope.rowsSelectedBis = $scope.gridApiBis.selection.getSelectedRows();
                                            $scope.newRow = $scope.rowsSelectedBis[0];
                                            $scope.gridOptionsDeliverables.data = [];
                                            for (var i = 0; i < $scope.newRow.deliverables.length; i++) {
                                                $scope.gridOptionsDeliverables.data[i] = $scope.newRow.deliverables[i];
                                            }
                                            if ($scope.newRow == $scope.projects || $scope.newRow == undefined) {
                                                return $scope.open = true;

                                            } else {
                                                $scope.projects = angular.copy($scope.newRow);
                                                return $scope.open = false;
                                            }

                                        };

                                        $scope.collapsed = true;

                                        $scope.isCollapsed = function() {
                                            $scope.rowsSelectedBis = $scope.gridApiBis.selection.getSelectedRows();
                                            $scope.newRow = $scope.rowsSelectedBis[0];
                                            // if they are equal or when you
                                            // have no row select (or you
                                            // deselect one)
                                            if ($scope.newRow == $scope.projects || $scope.newRow == undefined) {
                                                return $scope.collapsed = true;

                                            } else {
                                                $scope.projects = angular.copy($scope.newRow);
                                                return $scope.collapsed = false;
                                            }

                                        };

                                        /** * For project modal ** */
                                        $scope.manageProject = {
                                            'modalUnique' : {
                                                'actions' : {
                                                    '_showProjectModal' : function(action, textTitle) {
                                                        $scope.action = action;
                                                        $scope.modalTitle = textTitle;
                                                        $scope.disabledNameProject = $scope.isActive("name");
                                                        $scope.disabledResponsable = $scope.isActive("responsable");
                                                        if (action === "edit" || action === "delete") {
                                                            $scope.rowSelected = $scope.gridApiBis.selection.getSelectedRows();
                                                            $scope.recupRow = $scope.rowSelected[0];
                                                            $scope.project = angular.copy($scope.recupRow);
                                                        } else {
                                                            $scope.project = {};

                                                        }
                                                        $("#modalId").modal('show');
                                                    },
                                                    '_confirmProject' : function() {
                                                        // alert($scope.project);
                                                        if ($scope.action === "add") {
                                                            $scope.createNewProject($scope.project);
                                                        } else if ($scope.action === "edit") {
                                                            $scope.editProject($scope.project);
                                                        } else if ($scope.action === "delete") {
                                                            $scope.deleteProject($scope.project);
                                                        }
                                                        $("#modalId").modal('hide');
                                                        $scope.project = {};
                                                    },
                                                    '_cancelFrame' : function() {
                                                        $("#modalId").modal('hide');
                                                    }
                                                }
                                            }
                                        };
                                        
                                        $scope.isActive = function(field){
                                            if(($scope.action === "edit" && field === "name") || $scope.action === "delete"){
                                                return true;
                                            }
                                            else {
                                                return false;
                                            }
                                        };

                                        function getProjectsSuccess(data) {

                                            $scope.paginatedProjects = data;
                                            // alert(data);
                                            // $scope.pagination.totalServerItems
                                            // =
                                            // $scope.paginatedProjects.$viewInfo.resultSize;

                                        }

                                        function getProjectsError(err) {
                                            alert("Error!!" + err);
                                            throw new Error('could not get projects list ' + err.message);
                                        }

                                        function getProjects() {
                                            // $interval( function()
                                            // {$scope.gridApi.selection.selectRow($scope.gridOptions.data[0]);},
                                            // 0, 1);
                                            // projectService.allPaginatedProjects($scope.pagination.currentPage,
                                            // $scope.pagination.pageSize,
                                            // getProjectsSuccess,
                                            // getProjectsError);
                                            projectService.allPaginatedProjects(getProjectsSuccess, getProjectsError);
                                        }

                                        $scope.pagination = {
                                            pageSize : 10000,
                                            currentPage : 1,
                                            totalServerItems : 0
                                        };
                                        $scope.hideGridDeliverables = true;
                                        $scope.gridOptionsDeliverables = {
                                            data : $scope.projectsDeliverables,
                                            enableSorting : true,
                                            enableFiltering : true,
                                            enableRowSelection : true,
                                            multiSelect : false,
                                            showGridFooter : true,
                                            columnDefs : [ {
                                                name : 'Deliverable',
                                                field : 'name'
                                            }, {
                                                name : 'Date Start',
                                                field : 'dateStart',
                                                cellFilter : 'date:\'dd/MM/yyyy\''
                                            }, {
                                                name : 'Date End',
                                                field : 'dateEnd',
                                                cellFilter : 'date:\'dd/MM/yyyy\''
                                            }, {
                                                name : 'Date New Start',
                                                field : 'dateNewStart',
                                                cellFilter : 'date:\'dd/MM/yyyy\''
                                            }, {
                                                name : 'Date New End',
                                                field : 'dateNewEnd',
                                                cellFilter : 'date:\'dd/MM/yyyy\''
                                            }, {
                                                name : 'Comment',
                                                field : 'comment'
                                            } ]
                                        }
                                        /** ******************************** */
                                        /** * For project Home Bis Grid ** */
                                        /** ******************************** */
                                        $scope.gridOptionsHomeBis = {
                                            data : 'paginatedProjects',
                                            enableSorting : true,
                                            enableFiltering : true,
                                            enableRowSelection : true,
                                            multiSelect : false,
                                            showGridFooter : true,
                                            columnDefs : [ {
                                                field : 'name',
                                                name : 'Project'

                                            }, {
                                                field : 'responsable',
                                                name : 'Responsable'

                                            } ]
                                        };

                                        $scope.gridOptionsHomeBis.enableRowHeaderSelection = false;
                                        $scope.gridOptionsHomeBis.onRegisterApi = function(gridApiBis) {
                                            $scope.gridApiBis = gridApiBis;
                                            $scope.rowsSelectedBis = function() {
                                                $scope.hideGridDeliverables = false;
                                                $scope.changeData();
                                                // $scope.rowsSelectedBis =
                                                // $scope.gridApiBis.selection.getSelectedRows();
                                                // $scope.recupRowBis =
                                                // $scope.rowsSelectedBis[0];
                                                // $scope.projectDeliverables =
                                                // angular.copy($scope.recupRowBis);
                                            };
                                        };

                                        $scope.createNewProject = function(newProject) {
                                            // $scope.isDisable =
                                            // managingServices.setIsDisable(false);
                                            projectService
                                                    .addProject(
                                                            newProject,
                                                            function() {
                                                                getProjects();
                                                                managingServices.notificationSuccess("created", newProject.name);
                                                            },
                                                            function(err) {
                                                                managingServices
                                                                        .notificationError("The Project hasn't been created, verify if the name doesn't already exist or if the rules of creation are respected");
                                                            });
                                        };

                                        $scope.editProject = function(project) {
                                           // $scope.isDisable = managingServices.setIsDisable(true);       
                                            projectService.updateProject(project, function() {
                                                    getProjects();
                                                    managingServices.notificationSuccess("updated", project.name);
                                                }, function(err) {
                                                    managingServices.notificationError("The project hasn't been updated");
                                                });
                                        };
                                        
                                        $scope.deleteProject = function(project) {
                                            projectService.deleteProject(project, function() {
                                                getProjects();
                                                managingServices.notificationSuccess("deleted", project.name);
                                            }, function(err) {
                                                managingServices.notificationError("The project hasn't been deleted");
                                            });
                                        };

                                        /** * For deliverable modal ** */
                                        $scope.manageDeliverable = {
                                            'modalUnique' : {
                                                'actions' : {
                                                    '_openFrame' : function() {
                                                        $("#modalDeliverableId").modal('show');
                                                    },
                                                    '_cancelFrame' : function() {
                                                        $("#modalDeliverableId").modal('hide');
                                                    }
                                                }
                                            }
                                        };

                                        $scope.changeData = function() {
                                            $scope.rowSelected = $scope.gridApi.selection.getSelectedRows();
                                            if ($scope.rowSelected == null) {
                                                $scope.hideGridDeliverables = true;
                                            } else {
                                                $scope.gridOptionsDeliverables.data = $scope.rowSelected[0].deliverables;
                                            }
                                        };

                                        // For users

                                        function getUsers() {
                                            userService.allUsers(function(users) {
                                                alert("je suis la dans getusers");
                                                $scope.users = users;
                                            });
                                        }
                                        ;

                                        getProjects();
                                        getUsers();

                                    } ]);
            // FIN CONTROLLER ProjectsManagementController

            return {
                angularModules : [ 'ProjectsManagement' ]
            };
        });