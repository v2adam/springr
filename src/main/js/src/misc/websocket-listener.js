/*
var SockJS = require('sockjs-client'); // <1>
require('stompjs'); // <2>

function register(registrations) {
    var socket = SockJS('/payroll'); // <3>
    var stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        registrations.forEach(function (registration) { // <4>
            stompClient.subscribe(registration.route, registration.callback);
        });
    });
}

module.exports.register = register;

*/

import SockJS from 'sockjs-client';
var Stomp = require("stompjs/lib/stomp.js").Stomp;

let stompClient = null;
const socket = new SockJS('/my_endpoint');
stompClient = Stomp.over(socket);


socket.onopen = () => {
   console.log('onopen');

    stompClient.subscribe('/topic/greetings', (greeting) => {
        console.log(JSON.parse(greeting.body).content);
    });


    stompClient.subscribe('/topic/updated_list', (greeting) => {
        console.log(JSON.parse(greeting.body).content);
    });

};

socket.onmessage = (e) => {
    console.log('message', e.data);
};

socket.onclose = () => {
    console.log('close');
};

socket.onerror = (e) => {
    console.log(e);
};

export {
    socket,
    stompClient
}
