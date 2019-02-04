import { createStore, applyMiddleware } from "redux";
import words from '../reducers/wordReducers';
import thunk from 'redux-thunk';

export default () => {
    return createStore(words, applyMiddleware(thunk));
};