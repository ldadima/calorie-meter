import React from "react";
import {Card, Table} from "react-bootstrap";
import axios from 'axios';

export default class FoodList extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            foods: []
        };
    }

    componentDidMount() {
        this.findAllFood();
    }

    findAllFood() {
        axios.get("http://localhost:8080//calorie-meter/allFood")
            .then(response => response.data)
            .then((data) => {
                this.setState({foods: data});
            });
    }

    render() {
        return (
            <Card>
                <Card.Header>Список Еды</Card.Header>
                <Card.Body>
                    <Table>
                        <thead>
                        <tr>
                            <th align="center">Название</th>
                            <th align="center">Калории (на 100 гр.)</th>
                            <th align="center">Калорийность</th>
                        </tr>
                        </thead>
                        <tbody>
                            {this.state.foods.length === 0 ?
                                <tr align="center">
                                <td colSpan="3">Еда не найдена</td>
                                </tr>
                                : this.state.foods.map((food) => (
                                    <tr key={food.id}>
                                        <td>{food.name}</td>
                                        <td>{food.calories}</td>
                                        <td>{food.level}</td>
                                    </tr>
                                ))
                            }
                        </tbody>
                    </Table>
                </Card.Body>
            </Card>
        );
    }
}