import React from 'react';
import {createMuiTheme, MuiThemeProvider} from 'material-ui/styles';
import Reboot from 'material-ui/Reboot';

// A theme with custom primary and secondary color.
// It's optional.
const theme = createMuiTheme({});

// Expose the theme as a global variable so people can play with it.
if (process.browser) {
    window.theme = theme;
}

function withRoot(Component) {
    function WithRoot(props) {
        // MuiThemeProvider makes the theme available down the React tree
        // thanks to React context.
        return (
            <MuiThemeProvider theme={theme}>
                {/* Reboot kickstart an elegant, consistent, and simple baseline to build upon. */}
                <Reboot/>
                <Component {...props} />
            </MuiThemeProvider>
        );
    }

    return WithRoot;
}

export default withRoot;