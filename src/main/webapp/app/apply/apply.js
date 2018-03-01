angular.module('apply.controller', [])
    .controller('ApplyCtrl', ['$scope','$http','Restangular','$state','$timeout', function ($scope, $http,Restangular,$state,$timeout) {
        $scope.result = {'message':'Initialize'};

        Restangular.one('part/apply?number=100').get().then(function (data) {
            console.log(JSON.stringify(data));
            $scope.result.message = data;
        });

        $scope.applyForm = {accountName:"Coral", startDate: _.now(), endDate:_.now(), applyNumber: 2, leaveType: 1};

        var w;
        var index = 1;
        $scope.loadData = function() {
            if(!w) {
                w = new Worker("app/apply/parts_worker.js");
                w.postMessage(index++);
                w.onmessage = function(event) {
                    console.log(event.data);
                    $scope.terminate();
                };
            }
        }

        $scope.terminate = function() {
            $timeout(function() {
                w.terminate();
                w = undefined;
            },500);
        }

        $scope.loadData();
    }]);
