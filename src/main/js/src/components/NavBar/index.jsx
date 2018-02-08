import React from 'react';
import {Icon, Menu} from 'antd';
import history from '../../misc/history';

const SubMenu = Menu.SubMenu;
const MenuItemGroup = Menu.ItemGroup;


export default class NavBar extends React.Component {

    state = {
        current: 'home',
    };


    handleClick = (e) => {
        const key = e.key;

        console.log('click ', e);
        this.setState({
            current: key,
        });

        switch (key) {

            case 'home':
                history.push('/');
                break;

            case 'page1':
                history.push('/react/page1');
                break;

            case 'page2':
                history.push('/react/page2');
                break;

            case 'page3':
                history.push('/react/page3');
                break;

            case 'page4':
                history.push('/react/page4');
                break;

            case 'page5':
                history.push('/react/page5');
                break;


            case 'page6':
                history.push('/react/page6');
                break;


            case 'page7':
                history.push('/react/page7');
                break;

            default:
                history.push(`/404`);
                break;
        }
    };


    render() {
        return (
            <Menu
                onClick={this.handleClick}
                selectedKeys={[this.state.current]}
                mode="horizontal"
            >

                <Menu.Item key="home">
                    <Icon type="home"/>Home
                </Menu.Item>

                <Menu.Item key="page1">
                    <Icon type="dashboard"/>Page 1
                </Menu.Item>

                <Menu.Item key="page2">
                    <Icon type="picture"/>Page 2
                </Menu.Item>

                <Menu.Item key="page3">
                    <Icon type="desktop"/>Page 3
                </Menu.Item>

                <Menu.Item key="page4">
                    <Icon type="lock"/>Page 4
                </Menu.Item>

                <Menu.Item key="page5">
                    <Icon type="file-pdf"/>Page 5
                </Menu.Item>

                <Menu.Item key="page6">
                    <Icon type="file-pdf"/>Page 6
                </Menu.Item>

                <Menu.Item key="page7">
                    <Icon type="file-pdf"/>Page 7
                </Menu.Item>



                <SubMenu title={<span><Icon type="setting"/>Navigation Three - Submenu</span>}>
                    <MenuItemGroup title="Item 1">
                        <Menu.Item key="setting:1">Option 1</Menu.Item>
                        <Menu.Item key="setting:2">Option 2</Menu.Item>
                    </MenuItemGroup>
                    <MenuItemGroup title="Item 2">
                        <Menu.Item key="setting:3">Option 3</Menu.Item>
                        <Menu.Item key="setting:4">Option 4</Menu.Item>
                    </MenuItemGroup>

                    <MenuItemGroup title="Item 3">
                        <SubMenu key="sub3" title="Submenu">
                            <Menu.Item key="7">Option 7</Menu.Item>
                            <Menu.Item key="8">Option 8</Menu.Item>
                        </SubMenu>
                    </MenuItemGroup>

                </SubMenu>

                <Menu.Item key="logout">
                    <a href="/logout" target="_blank" rel="noopener noreferrer">
                    <Icon type="logout"/>Logout</a>
                </Menu.Item>

            </Menu>
        );
    }

}



