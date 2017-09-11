define([ 'require', 'module', '{angular}/angular', '{w20-core}/modules/env', '{w20-core}/modules/culture', '{w20-core}/modules/notifications', '{angular-ui-grid}/ui-grid' ], 
        function( require, module, angular) {
    
    var manageServiceUserManagement = angular.module('UserManagement', [ 'ui.bootstrap', 'ui.grid.pagination', 'ui.grid', 'ui.grid.selection',
            'ui.grid.resizeColumns', 'ui.grid.autoResize', 'w20CoreEnv', 'w20CoreCulture', 'w20CoreNotifications', 'manageServices']), config = module && module.config() || {};

    manageServiceUserManagement.factory('UserService', [ '$resource', function($resource) {
        var User = $resource(require.toUrl(config.apiUrl + 'users/:userId'), {}, {
            'update' : {
                method : 'PUT'
            }
        });

        return {
            allUsers : function(success,error){
                return User.query({},success,error);
            },
            
            allPaginatedUsers : function(pageIndex, pageSize, success, error) {
                return User.query({
                    pageIndex : pageIndex - 1,
                    pageSize : pageSize
                }, success, error);
            },
            searchPaginatedUsers : function(searchString, pageIndex, pageSize, success, error) {
                return User.query({
                    searchString : searchString,
                    pageIndex : pageIndex - 1,
                    pageSize : pageSize
                }, success, error);
            },
            user : function(login, success, error) {
                return User.get({
                    userId : login
                }, success, error);
            },
            deleteUser : function(user, success, error) {
                user.$delete({
                    userId : user.login
                }, success, error);
            },
            addUser : function(user, success, error) {
                var newUser = new User();

                newUser.login = user.login;
                newUser.firstName = user.firstName;
                newUser.name = user.name;

                newUser.$save(success, error);
            },
            updateUser : function(user, success, error) {
                user.$update({
                    userId : user.login
                }, success, error);
            }
        };
    } ]);


    manageServiceUserManagement.controller('UsersManagementController', [ '$scope', '$rootScope', '$interval', 'UserService', 'ManagingServices',
            function($scope, $rootScope, $interval, userService, managingServices) {
                
                $scope.gridOptions = {
                    data : 'paginatedUsers',
                    enableFiltering : true,
                    enableRowSelection : true,
                    multiSelect : false,
                    showGridFooter : true,
                    columnDefs : [ {
                        field : 'login',
                        name : 'Login'
                    },{
                        field : 'name',
                        name : 'Name'
                    },{
                        field : 'firstName',
                        name : 'First Name'
                    }]
                };    
                
                              
                $scope.gridOptions.enableRowHeaderSelection = false;
                $scope.gridOptions.onRegisterApi = function(gridApi){
                    $scope.gridApi = gridApi;
                    $scope.rowsSelected = function(){
                        $scope.haveNoRowSelected = false;
                    };
                };
                              
                $scope.haveNoRowSelected = true;
                
                $scope.manageUser = {
                    'modalUnique':{
                        'actions':{
                            '_showUserModal': function(action, textTitle){ 
                                    $scope.regexLogin = /[a-z][0-9]{6}/i;
                                    $scope.regexName = /[a-z]{2,}/i;
                                    $scope.action = action;
                                    $scope.modalTitle = textTitle;
                                    $scope.disabledLogin = $scope.isActive("login");
                                    $scope.disabledFirstName = $scope.isActive("firstName");
                                    $scope.disabledName = $scope.isActive("name");
                                    if (action === "edit" || action === "delete"){  
                                        $scope.rowSelected = $scope.gridApi.selection.getSelectedRows();
                                        $scope.recupRow = $scope.rowSelected[0];
                                        $scope.user = angular.copy($scope.recupRow);                                    
                                    }
                                    else{
                                        $scope.user = {};
                                        
                                    }
                                    $("#modalId").modal('show');      
                            },
                            '_confirmUser' : function(){
                                    if($scope.action === "add"){
                                        $scope.createNewUser($scope.user);
                                    }
                                    else if($scope.action === "edit"){
                                        $scope.editUser($scope.user);
                                    }
                                    else if($scope.action === "delete"){
                                        $scope.deleteUser($scope.user);
                                    }
                                    $("#modalId").modal('hide');
                                    $scope.user = {};
                            },
                            '_cancelFrame': function(){
                                    $scope.regexLogin=null;
                                    $scope.regexName = null;
                                    $("#modalId").modal('hide');
                                    $scope.user = {};
                            }
                        }
                     }
                };  
                
                $scope.isActive = function(field){
                    if(($scope.action === "edit" && field === "login") || $scope.action === "delete"){
                        return true;
                    }
                    else {
                        return false;
                    }
                };
                         
                function getUsersSuccess(data) {      
                    $scope.paginatedUsers = data;
                    $scope.pagination.totalServerItems = $scope.paginatedUsers.$viewInfo.resultSize;                    
                }

                function getUsersError(err) {
                    alert("Error!!" + err);
                    throw new Error('could not get users list ' + err.message);
                }

                function getUsers() {
                    userService.allPaginatedUsers($scope.pagination.currentPage, $scope.pagination.pageSize, getUsersSuccess, getUsersError);
                }

                function searchUsers(searchString) {
                    if (searchString) {
                        userService.searchPaginatedUsers(searchString, $scope.pagination.currentPage, $scope.pagination.pageSize, getUsersSuccess,
                                getUsersError);
                    } else {
                        getUsers();
                    }
                }

                $scope.pagination = {
                    pageSize : 10000,
                    currentPage : 1,
                    totalServerItems : 0
                };

                $scope.pageChanged = function() {
                    getUsers();
                };
                              
                $scope.createNewUser = function(newUser) {
                    $scope.isDisable = managingServices.setIsDisable(false);                   
                        userService.addUser(newUser, function() {
                            getUsers();
                            managingServices.notificationSuccess("created", newUser.login);
                        }, function(err) {
                            managingServices.notificationError("The User hasn't been created, verify if the login doesn't already exist or if the rules of creation are respected");
                        });
                };

                $scope.editUser = function(user) {
                    $scope.isDisable = managingServices.setIsDisable(true);       
                        userService.updateUser(user, function() {
                            getUsers();
                            managingServices.notificationSuccess("updated", user.login);
                        }, function(err) {
                            managingServices.notificationError("The user hasn't been updated");
                        });
                };

                $scope.deleteUser = function(user) {
                        userService.deleteUser(user, function() {
                            getUsers();
                            managingServices.notificationSuccess("deleted", user.login);
                        }, function(err) {
                            managingServices.notificationError("The user hasn't been deleted");
                        });
                };

                $scope.$watch('searchedUser', function(newSearch, oldSearch) {
                    if (newSearch !== oldSearch) {
                        searchUsers(newSearch);
                    }
                });

                getUsers();
            } ]);

    return {
        angularModules : [ 'UserManagement' ]
    };
});
