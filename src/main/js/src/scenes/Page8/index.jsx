import React, {Component} from 'react';
import ErrorBoundary from "../../components/ErrorBoundary";
import ReactDataGrid from 'react-data-grid';
import 'react-table/react-table.css';
import {Button, Icon, Upload, message} from 'antd';

export default class Page8 extends Component {

    /*
        constructor(props) {
            super(props);
            this.state = {};
        }


        onChange = (info) => {
            console.log('onChange');
            if (info.file.status !== 'uploading') {
                console.log(info.file, info.fileList);
            }
            if (info.file.status === 'done') {
                message.success(`${info.file.name} file uploaded successfully`);
            } else if (info.file.status === 'error') {
                message.error(`${info.file.name} file upload failed.`);
            }

            let fileList = info.fileList;

            this.setState({fileList});

        };
    */

    /*
    render() {
        return (
            <ErrorBoundary>
                <Upload name={"file"}
                        action={"/my_api/files/upload/xls"}
                        onChange={this.onChange}
                        multiple={false}
                        fileList={this.state.fileList}
                        onRemove={this.remove}
                        beforeUpload={this.beforeUpload}
                        accept={"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"}>
                    <Button type="primary">
                        <Icon type="upload"/>Upload Xls
                    </Button>
                </Upload>
            </ErrorBoundary>
        );
    }
    */


    constructor(props, context) {
        super(props, context);
        this.createRows();
        this._columns = [
            {key: 'id', name: 'ID'},
            {key: 'title', name: 'Title'},
            {key: 'count', name: 'Count'}];

        this.state = {
            fileList: []
        };
    }

    createRows = () => {
        let rows = [];
        for (let i = 1; i < 20; i++) {
            rows.push({
                id: i,
                title: 'Title ' + i,
                count: i * 20
            });
        }

        this._rows = rows;
    };

    rowGetter = (i) => {
        return this._rows[i];
    };


    onChange = (info) => {
        console.log('onChange');
        if (info.file.status !== 'uploading') {
            console.log(info.file, info.fileList);
        }
        if (info.file.status === 'done') {
            message.success(`${info.file.name} file uploaded successfully`);
        } else if (info.file.status === 'error') {
            message.error(`${info.file.name} file upload failed.`);
        }

        let fileList = info.fileList;

        this.setState({fileList});

    };


    render() {
        const data = [{
            name: 'Tanner Linsley',
            age: 26,
            friend: {
                name: 'Jason Maurer',
                age: 23,
            }
        }];

        const columns = [{
            Header: 'Name',
            accessor: 'name' // String-based value accessors!
        }, {
            Header: 'Age',
            accessor: 'age',
            Cell: props => <span className='number'>{props.value}</span> // Custom cell components!
        }, {
            id: 'friendName', // Required because our accessor is not a string
            Header: 'Friend Name',
            accessor: d => d.friend.name // Custom value accessors!
        }, {
            Header: props => <span>Friend Age</span>, // Custom header components!
            accessor: 'friend.age'
        }];

        return (
            <ErrorBoundary>
                <Upload name={"file"}
                        action={"/my_api/files/upload/xls"}
                        onChange={this.onChange}
                        multiple={false}
                        fileList={this.state.fileList}
                        accept={"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"}>
                    <Button type="primary">
                        <Icon type="upload"/>Upload Xls
                    </Button>
                </Upload>
                <ReactDataGrid
                    columns={this._columns}
                    rowGetter={this.rowGetter}
                    rowsCount={this._rows.length}
                    minHeight={500}
                />
            </ErrorBoundary>
        )
    };

}
