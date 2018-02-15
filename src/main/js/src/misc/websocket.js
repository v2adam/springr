import SockJS from 'sockjs-client';

const Stomp = require("stompjs/lib/stomp.js").Stomp;

const WebSocket = function () {

    this.socket = null;
    this.stompClient = null;

    this.handshake = function () {
        this.socket = new SockJS('/my_endpoint');
        this.stompClient = Stomp.over(this.socket);
    };

    this.register = function (registrations, onOpenCb) {
        this.stompClient.connect({}, (frame) => {
            registrations.forEach((registration) => {
                //  var subscription = client.subscribe(...);
                //  subscription.unsubscribe();
                this.stompClient.subscribe(registration.route, registration.callback);
            });
            onOpenCb();
        }, (err) => {
            console.log(err);
        });
    };
};

export {
    WebSocket
}
