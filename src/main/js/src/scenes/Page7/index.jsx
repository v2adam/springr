import React, {Component} from 'react';
import {Avatar, Input, List, message, notification, Select, Switch} from 'antd';
import {randomColor} from 'randomcolor';

import {WebSocket} from "../../misc/websocket";


const Option = Select.Option;


let stompClient;

export default class Page7 extends Component {


    constructor(props) {
        super(props);
        this.state = {
            message: '',
            messageBoard: [],
            connected: false,
            color: randomColor(),
            selectedPrivateUser: 'none'
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

                const ws = new WebSocket();
                ws.handshake();

                stompClient = ws.stompClient;

                ws.register([
                    {route: '/topic/newMessage', callback: this.refreshMessageBoard},
                    {route: '/user/queue/private', callback: this.privateMessageReceived}
                ], this.onOpenHandler);

            } else {
                stompClient.disconnect(() => {
                    message.info('Disconnected', 3);
                });
            }
        });
    };


    onOpenHandler = () => {
        message.info('Connected', 3);
    };


    privateMessageReceived = (receivedMsg) => {
        const msg = JSON.parse(receivedMsg.body);
        const args = {
            message: msg.message,
            description: msg.message,
            duration: 3,
        };
        notification.open(args);
    };


    selectUser = (selectedUser) => {
        this.setState({
            selectedPrivateUser: selectedUser
        }, () => {
            if (this.state.selectedPrivateUser !== 'none') {
                const msg = {
                    message: `Ezt csak te kapod meg ${this.state.selectedPrivateUser} !`
                };
                stompClient.send(`/user/${this.state.selectedPrivateUser}/queue/private`, {}, JSON.stringify(msg));
            }
        });
    };


    render() {
        return (
            <div>
                <h1>Page7</h1>
                <Switch defaultChecked={false} onChange={this.connect}/>
                <Select defaultValue="none" style={{width: 120}} onChange={this.selectUser}>
                    <Option value="none">none</Option>
                    <Option value="user">user</Option>
                    <Option value="admin">admin</Option>
                </Select>
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
