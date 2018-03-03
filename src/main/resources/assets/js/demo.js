function httpGet(theUrl)
{
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", theUrl, false ); // false for synchronous request
    xmlHttp.send( null );
    return xmlHttp.responseText;
}

var app = new Vue({
    el: '#app',
    computed: {
        viewCount: function() {
            var views = JSON.parse(httpGet('/api/v0/views'));
            var result = views.count;
            console.log("View count: " + result);
            return result;
        }
    }
});