import React, {Component} from 'react';
import {Button, Divider, Dropdown, Icon, Menu, Modal, Table, Tooltip} from 'antd';
import PropTypes from "prop-types";

const confirm = Modal.confirm;

export default class MyTable extends Component {


    constructor(props) {
        super(props);
        this.state = {
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
            markDelete: []
        }
    }

    _onChange = (e) => {
        console.log(e);

    };

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


    render() {
        const {columns, data, editorMode} = this.props;
        const {extraColumn, modalVisible, markDelete} = this.state;

        const showColumn = editorMode ? columns.concat(extraColumn) : columns;

        return (
            [
                <Table
                    key="tablekey"
                    columns={showColumn}
                    dataSource={data}
                    rowKey={record => record.id}
                    onChange={this._onChange}
                />
            ]


        );
    }
}


MyTable.propTypes = {
    columns: PropTypes.array.isRequired,
    data: PropTypes.array.isRequired,
};

MyTable.defaultProps = {
    columns: [],
    data: [],
};
