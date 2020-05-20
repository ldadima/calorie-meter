import React from "react";
import {Button, Card, Col, Form} from "react-bootstrap";
import axios from 'axios';
import MyToast from "./MyToast";

export default class Food extends React.Component {
    constructor(props) {
        super(props);
        this.state = this.initialState;
        this.state.show = false;
        this.submitFood = this.submitFood.bind(this);
        this.foodChange = this.foodChange.bind(this);
    }

    initialState = {name:'', calories:'', level:''};

    submitFood(event) {
        event.preventDefault();

        const food = {
            name: this.state.name,
            calories: this.state.calories,
            level: this.state.level
        };

        axios.post("http://localhost:8080//calorie-meter/newFood", food)
            .then(response => {
                if (response.data != null) {
                    this.setState({"show":true});
                    setTimeout(() => this.setState({"show":false}), 3000);
                } else {
                    this.setState({"show":false});
                }
            });

        this.setState(this.initialState);
    }

    foodChange(event){
        this.setState({[event.target.name]:event.target.value});
    }

    render() {
        return (
            <div>
                <div style={{"display":this.state.show ? "block" : "none"}} align="right">
                    <MyToast children = {{show:this.state.show, message:"Добавлено в базу данных"}}/>
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
                                    <Form.Control required autoComplete="off" type="text" name="calories"
                                                  value={this.state.calories}
                                                  onChange={this.foodChange}
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