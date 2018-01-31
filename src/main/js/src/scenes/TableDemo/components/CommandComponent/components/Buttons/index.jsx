import React from 'react';
import PropTypes from 'prop-types';
import Button from 'material-ui/Button';
import IconButton from 'material-ui/IconButton';

import DeleteIcon from 'material-ui-icons/Delete';
import EditIcon from 'material-ui-icons/Edit';
import SaveIcon from 'material-ui-icons/Save';
import CancelIcon from 'material-ui-icons/Cancel';


const AddButton = ({onExecute}) => (
    <div style={{textAlign: 'center'}}>
        <Button
            color="primary"
            onClick={onExecute}
            title="Create new row"
        >
            New
        </Button>
    </div>
);
AddButton.propTypes = {
    onExecute: PropTypes.func.isRequired,
};


const EditButton = ({onExecute}) => (
    <IconButton onClick={onExecute} title="Edit row">
        <EditIcon/>
    </IconButton>
);

EditButton.propTypes = {
    onExecute: PropTypes.func.isRequired,
};


const DeleteButton = ({onExecute}) => (
    <IconButton onClick={onExecute} title="Delete row">
        <DeleteIcon/>
    </IconButton>
);

DeleteButton.propTypes = {
    onExecute: PropTypes.func.isRequired,
};


const CommitButton = ({onExecute}) => (
    <IconButton onClick={onExecute} title="Save changes">
        <SaveIcon/>
    </IconButton>
);

CommitButton.propTypes = {
    onExecute: PropTypes.func.isRequired,
};


const CancelButton = ({onExecute}) => (
    <IconButton color="secondary" onClick={onExecute} title="Cancel changes">
        <CancelIcon/>
    </IconButton>
);

CancelButton.propTypes = {
    onExecute: PropTypes.func.isRequired,
};


export {
    AddButton,
    EditButton,
    DeleteButton,
    CommitButton,
    CancelButton
}