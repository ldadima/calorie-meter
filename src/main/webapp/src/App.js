import React from 'react';
import './App.css';
import NavigationBar from "./components/NavigationBar";
import Welcome from "./components/Welcome";
import {Container, Row, Col} from 'react-bootstrap';
import Footer from "./components/Footer";
import Food from "./components/Food";
import FoodList from "./components/FoodList";
import {BrowserRouter as Router, Switch, Route} from 'react-router-dom';

function App() {
    const marginTop = {
        marginTop:"20px"
    };
  return (
    <Router>
        <NavigationBar/>
        <Container>
            <Row>
                <Col lg={12} style={marginTop}>
                    <Switch>
                        <Route path="/" exact component={Welcome}/>
                        <Route path="/list" exact component={FoodList}/>
                        <Route path="/add" exact component={Food}/>
                    </Switch>
                </Col>
            </Row>
        </Container>
        <Footer/>
    </Router>
  );
}

export default App;
