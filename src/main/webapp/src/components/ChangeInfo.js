import React from "react";
import {Button, Card, Col, Form} from "react-bootstrap";
import axios from 'axios';
import MyToast from "./MyToast";

export default class ChangeInfo extends React.Component {
    constructor(props) {
        super(props);
        this.state = this.initialState;
        this.state.show = false;
        this.submitInfo = this.submitInfo.bind(this);
        this.infoChange = this.infoChange.bind(this);
    }

    initialState = {weight:'', height:''};

    submitInfo(event) {
        event.preventDefault();

        const info = {
            login: localStorage.getItem('login'),
            weight: this.state.weight,
            height: this.state.height
        };

        axios.put("http://localhost:8080/user/changeInfo", info)
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

    infoChange(event){
        this.setState({[event.target.name]:event.target.value});
    }

    render() {
        return (
            <div>
                <div style={{"display":this.state.show ? "block" : "none"}} align="right">
                    <MyToast children = {{show:this.state.show, message:"Данные изменены"}}/>
                </div>
                <Card>
                    <Card.Header>Изменить данные</Card.Header>
                    <Form onSubmit={this.submitInfo} id="infoFormId">
                        <Card.Body>
                            <Form.Row>
                                <Form.Group as={Col} controlId="formGridWeight">
                                    <Form.Label>Вес</Form.Label>
                                    <Form.Control required autoComplete="off" type="text" name="weight"
                                                  value={this.state.weight}
                                                  onChange={this.infoChange}
                                                  placeholder="Введите свой вес"/>
                                </Form.Group>
                                <Form.Group as={Col} controlId="formGridHeight">
                                    <Form.Label>Рост</Form.Label>
                                    <Form.Control required autoComplete="off" type="text" name="height"
                                                  value={this.state.height}
                                                  onChange={this.infoChange}
                                                  placeholder="Введите свой рост"/>
                                </Form.Group>
                            </Form.Row>
                        </Card.Body>
                        <Card.Footer style={{"textAlign": "right"}}>
                            <Button size="sm" variant="primary" type="submit">
                                Изменить
                            </Button>
                        </Card.Footer>
                    </Form>
                </Card>
            </div>
        );
    }
}