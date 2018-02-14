import React, {Component} from 'react';
import {Avatar, Input, List, Switch} from 'antd';
import {randomColor} from 'randomcolor';

import {WebSocketFactory} from "../../misc/websocket";


let stompClient;

export default class Page7 extends Component {


    constructor(props) {
        super(props);
        this.state = {
            message: '',
            messageBoard: [],
            connected: false,
            color: randomColor()
        }
    }

    refreshMessageBoard = (frame) => {
        const msg = JSON.parse(frame.body);
        this.setState({
            messageBoard: [...this.state.messageBoard, msg]
        });
    };

    sendMessage = () => {
        stompClient.send('/app/newMessage', {}, JSON.stringify({
            content: this.state.message,
            avatarColor: this.state.color
        }));

        this.setState({
            message: null
        });
    };

    updateMessage = (e) => {
        this.setState({
            message: e.target.value
        });
    };

    connect = (changed) => {
        this.setState({connected: changed}, () => {
            if (this.state.connected) {
                const factory = new WebSocketFactory();
                stompClient = factory.createSocket([{route: '/topic/newMessage', callback: this.refreshMessageBoard}]);
            } else {
                stompClient.disconnect()
            }
        });
    };

    render() {
        return (
            <div>
                <h1>Page7</h1>
                <Switch defaultChecked={false} onChange={this.connect}/>
                <List
                    itemLayout="horizontal"
                    dataSource={this.state.messageBoard}
                    renderItem={item => (
                        <List.Item>
                            <List.Item.Meta
                                avatar={<Avatar style={{backgroundColor: item.avatarColor, verticalAlign: 'middle'}}
                                                size="large">
                                    {item.author}
                                </Avatar>}
                                title={item.author}
                                description={item.timeStamp}
                            />
                            {item.content}
                        </List.Item>
                    )}
                />
                <Input placeholder="Write your message"
                       onChange={this.updateMessage}
                       disabled={!this.state.connected}
                       value={this.state.message}
                       autosize={{minRows: 2, maxRows: 6}}
                       onPressEnter={this.sendMessage}/>
            </div>
        );
    }
}
