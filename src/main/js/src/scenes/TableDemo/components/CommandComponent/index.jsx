import React from "react";
import PropTypes from "prop-types";
import {AddButton, CancelButton, CommitButton, DeleteButton, EditButton} from "./components/Buttons";

const commandComponents = {
    add: AddButton,
    edit: EditButton,
    delete: DeleteButton,
    commit: CommitButton,
    cancel: CancelButton,
};

const Command = ({id, onExecute}) => {
    const CommandButton = commandComponents[id];
    return (
        <CommandButton
            onExecute={onExecute}
        />
    );
};

Command.propTypes = {
    id: PropTypes.string.isRequired,
    onExecute: PropTypes.func.isRequired,
};

export default Command;