import React, {Component} from 'react';
import {Button, Divider, Dropdown, Icon, Menu, Modal, Table, Tooltip} from 'antd';
import PropTypes from "prop-types";
import _ from 'lodash';
const confirm = Modal.confirm;

export default class MyTable extends Component {


    constructor(props) {
        super(props);
        this.state = {
            sortedInfo: null,
            filteredInfo: null,
            columns: [{
                title: 'ID',
                dataIndex: 'id',
                key: 'id',
                sorter: (a, b) => a.id - b.id
            }, {
                title: 'Name',
                dataIndex: 'name',
                key: 'name',
                sorter: (a, b) => a.name.toLowerCase().localeCompare(b.name.toLowerCase())
            }, {
                title: 'Status',
                dataIndex: 'status',
                key: 'status',
                sorter: (a, b) => a.status.toLowerCase().localeCompare(b.status.toLowerCase())
            }
            ],
            extraColumn: [{
                title: 'Action',
                key: 'action',
                render: (text, record) => (
                    <span>
                      <Icon type="edit"/>
                      <Divider type="vertical"/>
                        <Tooltip placement="top" title={"Delete"}>
                            <Button onClick={() => this.showDeleteConfirm(record, props.delete)}>
                                <Icon type="delete"/>
                            </Button>
                        </Tooltip>
                      <Divider type="vertical"/>
                         <Dropdown overlay={
                             <Menu>
                                 <Menu.Item>
                                     <a target="_blank" rel="noopener noreferrer" href="http://www.alipay.com/">1st menu
                                         item</a>
                                 </Menu.Item>
                                 <Menu.Item>
                                     <a target="_blank" rel="noopener noreferrer" href="http://www.taobao.com/">2nd menu
                                         item</a>
                                 </Menu.Item>
                                 <Menu.Item>
                                     <a target="_blank" rel="noopener noreferrer" href="http://www.tmall.com/">3rd menu
                                         item</a>
                                 </Menu.Item>
                             </Menu>
                         }>
                            <a className="ant-dropdown-link" href="#">
                              Hover me <Icon type="down"/>
                            </a>
                          </Dropdown>
                    </span>
                ),
            }],
            modalVisible: false,
            selectedRowKeys: [],
            selectedRows: [],
            loading: false,
            pagination: {},
        }
    }

    handleModalCancel = () => {
        this.setState({
            modalVisible: false
        })
    };


    showDeleteConfirm = (record, deleteRecord) => {
        Modal.confirm({
            title: `Are you sure delete ${record.name} ?`,
            content: 'Bla bla ...',
            okText: 'Delete',
            okType: 'danger',
            cancelText: 'Cancel',
            onOk() {
                console.log('OK');
                deleteRecord(record);
            },


        });
    };


    handleChange = (pagination, filters, sorter) => {
        console.log('Various parameters', pagination, filters, sorter);
        this.setState({
            filteredInfo: filters,
            sortedInfo: sorter,
        });
    };

    setAgeSort = () => {
        console.log('setAgeSort');
        this.setState({
            sortedInfo: {
                order: 'descend',
                columnKey: 'age',
            },
        });
    };


    render() {
        const {data, editorMode, loading} = this.props;
        const {extraColumn, modalVisible, columns, pagination} = this.state;

        const showColumn = editorMode ? columns.concat(extraColumn) : columns;


        const rowSelection = {
            onChange: (selectedRowKeys, selectedRows) => {
                console.log(`selectedRowKeys: ${selectedRowKeys}`, 'selectedRows: ', selectedRows);
                this.setState({
                    selectedRowKeys: selectedRowKeys,
                    selectedRows: selectedRows
                })
            },

            getCheckboxProps: record => ({
                disabled: record.id === 'Disabled User', // Column configuration not to be checked
                name: record.id,
            }),
        };


        return (
            [
                <Table
                    key="tablekey"
                    columns={showColumn}
                    dataSource={data}
                    rowKey={record => record.id}
                    rowSelection={rowSelection}
                    onChange={this.handleChange}
                    loading={loading}
                    pagination={pagination}
                />
            ]


        );
    }
}


MyTable.propTypes = {
    data: PropTypes.array.isRequired,
};

MyTable.defaultProps = {
    data: [],
};
