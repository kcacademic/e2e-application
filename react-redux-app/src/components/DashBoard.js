import React from 'react';
import WordList from './WordList';

const DashBoard = (props) => {
    console.log(props.WordList);
    return(
        <div className='container__list'>
            <WordList />
        </div>
    )
};

export default DashBoard;