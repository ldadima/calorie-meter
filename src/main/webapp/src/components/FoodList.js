import React from "react";
import {Card, Table, Button, InputGroup, FormControl} from "react-bootstrap";
import axios from 'axios';
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faSearch, faTimes, faFastForward, faStepBackward, faStepForward, faFastBackward} from '@fortawesome/free-solid-svg-icons'
import './Style.css';

export default class FoodList extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            foods: [],
            search: '',
            currentPage : 1,
            foodsPerPage : 10
        };
    }

    componentDidMount() {
        this.findAllFood(this.state.currentPage);
    }

    findAllFood(currentPage) {
        currentPage -= 1;
        axios.get("http://localhost:8080/calorie-meter/allFood?page="+currentPage+"&size="+this.state.foodsPerPage)
            .then(response => response.data)
            .then((data) => {
                this.setState({
                    foods: data.content,
                    totalPages: data.totalPages,
                    totalElements: data.totalElements,
                    currentPage: data.number + 1
                });
            });
    }

    changePage = event => {
        let targetPage = parseInt(event.target.value);
        if(isNaN(targetPage)) {
            targetPage = this.state.currentPage;
        }
        if(targetPage > this.state.totalPages) {
            targetPage = this.state.totalPages;
        }
        if(this.state.search) {
            this.searchData(targetPage);
        } else {
            this.findAllFood(targetPage);
        }
        this.setState({
            [event.target.name]: targetPage
        });
    };

    firstPage = () => {
        let firstPage = 1;
        if(this.state.currentPage > firstPage) {
            if(this.state.search) {
                this.searchData(firstPage);
            } else {
                this.findAllFood(firstPage);
            }
        }
    };

    prevPage = () => {
        let prevPage = 1;
        if(this.state.currentPage > prevPage) {
            if(this.state.search) {
                this.searchData(this.state.currentPage - prevPage);
            } else {
                this.findAllFood(this.state.currentPage - prevPage);
            }
        }
    };

    lastPage = () => {
        let condition = Math.ceil(this.state.totalElements / this.state.foodsPerPage);
        if(this.state.currentPage < condition) {
            if(this.state.search) {
                this.searchData(condition);
            } else {
                this.findAllFood(condition);
            }
        }
    };

    nextPage = () => {
        if(this.state.currentPage < Math.ceil(this.state.totalElements / this.state.foodsPerPage)) {
            if(this.state.search) {
                this.searchData(this.state.currentPage + 1);
            } else {
                this.findAllFood(this.state.currentPage + 1);
            }
        }
    };

    searchChange = event => {
        this.setState({
            [event.target.name] : event.target.value
        });
    };

    cancelSearch = () => {
        this.setState({"search" : ''});
        this.findAllFood(this.state.currentPage);
    };

    searchData = (currentPage) => {
        currentPage -= 1;
        axios.get("http://localhost:8080//calorie-meter/allFood/search/"+this.state.search+"?page="+currentPage+"&size="+this.state.foodsPerPage)
            .then(response => response.data)
            .then((data) => {
                this.setState({
                    foods: data.content,
                    totalPages: data.totalPages,
                    totalElements: data.totalElements,
                    currentPage: data.number + 1
                });
            });
    };

    render() {
        const {foods, currentPage, totalPages, search} = this.state;

        return (
            <div>
                <Card>
                    <Card.Header>
                        <div style={{"float":"left"}}>
                            Список Еды
                        </div>
                        <div style={{"float":"right"}}>
                            <InputGroup size="sm">
                                <FormControl placeholder="Search" name="search" value={search}
                                             onChange={this.searchChange}/>
                                <InputGroup.Append>
                                    <Button size="sm" variant="outline-info" type="button" onClick={this.searchData}>
                                        <FontAwesomeIcon icon={faSearch}/>
                                    </Button>
                                    <Button size="sm" variant="outline-danger" type="button" onClick={this.cancelSearch}>
                                        <FontAwesomeIcon icon={faTimes} />
                                    </Button>
                                </InputGroup.Append>
                            </InputGroup>
                        </div>
                    </Card.Header>
                    <Card.Body>
                        <Table>
                            <thead>
                            <tr>
                                <th align="center">Название</th>
                                <th align="center">Килокалории (на 100 гр.)</th>
                                <th align="center">Калорийность</th>
                            </tr>
                            </thead>
                            <tbody>
                                {
                                    foods.length === 0 ?
                                    <tr align="center">
                                    <td colSpan="3">Еда не найдена</td>
                                    </tr>
                                    : foods.map((food) => (
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
                    {
                        foods.length > 0 ?
                        <Card.Footer>
                            <div style={{"float":"left"}}>
                                Страница {currentPage} из {totalPages}
                            </div>
                            <div style={{"float":"right"}}>
                                <InputGroup size="sm">
                                    <InputGroup.Prepend>
                                        <Button type="button" variant="outline-info" disabled={currentPage === 1 ? true : false}
                                                onClick={this.firstPage}>
                                            <FontAwesomeIcon icon={faFastBackward} /> First
                                        </Button>
                                        <Button type="button" variant="outline-info" disabled={currentPage === 1 ? true : false}
                                                onClick={this.prevPage}>
                                            <FontAwesomeIcon icon={faStepBackward} /> Prev
                                        </Button>
                                    </InputGroup.Prepend>
                                    <FormControl name="currentPage" value={currentPage}
                                                 onChange={this.changePage}/>
                                    <InputGroup.Append>
                                        <Button type="button" variant="outline-info" disabled={currentPage === totalPages ? true : false}
                                                onClick={this.nextPage}>
                                            <FontAwesomeIcon icon={faStepForward} /> Next
                                        </Button>
                                        <Button type="button" variant="outline-info" disabled={currentPage === totalPages ? true : false}
                                                onClick={this.lastPage}>
                                            <FontAwesomeIcon icon={faFastForward} /> Last
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