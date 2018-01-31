import React, {Component} from 'react'
import EnhancedTable from "./components/EnhancedTable";
import PropTypes from "prop-types";

export default class DataTable extends Component {


    constructor(props) {
        super(props);
    }


    render() {
        return (
            <EnhancedTable
                tableContent={this.props.tableContent}
                tableTitle={this.props.tableTitle}
                deleteSelected={this.props.deleteSelected}
                addNew={this.props.addNew}/>
        );
    }
}

DataTable.propTypes = {
    tableContent: PropTypes.array.isRequired,
    tableTitle: PropTypes.string,
    deleteSelected: PropTypes.func.isRequired,
    addNew: PropTypes.func.isRequired
};

DataTable.defaultProps = {
    tableContent: [],
    tableTitle: ''
};