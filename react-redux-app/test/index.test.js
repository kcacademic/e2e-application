import React from 'react';
import renderer from 'react-test-renderer';
import { Provider } from 'react-redux';
import AppRouter from '../src/routers/AppRouter';
import getAppStore from '../src/store/store';

test('Dashboard component with TagCloud rendered.', () => {

    const store = getAppStore();
    const component = renderer.create(
        <Provider store={store}>
            <AppRouter />
        </Provider>
    );
    let tree = component.toJSON();
    expect(tree).toMatchSnapshot();
});