import React from 'react';
import {Grid} from 'semantic-ui-react'
import { NavLink } from 'react-router-dom';

const PageNotFound = (props) => (
    <Grid textAlign='center'>
        <Grid.Column>
            <h1>Page not found</h1>
            <li><NavLink activeClassName="active" to="/">Home</NavLink></li>
        </Grid.Column>
    </Grid>
);

export default PageNotFound;
