var app = angular.module('app',[]);

app.controller('postcontroller', ['$scope','ProductCRUDService', function ($scope,ProductCRUDService) {
	
	//update product
	
   $scope.updateProduct = function () {
        ProductCRUDService.updateProduct($scope.productId,$scope.productName,$scope.productDesc,$scope.productType,$scope.quantity)
          .then(function success(response){
              $scope.postResultMessage = 'Product data updated!';
              $scope.errorMessage = '';
          },
          function error(response){
              $scope.postResultMessage = 'Error updating product!';
              $scope.message = '';
          });
    }
  
	
	
    $scope.getProduct = function () {
        var id = $scope.productId;
        ProductCRUDService.getUser($scope.id)
          .then(function success(response){
              $scope.product = response.data;
              $scope.message='';
              $scope.errorMessage = '';
          },
          function error (response ){
              $scope.message = '';
              if (response.status === 404){
                  $scope.errorMessage = 'Product not found!';
              }
              else {
                  $scope.errorMessage = "Error getting product!";
              }
          });  
         
    }
    
    //submit product
  
    $scope.submitForm = function () {
       ProductCRUDService.addProduct($scope.productId,$scope.productName, $scope.productDesc,$scope.productType,$scope.quantity)
         .then (function success(response){
             $scope.postResultMessage = 'Product info added Sucessfully';
             $scope.errorMessage = '';
         },
         function error(response){
             $scope.postResultMessage = 'Error adding product!';
             $scope.message = '';
       });
  
}
   
   
//delete product
    
   $scope.deleteProduct = function () {
        ProductCRUDService.deleteProduct($scope.productId)
          .then (function success(response){
              $scope.postResultMessage = 'Product  deleted!';
              $scope.user = null;
              $scope.errorMessage='';
          },
          function error(response){
              $scope.postResultMessage = 'Error deleting Product!';
              $scope.message='';
          })
    }
    
    $scope.getAllProducts = function () {
        ProductCRUDService.getAllProducts()
          .then(function success(response){
              $scope.response = response.data;
              $scope.postResultMessage='Sucessful';
              $scope.errorMessage = '';
          },
          function error (response ){
              $scope.postResultMessage='error';
              $scope.errorMessage = 'Error getting products!';
          });
    }
  
    $scope.editProduct = function (productId,productName,productDesc,productType,quantity) {
//	alert('edit'+productId);
    	  $scope.productId=productId;
    	  $scope.productName=productName;
    	  $scope.productDesc=productDesc;
    	  $scope.productType=productType;
    	  $scope.quantity=quantity;
    	  
}
    //reset product

    $scope.resetProduct = function (productId,productName,productDesc,productType,quantity) {
        	  $scope.productId="";
        	  $scope.productName="";
        	  $scope.productDesc="";
        	  $scope.productType="";
        	  $scope.quantity="";
        	  
    }
}]);

//order controller

app.controller('ordercontroller', ['$scope','OrderCRUDService', function ($scope,OrderCRUDService) {

	//submit order
	 
  
	$scope.submitOrderForm = function () {
	       OrderCRUDService.addOrder($scope.orderName, $scope.orderPrice,$scope.productId,$scope.orderStatus)
	         .then (function success(response){
	             $scope.postResultMessage = 'Order Sucessfully Submitted';
	             $scope.errorMessage = '';
	         },
	         function error(response){
	             $scope.errorMessage = 'Error adding order!';
	             $scope.message = '';
	       });
	  
	}
	//update order 
	
	 $scope.updateOrder = function () {
		 OrderCRUDService.updateOrder($scope.orderName,$scope.orderPrice,$scope.productId,$scope.orderStatus,$scope.orderId)
	          .then(function success(response){
	              $scope.postResultMessage = 'Order data updated!';
	              $scope.errorMessage = '';
	          },
	          function error(response){
	              $scope.postResultMessage = 'Error updating order!';
	              $scope.message = '';
	          });
	    }
	   //delete order
	 
	 $scope.deleteOrder = function () {
		 OrderCRUDService.deleteOrder($scope.productId)
	          .then (function success(response){
	              $scope.postResultMessage = 'Order  deleted!';
	              $scope.errorMessage='';
	          },
	          function error(response){
	              $scope.postResultMessage = 'Error deleting Order!';
	              $scope.message='';
	          })
	    }
	 $scope.getTemplate = function (order) {
	        if (employee.empID === $scope.selected.empID){
				return 'edit';
			}
	        else return 'display';
	    }
	 
	 $scope.reset = function () {
	        $scope.selected = {};
	    }
		
	   $scope.editEmployee = function (employee) {
	        $scope.selected = angular.copy(employee);
	    }
		
//get orders
    
    $scope.getAllOrders = function () {
        OrderCRUDService.getAllOrders()
          .then(function success(response){
              $scope.response = response.data;
              $scope.postResultMessage='Sucessful';
              $scope.errorMessage = '';
          },
          function error (response ){
              $scope.postResultMessage='error';
              $scope.errorMessage = 'Error getting orders!';
          });
    }
    
    $scope.editOrder = function(order) {
      //  alert("in eedit"+order.orderName);
        $scope.orderName = order.orderName;
        $scope.orderPrice = order.orderPrice;
        $scope.productId = order.productId;
        $scope.orderStatus=order.orderStatus;
       
    };


}]);	


app.service('ProductCRUDService',['$http', function ($http) {
	
   /* this.getUser = function getUser(userId){
        return $http({
          method: 'GET',
          url: 'users/'+userId
        });
	}
	*/
	 this.addProduct = function addProduct(productId,productName, productDesc,productType,quantity){
	        return $http({
	          method: 'POST',
	          url: 'createProduct',
	          data: {productId:productId,productName:productName, productDesc:productDesc, productType:productType, quantity:quantity}
	        });
	    }
		
   this.deleteProduct = function deleteProduct(id){
        return $http({
          method: 'DELETE',
          url: 'deleteProduct/'+id
        })
    }
	
    this.updateProduct = function updateProduct(productId,productName, productDesc,productType,quantity){
        return $http({
          method: 'PUT',
          url: 'updateProduct',
          data: {productId:productId,productName:productName, productDesc:productDesc, productType:productType, quantity:quantity}
        })
    }

    this.getAllProducts = function getAllProducts(){
        return $http({
          method: 'GET',
          url: 'getAllProducts'
        });
    }

}]);

app.service('OrderCRUDService',['$http', function ($http) {
	
	   /* this.getUser = function getUser(userId){
	        return $http({
	          method: 'GET',
	          url: 'users/'+userId
	        });
		}
		*/
	    this.addOrder = function addOrder(orderName, orderPrice,productId,orderStatus){
	        return $http({
	          method: 'POST',
	          url: 'createOrder',
	          data: {orderName:orderName, orderPrice:orderPrice, productId:productId, orderStatus:orderStatus}
	        });
	    }
	    
	    //update order
	    this.updateOrder = function updateOrder(orderName,orderPrice, productId,orderStatus,orderId){
	        return $http({
	          method: 'PUT',
	          url: 'updateOrder',
	          data: {orderName:orderName,orderPrice:orderPrice, productId:productId, orderStatus:orderStatus,orderId:orderId}
	        })
	    }
//delete order
	    this.deleteOrder = function deleteOrder(productId){
	        return $http({
	          method: 'DELETE',
	          url: 'deleteOrder/'+productId
	        })
	    }
		
	    
	    //get orders
	    
	    this.getAllOrders = function getAllOrders(){
	        return $http({
	          method: 'GET',
	          url: 'getAllOrders'
	        });
	    }
}]);

