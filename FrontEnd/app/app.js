/**
 * Created by kmiao on 3/30/15.
 */
angular.module('groceryApp', []).controller('GroceryListController', function() {
    var groceryList = this;
    groceryList.items = [
        {text:'learn angular', done:true},
        {text:'build an angular app', done:false}];

    groceryList.addItem = function() {
        groceryList.items.push({text:groceryList.itemText, done:false});
        groceryList.itemText = '';

        groceryList.items.push({text:groceryList.itemQuantity, done:false});
        groceryList.itemQuantity = '';
    };

    groceryList.remaining = function() {
        var count = 0;
        angular.forEach(groceryList.items, function(item) {
            count += item.done ? 0 : 1;
        });
        return count;
    };

    groceryList.archive = function() {
        var oldItems = groceryList.items;
        groceryList.items = [];
        angular.forEach(oldItems, function(items) {
            if (!item.done) groceryList.items.push(items);
        });
    };
})
