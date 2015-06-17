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
			//alert(JSON.stringify(data[1]));
			$scope.chapterTable .reload();
		};

		var handleError = function (response) {
			$log.log("Error");
			data = [{}];
			$scope.chapterTable .reload();
		};
		
		//Add Chapter
		$scope.addRow = function(title, desc, id, addChapterFm) {

			var confirmAdd = function (response) {
				$scope.data.push({"objR":{"chapId":response.data,"chapDescr":desc,"chapTitle":title,"orderId":id,"published":false},"objL":[]});
				$scope.showConfirmation("success", "Chapter titled " + "'"+title +"' was added!");

			} 

			var failAdd = function (response) {
				$scope.showConfirmation("fail", "Chapter titled " + "'"+title +"' was not added! Please try again" );
			} 

			if (addChapterFm.$valid){
				listChapters.addChapter(title, desc, id).then(confirmAdd, failAdd);
			}

		};

		//Delete Chapter
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
			$scope.chapterTable .reload();
			init();

	  	};
	  	
	    var show = false;
	    //Expand Collapse -WIP
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
	    
	    // Dialog Modal
	    var myModal;
	    
	    $scope.openDialog = function(url){
	    	myModal = modal.open({templateUrl: url});
	    }
	    
	    $scope.closeDialog = function(){
	    	myModal.close();
	    }
	    
	    // View Row Details from Table
	    $scope.chapterClick = function(rowData){
	    	
	    	var chapTitle = rowData.chapTitle;
	    	$scope.showPane("chapter", chapTitle, "");

	    }
	    
	    $scope.exerciseClick = function(rowData){
	    	
	 	   var title = rowData.subChapTitle;
	 	   var chapId = rowData.chapId;
	 	   var chapTitle = "";
	    	
	  		var comArr = eval( data );
		  		for( var i = 0; i < comArr.length; i++ ) {
		  			if( comArr[i].objR.chapId === chapId ) {
		  				chapTitle = comArr[i].objR.chapTitle;
		  			}
		  		}
	    	
	    	
	    	$scope.showPane("subchapter", chapTitle, title);

	    }
	    
	    //Show Chapter vs. Exercise (Subchapter) View
	    $scope.showChapterPane = false;
	    $scope.showSubChapPane = false;
	    $scope.chapName = "";
	    $scope.subChapName = "";
	    
	    $scope.showPane = function(paneType, chap, subChap){
	    	if (paneType == "subchapter"){
		    	  $scope.showChapterPane = false;
		          $scope.showSubChapPane = true;
		          $scope.chapName = chap;
		          $scope.subChapName = subChap;
	    	}
	    	else if (paneType == "chapter"){
		    	  $scope.showChapterPane = true;
		          $scope.showSubChapPane = false;
		          $scope.chapName = chap;
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
	    $scope.submitSubchapter = function(subTitle, chapId){
			listChapters.addSubChapter(chapId, subTitle, 'some desc', '1').then( handleSuccess, handleError );
			$scope.showConfirmation("success", "SubChapter" + " titled '"+subTitle +"' has been created!");
			init();
	    }
	    
		var init = function () {
			listChapters.search().then( handleSuccess, handleError );

			};
		init();

}]);