import React from 'react';
import {Table} from 'antd';
import axios from 'axios';

const columns = [{
    title: 'Name',
    dataIndex: 'name',
    sorter: true,
    render: name => `${name.first} ${name.last}`,
    width: '20%',
}, {
    title: 'Gender',
    dataIndex: 'gender',
    filters: [
        {text: 'Male', value: 'male'},
        {text: 'Female', value: 'female'},
    ],
    width: '20%',
}, {
    title: 'Email',
    dataIndex: 'email',
}];

export default class Page2 extends React.Component {
    state = {
        data: [],
        pagination: {},
        loading: false,
    };

    handleTableChange = (pagination, filters, sorter) => {
        const pager = {...this.state.pagination};
        pager.current = pagination.current;
        this.setState({
            pagination: pager,
        });
        this.fetch({
            results: pagination.pageSize,
            page: pagination.current,
            sortField: sorter.field,
            sortOrder: sorter.order,
            ...filters,
        });
    };

    fetch = (params = {}) => {
        console.log('params:', params);
        this.setState({loading: true});

        const opts = {
            headers: {
                'Content-Type': 'application/json',
            }
        };


        axios.get('https://randomuser.me/api/?results=10', opts).then((data) => {
            const pagination = {...this.state.pagination};
            console.log(data.data.results);
            // Read total count from server
            // pagination.total = data.totalCount;
            pagination.total = 200;
            this.setState({
                loading: false,
                data: data.data.results,
                pagination,
            });
        });
    };

    componentDidMount() {
        this.fetch();
    };

    render() {
        return (
            <Table columns={columns}
                   rowKey={record => record.registered}
                   dataSource={this.state.data}
                   pagination={this.state.pagination}
                   loading={this.state.loading}
                   onChange={this.handleTableChange}
            />
        );
    }
}

