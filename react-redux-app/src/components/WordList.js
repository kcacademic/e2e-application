import React from 'react';
import { connect } from 'react-redux';
import Word from './Word';
import MyCloud from './MyCloud';

const WordList = (props) => (
    <div>
    <div>
        <MyCloud words={props.words}/>
    </div>
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

const mapStateToProps = (state) => {
    return {
        words: state
    };
}

export default connect(mapStateToProps)(WordList);