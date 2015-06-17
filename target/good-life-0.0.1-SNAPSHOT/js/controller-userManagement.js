var userManagement = angular.module('userManagement', []);

/*
	Admin Seach page controller
*/
userManagement.controller('AdminSearch', ['$scope', '$log', '$filter', 'ngTableParams', 'userService',
	function($scope, $log, $filter, ngTableParams, userService) {

		$scope.submitSearch = function(searchForm) {
			$log.log( 'Submitting values' );
			var idsForRoles = getIdsForRoles($scope.roleOptions);
			var parameters = buildObject(searchForm.textInput, searchForm.typeSelected.id, idsForRoles);
			$log.log(idsForRoles);
			$log.log(parameters);
			// data = userService.search(idsForRoles, parameters);
			// $scope.userTable .reload();
			userService.search(idsForRoles, parameters).then( handleSuccess, handleError );
		};

		//Search Options for the Text Field
		$scope.searchOptions = [
			{ label:'User Name', id:"usr_nm"},
			{ label:'First Name', id:"frst_nm"},
			{ label:'Last Name', id:"lst_nm"},
			{ label:'E-mail', id:"email"}];

		//Role options
		$scope.roleOptions = [
			{ name:'Student', id:'student', ticked: true},
			{ name:'Facilitator', id:'facilitator', ticked: false},
			{ name:'Moderator', id:'moderator', ticked: false}];

		//Table Information
		var data = [];

		$scope.userTable = new ngTableParams({
			//Settings
			page: 1,
			count: 10,
			sorting: {
				name: 'asc'
			}
		}, {
			total: data.length,
			getData: function($defer, params) {
				var orderedData = params.sorting() ? $filter('orderBy')(data, params.orderBy()) : data;
				$defer.resolve(orderedData.slice((params.page()-1)*params.count(),params.page()*params.count()));
			}
		});

		var getIdsForRoles = function(roleOptions) {
			var rolechoosen = {student:0, moderator:0, facilitator:0};
			angular.forEach(roleOptions, function(value, key) {
				if( value.ticked === true ) {
					if(value.id == "student") rolechoosen.student = 1;
					if(value.id == "facilitator") rolechoosen.facilitator = 1;
					if(value.id == "moderator") rolechoosen.moderator = 1;
						
				}
			});
			return rolechoosen;
		};

		var buildObject = function(textInput, id, idsForRoles) {
			var sendObject = {};
			sendObject.input = textInput;
			sendObject.id = id;
			sendObject.sb = idsForRoles.student;
			sendObject.mb = idsForRoles.moderator;
			sendObject.fb = idsForRoles.facilitator;
			return sendObject;
		};

		var handleSuccess = function (response) {
			$log.log("Succesful search");
			data = response.data;
			$scope.userTable .reload();
		};

		var handleError = function (response) {
			$log.log("Error with search");
			data = [{}];
			$scope.userTable .reload();
		};

}]);