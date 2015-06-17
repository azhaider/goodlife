var curriculum = angular.module('curriculum', []);

/*
	Chapter Builder page controller
*/
curriculum.controller('ChapterBuilder', ['$scope', '$log', '$filter', 'ngTableParams', 'listChapters','$modal',
	function($scope, $log, $filter, ngTableParams, listChapters, modal) {

		//Table Information
		var data = [];

		$scope.chapterTable = new ngTableParams({
			//Settings
			page: 1,
			count: 10,
	        filter: {

	            chapTitle: ''       // initial filter

	        }
		}, {
			total: data.length,
	        //groupBy: 'data.curriculumTreeList.objR.chapId',
		       getData: function($defer, params) {

		            var page = data.slice((params.page() - 1) * params.count(), params.page() * params.count());
		            $scope.data=page;
                    $defer.resolve(page);
		            
		        }
		});
		

		var handleSuccess = function (response) {
			$log.log("Successful");
			data = response.data;
			//alert(JSON.stringify(data));
			$scope.chapterTable .reload();
		};

		var handleError = function (response) {
			$log.log("Error");
			data = [{}];
			$scope.chapterTable .reload();
		};
		
	    $scope.addRow = function(title, desc, id, addChapterFm) {
	    	if (addChapterFm.$valid){
				listChapters.addChapter(title, desc, id).then( handleSuccess, handleError );
				$scope.showConfirmation("success", "Chapter titled " + "'"+title +"' was added!");
				init();
	    	}

	      };
	      
	    $scope.removeRow = function(chapId){		
	  		var index = -1;		
	  		var comArr = eval( data );
		//	alert(JSON.stringify(comArr[0].objR));
	  		for( var i = 0; i < comArr.length; i++ ) {
	  			if( comArr[i].objR.chapId === chapId ) {
	  				index = i;
	  				break;
	  			}
	  		}

	  		if( index === -1 ) {
				$scope.showConfirmation("fail", "Chapter" + "#"+chapId +" was not deleted!");
	  		}
	  		data.splice( index, 1 );		
			listChapters.deleteChapter(chapId).then( handleSuccess, handleError );
			$scope.showConfirmation("success", "Chapter" + " #"+chapId +" has been deleted!");
			init();
	  	};
	  	
	    var show = false;

	    $scope.showButton = function(val){
	    if(angular.isUndefined(val)){
	 	    	 return false;
	 	     }
	    else{
	      return show;
	    }
	    }
	  	
	    $scope.hideRows = function(){
	    	show = false;
	    };
	  	
	    $scope.showRows = function(){
	    	show = true;
	    };
  	
	    $scope.showRow = function(val){

	     if(typeof val == 'undefined'){
	    	// alert('false');
	    	 return false;

	     }
	     else{
	    	 return true;
	     }
	    };
	    
	    var myModal;
	    
	    $scope.openDialog = function(url){
	    	myModal = modal.open({templateUrl: url});
	    }
	    
	    $scope.closeDialog = function(){
	    	myModal.close();
	    }
	    
	    $scope.tableClick = function(event){
	    	
	    	//alert(event.target.textContent);
	    	
	    	if (event.target.nodeName == "TD"){
		    	$scope.showPane("subchapter");
	    	}
	    	else if (event.target.nodeName == "TH"){
		    	$scope.showPane("chapter");
	    	}
	    }
	    
	    $scope.showChapterPane = false;
	    $scope.showSubChapPane = false;
	    
	    $scope.showPane = function(paneType){
	    	if (paneType == "subchapter"){
		    	  $scope.showChapterPane = false;
		          $scope.showSubChapPane = true;
	    	}
	    	else if (paneType == "chapter"){
		    	  $scope.showChapterPane = true;
		          $scope.showSubChapPane = false;
	    	}

	    };
	    
	    $scope.showSuccess = false;
	    $scope.showFailure = false;
	    $scope.confirmMsg = "";
	    
	    $scope.showConfirmation = function(flag, message){
	    	if (flag == "success"){
		    	  $scope.showSuccess = true;
		          $scope.showFailure = false;
	    	}
	    	else if (flag == "fail"){
		    	  $scope.showFailure = true;
		          $scope.showSuccess = false;
	    	}
	    	
	          $scope.confirmMsg = message;


	    };
	    
		var init = function () {
			listChapters.search().then( handleSuccess, handleError );

			};
		init();

}]);