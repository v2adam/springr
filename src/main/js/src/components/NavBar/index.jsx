import React, {Component} from 'react';
import AppBar from 'material-ui/AppBar';
import Tabs, {Tab} from 'material-ui/Tabs';
import history from '../../misc/history';
import DataTable from "../../scenes/DataTableScene";

export default class NavBar extends Component {

    constructor(props) {
        super(props);
        this.state = {
            selected: 0
        }
    }


    handleChange = (event, value) => {
        this.setState({
            selected: value
        });

        switch (value) {
            case 0:
                break;

            case 1:
                history.push('/');
                break;

            case 2:
                history.push('/react/demo_table');
                break;

            case 3:
                history.push('/react/simple_table');
                break;

            case 4:
                history.push('/react/datatable');
                break;


            default:
                history.push(`/404`);
                break;
        }
    };


    render() {
        return (
            <AppBar position="static">
                <Tabs value={this.state.selected} onChange={this.handleChange}>
                    <Tab label="WebSiteLogo" href="/"/>
                    <Tab label="Home"/>
                    <Tab label="Table demo"/>
                    <Tab label="Simple table"/>
                    <Tab label="Datatable"/>
                </Tabs>
            </AppBar>
        );
    }

}
