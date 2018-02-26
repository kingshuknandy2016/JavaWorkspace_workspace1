var MyApp = angular.module('MyApp', [ngRoute]);
MyApp.config(function ($routeProvider) {

    $routeProvider.when('/Home', { templateUrl: 'OUTPUT/Home.html',
                                    controller: 'HomeController'
    })
		          .when('/Customer', { templateUrl: 'OUTPUT/Customer.html',
		                                controller: 'CustomerController'
		          })
		          .when('/Employee', { templateUrl: 'OUTPUT/Employee.html',
		                                controller: 'EmployeeController'
		          });

		      });
		                        

MyApp.controller('HomeController', function ($scope) {
    $scope.message = "Home Page details";
});

MyApp.controller('CustomerController', function ($scope) {
    $scope.message = "Customer Page details";
});

MyApp.controller('EmployeeController', function ($scope) {
    $scope.message = "Employee Page details";
});
