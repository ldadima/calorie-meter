import React from "react";
import {Button, Card, Col, Form} from "react-bootstrap";
import axios from 'axios';
import MyToast from "./MyToast";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import moment from 'moment';

export default class CreateUser extends React.Component {
    constructor(props) {
        super(props);
        this.state = this.initialState;
        this.state.show = false;
        this.state.message = '';
        this.submitUser = this.submitUser.bind(this);
        this.userChange = this.userChange.bind(this);
        this.changeGender = this.changeGender.bind(this);
    }

    initialState = {
        login: '',
        password: '',
        birthday: new Date(),
        weight: '',
        height: '',
        gender: ''
    };

    submitUser(event) {
        event.preventDefault();

        const info = {
            login: this.state.login,
            password: this.state.password,
            birthday: moment(this.state.birthday).format('YYYY-MM-DD'),
            weight: this.state.weight,
            height: this.state.height,
            gender: this.state.gender
        };
        axios.post("http://35.220.211.12:8080/calorie-meter/user/create", info)
            .then(response => {
                this.setState({
                    "show": true,
                    "message": 'Регистрация успешна'
                });
                localStorage.setItem('login', info.login);
                localStorage.setItem('password', info.password);
                window.location.assign("http://35.220.211.12:3000/")
            })
            .catch(error => {
                this.setState({
                    "show": true,
                    "message": error.response.status === 400 ? error.response.data.errors[0].defaultMessage : error.response.data
                });
            });
        setTimeout(() => this.setState({"show":false}), 3000);
        this.setState(this.initialState);
    }

    userChange(event){
        this.setState({[event.target.name]:event.target.value});
    }

    handleChange = date => {
        this.setState({
            "birthday": date
        });
    };

    changeGender = (e) => {
        this.setState({
            "gender": e.target.value
        })
    }

    render() {
        return (
            <div>
                <div style={{"display": this.state.show ? "block" : "none"}} align="right">
                    <MyToast show={this.state.show} message={this.state.message}/>
                </div>
                <Card>
                    <Card.Header>Создать аккаунт</Card.Header>
                    <Form onSubmit={this.submitUser} id="userFormId">
                        <Card.Body>
                            <Form.Row>
                                <Form.Group as={Col} md="4" controlId="formGridLogin">
                                    <Form.Label>Логин</Form.Label>
                                    <Form.Control required autoComplete="off" type="text" name="login"
                                                  value={this.state.login}
                                                  onChange={this.userChange}
                                                  placeholder="Введите логин"/>
                                </Form.Group>
                                <Form.Group as={Col} md="4" controlId="formGridPassword">
                                    <Form.Label>Пароль</Form.Label>
                                    <Form.Control required autoComplete="off" type="text" name="password"
                                                  value={this.state.password}
                                                  onChange={this.userChange}
                                                  placeholder="Введите пароль"/>
                                </Form.Group>
                                <Form.Group as={Col} md="2" controlId="formGridBirthday">
                                    <Form.Label>Дата Рождения</Form.Label>
                                    <DatePicker
                                        selected={this.state.birthday}
                                        onChange={this.handleChange}
                                    />
                                </Form.Group>
                                <Form.Group as={Col} md="20" controlId="formGridGender" required autoComplete="off">
                                    <Form.Label column="none">Пол</Form.Label>
                                    <input type="radio" value="М" checked={this.state.gender === "М"} onChange={this.changeGender} />Мужской
                                    <input type="radio" value="Ж" checked={this.state.gender === "Ж"} onChange={this.changeGender} />Женский
                                </Form.Group>
                                <Form.Group as={Col} md="4" controlId="formGridWeight">
                                    <Form.Label>Вес</Form.Label>
                                    <Form.Control required autoComplete="off" type="number" name="weight"
                                                  value={this.state.weight}
                                                  onChange={this.userChange}
                                                  min="0"
                                                  placeholder="Введите вес в килограммах"/>
                                </Form.Group>
                                <Form.Group as={Col} md="4" controlId="formGridHeight">
                                    <Form.Label>Рост</Form.Label>
                                    <Form.Control required autoComplete="off" type="number" name="height"
                                                  value={this.state.height}
                                                  onChange={this.userChange}
                                                  min="0"
                                                  placeholder="Введите рост в сантиметрах"/>
                                </Form.Group>
                            </Form.Row>
                        </Card.Body>
                        <Card.Footer style={{"textAlign": "right"}}>
                            <Button size="sm" variant="primary" type="submit">
                                Создать
                            </Button>
                        </Card.Footer>
                    </Form>
                </Card>
            </div>
        );
    }
}