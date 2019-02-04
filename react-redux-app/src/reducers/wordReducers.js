const wordsReducerDefaultState = [];

export default (state = wordsReducerDefaultState, action) => {
    switch (action.type) {
        case 'GET_WORDs':
            return action.words;
        default:
            return state;
    }
};