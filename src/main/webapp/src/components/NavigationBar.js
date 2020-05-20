import React from "react";
import {Navbar, Nav} from 'react-bootstrap';
import {Link} from "react-router-dom";

class NavigationBar extends React.Component{
    render() {
        return (
            <Navbar bg="light" variant="light">
                <Link to={""} className="navbar-brand">
                    <img src="https://sun9-20.userapi.com/YRCLG0nl7t68DntVXrXGaBut9z4gWIRgpsrylg/1YGLOAWFMbc.jpg" width="25" height="25" alt="brand"/>              Счетчик калорий FIT-edition
                </Link>
                <Nav className="mr-auto">
                    <Link to={"list"} className="nav-link">Список еды</Link>
                    <Link to={"add"} className="nav-link">Добавить новую еду</Link>
                    <Link to={"change"} className="nav-link">Изменить данные</Link>
                </Nav>
            </Navbar>
        );
    }
}
export default NavigationBar;