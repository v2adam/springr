import React, {Component} from 'react';
import {Button, InputNumber} from 'antd';
import axios from 'axios';
import {stompClient} from '../../misc/websocket-listener';


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


    refreshAndGoToLastPage = () => {
        console.log('refreshAndGoToLastPage');
    };

    refreshCurrentPage = () => {
        console.log('refreshCurrentPage');
        this.fetchData();
    };


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
        console.log('delete');


        this.setState({loading: true});

        axios.delete(`/my_api/random_users/${this.state.delNumber}`).then(res => {
            console.log(res);
        }).catch(err => console.log(err));


        stompClient.send('/app/hello', {}, JSON.stringify({uziPaylaod: this.state.delNumber}));

    };


    changeNumber = (value) => {
        console.log('changed', value);
        this.setState({
            delNumber: value
        });
    };


    autoAdd = () => {
        console.log('autoAdd');

        this.setState({loading: true});


        const randomUser = {
            gender: Math.random() + '',
            email: "ujemailezlesz@uj.com",
            dob: "1985-06-25 11:47:36",
            registered: "2012-03-13 22:19:02",
            phone: "04-327-370",
            cell: "041-907-28-59",
            nat: "FI",
            name: {
                title: "mr",
                first: "onni",
                last: "koivisto"
            },
            location: {
                street: "1550 itsenäisyydenkatu",
                city: "sund",
                state: "northern savonia",
                postcode: "81590"
            },
            login: {
                username: "lazycat105",
                password: "gypsy",
                salt: "yrmRoKbb",
                md5: "cedfbc80140ef7f99ffec1d909b1090a",
                sha1: "38ece5fccb9bf4943fc294c806a6fc423b4615dc",
                sha256: "6b87ec64c3d123c7a5dc9dd23215a8aaae49763bb002dc394ef72428b8ee90e1"
            },
            id: {
                name: "HETU",
                value: "785-1370"
            },
            picture: {
                large: "https://randomuser.me/api/portraits/men/4.jpg",
                medium: "https://randomuser.me/api/portraits/med/men/4.jpg",
                thumbnail: "https://randomuser.me/api/portraits/thumb/men/4.jpg"
            }
        };


        axios.post(`/my_api/random_users`, randomUser).then(res => {
            console.log(res);
        }).catch(err => console.log(err));

      //  stompClient.send('/app/hello', randomUser);



        stompClient.send('/app/hello', {}, JSON.stringify({uziPaylaod: this.state.delNumber}));

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
