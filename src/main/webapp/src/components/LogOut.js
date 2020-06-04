import React from "react";
import {Button, Card} from "react-bootstrap";

export default class LogOut extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            login: localStorage.getItem('login')
        };
        this.handleClick = this.handleClick.bind(this);
    }

    handleClick() {
        localStorage.setItem('login', null);
        localStorage.setItem('password', null);
        window.location.assign("http://" + (process.env.NODE_ENV === "production" ? "10.170.0.2" : "localhost") + ":3000/login")
    }

    render() {
        return (
            <div>
                <Card>
                    <Card.Header>
                        <div style={{"float":"left"}}>
                            Выход
                        </div>
                    </Card.Header>
                    <Card.Body>
                        <div style={{"float":"center"}}>
                            Вы уверены, что хотите выйти из аккаута? ({this.state.login})
                        </div>
                    </Card.Body>
                    <Card.Footer>
                        <Button type="button" variant="outline-info" onClick={this.handleClick}>
                            Выйти
                        </Button>
                    </Card.Footer>
                </Card>
            </div>
        );
    }
}
