import React from 'react';
import { connect } from 'react-redux';
import MyCloud from './MyCloud';
import MyList from './MyList';

const WordDisplay = (props) => (
    <div>
        <div>
            <MyCloud words={props.words}/>
        </div>
        <div>
            <MyList words={props.words}/>
        </div>
    </div>
);

const mapStateToProps = (state) => {
    return {
        words: state
    };
}

export default connect(mapStateToProps)(WordDisplay);