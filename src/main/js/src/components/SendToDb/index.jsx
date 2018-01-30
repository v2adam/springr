import React, {Component} from 'react';
import Button from 'material-ui/Button';
import PropTypes from 'prop-types';
import axios from 'axios';

const style = {
    margin: 12,
};


export default class SendToDb extends Component {

    constructor(props) {
        super(props);
    }

    componentWillReceiveProps() {
        this.setState({
            selected: this.props.selected
        });
    }

    sendRequest = async () => {

        const opts = {
            headers: {
                'Content-Type': 'application/json',
            }
        };

        try {
            const res = await axios.patch('/my_api/store_employee', JSON.stringify(this.state.selected), opts);
        } catch (err) {
            console.log(`PATCH baj van: ${err}`);
        }

        this.props.update();

        this.setState({
            selected: []
        });

    };


    render() {
        return (
            <Button raised color="secondary" style={style} onClick={this.sendRequest}>Delete selected</Button>
        );
    }
}

SendToDb.defaultProps = {
    selected: []
};

SendToDb.propTypes = {
    selected: PropTypes.array,
    update: PropTypes.func.isRequired
};


