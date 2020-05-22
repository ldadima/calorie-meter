import React from 'react';
import {Route} from 'react-router-dom';

export default function AuthRoute({ trueComponent, falseComponent, decisionFunc, ...rest }) {
    return (
        <Route
            {...rest}
            component={decisionFunc() ? trueComponent : falseComponent}
        />
    )
}