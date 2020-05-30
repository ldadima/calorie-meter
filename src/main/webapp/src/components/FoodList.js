import React from "react";
import {Button, Card, FormControl, InputGroup, Table} from "react-bootstrap";
import axios from 'axios';
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {
    faFastBackward,
    faFastForward,
    faPlusCircle,
    faSearch,
    faStepBackward,
    faStepForward,
    faTimes
} from '@fortawesome/free-solid-svg-icons'
import DialogTitle from "@material-ui/core/DialogTitle";
import DialogContent from "@material-ui/core/DialogContent";
import TextField from "@material-ui/core/TextField";
import DialogActions from "@material-ui/core/DialogActions";
import Dialog from "@material-ui/core/Dialog";
import MyToast from "./MyToast";

export default class FoodList extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            foods: [],
            search: '',
            currentPage : 1,
            foodsPerPage : 10,
            weight: '0',
            open: false,
            foodId: 0,
            show: false,
            message: ''
        };
    }

    componentDidMount() {
        this.findAllFood(this.state.currentPage);
    }

    findAllFood(currentPage) {
        currentPage -= 1;
        axios.get("http://localhost:8080/calorie-meter/food/allFood?page="+currentPage+"&size="+this.state.foodsPerPage)
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
        if(isNaN(currentPage)) {
            currentPage = 0;
        }
        if(currentPage > this.state.totalPages) {
            currentPage = this.state.totalPages;
        }
        axios.get("http://localhost:8080/calorie-meter/food/foodContainsString?subName="+this.state.search+"&page="+currentPage+"&size="+this.state.foodsPerPage)
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

    handleClickOpen = (foodId) =>  {
        this.setState({"foodId": foodId,
            "open" : true});
    };

    handleClose = () => {
        this.setState({"foodId": 0,
            "open" : false});
    };

    addFood = () => {
        const info = {
            login: localStorage.getItem('login'),
            foodId: this.state.foodId,
            weight: this.state.weight
        };
        this.handleClose();
        axios.post("http://localhost:8080/calorie-meter/user/addFood?login="+ info.login + "&foodId="+info.foodId+"&weight="+info.weight)
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
    };

    setWeight = (event) => {
        this.setState({"weight" : event.target.value});
    };


    render() {
        const {foods, currentPage, totalPages, search} = this.state;

        return (
            <div>
                <div style={{"display": this.state.show ? "block" : "none"}} align="right">
                    <MyToast children={{show: this.state.show, message: this.state.message}}/>
                </div>
                <Card>
                    <Card.Header>
                        <div style={{"float":"left"}}>
                            Список Еды
                        </div>
                        <div style={{"float":"right"}}>
                            <InputGroup size="sm">
                                <FormControl placeholder="Поиск" name="search" value={search}
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
                                <th></th>
                                <th>Название</th>
                                <th>Килокалории (на 100 гр.)</th>
                                <th>Калорийность</th>
                            </tr>
                            </thead>
                            <tbody>
                                {
                                    foods.length === 0 ?
                                    <tr align="center">
                                    <td colSpan="4">Еда не найдена</td>
                                    </tr>
                                    : foods.map((food) => (
                                        <tr key={food.id}>
                                            <td align="center"><Button size="sm" variant="success" onClick={this.handleClickOpen.bind(this, food.id)}><FontAwesomeIcon icon={faPlusCircle} /></Button></td>
                                            <td align="left">{food.name}</td>
                                            <td align="left">{food.calories}</td>
                                            <td align="left">{food.level}</td>
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
                <Dialog open={this.state.open} onClose={this.handleClose} aria-labelledby="form-dialog-title">
                    <DialogTitle id="form-dialog-title">Введите вес</DialogTitle>
                    <DialogContent>
                        <TextField
                            onChange={this.setWeight}
                            autoFocus
                            margin="dense"
                            id="name"
                            type="number"
                            InputProps={{ inputProps: { min: 0} }}
                            fullWidth
                        />
                    </DialogContent>
                    <DialogActions>
                        <Button onClick={this.handleClose} color="primary">
                            Отмена
                        </Button>
                        <Button onClick={this.addFood.bind(this)} color="primary">
                            Ок
                        </Button>
                    </DialogActions>
                </Dialog>
            </div>
        );
    }
}