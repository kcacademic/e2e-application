import axios from '../axios/axios';

const _getWords = (words) => ({
    type: 'GET_WORDs',
    words
});

export const getWords = () => {
    return (dispatch) => {
        return axios.get('words').then(result => {
            const words = [];
			console.log(result.data);
            result.data.forEach(item => {
                words.push(item);
            });

            dispatch(_getWords(words));
        });
    };
};