import React, {Component} from 'react';
import {Input, Popconfirm, Table} from 'antd';
import axios from "axios/index";

const data = [];
const EditableCell = ({editable, value, onChange}) => (
    <div>
        {editable
            ? <Input style={{margin: '-5px 0'}} value={value} onChange={e => onChange(e.target.value)}/>
            : value
        }
    </div>
);

export default class Page3 extends Component {
    constructor(props) {
        super(props);
        this.columns = [{
            title: 'idField',
            dataIndex: 'idField',
            render: (text, record) => this.renderColumns(text, record, 'idField'),
        }, {
            title: 'username',
            dataIndex: 'login.username',
            render: (text, record) => this.renderColumns(text, record, 'username'),
        }, {
            title: 'email',
            dataIndex: 'email',
            render: (text, record) => this.renderColumns(text, record, 'email'),
        }, {
            title: 'registered',
            dataIndex: 'registered',
            render: (text, record) => this.renderColumns(text, record, 'registered'),
        }, {
            title: 'city',
            dataIndex: 'location.city',
            render: (text, record) => this.renderColumns(text, record, 'city'),
        }, {
            title: 'state',
            dataIndex: 'location.state',
            render: (text, record) => this.renderColumns(text, record, 'state'),
        }, {
            title: 'street',
            dataIndex: 'location.street',
            render: (text, record) => this.renderColumns(text, record, 'street'),
        },

            {
                title: 'operation',
                dataIndex: 'operation',
                render: (text, record) => {
                    const {editable} = record;
                    return (
                        <div className="editable-row-operations">
                            {
                                editable ?
                                    <span>
                  <a onClick={() => this.save(record.key)}>Save</a>
                  <Popconfirm title="Sure to cancel?" onConfirm={() => this.cancel(record.key)}>
                    <a>Cancel</a>
                  </Popconfirm>
                </span>
                                    : <a onClick={() => this.edit(record.key)}>Edit</a>
                            }
                        </div>
                    );
                },
            }];

        this.state = {data};
        this.cacheData = data.map(item => ({...item}));
    }


    componentDidMount() {
        this.fetchData();
    }


    fetchData = () => {
        this.setState({loading: true});
        axios.get('/my_api/random_users').then(res => {
            this.setState({
                data: _.map(res.data, (one, i) => {
                    return {...one};
                }),
                loading: false
            });
            console.log(res);
        }).catch(err => console.log(err));
    };


    renderColumns(text, record, column) {
        return (
            <EditableCell
                editable={record.editable}
                value={text}
                onChange={value => this.handleChange(value, record.key, column)}
            />
        );
    }

    handleChange(value, key, column) {
        const newData = [...this.state.data];
        const target = newData.filter(item => key === item.key)[0];
        if (target) {
            target[column] = value;
            this.setState({data: newData});
        }
    }

    edit(key) {
        const newData = [...this.state.data];
        const target = newData.filter(item => key === item.key)[0];
        if (target) {
            target.editable = true;
            this.setState({data: newData});
        }
    }

    save(key) {
        const newData = [...this.state.data];
        const target = newData.filter(item => key === item.key)[0];
        if (target) {
            delete target.editable;
            this.setState({data: newData});
            this.cacheData = newData.map(item => ({...item}));
        }
    }

    cancel(key) {
        const newData = [...this.state.data];
        const target = newData.filter(item => key === item.key)[0];
        if (target) {
            Object.assign(target, this.cacheData.filter(item => key === item.key)[0]);
            delete target.editable;
            this.setState({data: newData});
        }
    }

    render() {
        return <Table bordered dataSource={this.state.data} columns={this.columns} rowKey="idField"/>;
    }
}

