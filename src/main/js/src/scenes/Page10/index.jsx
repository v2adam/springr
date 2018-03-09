import React, {Component} from 'react';
import axios from 'axios';
import _ from 'lodash';
import {Button, Divider, Icon, Input, message, Modal, Popconfirm, Table, Upload} from 'antd';
import ErrorBoundary from "../../components/ErrorBoundary";
import './style.css';

const confirm = Modal.confirm;


const EditableCell = ({editable, value, onChange}) => (
    <div>
        {editable
            ? <Input style={{margin: '-5px 0'}} value={value} onChange={e => onChange(e.target.value)}/>
            : value
        }
    </div>
);


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
            changedRows: [],
            columns: [{
                dataIndex: 'id',
                title: 'id',
            }, {
                dataIndex: 'someField',
                title: 'somesomeFieldID',
                render: (text, record) => this.renderColumns(text, record, 'someField'),
            }, {
                dataIndex: 'issueId',
                title: 'issueId',
            }, {
                dataIndex: 'valami',
                title: 'valami',
                render: (text, record) => this.renderColumns(text, record, 'valami'),
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
                dataIndex: 'action',
                render: (text, record) => {
                    const {editable} = record;
                    return (
                        <div className="editable-row-operations">
                            {
                                editable ?
                                    <span>
                                        <Button onClick={() => this.save(record.id)}>
                                            <Icon type="save"/>
                                        </Button>
                                        <Divider type="vertical"/>
                                      <Popconfirm title="Sure to cancel?" onConfirm={() => this.cancel(record.id)}>
                                          <Button>
                                            <Icon type="close"/>
                                          </Button>
                                      </Popconfirm>
                                    </span>
                                    : <Button onClick={() => this.edit(record.id)}>
                                        <Icon type="edit"/>
                                    </Button>
                            }
                            <Divider type="vertical"/>
                            <Button onClick={() => this.showConfirm(record)}>
                                <Icon type="delete"/>
                            </Button>
                        </div>
                    );
                },
            }]
        };
        this.cacheData = this.state.rows.map(item => ({...item}));
    }

    renderColumns = (text, record, column) => {
        return (
            <EditableCell
                editable={record.editable}
                value={text}
                onChange={value => this.handleChange(value, record.id, column)}
            />
        );
    };

    // [{id, oszlop, értéke},{id, oszlop, értéke}]
    storeValueChanges = (value, key, column) => {
        const found = this.state.changedRows.find(item => (key === item.id && column === item.column));
        if (found) {
            const all = this.state.changedRows.slice();
            const withoutFound = _.pull(all, found);
            found.value = value;
            withoutFound.push(found);
            this.setState({changedRows: withoutFound});
        } else {
            const newOne = {id: key, column: column, value: value};
            const all = this.state.changedRows.slice();
            all.push(newOne);
            this.setState({changedRows: all});
        }
    };


    handleChange = (value, key, column) => {
        const newData = [...this.state.rows];
        const target = newData.filter(item => key === item.id)[0];
        if (target) {
            target[column] = value;
            this.setState({rows: newData});
            this.storeValueChanges(value, key, column);
        }
    };

    edit = (key) => {
        const newData = [...this.state.rows];
        const target = newData.filter(item => key === item.id)[0];
        if (target) {
            target.editable = true;
            this.setState({rows: newData});
        }
    };

    save = (key) => {
        const newData = [...this.state.rows];
        const target = newData.filter(item => key === item.id)[0];
        console.log(target);
        if (target) {
            delete target.editable;
            this.setState({rows: newData});
            this.cacheData = newData.map(item => ({...item}));
            this.updateCell(key);
        }
    };

    cancel = (key) => {
        const newData = [...this.state.rows];
        const target = newData.filter(item => key === item.id)[0];
        if (target) {
            Object.assign(target, this.cacheData.filter(item => key === item.id)[0]);
            delete target.editable;
            this.setState({data: newData});
        }
    };


    updateCell = async (key) => {
        await this.setState({loadingTable: true});
        try {
            const opts = {
                headers: {
                    'Content-Type': 'application/json-patch+json',
                }
            };

            // módosításokat mappelem a patch payload-á
            const foundChanges = this.state.changedRows.filter(item => (key === item.id));
            const payload = foundChanges.map(one => {
                if(_.isEmpty(one.value)){
                    return {op: 'delete', path: `/${one.column}`}
                }else{
                    return {op: 'replace', path: `/${one.column}`, value: one.value}
                }
            });

            const res = await axios.patch(`/my_api/my_first_xls/${key}`, payload, opts);
            await this.setState({loadingTable: false});
            message.success(`Record patched successfully`);

            // kiveszem ha sikerült
            const all = this.state.changedRows.slice();
            this.setState({changedRows: all.filter(item => (key !== item.id))});


        } catch (error) {
            await this.setState({loadingTable: false,});
            if (error.response.status === 500) {
                message.error(`${error.response.data.apierror.message}`);
            } else {
                message.error(`Something gone wrong`);
            }
        }
    };


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
            if (error.response.status === 404) {
                message.error(`${error.response.data.apierror.message}`);
            } else {
                message.error(`Something gone wrong`);
            }
            await this.setState({loadingTable: false});
        }
    };


    showConfirm = (record) => {
        confirm({
            title: 'Are you sure you want to delete record?',
            content: `The ID is ${record.id}`,
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
    };


    render() {

        const rowSelection = {
            onChange: this.onSelectChange,
        };

        return (

            <ErrorBoundary>
                <span>
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
                </span>
                <Table
                    rowKey='id'
                    title={() => 'Header'}
                    footer={() => 'Footer'}
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

