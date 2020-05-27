import React from "react";
import {Nav, Navbar} from 'react-bootstrap';
import {Link} from "react-router-dom";

export default class NavigationBar extends React.Component{
    render() {
        return (
            <Navbar bg="light" variant="light">
                <Link to={""} className="navbar-brand">
                    <img src="https://sun9-20.userapi.com/YRCLG0nl7t68DntVXrXGaBut9z4gWIRgpsrylg/1YGLOAWFMbc.jpg" width="25" height="25" alt="brand"/>              Счетчик калорий FIT-edition
                </Link>
                <Nav className="mr-auto">
                    <Link to={"list"} className="nav-link">Список еды</Link>
                    <Link to={"look"} className="nav-link">Просмотреть список съеденного</Link>
                    <Link to={"add"} className="nav-link">Создать новую еду</Link>
                    <Link to={"change"} className="nav-link">Изменить данные</Link>
                    <Link to={"close"} className="nav-link">Выйти</Link>
                </Nav>
            </Navbar>
        );
    }
}