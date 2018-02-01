import React, {Component} from 'react';
import { DatePicker } from 'antd';


export default class MainPage extends Component {


    render() {
        return (
            <div>
                <h1>Main page</h1>
                <DatePicker />
            </div>
        );
    }
}
