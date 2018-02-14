import React, {Component} from 'react';
import {Button, Input, Switch, List, Avatar } from 'antd';
import {WebSocketFactory} from "../../misc/websocket";

const {TextArea} = Input;
let stompClient;


export default class Page7 extends Component {


    constructor(props) {
        super(props);
        this.state = {
            message: '',
            messageBoard: [],
            connected: false
        }
    }

    componentDidMount() {
    };


    refreshMessageBoard = (frame) => {
        const msg = frame.body;
        this.setState({
            messageBoard: [...this.state.messageBoard, msg]
        });
    };

    sendMessage = () => {
        stompClient.send('/app/newMessage', {}, JSON.stringify({payload: this.state.message}));

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
          if(this.state.connected){
              const factory = new WebSocketFactory();
              stompClient = factory.createSocket([{route: '/topic/newMessage', callback: this.refreshMessageBoard}]);
          }else{
              stompClient.disconnect();
          }
      });
    };

    render() {
        return (
            <div>
                <h1>Page7</h1>
                <Switch defaultChecked={false} onChange={this.connect} />
                <List
                    itemLayout="horizontal"
                    dataSource={this.state.messageBoard}
                    renderItem={item => (
                        <List.Item>
                            <List.Item.Meta
                                avatar={<Avatar src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png" />}
                                title={<a href="https://ant.design"></a>}
                                description={item}
                            />
                        </List.Item>
                    )}
                />
                <TextArea rows={4}
                          placeholder="Write your message"
                          onChange={this.updateMessage}
                          disabled={!this.state.connected}
                          value={this.state.message}
                          onPressEnter={this.sendMessage}/>
            </div>
        );
    }
}
