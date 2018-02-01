import React, {Component} from 'react';
import history from '../../misc/history';

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
                history.push('/react/page1');
                break;

            case 3:
                history.push('/react/page2');
                break;

            case 4:
                history.push('/react/page3');
                break;

            case 5:
                history.push('/react/page4');
                break;

            case 6:
                history.push('/react/page5');
                break;

            default:
                history.push(`/404`);
                break;
        }
    };


    render() {
        return (
            <h1>Navbar</h1>
        )
    }

}
