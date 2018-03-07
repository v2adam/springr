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
import Page6 from "./scenes/Page6";
import Page7 from "./scenes/Page7";
import Page8 from "./scenes/Page8";
import Page9 from "./scenes/Page9";
import Page10 from "./scenes/Page10";

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
                    <Route path="/react/page6" component={Page6}/>
                    <Route path="/react/page7" component={Page7}/>
                    <Route path="/react/page8" component={Page8}/>
                    <Route path="/react/page9" component={Page9}/>
                    <Route path="/react/page10" component={Page10}/>

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
