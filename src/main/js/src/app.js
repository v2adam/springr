import React, {Component} from 'react';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';
import {Route, Switch, withRouter} from 'react-router';
import MainPage from "./scenes/MainPage";
import TableDemo from "./scenes/TableDemo";
import PageNotFound from "./scenes/PageNotFound";
import withRoot from "./withRoot";
import NavBar from "./components/NavBar";
import SimpleTable from "./scenes/SimpleTable";
import DataTableScene from "./scenes/DataTableScene";

class App extends Component {
    render() {
        return (
            [
                <NavBar key="navBarKey"/>,
                <Switch key="switchKey">
                    <Route exact path="/" component={MainPage}/>
                    <Route path="/react/demo_table" component={TableDemo}/>
                    <Route path="/react/simple_table" component={SimpleTable}/>
                    <Route path="/react/datatable" component={DataTableScene}/>
                    <Route component={PageNotFound}/>
                    {/*<Redirect to="/404"/>*/}
                </Switch>
            ]
        );
    }
}

function mapStateToProps(state) {
    return {};
}


function mapDispatchToProps(dispatch) {
    return bindActionCreators({}, dispatch);
}

export default withRoot(withRouter(connect(mapStateToProps, mapDispatchToProps)(App)));

App.propTypes = {};

App.defaultProps = {};
