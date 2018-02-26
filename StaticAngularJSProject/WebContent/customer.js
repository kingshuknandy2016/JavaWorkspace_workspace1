/**
 * 
 */

var appli=angular.module('FirstApp', []);
appli.controller('FirstController', function($scope) {
	$scope.customerOBJ=[
{ 'customerName': 'Infitech', 'custId': 1010,'custLoc': 'Bangalore', 'Payment': 5000},
{ 'customerName': 'SPI', 'custId': 1011,'custLoc': 'Mysore', 'Payment': 7000},
{ 'customerName': 'Infosis', 'custId': 1012, 'custLoc': 'kolkata', 'Payment': 8000 } 
]
});