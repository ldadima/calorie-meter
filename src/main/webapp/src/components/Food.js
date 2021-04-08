import React from "react";
import {Button, Card, Col, Form} from "react-bootstrap";
import axios from 'axios';
import MyToast from "./MyToast";

export default class Food extends React.Component {
    constructor(props) {
        super(props);
        this.state = this.initialState;
        this.state.show = false;
        this.state.message = '';
        this.submitFood = this.submitFood.bind(this);
        this.foodChange = this.foodChange.bind(this);
    }

    initialState = {name:'', calories:'', level:null};

    submitFood(event) {
        event.preventDefault();

        const food = {
            name: this.state.name,
            calories: this.state.calories,
            level: this.state.level
        };

        axios.post("http://localhost:8080/calorie-meter/food/newFood", food)
            .then(response => {
                this.setState({
                    "show": true,
                    "message": 'Добавлено успешно'
                });
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

    foodChange(event){
        this.setState({[event.target.name]:event.target.value});
    }

    render() {
        return (
            <div>
                <div style={{"display": this.state.show ? "block" : "none"}} align="right">
                    <MyToast show={this.state.show} message={this.state.message}/>
                </div>
                <Card>
                    <Card.Header>Добавить еду</Card.Header>
                    <Form onSubmit={this.submitFood} id="foodFormId">
                        <Card.Body>
                            <Form.Row>
                                <Form.Group as={Col} controlId="formGridTitle">
                                    <Form.Label>Название</Form.Label>
                                    <Form.Control required autoComplete="off" type="text" name="name"
                                                  value={this.state.name}
                                                  onChange={this.foodChange}
                                                  placeholder="Введите название еды"/>
                                </Form.Group>
                                <Form.Group as={Col} controlId="formGridCalorie">
                                    <Form.Label>Килокалории</Form.Label>
                                    <Form.Control required autoComplete="off" type="number" name="calories"
                                                  value={this.state.calories}
                                                  onChange={this.foodChange}
                                                  min="0"
                                                  placeholder="Введите количество килокалорий на 100 грамм"/>
                                </Form.Group>
                            </Form.Row>
                        </Card.Body>
                        <Card.Footer style={{"textAlign": "right"}}>
                            <Button size="sm" variant="primary" type="submit">
                                Добавить
                            </Button>
                        </Card.Footer>
                    </Form>
                </Card>
            </div>
    )
        ;
    }
}