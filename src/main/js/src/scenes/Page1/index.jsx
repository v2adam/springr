import React, {Component} from 'react';
import axios from "axios/index";
import _ from 'lodash';
import {Switch} from 'antd';

import MyTable from "./components/Table";

export default class Page1 extends Component {

    constructor(props) {
        super(props);
        this.state = {
            data: [],
            columns: [{
                title: 'ID',
                dataIndex: 'id',
            }, {
                title: 'Name',
                dataIndex: 'name',
            }, {
                title: 'Status',
                dataIndex: 'status',
            }
            ],
            editorMode: true

        }
    }


    componentDidCatch() {
        console.log('baj van');
    }

    componentDidMount() {
        this.fetchData();
    }

    fetchData = () => {
        axios.get('/my_api/employees').then(res => {
            this.setState({
                data: _.map(res.data, (one, i) => {
                    return {...one};
                })
            });
        }).catch(err => console.log(err));
    };


    changeEditorMode = (checked) => {
        console.log(`switch to ${checked}`);
        this.setState({
            editorMode: checked
        })
    };

    delete = async (record) => {

        const opts = {
            headers: {
                'Content-Type': 'application/json',
            }
        };

        try {
            const res = await axios.delete(`/my_api/employees/${record.id}`, opts);
        } catch (err) {
            console.log(`DELETE baj van: ${err}`);
        }

        this.fetchData();
    };


    render() {
        const {data, columns, editorMode} = this.state;
        return (
            [
                <Switch key="switchKey" defaultChecked onChange={this.changeEditorMode}/>,
                <MyTable key="myTableKey" columns={columns} data={data} editorMode={editorMode} delete={this.delete}/>
            ]
        );
    }

}
