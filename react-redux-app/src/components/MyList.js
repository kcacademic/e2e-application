import React from 'react';
import Word from './Word';

const MyList = (props) => (
    <div>
        <div style={{display: 'flex',  justifyContent:'center', alignItems:'center'}}>
            <ul>
                {props.words.map(word => {
                    return (
                        <li key={word.word}>
                            <Word {...word} />
                        </li>
                    );
                })}
            </ul>
        </div>
    </div>
);

export default MyList;