/**
 * Created by kmiao on 3/30/15.
 */
angular.module('groceryApp', []).controller('GroceryListController', function($scope, $http) {
    $scope.main = {

    };

    $scope.init = function() {
        $http.get("/sandbox/api/grocery-list/lists").success(function(response) {
            $scope.main.lists = response;
        });

        $http.get("/sandbox/api/grocery-list/stores").success(function(response) {
            $scope.main.stores = response;
        });

        $http.get("/sandbox/api/grocery-list/items").success(function(response) {
            $scope.main.items = response;
        });
    };

    $scope.genOpenPurchases = function() {
        $scope.openPurchases = [];
        $scope.main.selectedList.purchases.forEach(function(item) {
            $scope.openPurchases.push(false);
        });
    };

    $scope.init();

    $scope.displayList = false;

    $scope.display = function() {
        if ($scope.displayList == true) $scope.displayList = false;
        else $scope.displayList = true;
    };

    $scope.remaining = function(list) {
        var count = 0;
        angular.forEach(list, function(item) {
            count += item.done ? 0 : 1;
        });
        return count;
    };

    $scope.archive = function(list) {
        var oldItems = $scope.main.selectedList.items;
        $scope.main.selectedList.items = [];
        angular.forEach(oldItems, function(item) {
            if (!item.done) {
                $scope.main.selectedList.items.push(item);
            } else {
                $http.delete("/sandbox/api/grocery-list/" + $scope.main.selectedList.id + "/items/" + item.id);
                $('#alert').html('<div id="alert"><div id="fade" class="alert alert-success alert-dismissable fade in" style="font-size:small"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>Archived finished items!</div></div>');
                setTimeout(function() {
                    $("#fade").fadeTo(1000, 0).slideUp(1000, function(){
                        $(this).remove();
                    });
                }, 2000);
            }
        });
    };

    $scope.addGroceryList = function(name) {
        $http.post("/sandbox/api/grocery-list", {"name": name}).success(function(data, status) {
            data.items = [];
            $scope.main.lists.push(data);
            $('#newList')[0].reset();
            $('#alert').html('<div id="alert"><div id="fade" class="alert alert-success alert-dismissable fade in" style="font-size:small"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>Successfully created list!</div></div>');
            setTimeout(function() {
                $("#fade").fadeTo(1000, 0).slideUp(1000, function(){
                    $(this).remove();
                });
            }, 2000);
        });
    };

    $scope.addGroceryStore = function(name) {
        $http.post("/sandbox/api/grocery-list/stores", {"name": name}).success(function(data, status) {
            data.stores = [];
            $scope.main.stores.push(data);
            $('#newList')[0].reset();
            $('#alert').html('<div id="alert"><div id="fade" class="alert alert-success alert-dismissable fade in" style="font-size:small"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>Successfully created list!</div></div>');
            setTimeout(function() {
                $("#fade").fadeTo(1000, 0).slideUp(1000, function(){
                    $(this).remove();
                });
            }, 2000);
        });
    };

    $scope.removeGroceryList = function(id) {
        $http.delete("/sandbox/api/grocery-list/" + id)
          .success(function(data) {
                var index = findIndex($scope.main.lists, data.id);
                $scope.main.lists.splice(index,1);
                $scope.main.selectedList = null;
                $('#alert').html('<div id="alert"><div id="fade" class="alert alert-success alert-dismissable fade in" style="font-size:small"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>Deleted grocery list!</div></div>');
                setTimeout(function() {
                    $("#fade").fadeTo(1000, 0).slideUp(1000, function(){
                        $(this).remove();
                    });
                }, 2000);
            });
    };

    $scope.addPurchase = function(name, quantity, measurement) {
        $http.post("/sandbox/api/grocery-list/" + $scope.main.selectedList.id
          + "/purchases", {"name": name, "quantity": quantity, "measurement": measurement})
          .success(function(data) {
            $scope.main.selectedList.purchases.push(data);
            $('#newItem')[0].reset();
        });
    };

    $scope.addItem = function(name) {
        $http.post("/sandbox/api/grocery-list/items", {"name": name});
    };

    $scope.addStoreItem = function(storeId, itemId, name) {
        $http.post("/sandbox/api/grocery-list/stores/" + storeId + "/items", itemId);
    };

    $scope.checkPurchase = function(name, quantity, measurement, id, done) {
        if (done == false) {
            $http.put("/sandbox/api/grocery-list/" + $scope.main.selectedList.id
              + "/purchases/" + id, {"name": name, "quantity": quantity, "measurement": measurement, "done": true})
              .success(function(data) {
                    var index = findIndex($scope.main.selectedList.purchases, data.id);
                    $scope.main.selectedList.purchases[index] = {"id": id, "name": name, "quantity": quantity, "measurement": measurement, "done": true};
                });
        } else {
            $http.put("/sandbox/api/grocery-list/" + $scope.main.selectedList.id
            + "/purchases/" + id, {"name": name, "quantity": quantity, "measurement": measurement, "done": false})
                .success(function (data) {
                    var index = findIndex($scope.main.selectedList.purchases, data.id);
                    $scope.main.selectedList.purchases[index] = {"id": id, "name": name, "quantity": quantity, "measurement": measurement, "done": false};
                });
        }
    };

    $scope.changeItem = function(name, quantity, measurement, id, done) {
        $http.put("/sandbox/api/grocery-list/" + $scope.main.selectedList.id
        + "/items/" + id, {"name": name, "quantity": quantity, "measurement": measurement, "done": true})
            .success(function(data) {
                var index = findIndex($scope.main.selectedList.items, data.id);
                $scope.main.selectedList.items[index] = {"id": id, "name": name, "quantity": quantity, "measurement": measurement, "done": done};
            });
    };
})

$(document).ready(function(){
    $('[data-toggle=tooltip]').hover(function(){
        // on mouseenter
        $(this).tooltip('show');
    }, function(){
        // on mouseleave
        $(this).tooltip('hide');
    });
});

function findIndex(input, id) {
    for (i = 0; i < input.length; i++) {
        if (input[i].id == id) {
            return i;
        }
    }
    return null;
}