import React from 'react';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';

const Word = ({ word, count }) => (
    <div>
        <h4>{word} ({count})</h4>
    </div>
);

export default connect()(Word);