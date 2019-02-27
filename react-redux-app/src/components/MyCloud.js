import React, { Component } from 'react';
import randomColor from 'randomcolor';
import TagCloud from 'react-tag-cloud';
import '../styles/style.css';

const styles = {
  large: {
    fontSize: 60,
    fontWeight: 'bold'
  },
  small: {
    opacity: 0.7,
    fontSize: 16
  }
};

class MyCloud extends Component {
  componentDidMount() {
    setInterval(() => {
      this.forceUpdate();
    }, 300000);
  }

  render() {
	  console.log(this.props.words)
    return (
      <div className='app-outer'>
        <div className='app-inner'>
          <TagCloud 
            className='tag-cloud'
            style={{
              fontFamily: 'sans-serif',
              //fontSize: () => Math.round(Math.random() * 50) + 16,
              fontSize: 30,
              color: () => randomColor({
                hue: 'blue'
              }),
              padding: 5,
            }}>
			        {this.props.words.map(word => {
                return (
				          <li key={word.word}>
					          <div style={{fontSize: (word.count)}}> {word.word} </div>
				          </li>
                );
              })}
          </TagCloud>
        </div>
      </div>
    );
  }
}

export default MyCloud;