import React from 'react'
import PropTypes from 'prop-types';
import {TableEditRow,} from '@devexpress/dx-react-grid-material-ui';


const EditCell = (props) => {
    /*
    const availableColumnValues = availableValues[props.column.name];
    if (availableColumnValues) {
        return <LookupEditCell {...props} availableColumnValues={availableColumnValues}/>;
    }
    */

    // TODO: listából választhasson, ne csak a beírás számítson


    return <TableEditRow.Cell {...props} />;
};

EditCell.propTypes = {
    column: PropTypes.shape({name: PropTypes.string}).isRequired,
    availableColumnValues: PropTypes.array
};

export default EditCell;