import React, {Component} from 'react';
import axios from 'axios';
import _ from 'lodash';
import {Button, Divider, Icon, message, Modal, Table, Upload} from 'antd';
import ErrorBoundary from "../../components/ErrorBoundary";

const confirm = Modal.confirm;


class Page10 extends Component {

    constructor(props) {
        super(props);
        this.state = {
            loadingBtn: false,
            loadingTable: false,
            selectedRowKeys: [],
            fileList: [],
            header: [],
            rows: [],
            columns: [{
                dataIndex: 'id',
                title: 'id',
            }, {
                dataIndex: 'someField',
                title: 'somesomeFieldID',
            }, {
                dataIndex: 'issueId',
                title: 'issueId',
            }, {
                dataIndex: 'valami',
                title: 'valami',
            }, {
                dataIndex: 'groupId',
                title: 'groupId',
            }, {
                dataIndex: 'subGroupId',
                title: 'subGroupId',
            }, {
                dataIndex: 'date',
                title: 'date',
            }, {
                dataIndex: 'mtid',
                title: 'mtid',
            }, {
                dataIndex: 'cg',
                title: 'cg',
            }, {
                dataIndex: 'segm',
                title: 'segm',
            }, {
                dataIndex: 'ossnr',
                title: 'ossnr',
            }, {
                dataIndex: 'accmtid',
                title: 'accmtid',
            }, {
                dataIndex: 'agree',
                title: 'agree',
            }, {
                title: 'Action',
                key: 'action',
                render: (text, record) => (
                    <span>
                    <a href="#">Action 一 {record.id}</a>
                    <Divider type="vertical"/>
                    <Button onClick={() => this.showConfirm(record)}>
                        <Icon type="delete"/>
                    </Button>
                    <Divider type="vertical"/>
                    <a href="#" className="ant-dropdown-link">
                      More actions <Icon type="down"/>
                    </a>
                  </span>
                ),
            }]
        }
        ;
    }

    deleteSelectedRow = async (record) => {
        await this.setState({loadingTable: true});
        try {
            const opts = {
                headers: {
                    'Content-Type': 'application/json',
                }
            };
            const res = await axios.delete(`/my_api/my_first_xls/${record.id}`, opts);
            this.setState({rows: _.pull(this.state.rows, record), loadingTable: false});
            message.success(`Record deleted successfully`);
        } catch (error) {
            if(error.response.status === 404){
                message.error(`${error.response.data.apierror.message}`);
            } else{
                message.error(`Something gone wrong`);
            }
            await this.setState({loadingTable: false});
        }
    };


    showConfirm = (record) => {
        confirm({
            title: 'Are you sure you want to delete record?',
            content: `The ID is ${record.id}` ,
            onOk: () => this.deleteSelectedRow(record),
            onCancel() {
                console.log('Cancel');
            },
        });
    };


    // kell egy mappelés, hogy a header key-ei alapján legyen összerendelés a cellákban
    mapToHeaderKey = (one, i) => {

        if (!this.state.header) {
            let mapped = {};

            for (let x = 0; x < this.state.header.length; x++) {
                mapped[x] = this.state.rows[i][x];
            }

            console.log(mapped);
            return mapped;
        }

        return one;
    };

    onFileUploadChange = (info) => {

        if (info.file.status === 'uploading') {
            this.setState({loadingBtn: true, loadingTable: true});
        }

        if (info.file.status !== 'uploading') {
            console.log(info.file, info.fileList);
        }
        if (info.file.status === 'done') {
            message.success(`${info.file.name} file uploaded successfully`);

            this.setState({
                header: info.file.response.header.map((one) => ({key: one, name: one})),
                rows: info.file.response.rows.map(one => one)
            }, () => {
                console.log(this.state);
                let mapped = this.state.rows.map((one, i) => this.mapToHeaderKey(one, i));
                this.setState({rows: mapped, loadingBtn: false, loadingTable: false});
            });

        } else if (info.file.status === 'error') {
            message.error(`${info.file.name} file upload failed.`);
            this.setState({loadingBtn: false, loadingTable: false});
        }


        let fileList = info.fileList;

        this.setState({fileList});

    };


    onSelectChange = (selectedRowKeys, selectedRows) => {
        console.log(`selectedRowKeys: ${selectedRowKeys}`, 'selectedRows: ', selectedRows);
        this.setState({selectedRowKeys});
    }

    render() {

        const rowSelection = {
            onChange: this.onSelectChange,
        };

        return (

            <ErrorBoundary>
                <Upload name={"file"}
                        action={"/my_api/files/upload/xls"}
                        onChange={this.onFileUploadChange}
                        multiple={false}
                        fileList={this.state.fileList}
                        accept={"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"}>
                    <Button type="primary" loading={this.state.loading}>
                        <Icon type="upload"/>Upload Xls
                    </Button>
                </Upload>
                <Table
                    rowKey='id'
                    dataSource={this.state.rows}
                    columns={this.state.columns}
                    rowSelection={rowSelection}
                    loading={this.state.loadingTable}
                />
            </ErrorBoundary>
        )
    };

}

export default Page10;

