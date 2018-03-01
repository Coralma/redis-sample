importScripts('../widgets/webWorkerDom.js');
importScripts('../../lib/jquery/dist/jquery.min.js');

self.onmessage = function (oEvent) {
    console.log("JQuery version:", $.fn.jquery);
    $.getJSON("../../part.json",function(result){
        var timestamp = Date.parse(new Date());
        timestamp = timestamp / 1000;
        //当前时间戳为：1403149534
        console.log("Worker timestamp：" + timestamp);
        postMessage(result);
    });
};
