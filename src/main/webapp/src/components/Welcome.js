import React from "react";
import {Jumbotron} from "react-bootstrap";

class Welcome extends React.Component {
    render() {
        return (
            <Jumbotron>
                <h1>Добро пожаловать!</h1>
                <p>
                    Вы зашли на сайт, который поможет вам похудеть! <br></br>Следите за использованными калориями и не дайте себе переедать. <br></br>Удачи в ваших полезных начинаниях!
                    <br></br>Разработчики:<br></br>
                    Линевич Дмитрий Александрович -> d.linevich@g.nsu.ru<br></br>
                    Щеголева Ангелина Ивановна -> a.shshegoleva1@g.nsu.ru
                </p>
            </Jumbotron>
        );
    }
}

export default Welcome;