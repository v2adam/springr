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


    componentDidCatch(){
        console.log('componentDidCatch - DataTableScene');
    }


    fetchData = () => {
        axios.get('/my_api/store_employee').then(res => {
            this.setState({
                tableData: res.data
            });
        }).catch(err => console.log(err));
    };


    render() {

        const {tableData} = this.state;


        return (
            [
                <h1 key="key0">Top</h1>,
                <DataTable key="key1" tableContent={tableData}/>,
                <h1 key="key2">Bottom</h1>
            ]
        );
    }
}

