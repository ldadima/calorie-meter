import React, {Component} from 'react';
import axios from "axios";
import MyToast from "./MyToast";
import {Button, Card, Col, Form} from "react-bootstrap";

export default class LogIn extends Component {
    constructor(props) {
        super(props);
        this.state = this.initialState;
        this.state.show = false;
        this.state.message = '';
        this.submitLogin = this.submitLogin.bind(this);
        this.loginChange = this.loginChange.bind(this);
    }

    initialState = {
        login: '',
        password: ''
    };

    loginChange(event) {
        this.setState({[event.target.name]: event.target.value});
    }

    submitLogin(event) {
        event.preventDefault();

        const info = {
            login: this.state.login,
            password: this.state.password
        }

        axios.post("http://35.220.211.12:8080/calorie-meter/user/login", info)
            .then(response => {
                this.setState({
                    "show": true,
                    "message": 'Вход успешен'
                });
                localStorage.setItem('login', info.login);
                localStorage.setItem('password', info.password);
                window.location.assign("http://35.220.211.12:3000/")
            })
            .catch(error => {
                this.setState({
                    "show": true,
                    "message": error.response.data
                });
            });
        setTimeout(() => this.setState({"show": false}), 1000);
        this.setState(this.initialState);
    }

    render() {
        return (
            <div>
                <div style={{"display": this.state.show ? "block" : "none"}} align="right">
                    <MyToast show={this.state.show} message={this.state.message}/>
                </div>
                <Card>
                    <Card.Header>Войти</Card.Header>
                    <Form onSubmit={this.submitLogin} id="loginFormId">
                        <Card.Body>
                            <Form.Row>
                                <Form.Group as={Col} controlId="formGridLogin">
                                    <Form.Label>Логин</Form.Label>
                                    <Form.Control required autoComplete="off" type="text" name="login"
                                                  value={this.state.login}
                                                  onChange={this.loginChange}
                                                  placeholder="Введите логин"/>
                                </Form.Group>
                                <Form.Group as={Col} controlId="formGridPassword">
                                    <Form.Label>Пароль</Form.Label>
                                    <Form.Control required autoComplete="off" type="text" name="password"
                                                  value={this.state.password}
                                                  onChange={this.loginChange}
                                                  placeholder="Введите пароль"/>
                                </Form.Group>
                            </Form.Row>
                        </Card.Body>
                        <Card.Footer style={{"textAlign": "right"}}>
                            <Button size="sm" variant="primary" type="submit">
                                Войти
                            </Button>
                        </Card.Footer>
                    </Form>
                </Card>
            </div>
        );
    }
}