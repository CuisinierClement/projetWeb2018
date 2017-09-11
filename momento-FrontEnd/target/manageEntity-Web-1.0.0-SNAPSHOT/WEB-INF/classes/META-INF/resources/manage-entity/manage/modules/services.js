// SERVICES FOR ALL FRAGMENTS
define([ 'require', 'module', '{angular}/angular', '{w20-core}/modules/env', '{w20-core}/modules/culture', '{w20-core}/modules/notifications'], function(require, module, angular) {

    var module = angular.module('manageServices', [ 'w20CoreEnv', 'w20CoreCulture', 'w20CoreNotifications' ]);
    
    module.service('ManagingServices', [ 'CultureService', 'NotificationService', function(cultureService, notificationService) {
        var notificationManageService = {
              'notificationSuccess' : function(txt, login)  {
                  var options = {theme : 'alert alert-success'};
                  return notificationService.notify("Success!!! User " + login + " has been " + txt, false, options, false);
              },
              'notificationError' : function(txt)  {
                  var options = {theme : 'alert alert-danger'};
                  return notificationService.notify("Error !!! " + txt, false, options, false);
              },
              'isDisable' : 'false',
              'setIsDisable' : function(valeur){
                  return isDisable = valeur;
              },
              'getIsDisable' : function(){
                  return isDisable;
              }
        };
        return notificationManageService;
    }]);
    
    
 // Expose the angular module to W20 loader
    return {
        angularModules : [
            'manageServices'
        ]
    };
});