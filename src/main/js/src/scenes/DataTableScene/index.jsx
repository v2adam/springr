import React, {Component} from 'react'
import DataTable from "../../components/DataTable";
import axios from 'axios';

export default class DataTableScene extends Component {


    constructor(props) {
        super(props);
        this.state = {}
    }


    componentDidMount() {
        this.fetchData();
    };


    componentDidCatch() {
        console.log('componentDidCatch - DataTableScene');
    }


    fetchData = () => {
        axios.get('/my_api/employees').then(res => {
            this.setState({
                tableData: res.data
            });
        }).catch(err => console.log(err));
    };


    deleteSelected = async (selected) => {
        const opts = {
            headers: {
                'Content-Type': 'application/json',
            }
        };

        try {
            const res = await axios.patch('/my_api/employees', JSON.stringify(selected), opts);
        } catch (err) {
            console.log(`PATCH baj van: ${err}`);
        }

        this.fetchData();
    };


    addNew = async () => {
        await axios.post('/my_api/employees').catch(err => console.log(err));
        this.fetchData();
    };

    render() {

        const {tableData} = this.state;


        return (
            [
                <h1 key="key0">Top</h1>,
                <DataTable key="key1"
                           tableContent={tableData}
                           tableTitle="my title"
                           deleteSelected={this.deleteSelected}
                           addNew={this.addNew}/>,
                <h1 key="key2">Bottom</h1>
            ]
        );
    }
}

