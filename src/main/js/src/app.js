import React, {Component} from 'react';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';
import {Route, Switch, withRouter} from 'react-router';
import MainPage from "./scenes/MainPage";
import PageNotFound from "./scenes/PageNotFound";
import NavBar from "./components/NavBar";
import Page1 from "./scenes/Page1";
import Page2 from "./scenes/Page2";
import Page3 from "./scenes/Page3";
import Page4 from "./scenes/Page4";
import Page5 from "./scenes/Page5";

class App extends Component {
    render() {
        return (
            [
                <NavBar key="navBarKey"/>,
                <Switch key="switchKey">
                    <Route exact path="/" component={MainPage}/>
                    <Route path="/react/page1" component={Page1}/>
                    <Route path="/react/page2" component={Page2}/>
                    <Route path="/react/page3" component={Page3}/>
                    <Route path="/react/page4" component={Page4}/>
                    <Route path="/react/page5" component={Page5}/>
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

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(App));

App.propTypes = {};

App.defaultProps = {};
