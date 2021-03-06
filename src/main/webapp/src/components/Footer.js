import React, {Component} from 'react';

import {Col, Container, Navbar} from 'react-bootstrap';

export default class Footer extends Component {
    render() {
        let fullYear = new Date().getFullYear();

        return (
            <Navbar fixed="bottom" bg="light" variant="light">
                <Container>
                    <Col lg={12} className="text-center text-muted">
                        <div>{fullYear}-{fullYear+1}, All Rights Reserved by FIT</div>
                    </Col>
                </Container>
            </Navbar>
        );
    }
}