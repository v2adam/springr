import React, {Component} from 'react';
import {Button} from 'antd';
import axios from 'axios';

export default class Page4 extends Component {





    sendPost = () => {

        const randomUser = {
            gender: "male",
            email: "12312312312312312@123123123123",
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

        axios.post('/my_api/random_users', randomUser).then((res) => {
            console.log(res);
        }).catch(err => console.log(err));

    };


    render() {
        return (
            <div>
                <h1>Page4</h1>
                <Button type="primary" onClick={this.sendPost}>Primary</Button>
            </div>
        );
    }
}
