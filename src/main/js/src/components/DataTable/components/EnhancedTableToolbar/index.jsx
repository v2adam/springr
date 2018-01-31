import React, {Component} from 'react';
import PropTypes from 'prop-types';
import Toolbar from 'material-ui/Toolbar';
import Typography from 'material-ui/Typography';
import IconButton from 'material-ui/IconButton';
import Tooltip from 'material-ui/Tooltip';
import DeleteIcon from 'material-ui-icons/Delete';
import {lighten} from 'material-ui/styles/colorManipulator';
import {withStyles} from "material-ui/styles/index";
import classNames from "classnames";
import Button from 'material-ui/Button';
import AddIcon from 'material-ui-icons/Add';

const toolbarStyles = theme => ({
    root: {
        paddingRight: theme.spacing.unit,
    },
    highlight:
        theme.palette.type === 'light'
            ? {
                color: theme.palette.secondary.dark,
                backgroundColor: lighten(theme.palette.secondary.light, 0.4),
            }
            : {
                color: lighten(theme.palette.secondary.light, 0.4),
                backgroundColor: theme.palette.secondary.dark,
            },
    spacer: {
        flex: '1 1 100%',
    },
    actions: {
        color: theme.palette.text.secondary,
    },
    title: {
        flex: '0 0 auto',
    },
});


class EnhancedTableToolbar extends Component {

    render() {
        const {numSelected, classes, deleteHandler} = this.props;

        return (
            <Toolbar
                className={classNames(classes.root, {
                    [classes.highlight]: numSelected > 0,
                })}
            >
                <div className={classes.title}>
                    {numSelected > 0 ? (
                        <Typography type="subheading">{numSelected} selected</Typography>
                    ) : (
                        <Typography type="title">{this.props.title}</Typography>
                    )}
                </div>
                <div className={classes.spacer}/>
                <div className={classes.actions}>
                    {numSelected > 0 ? (
                        <Tooltip title="Delete">
                            <IconButton aria-label="Delete" onClick={this.props.deleteHandler}>
                                <DeleteIcon/>
                            </IconButton>
                        </Tooltip>
                    ) : (
                        <Tooltip id="tooltip-fab" className={classes.fab} title="Add">
                            <Button fab color="secondary" aria-label="Add" onClick={this.props.addNew}>
                                <AddIcon/>
                            </Button>
                        </Tooltip>
                    )}
                </div>
            </Toolbar>
        );
    }
}


EnhancedTableToolbar.propTypes = {
    classes: PropTypes.object.isRequired,
    numSelected: PropTypes.number.isRequired,
    title: PropTypes.string.isRequired,
    deleteHandler: PropTypes.func.isRequired,
    addNew: PropTypes.func.isRequired
};

export default withStyles(toolbarStyles)(EnhancedTableToolbar);


