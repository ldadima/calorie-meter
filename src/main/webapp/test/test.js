import React from 'react';
import {describe, it, beforeAll} from '@jest/globals';
import App from "../src/App";
import LogOut from "../src/components/LogOut";
import assert from 'chai';

import {configure} from 'enzyme';
import Adapter from 'enzyme-adapter-react-16';
configure({adapter: new Adapter()});

describe('App Component', () => {
    let app;
    beforeAll(() => {
        app = new App();
    });
    it('App Behaviour', () => {
        assert.isFalse(app.checkAuth());
    });
});

describe('LogOut Component', () => {
    let logout;
    beforeAll(() => {
        logout = new LogOut();
        logout.handleClick();
    });
    it('LogOut Behaviour', () => {
        assert.equal(localStorage.getItem('login'), null);
        assert.equal(localStorage.getItem('password'), null);
    });
});
