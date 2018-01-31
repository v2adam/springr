import React, {Component} from 'react'
import PropTypes from 'prop-types';
import {
    SortingState, EditingState, PagingState,
    IntegratedPaging, IntegratedSorting,
} from '@devexpress/dx-react-grid';
import {
    Grid,
    Table, TableHeaderRow, TableEditRow, TableEditColumn,
    PagingPanel, DragDropProvider, TableColumnReordering,
} from '@devexpress/dx-react-grid-material-ui';
import Paper from 'material-ui/Paper';
import Dialog, {
    DialogActions,
    DialogContent,
    DialogContentText,
    DialogTitle,
} from 'material-ui/Dialog';
import Button from 'material-ui/Button';
import IconButton from 'material-ui/IconButton';
import Input from 'material-ui/Input';
import Select from 'material-ui/Select';
import { MenuItem } from 'material-ui/Menu';
import { TableCell } from 'material-ui/Table';

import DeleteIcon from 'material-ui-icons/Delete';
import EditIcon from 'material-ui-icons/Edit';
import SaveIcon from 'material-ui-icons/Save';
import CancelIcon from 'material-ui-icons/Cancel';
import { withStyles } from 'material-ui/styles';

import axios from 'axios';



import Command from './components/CommandComponent'
import Cell from './components/Cell'
import EditCell from "./components/EditCell";




const getRowId = row => row.id;



export default class TableDemo extends Component {

    constructor(props) {
        super(props);
        this.state = {
            columns: [
                { name: 'id', title: 'UserID' },
                { name: 'name', title: 'Employee name' },
                { name: 'status', title: 'Status' }
            ],
            tableColumnExtensions: [
                { columnName: 'id', align: 'right' },
            ],
            rows:[],
            sorting: [],
            editingRowIds: [],
            addedRows: [],
            rowChanges: {},
            currentPage: 0,
            deletingRows: [],
            pageSize: 0,
            pageSizes: [5, 10, 0],
            columnOrder: ['id', 'name', 'status'],
        };
    };

    componentDidCatch(){
        console.log('componentDidCatch');
    }


    componentDidMount() {
        this.fetchData();
    }


    fetchData = () => {
        axios.get('/my_api/employees').then(res => {
            this.setState({
                rows: res.data
            });
        }).catch(err => console.log(err));

    };


    // sort
    changeSorting = (sorting) => {
        this.setState({sorting});
    };

    // paginator
    changeCurrentPage = currentPage => this.setState({ currentPage });

    changePageSize = pageSize => this.setState({ pageSize });


    // order
    changeColumnOrder = (order) => {
        this.setState({ columnOrder: order });
    };


    // editing
    changeEditingRowIds = editingRowIds => this.setState({ editingRowIds });

    changeRowChanges = rowChanges => this.setState({ rowChanges });

    changeAddedRows = (addedRows) => {
        console.log('changeAddedRows!');
        console.log(addedRows);
        this.setState({
        addedRows: addedRows.map(row => (Object.keys(row).length ? row : {
            id: 999,
            name: 'iamnew',
            status: 'nostatus'
        })),
    })
    };



    commitChanges = ({ added, changed, deleted }) => {
        let { rows } = this.state;
        if (added) {
            const startingAddedId = (rows.length - 1) > 0 ? rows[rows.length - 1].id + 1 : 0;
            rows = [
                ...rows,
                ...added.map((row, index) => ({
                    id: startingAddedId + index,
                    ...row,
                })),
            ];
        }
        if (changed) {
            rows = rows.map(row => (changed[row.id] ? { ...row, ...changed[row.id] } : row));
        }
        this.setState({ rows, deletingRows: deleted || this.state.deletingRows });
    };

    hello = (id) => {
        console.log('hellooooo' + id);
    };

    render() {

        const {
            rows,
            columns,
            tableColumnExtensions,
            sorting,
            editingRowIds,
            addedRows,
            rowChanges,
            currentPage,
            deletingRows,
            pageSize,
            pageSizes,
            columnOrder,
        } = this.state;


        return (
            <Paper>
                <h1>Top side</h1>

                <Command id='add' onExecute={() => this.hello('add')}/>
                <Command id='edit' onExecute={() => this.hello('edit')}/>
                <Command id='delete' onExecute={() => this.hello('delete')}/>
                <Command id='commit' onExecute={() => this.hello('commit')}/>
                <Command id='cancel' onExecute={() => this.hello('cancel')}/>

                <br/>


                <Grid
                    rows={rows}
                    columns={columns}
                    getRowId={getRowId}
                >
                    <SortingState
                        sorting={sorting}
                        onSortingChange={this.changeSorting}
                    />
                    <PagingState
                        currentPage={currentPage}
                        onCurrentPageChange={this.changeCurrentPage}
                        pageSize={pageSize}
                        onPageSizeChange={this.changePageSize}
                    />
                    <IntegratedSorting />
                    <IntegratedPaging />


                    <EditingState
                        editingRowIds={editingRowIds}
                        onEditingRowIdsChange={this.changeEditingRowIds}
                        rowChanges={rowChanges}
                        onRowChangesChange={this.changeRowChanges}
                        addedRows={addedRows}
                        onAddedRowsChange={this.changeAddedRows}
                        onCommitChanges={this.commitChanges}
                    />


                    <DragDropProvider />

                    <Table
                        columnExtensions={tableColumnExtensions}
                        cellComponent={Cell}/>


                    <TableColumnReordering
                        order={columnOrder}
                        onOrderChange={this.changeColumnOrder}
                    />



                    <TableHeaderRow showSortingControls/>
                    <TableEditRow
                        cellComponent={EditCell}
                    />
                    <TableEditColumn
                        width={120}
                        showAddCommand={!addedRows.length}
                        showEditCommand
                        showDeleteCommand
                        commandComponent={Command}
                    />
                    <PagingPanel
                        pageSizes={pageSizes}
                    />
                </Grid>
            </Paper>
        );
    }
}

