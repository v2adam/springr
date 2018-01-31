import React from 'react';
import PropTypes from 'prop-types';
import {Table} from '@devexpress/dx-react-grid-material-ui';

const Cell = (props) => {
    return <Table.Cell {...props} />;
};
Cell.propTypes = {
    column: PropTypes.shape({name: PropTypes.string}).isRequired,
};

export default Cell;