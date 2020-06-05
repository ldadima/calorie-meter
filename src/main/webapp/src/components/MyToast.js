import React, {Component} from "react";
import {Toast} from "react-bootstrap";

export default class MyToast extends Component{
    render() {
        const toastCss = {
            position: 'fixed',
            top: '10px',
            right: '10px',
            zIndex:'1',
            boxShadow: '0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19)'
        };

        return (
            <div style={this.props.show ? toastCss : null}>
                <Toast className={"border border-info bg-info text-white"} show={this.props.show}>
                    <Toast.Body>
                        {this.props.message}
                    </Toast.Body>
                </Toast>
            </div>
        );
    };
}