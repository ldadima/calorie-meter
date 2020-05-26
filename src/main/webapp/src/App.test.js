
// import {render} from '@testing-library/react';
// import App from './App';

// test('renders learn react link', () => {
//   const { getByText } = render(<App />);
//   const linkElement = getByText(/learn react/i);
//   expect(linkElement).toBeInTheDocument();
// });
import React from 'react';
import {describe, it, beforeAll} from 'jest';
import App from "App";
import LogOut from "/components/LogOut";
import {assert} from 'chai';

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

