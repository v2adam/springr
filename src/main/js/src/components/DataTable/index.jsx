import React, {Component} from 'react'
import EnhancedTable from "./components/EnhancedTable";
import PropTypes from "prop-types";

export default class DataTable extends Component {


    constructor(props) {
        super(props);
    }


    render() {
        return (
            <EnhancedTable tableContent={this.props.tableContent}/>
        );
    }
}

DataTable.propTypes = {
    tableContent: PropTypes.array.isRequired,
};

DataTable.defaultProps = {
    tableContent: []
};