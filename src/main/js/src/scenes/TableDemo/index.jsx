import React, {Component} from 'react'
import Table, {TableBody, TableCell, TableFooter, TableHead, TablePagination, TableRow,} from 'material-ui/Table';
import Paper from 'material-ui/Paper';
import Button from 'material-ui/Button';

import axios from 'axios';
import SendToDb from "../../components/SendToDb";


const styles = theme => ({
    root: {
        width: '100%',
        marginTop: theme.spacing.unit * 3,
        overflowX: 'auto',
    },
    table: {
        minWidth: 700,
    },
});


export default class TableDemo extends Component {


    constructor(props) {
        super(props);

        this.state = {
            tableData: [],
            selected: [],
            page: 0,
            rowsPerPage: 5,
        }
    }


    componentDidMount() {
        this.fetchData();
    };


    fetchData = () => {
        axios.get('/my_api/store_employee').then(res => {
            this.setState({
                tableData: res.data
            });
        }).catch(err => console.log(err));
    };


    sendAll = () => {
        console.log('sendAll');

        const opts = {
            headers: {
                'Content-Type': 'application/json',
            }
        };

        axios.post('/my_api/store_all_employee', JSON.stringify(this.state.tableData), opts).then(res => this.fetchData()).catch(err => console.log(err));
    };


    update = () => {
        this.fetchData();
    };


    // -------------------- paginátor --------------------
    // lista mérete
    handleChangePage = (event, page) => {
        console.log('handleChangePage: ' + page);
        this.setState({page});
    };

    // oldalszáma
    handleChangeRowsPerPage = (event) => {
        console.log('handleChangeRowsPerPage: ' + event.target.value);
        this.setState({rowsPerPage: event.target.value});
    };


    render() {
        const {classes} = this.props;
        const {tableData, selected, rowsPerPage, page} = this.state;
        const emptyRows = rowsPerPage - Math.min(rowsPerPage, tableData.length - page * rowsPerPage);

        return (
            <Paper>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>ID</TableCell>
                            <TableCell>Name</TableCell>
                            <TableCell>Status</TableCell>
                        </TableRow>
                    </TableHead>


                    <TableBody>
                        {tableData.slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage).map(row => {
                            return (
                                <TableRow
                                    hover
                                    key={row.id}>
                                    <TableCell>{row.id}</TableCell>
                                    <TableCell>{row.name}</TableCell>
                                    <TableCell>{row.status}</TableCell>
                                </TableRow>
                            );
                        })}
                        {emptyRows > 0 && (
                            <TableRow style={{height: 49 * emptyRows}}>
                                <TableCell colSpan={6}/>
                            </TableRow>
                        )}
                    </TableBody>

                    <TableFooter>
                        <TableRow>
                            <TablePagination
                                colSpan={6}
                                count={tableData.length}
                                rowsPerPage={rowsPerPage}
                                page={page}
                                onChangePage={this.handleChangePage}
                                onChangeRowsPerPage={this.handleChangeRowsPerPage}
                            />
                        </TableRow>
                    </TableFooter>


                </Table>
                <Button color="primary" onClick={this.sendAll}>Send all</Button>
                <SendToDb selectedRows={selected} update={this.update}/>
            </Paper>
        );
    }
}

