define([ 'require', 'module', '{angular}/angular', '{w20-core}/modules/ui' ], function(require, module, angular) {

    var manageServiceReferentielManagement = angular.module('ReferentielManagement', [ 'ui.bootstrap' ]);
    // manageServiceReferentielManagement.value('storedReferentiel', {});
    // manageServiceReferentielManagement.value('editedReferentiel', {});

    manageServiceReferentielManagement.controller('ReferentielManagementController', [ '$scope', function($scope) {
        //alert("Chargement AngularJS en cours");
    } ]);
    // FIN CONTROLLER ReferentielManagementController

    return {
        angularModules : [ 'ReferentielManagement' ]
    };
});
