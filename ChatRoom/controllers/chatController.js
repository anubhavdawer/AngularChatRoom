/**
 * Created by adawer on 1/6/2016.
 */
angular.module('myApp',[])
    .controller('postCtrl',['$http',function($http){
        var self=this;
        self.chats=[];
        self.newchat={};
        $http.get("http://localhost:8080/ConnectDatabase/rest/chats")
            .then(function(response) {
                self.chats = response.data.chatDto;
            },function(errResponse){
                console.error("fetch Error");
            });
        self.add=function(){
            $http.post("http://localhost:8080/ConnectDatabase/rest/chats",self.newchat)
                .then(function(response){
                    self.chats.push(self.newchat);
                    self.newchat={};
                });

        };

    }]);

