import SockJS from 'sockjs-client';
const Stomp = require("stompjs/lib/stomp.js").Stomp;


function WebSocketFactory() {

    this.createSocket = function (registrations) {
        const socket = new SockJS('/my_endpoint');
        const stompClient = Stomp.over(socket);

        stompClient.connect({}, (frame) => {
            registrations.forEach((registration) => {
                stompClient.subscribe(registration.route, registration.callback);
            });
        });

        return stompClient;
    }
}

export {
    WebSocketFactory
}
