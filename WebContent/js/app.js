'use strict';

var forceForGood = angular.module('forceForGood', [
	'ngRoute',
	'ui.bootstrap',
	'userManagement',
	'curriculum',
	'ngTable',
	'adminCenter',
	'multi-select',
	'adminFilter',
	'adminService',
	'chapterServices',
	'studentServices',
	'angularFileUpload',
	'ngAnimate']);

forceForGood.config(['$routeProvider',
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
			when('/curriculum', {
				templateUrl: 'partials/curriculum.html',
				controller: 'studentCurriculumView'
			}). 
			otherwise({
				redirectTo: '/curriculum'
			});
	}]);