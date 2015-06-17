forceForGood.controller('studentCurriculumView', ['$scope', '$log', 'student',
	function($scope, $log, student) {
		//Preset Values
		$scope.userId = 1;	


		//Update form content
		$scope.subChapterClicked = function(subChapter) {
			student.getSubChapterForm(subChapter.subChapId).$promise.then(function(subChapterFormList) {
				subChapterFormTemp = subChapterFormList[0];
				subChapterTypeTemp  = subChapterFormList[1];
				if ( subChapterTypeTemp == 'm' ) {
					angular.forEach(subChapterFormTemp, function(subChapQues) {
						student.getMultChoiceOption(subChapQues.multiQuesId).$promise.then(function(result) {
							subChapQues.options=result;
						});
						student.getMultiUserAnswer($scope.userId, subChapQues.multiQuesId).$promise.then(function(result) {
							subChapQues.userAnswer = result.userAnswer;
							$log.log("result of multi answer");
							$log.log(result);
						});
					});

				}
				else if ( subChapterTypeTemp == 's' ) {
					$log.log(subChapterFormTemp);
					angular.forEach( subChapterFormTemp, function(subChapQues) {
						student.getShortAnsUserAnswer($scope.userId, subChapQues.saQId).$promise.then(function(result) {
							subChapQues.userAnswer  = result.userAnswer;
							$log.log("result of short answer");
							$log.log(result);
						});
					});
				} else {

				}
				$scope.subChapterSelected = subChapter;
				$scope.subChapterForm = subChapterFormTemp;
				$scope.subChapterType = subChapterTypeTemp;
			});
			
		};

		function handleSuccess( response ) {
			$log.log("Good call");
			$log.log(response);
			$scope. chapters = response.data;
		}

		function handleError( response ) {
			$log.log("Error with http call");
		}

	}]);