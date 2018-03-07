import React, {Component} from 'react';
import ErrorBoundary from "../../components/ErrorBoundary";
import BootstrapTable from 'react-bootstrap-table-next';
import 'react-bootstrap-table-next/dist/react-bootstrap-table2.min.css';
import filterFactory, { textFilter, Comparator  } from 'react-bootstrap-table2-filter';
import paginationFactory from 'react-bootstrap-table2-paginator';

class Page9 extends Component {


    constructor(props) {
        super(props);
        this.state = {
            fileList: [],
            header: [],
            rows: []
        };
    }


    priceFormatter = (cell, row) => {
        if (row.onSale) {
          return (
            <span>
              <strong style={ { color: 'red' } }>$ { cell } NTD(Sales!!)</strong>
            </span>
          );
        }
      
        return (
          <span>$ { cell } NTD</span>
        );
      }


      rankFormatter = (cell, row, rowIndex, formatExtraData) => {
        return (
          <i className={ formatExtraData[cell] } />
        );
      }



      priceHeaderFormatter = (column, colIndex, { sortElement, filterElement }) => {
        return (
          <div style={ { display: 'flex', flexDirection: 'column' } }>
            { filterElement }
            <h5><strong>$$ { column.text } $$</strong></h5>
            { sortElement }
          </div>
        );
      }
      

    render() {

        const selectOptions = {
            0: 'good',
            1: 'Bad',
            2: 'unknown'
          };
          

        const columns = [{
            dataField: 'id',
            text: 'Product ID',
            attrs: { title: 'id column', align: 'center' },
            headerStyle: (column, colIndex) => {
                if (colIndex % 2 === 0) {
                  return {
                    backgroundColor: '#81c784'
                  };
                }
                return {
                  backgroundColor: '#c8e6c9'
                };
              },
              filter: textFilter({
                comparator: Comparator.EQ,
                caseSensitive: true
            }),
          }, {
            dataField: 'name',
            text: 'Product Name',
            align: (cell, row, rowIndex, colIndex) => {
                if (rowIndex % 2 === 0) return 'right';
                return 'left';
              },
            title: (cell, row, rowIndex, colIndex) => `this is custom title for ${cell}`,
            events: {
                onClick: () => alert('Click on Product ID field')
              }
          }, {
            dataField: 'price',
            text: 'Product Price',
            formatter: this.priceFormatter,
            headerFormatter: this.priceHeaderFormatter,
            filter: textFilter({
                defaultValue: '3',
                options: selectOptions
              }),
            sort: true,
          }, {
            dataField: 'rank',
            text: 'Rank',
            formatter: this.rankFormatter,
            formatExtraData: {
              up: 'glyphicon glyphicon-chevron-up',
              down: 'glyphicon glyphicon-chevron-down'},
              style: (cell, row, rowIndex, colIndex) => {
                if (rowIndex % 2 === 0) {
                  return {
                    backgroundColor: '#81c784'
                  };
                }
                return {
                  backgroundColor: '#c8e6c9'
                };
              },  
              headerAlign: (column, colIndex) => 'right',
              headerTitle: (column, colIndex) => `this is custom title for ${column.text}`
          },{
            dataField: 'col',
            text: 'rejtett',
            hidden: true
          }];


          const products = [
              {id: 1, name: 'valami', price: 103000, rank:'up', col: 12 },
              {id: 2, name: 'asd', price: 345, rank:'up', col: 12 },
              {id: 3, name: 'sdgfsdg', price: 3245235, rank:'down', col:12 },
              {id: 4, name: 'xcv', price: 123, rank:'down', col: 12 }
          ];

          
        const rowEvents = {
            onClick: (e, row, rowIndex) => {
              alert(`clicked on row with index: ${rowIndex}`);
            }
          };

        return (
     
            <ErrorBoundary>
                <BootstrapTable
                    exportCSV={ true } 
                    keyField='id'
                    data={ products } 
                    columns={ columns }
                    striped
                    hover
                    condensed
                    noDataIndication="Table is Empty"
                    caption="ez egy caption"
                    filter={ filterFactory() }
                    rowEvents={ rowEvents }
                    pagination={ paginationFactory()}
                />
            </ErrorBoundary>
        )
    };

}

export default Page9;

