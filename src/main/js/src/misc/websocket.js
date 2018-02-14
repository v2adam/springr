import SockJS from 'sockjs-client';

const Stomp = require("stompjs/lib/stomp.js").Stomp;


function WebSocketFactory() {

    this.createSocket = function (registrations) {
        const socket = new SockJS('/my_endpoint');
        const stompClient = Stomp.over(socket);

        // connect(headers, connectCallback, errorCallback)
        stompClient.connect({}, (frame) => {
            registrations.forEach((registration) => {
                //  var subscription = client.subscribe(...);
                //  subscription.unsubscribe();
                stompClient.subscribe(registration.route, registration.callback);
            });
        }, (err) => {
            console.log(err);
        });

        return stompClient;
    }
}

export {
    WebSocketFactory
}
