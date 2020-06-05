import React from 'react';
import {shallow, configure} from 'enzyme';
import Adapter from 'enzyme-adapter-react-16';
import FoodList from "../components/FoodList";
import Food from "../components/Food";
import Footer from "../components/Footer";
import AuthNavBar from "../components/AuthNavBar";
import NavigationBar from "../components/NavigationBar";
import Welcome from "../components/Welcome";

configure({adapter: new Adapter()});

describe('Food Component', () => {
    it('show', () => {
        const wrapper = shallow(<Food/>);
        expect(wrapper.instance().state.show).toEqual(false);
    });
    it('name', () => {
        const wrapper = shallow(<Food/>);
        expect(wrapper.instance().state.name).toEqual('');
    });
    it('calories', () => {
        const wrapper = shallow(<Food/>);
        expect(wrapper.instance().state.calories).toEqual('');
    });
    it('level', () => {
        const wrapper = shallow(<Food/>);
        expect(wrapper.instance().state.level).toEqual(null);
    });
    it('Button', () => {
        const wrapper = shallow(<Food/>);
        wrapper.find('Button').simulate('click');
        expect(wrapper.find('Button').length).toBe(1);
    });
    it('Form', () => {
        const wrapper = shallow(<Food/>);
        wrapper.find('Form').simulate('submit', { preventDefault () {} });
        expect(wrapper.find('Form').length).toBe(1);
    });
    it('Change Food', () => {
        const wrapper = shallow(<Food/>);
        wrapper.instance().foodChange({target: {name: "name", value: "Test"}})
        expect(wrapper.instance().state.name).toBe("Test");
    });
});

describe('FoodList Component', () => {
    it('foods', () => {
        const wrapper = shallow(<FoodList/>);
        expect(wrapper.instance().state.foods).toEqual([]);
    });
    it('currentPage', () => {
        const wrapper = shallow(<FoodList/>);
        expect(wrapper.instance().state.currentPage).toEqual(1);
    });
    it('foodsPerPage', () => {
        const wrapper = shallow(<FoodList/>);
        expect(wrapper.instance().state.foodsPerPage).toEqual(10);
    });
    it('show', () => {
        const wrapper = shallow(<FoodList/>);
        expect(wrapper.instance().state.show).toEqual(false);
    });
    it('message', () => {
        const wrapper = shallow(<FoodList/>);
        expect(wrapper.instance().state.message).toEqual('');
    });
    it('foodId', () => {
        const wrapper = shallow(<FoodList/>);
        expect(wrapper.instance().state.foodId).toEqual(0);
    });
    it('weight', () => {
        const wrapper = shallow(<FoodList/>);
        expect(wrapper.instance().state.weight).toEqual('0');
    });
    it('search', () => {
        const wrapper = shallow(<FoodList/>);
        expect(wrapper.instance().state.search).toEqual('');
        wrapper.find('FormControl').simulate('change', { target: {value : ""}});
        expect(wrapper.instance().state.search).toEqual("");
        wrapper.instance().searchData(1);
        wrapper.instance().searchData(NaN);
        wrapper.instance().setState({'totalPages': 2});
        wrapper.instance().searchData(10);
        wrapper.instance().cancelSearch();
        expect(wrapper.instance().state.search).toEqual('');
    });
    it('open', () => {
        const wrapper = shallow(<FoodList/>);
        expect(wrapper.instance().state.open).toEqual(false);
    });
    it('first and prev page', () => {
        const wrapper = shallow(<FoodList/>);
        wrapper.instance().firstPage();
        wrapper.instance().prevPage();
        wrapper.instance().setState({'currentPage': 2});
        wrapper.instance().firstPage();
        wrapper.instance().prevPage();
        wrapper.instance().setState({'search': 'Test'});
        wrapper.instance().firstPage();
        wrapper.instance().prevPage();
        expect(wrapper.find('Button').length).toBe(4);
    });
    it('Change Page', () => {
        const wrapper = shallow(<FoodList/>);
        wrapper.instance().setState({'totalPages': 2});
        wrapper.instance().changePage({target: {name: "currentPage", value: 1}});
        expect(wrapper.instance().state.currentPage).toBe(1);
        wrapper.instance().changePage({target: {name: "currentPage", value: NaN}});
        expect(wrapper.instance().state.currentPage).toBe(1);
        wrapper.instance().setState({'search': 'Test'});
        wrapper.instance().changePage({target: {name: "currentPage", value: 10}});
        expect(wrapper.instance().state.currentPage).toBe(2);
    });
    it('Set Weight', () => {
        const wrapper = shallow(<FoodList/>);
        wrapper.instance().setWeight({target: {value: 100}});
        expect(wrapper.instance().state.weight).toBe(100);
    });
    it('last and next page', () => {
        const wrapper = shallow(<FoodList/>);
        wrapper.instance().lastPage();
        wrapper.instance().nextPage();
        wrapper.instance().setState({'currentPage': 1});
        wrapper.instance().setState({'totalElements': 50});
        wrapper.instance().lastPage();
        wrapper.instance().nextPage();
        wrapper.instance().setState({'search': 'Test'});
        wrapper.instance().lastPage();
        wrapper.instance().nextPage();
        expect(wrapper.find('Button').length).toBe(4);
    });
    it('open', () => {
        const wrapper = shallow(<FoodList/>);
        wrapper.instance().handleClickOpen(1);
        expect(wrapper.instance().state.foodId).toEqual(1);
        expect(wrapper.instance().state.open).toEqual(true);
    });
    it('close', () => {
        const wrapper = shallow(<FoodList/>);
        wrapper.instance().handleClose();
        expect(wrapper.instance().state.foodId).toEqual(0);
        expect(wrapper.instance().state.open).toEqual(false);
    });
});

describe('Components', () => {
    it('AuthNavBar', () => {
        const wrapper1 = shallow(<Footer/>);
        const wrapper2 = shallow(<AuthNavBar/>);
        expect(wrapper2.find('Link').length).toBe(3);
    });it('NavigationBar', () => {
        const wrapper = shallow(<NavigationBar/>);
        expect(wrapper.find('Link').length).toBe(6);
    });it('Welcome', () => {
        const wrapper = shallow(<Welcome/>);
        expect(wrapper.find('Jumbotron').length).toBe(1);
    });
});