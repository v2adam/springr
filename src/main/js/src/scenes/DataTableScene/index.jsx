import React, {Component} from 'react'
import DataTable from "../../components/DataTable";


export default class DataTableScene extends Component {


    render() {
        return (
            [
                <h1 key="key0">Top</h1>,
                <DataTable key="key1"/>,
                <h1 key="key2">Bottom</h1>
            ]
        );
    }
}

