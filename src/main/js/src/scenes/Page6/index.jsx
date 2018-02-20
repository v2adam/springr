import React, {Component} from 'react';
import {Button, Icon, List, message, Upload} from 'antd';
import axios from "axios/index";
import ErrorBoundary from "../../components/ErrorBoundary";

export default class Page6 extends Component {


    constructor(props) {
        super(props);
        this.state = {
            folderContent: [],
            name: 'file',
            action: '/my_api/files/upload',
            fileList: []
        };
    }

    componentDidMount() {
        this.listFiles();
    }


    listFiles = () => {
        console.log('listFiles');

        axios.get('/my_api/files').then(res => {
            console.log(res);
            this.setState({
                folderContent: res.data
            });

            const mapped = this.state.folderContent.map((one, i) => ({
                uid: i + 1,
                name: one.fileName,
                status: 'done',
                url: one.path
            }));

            this.setState({
                fileList: mapped
            });

        }).catch(err => {
            console.log(err);
            message.error('Baj van');
        });
    };


    onChange = (info) => {
        console.log('onChange');
        if (info.file.status !== 'uploading') {
            console.log(info.file, info.fileList);
        }
        if (info.file.status === 'done') {
            message.success(`${info.file.name} file uploaded successfully`);
        } else if (info.file.status === 'error') {
            message.error(`${info.file.name} file upload failed.`);
        }

        let fileList = info.fileList;

        this.setState({fileList});

    };


    remove = (file) => {
        console.log(file);
    };

    beforeUpload = (file, fileList) => {
        console.log(file, fileList);
    };


    render() {
        const props = {
            name: 'file',
            action: '/my_api/files/upload',
            onChange: this.onChange,
            multiple: false,
        };

        return (
            <ErrorBoundary>
                <Upload {...props}
                        fileList={this.state.fileList}
                        onRemove={this.remove}
                        beforeUpload={this.beforeUpload}
                        accept={"*"}>
                    <Button>
                        <Icon type="upload"/> Upload
                    </Button>
                </Upload>
                <Button onClick={this.listFiles} type="secondary">List</Button>

                <List
                    header={<div>Content</div>}
                    bordered
                    dataSource={this.state.folderContent}
                    renderItem={(item, i) => (
                        <List.Item><a key={`folderKey_${i}`} href={item.path}>{item.fileName}</a></List.Item>)}
                />

            </ErrorBoundary>
        );
    }
}
