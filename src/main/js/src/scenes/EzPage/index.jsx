import React from 'react';
import {Grid} from 'semantic-ui-react'
import { NavLink } from 'react-router-dom';

const EzPage = (props) => (
    <Grid textAlign='center'>
        <Grid.Column>
            <h1>Ez page</h1>
            <li><NavLink activeClassName="active" to="/js/many_example">Many example</NavLink></li>
        </Grid.Column>
    </Grid>
);

export default EzPage;
