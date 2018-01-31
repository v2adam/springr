import React, {Component} from 'react'


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

    }


    render() {
        return (
            <h1>Table demo</h1>
        );
    }
}

