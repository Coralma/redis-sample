angular.module('grid.controller', [])
    .controller('GridCtrl', ['$scope','$http','Restangular','$state','$timeout', function ($scope, $http,Restangular,$state,$timeout) {
        var w;
        var index = 1;
        $scope.loadData = function() {
            if(!w) {
                w = new Worker("app/apply/parts_worker.js");
                w.postMessage(index++);
                w.onmessage = function(event) {
                    $scope.partJson = event.data;
                    //console.log("Data in grid" + event.data);
                    var timestamp = Date.parse(new Date());
                    timestamp = timestamp / 1000;
                    //当前时间戳为：1403149534
                    console.log("Angular timestamp：" + timestamp);
                    $scope.terminate();
                };
            }
        };

        $scope.terminate = function() {
            $timeout(function() {
                w.terminate();
                w = undefined;
            },500);
        };

        //$scope.loadData();
        /*$.getJSON("part.json",function(result){
            console.log(result);
        });*/

        //生产随机数
        $scope.randomStr = function(len) {
            len = len || 32;
            var chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz123456789';
            var maxPos = chars.length;
            var pwd = '';
            for (var i = 0; i < len; i++) {
                pwd += chars.charAt(Math.floor(Math.random() * maxPos));
            }
            return pwd;
        };
        $scope.randomData = [];
        for(var j = 0; j < 500; j++) {
            $scope.randomData.push({
                "id":j+1,
                "firstName": $scope.randomStr(10),
                "lastName": $scope.randomStr(5),
                "company": $scope.randomStr(3),
                "income": 20000,
                "employed": true});
           // console.log($scope.randomData);
        }

        //生产grid
        $scope.gridOptions = {
            enableOverflow : true,
            overflowHeight : '300px',
            useExternalPagination: false,
            enableRowSelection: false,
            onRegisterApi : function(gridApi){
                $scope.gridApi = gridApi;
            },
            columnDefs : [
                {displayName: 'id', field:'id',enableSorting: true},
                {displayName: 'FirstName', field:'firstName'},
                {displayName: 'LastName', field:'lastName',cellStyle: "width:200px",cellClass: 'center-data'},
                {displayName: 'Company', field:'company',cellStyle: "width:200px"},
                {displayName: 'Income', field:'income',cellStyle: "width:200px", filter: 'decimalFilter', cellClass: 'right-number'},
                {displayName: 'Employed', field:'employed',cellStyle: "width:150px", cellTemplate:'<input type="checkbox" ng-model="item[col.field]">'}
            ],
            data: $scope.randomData
            /*data : [
                {"firstName": "Cox","lastName": "Carney","company": "IBM","income": 32900,"employed": true},
                {"firstName": "Lorraine","lastName": "Wise","company": "Google","income": 58000,"employed": false},
                {"firstName": "Nancy","lastName": "Eric","company": "IBM","income": 46000,"employed": false},
                {"firstName": "Lora","lastName": "Karl","company": "IBM","income": 20000,"employed": true},
                {"firstName": "Angel","lastName": "Pete","company": "Google","income": 32000,"employed": false},
                {"firstName": "Babala","lastName": "Mildred","company": "IBM","income": 11000,"employed": true},
                {"firstName": "Wind","lastName": "Owen","company": "Facebook","income": 25000,"employed": true},
                {"firstName": "Iris","lastName": "Lee","company": "Facebook","income": 22000,"employed": true},
                {"firstName": "Rocky","lastName": "Alax","company": "Github","income": 16000,"employed": false},
                {"firstName": "Muse","lastName": "Dick","company": "Github","income": 14500,"employed": false}
            ]*/
        };
        $scope.gridOptions.totalItems = 201;

        $scope.submitData = function() {
            _.forEach($scope.randomData, function(d) {
                d.id = d.id + 10000;
            });
            //$scope.gridApi.refreshGridData($scope.randomData);
            $scope.gridApi.refreshGridData(null);
        }

        $scope.rowSelect = function(row) {
            console.log('run row select');
        }

        $scope.selectedRows = function() {
            var rows = $scope.gridApi.getSelectedRows();
            console.log(JSON.stringify(rows));
        }

        $scope.unSelectedRows = function() {
            $scope.gridApi.cleanSelectedSingleRow();
        }


        $scope.dbClickRowSelect = function(row) {
            console.log('double click row select');
            console.log(JSON.stringify(row));
        }

        $scope.pageChange = function(pageIndex, pageSize) {
            console.log('The pageIndex is ' + pageIndex + ', pageSize is : ' + pageSize)
        }

        $scope.sortChange = function(pageIndex, pageSize, sortField, sortType) {
            console.log('The pageIndex is ' + pageIndex + ', pageSize is ' + pageSize + ", sortField is " + sortField + ", sortType is " + sortType);
        }

    }]);
