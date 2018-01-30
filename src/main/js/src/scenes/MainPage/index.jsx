import React, {Component} from 'react'
import Button from "material-ui/es/Button/Button";
import axios from 'axios';


export default class MainPage extends Component {

    primaryBtn = async () => {
        const result = await axios('/my_api/users');
        console.log(result.data);
    };

    render() {
        return (
            [
                <div key="divtop">
                    <Button raised color="secondary" onClick={this.primaryBtn}>
                        Super Secret Password
                    </Button>
                </div>,

                <div key="divb">
                    <Button>Default</Button>
                    <Button color="primary">
                        Primary
                    </Button>
                    <Button color="secondary">
                        Secondary
                    </Button>
                    <Button disabled>
                        Disabled
                    </Button>
                    <Button href="#flat-buttons">
                        Link
                    </Button>
                    <Button disabled href="/">
                        Link disabled
                    </Button>
                    <Button dense>
                        Dense
                    </Button>
                    <Button data-something="here I am">
                        Does something
                    </Button>
                </div>
            ]
        );
    }
}

