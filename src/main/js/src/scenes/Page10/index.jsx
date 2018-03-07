import React, {Component} from 'react';
import {Button, Icon, message, Upload} from 'antd';
import ErrorBoundary from "../../components/ErrorBoundary";
import BootstrapTable from 'react-bootstrap-table-next';
import 'react-bootstrap-table-next/dist/react-bootstrap-table2.min.css';

class Page10 extends Component {


    constructor(props) {
        super(props);
        this.state = {
            fileList: [],
            header: [],
			rows: [],
			columns : [{
				dataField: 'id',
				text: 'id',
			  },{
				dataField: 'someField',
				text: 'somesomeFieldID',
			  },{
				dataField: 'issueId',
				text: 'issueId',
			  }, {
				dataField: 'valami',
				text: 'valami',
			  }, {
				dataField: 'groupId',
				text: 'groupId',
			  },{
				dataField: 'subGroupId',
				text: 'subGroupId',
			  },{
				dataField: 'date',
				text: 'date',
			  },{
				dataField: 'mtid',
				text: 'mtid',
			  },{
				dataField: 'cg',
				text: 'cg',
			  },{
				dataField: 'segm',
				text: 'segm',
			  },{
				dataField: 'ossnr',
				text: 'ossnr',
			  },{
				dataField: 'accmtid',
				text: 'accmtid',
			  },{
				dataField: 'agree',
				text: 'agree',
			  }]
        };
	}
	
    // kell egy mappelés, hogy a header key-ei alapján legyen összerendelés a cellákban
    mapToHeaderKey = (one, i) => {

		if(!this.state.header){
            let mapped = {};

            for (let x = 0; x < this.state.header.length; x++) {
                mapped[x] = this.state.rows[i][x];
            }

            console.log(mapped);
            return mapped;
        }

        return one;
    };


    onChange = (info) => {
        if (info.file.status !== 'uploading') {
            console.log(info.file, info.fileList);
        }
        if (info.file.status === 'done') {
            message.success(`${info.file.name} file uploaded successfully`);

            this.setState({
                header: info.file.response.header.map((one) => ({key: one, name: one})),
                rows: info.file.response.rows.map(one => one)
            }, () => {
                console.log(this.state);
                let mapped = this.state.rows.map((one, i) => this.mapToHeaderKey(one, i));
                this.setState({rows: mapped});
            });

        } else if (info.file.status === 'error') {
            message.error(`${info.file.name} file upload failed.`);
        }


        let fileList = info.fileList;

        this.setState({fileList});

	};
	

    render() {

        return (
     
            <ErrorBoundary>
                <Upload name={"file"}
                        action={"/my_api/files/upload/xls"}
                        onChange={this.onChange}
                        multiple={false}
                        fileList={this.state.fileList}
                        accept={"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"}>
                    <Button type="primary">
                        <Icon type="upload"/>Upload Xls
                    </Button>
                </Upload>
				<BootstrapTable
                    keyField='id'
                    data={ this.state.rows } 
                    columns={ this.state.columns }
                    striped
                    hover
                    condensed
                    noDataIndication="Table is Empty"
                    caption="ez egy caption"
                />
            </ErrorBoundary>
        )
    };

}

export default Page10;

