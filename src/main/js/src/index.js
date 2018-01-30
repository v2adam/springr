import React from 'react';
import ReactDOM from 'react-dom';
import {Provider} from 'react-redux';
import {Router} from 'react-router'


import history from './misc/history';
import store from './store';
import App from './App';


ReactDOM.render(
    <Provider store={store}>
        <Router history={history}>
            <div>
                <App/>
            </div>
        </Router>
    </Provider>, document.getElementById('root'));
