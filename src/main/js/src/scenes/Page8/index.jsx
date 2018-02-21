import React, {Component} from 'react';
import ErrorBoundary from "../../components/ErrorBoundary";
import ReactDataGrid from 'react-data-grid';
import 'react-table/react-table.css';
import {Button, Icon, message, Upload} from 'antd';

export default class Page8 extends Component {


    constructor(props) {
        super(props);
        this.state = {
            fileList: [],
            header: [],
            rows: []
        };
    }


    // kell egy mappelés, hogy a header key-ei alapján legyen összerendelés a cellákban
    mapToHeaderKey = (i) => {

        let mapped = {};

        for (let x = 0; x < this.state.header.length; x++) {
            mapped[x] = this.state.rows[i][x];
        }

        return mapped;
    };


    onChange = (info) => {
        if (info.file.status !== 'uploading') {
            console.log(info.file, info.fileList);
        }
        if (info.file.status === 'done') {
            message.success(`${info.file.name} file uploaded successfully`);

            this.setState({
                header: info.file.response.header.map((one, i) => ({key: i, name: one})),
                rows: info.file.response.rows.map(one => one)
            }, () => {
                let mapped = this.state.rows.map((one, i) => this.mapToHeaderKey(i));
                this.setState({rows: mapped});
            });

        } else if (info.file.status === 'error') {
            message.error(`${info.file.name} file upload failed.`);
        }


        let fileList = info.fileList;

        this.setState({fileList});

    };

    rowGetter = (i) => {
        return this.state.rows[i];
    };


    render() {

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
                    columns={this.state.header}
                    rowGetter={this.rowGetter}
                    rowsCount={this.state.rows.length}
                    minHeight={500}
                />
            </ErrorBoundary>
        )
    };

}
