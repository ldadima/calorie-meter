import React, {Component} from 'react';
import NavigationBar from "./components/NavigationBar";
import Welcome from "./components/Welcome";
import {Col, Container, Row} from 'react-bootstrap';
import Footer from "./components/Footer";
import Food from "./components/Food";
import FoodList from "./components/FoodList";
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import ChangeInfo from "./components/ChangeInfo";
import axios from "axios";
import AuthRoute from "./components/AuthRoute";
import LogIn from "./components/LogIn";
import EatenList from "./components/EatenList";
import AuthNavBar from "./components/AuthNavBar";
import CreateUser from "./components/CreateUser";
import LogOut from "./components/LogOut";

export default class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            userLoggedIn: false,
            time: 0
        }
    }

    checkAuth() {
        if (this.state.time < 3000 && this.state.userLoggedIn) {
            return this.state.userLoggedIn;
        }
        const login = localStorage.getItem('login');
        const password = localStorage.getItem('password');
        const info = {
            login: login,
            password: password
        }
        axios.post("http://127.0.0.1:8080/calorie-meter/user/login", info)
            .then((response) => {
                this.setState({
                    userLoggedIn: response.data
                });
            })
        return this.state.userLoggedIn;
    }

    render() {
        return (
            <Router>
                <AuthRoute exact={true} trueComponent={NavigationBar} falseComponent={AuthNavBar}
                           decisionFunc={this.checkAuth.bind(this)}/>
                <Container>
                    <Row>
                        <Col lg={12} className={"margin-top"}>
                            <Switch>
                                <Route path="/" exact component={Welcome}/>
                                <AuthRoute path="/list" exact={true} trueComponent={FoodList} falseComponent={LogIn}
                                           decisionFunc={this.checkAuth.bind(this)}/>
                                <AuthRoute path="/add" exact={true} trueComponent={Food} falseComponent={LogIn}
                                           decisionFunc={this.checkAuth.bind(this)}/>
                                <AuthRoute path="/change" exact={true} trueComponent={ChangeInfo}
                                           falseComponent={LogIn} decisionFunc={this.checkAuth.bind(this)}/>
                                <AuthRoute path="/look" exact={true} trueComponent={EatenList}
                                           falseComponent={LogIn} decisionFunc={this.checkAuth.bind(this)}/>
                                <AuthRoute path="/close" exact={true} trueComponent={LogOut}
                                           falseComponent={LogIn} decisionFunc={this.checkAuth.bind(this)}/>
                                <Route path="/create" exact component={CreateUser}/>
                                <Route path="/login" exact component={LogIn}/>
                            </Switch>
                        </Col>
                    </Row>
                </Container>
                <Footer/>
            </Router>
        );
    }
}
