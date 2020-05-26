import App from "../App";
import LogOut from "../components/LogOut";

describe('App Component', () => {
    let app;
    beforeAll(() => {
        app = new App();
    });
    it('App Behaviour', () => {
        expect(app.checkAuth()).toEqual(false);
    });
});

