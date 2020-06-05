import React from "react";
import {Button, Card, FormControl, InputGroup, Table} from "react-bootstrap";
import axios from 'axios';
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {
    faFastBackward,
    faFastForward,
    faStepBackward,
    faStepForward, faTimes,
    faTrash
} from '@fortawesome/free-solid-svg-icons'
import MyToast from "./MyToast";

export default class EatenList extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            foods: [],
            currentPage: 1,
            foodsPerPage: 10,
            show: false,
            message: '',
            currentCalorie: 0,
            normalCalorie: 0
        };
    }

    componentDidMount() {
        this.findAllFood(this.state.currentPage);
        this.setNormalCalorie();

    }

    setNormalCalorie() {
        axios.get("http://35.220.211.12:8080/calorie-meter/user/calculateNorm?login=" + localStorage.getItem('login'))
            .then(response => response.data)
            .then((data) => {
                this.setState({
                    normalCalorie: data
                });
            });
    }

    findAllFood(currentPage) {
        currentPage -= 1;
        axios.get("http://35.220.211.12:8080/calorie-meter/user/userFoods?page=" + currentPage + "&size=" + this.state.foodsPerPage + "&login=" + localStorage.getItem('login'))
            .then(response => response.data)
            .then((data) => {
                this.setState({
                    foods: data.page.content,
                    currentCalorie: data.calories,
                    totalPages: data.page.totalPages,
                    totalElements: data.page.totalElements,
                    currentPage: data.page.number + 1
                });
            });
    }

    changePage = event => {
        let targetPage = parseInt(event.target.value);
        if (isNaN(targetPage)) {
            targetPage = this.state.currentPage;
        }
        if (targetPage > this.state.totalPages) {
            targetPage = this.state.totalPages;
        }
        this.findAllFood(targetPage);
        this.setState({
            [event.target.name]: targetPage
        });
    };

    firstPage = () => {
        let firstPage = 1;
        if (this.state.currentPage > firstPage) {
            this.findAllFood(firstPage);
        }
    };

    prevPage = () => {
        let prevPage = 1;
        if (this.state.currentPage > prevPage) {
            this.findAllFood(this.state.currentPage - prevPage);
        }
    };

    lastPage = () => {
        let condition = Math.ceil(this.state.totalElements / this.state.foodsPerPage);
        if (this.state.currentPage < condition) {
            this.findAllFood(condition);
        }
    };

    nextPage = () => {
        if (this.state.currentPage < Math.ceil(this.state.totalElements / this.state.foodsPerPage)) {
            this.findAllFood(this.state.currentPage + 1);
        }
    };

    deleteFood = (foodId) => {
        axios.delete("http://35.220.211.12:8080/calorie-meter/user/deleteFood/?login=" + localStorage.getItem('login') + "&food=" + foodId)
            .then(() => {
                this.setState({
                    "show": true,
                    "message": 'Удалено успешно'
                });
                this.findAllFood(this.state.currentPage);
                setTimeout(() => this.setState({"show": false}), 3000);
            })
    };

    deleteAll = () => {
        axios.delete("http://35.220.211.12:8080/calorie-meter/user/deleteAll/?login=" + localStorage.getItem('login'))
            .then(() => {
                this.setState({
                    "show": true,
                    "message": 'Удалено успешно'
                });
                this.findAllFood(this.state.currentPage);
                setTimeout(() => this.setState({"show": false}), 3000);
            })
    }

    render() {
        const {foods, currentPage, totalPages} = this.state;
        return (
            <div>
                <div style={{"display": this.state.show ? "block" : "none"}} align="right">
                    <MyToast children={{show: this.state.show, message: this.state.message}}/>
                </div>
                <Card>
                    <Card.Header>
                        <div style={{"float": "left"}}>
                            Список Съеденной Еды
                        </div>
                        <div style={{"float": "right"}}>
                            Килокалорий {this.state.currentCalorie}/{this.state.normalCalorie}
                        </div>
                    </Card.Header>
                    <Card.Body>
                        <Table>
                            <thead>
                            <tr>
                                <th>Название</th>
                                <th>Вес</th>
                                <th>Калорийность</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            {
                                foods.length === 0 ?
                                    <tr align="center">
                                        <td colSpan="4">Список пуст</td>
                                    </tr>
                                    : foods.map((food) => (
                                        <tr key={food.id}>
                                            <td align="left">{food.name}</td>
                                            <td align="left">{food.weight}</td>
                                            <td align="left">{food.calories}</td>
                                            <td align="left"><Button size="sm" variant="outline-danger"
                                                                       onClick={this.deleteFood.bind(this, food.id)}><FontAwesomeIcon
                                                icon={faTimes}/></Button></td>
                                        </tr>
                                    ))
                            }
                            </tbody>
                        </Table>
                        <div style={{"float": "right"}}>
                            <Button type="button" variant="danger"
                                            disabled={this.state.currentCalorie === 0}
                                            onClick={this.deleteAll.bind(this)}>
                                        <FontAwesomeIcon icon={faTrash}/> Удалить Все
                            </Button>
                        </div>
                    </Card.Body>
                    {
                        foods.length > 0 ?
                            <Card.Footer>
                                <div style={{"float": "left"}}>
                                    Страница {currentPage} из {totalPages}
                                </div>
                                <div style={{"float": "right"}}>
                                    <InputGroup size="sm">
                                        <InputGroup.Prepend>
                                            <Button type="button" variant="outline-info"
                                                    disabled={currentPage === 1 ? true : false}
                                                    onClick={this.firstPage}>
                                                <FontAwesomeIcon icon={faFastBackward}/> First
                                            </Button>
                                            <Button type="button" variant="outline-info"
                                                    disabled={currentPage === 1 ? true : false}
                                                    onClick={this.prevPage}>
                                                <FontAwesomeIcon icon={faStepBackward}/> Prev
                                            </Button>
                                        </InputGroup.Prepend>
                                        <FormControl name="currentPage" value={currentPage}
                                                     onChange={this.changePage}/>
                                        <InputGroup.Append>
                                            <Button type="button" variant="outline-info"
                                                    disabled={currentPage === totalPages ? true : false}
                                                    onClick={this.nextPage}>
                                                <FontAwesomeIcon icon={faStepForward}/> Next
                                            </Button>
                                            <Button type="button" variant="outline-info"
                                                    disabled={currentPage === totalPages ? true : false}
                                                    onClick={this.lastPage}>
                                                <FontAwesomeIcon icon={faFastForward}/> Last
                                            </Button>
                                        </InputGroup.Append>
                                    </InputGroup>
                                </div>
                            </Card.Footer> : null
                    }
                </Card>
            </div>
        );
    }
}