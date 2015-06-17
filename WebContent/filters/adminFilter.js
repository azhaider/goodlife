angular.module('adminFilter', [])
	.filter('roleType', function() {
		return function(input) {
			if(input == "s") {
				return "Student";
			}
			if(input == "a") {
				return "Admin";
			}
			if(input == "f") {
				return "Facilitator";
			}
			if(input == "m") {
				return "Moderator";
			}
			
		};
	});