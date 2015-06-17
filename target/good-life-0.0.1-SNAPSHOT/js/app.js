'use strict';

var curriculumMakerApp = angular.module('curriculumMakerApp', [
	'ngRoute',
	'ui.bootstrap',
	'userManagement',
	'curriculum',
	'ngTable',
	'adminCenter',
	'multi-select',
	'adminFilter',
	'adminService',
	'chapterServices']);

curriculumMakerApp.config(['$routeProvider',
	function($routeProvider) {
		$routeProvider.
			when('/searchUsers', {
				templateUrl:'partials/search.html',
				controller: 'AdminSearch'
			}).
			when('/adminConsole', {
				templateUrl: 'partials/adminConsole.html',
				controller: 'AdminConsole'
			}). 
			when('/chapterBuilder', {
				templateUrl: 'partials/chapterBuilder.html',
				controller: 'ChapterBuilder'
			}). 
			otherwise({
				redirectTo: '/searchUsers'
			});
	}]);