import React, {Component} from 'react';
import {Button, InputNumber} from 'antd';
import axios from 'axios';


export default class Page6 extends Component {


    constructor(props) {
        super(props);
        this.state = {
            data: [],
            loading: false,
            delNumber: 0,
            timestamp: ''
        };
            }


    componentDidMount() {
        this.fetchData();
    }




    fetchData = () => {
        this.setState({loading: true});
        axios.get('/my_api/random_users').then(res => {
            this.setState({
                data: _.map(res.data, (one, i) => {
                    return {id: one.idField};
                }),
                loading: false
            });
            console.log(res);
        }).catch(err => console.log(err));
    };


    deleteFirst = () => {

    };


    changeNumber = (value) => {

    };


    autoAdd = () => {

    };

    render() {
        const {data, delNumber} = this.state;

        return (
            <div>
                <h1>Page6</h1>
                <InputNumber min={0} defaultValue={delNumber} onChange={this.changeNumber}/>
                <Button type="primary" onClick={this.deleteFirst}>Delete</Button>
                <Button type="secondary" onClick={this.autoAdd}>AutoAdd</Button>
                {JSON.stringify(data)}
            </div>
        );
    }
}
