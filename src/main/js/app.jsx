import React, {Component} from 'react';
import ReactDOM from 'react-dom';
import './app.css';
import axios from 'axios';

const wrapper = {
    margin: '15px',
};

const row = {
    display: 'flex',
    flexDirection: 'row',
    flexWrap: 'wrap',
    width: '100%',
    marginTop: '15px',
    marginBottom: '15px'
};

const column = {
    display: 'flex',
    flexDirection: 'column',
    flexBasis: '100%',
    flex: 1,
    marginLeft: '15px',
    marginRight: '15px'
};

const border = {
    borderStyle: 'solid'
};

const margin = {margin: '15px'};

const AnotherBeer = ({changeBeer}) => (
    <div style={{
        ...row,
        ...border,
        display: 'flex',
        justifyContent: 'space-between'
    }}>
        <h1 style={margin}>The random beer app</h1>
        <button style={margin} onClick={changeBeer}>
            Show Another Beer
        </button>
    </div>
);
const BeerDetails = ({beer}) => (
    <div style={row}>
        <div style={column}>
            <div style={{...row, ...border}}>
                <h1 style={{...row, ...margin}}>Beer Details</h1>
                <h2 style={{...row, ...margin}}>Name: {beer.name}</h2>
                <h2 style={{...row, ...margin}}>Alcohol Percentage: {beer.alcoholPercentage}</h2>
                <h2 style={{...row, ...margin}}>Beer Description: {beer.description}</h2>
            </div>
        </div>
        <div style={column}>
            <div style={{...row, ...border}}>
                <h1 style={{...row, ...margin}}>Brewery Details</h1>
                {
                    beer.brewery ?
                        <div>
                            <h2 style={{...row, ...margin}}>Name: {beer.brewery.name}</h2>
                            <h2 style={{...row, ...margin}}>Location: {beer.brewery.location}</h2>
                        </div> :
                        <div/>
                }
            </div>
        </div>
    </div>
);

class App extends Component {
    state = {
        beer: {}
    };

    componentWillMount = () => {
        this.changeBeer();
    };

    changeBeer = () => {
        axios.get('api/beer/random').then((response) => {
            this.setState({beer: response.data});
        })
    };

    render() {

        return (
            <div style={wrapper}>
                <AnotherBeer changeBeer={this.changeBeer}/>
                <BeerDetails beer={this.state.beer || {}}/>
            </div>
        );
    }
}

ReactDOM.render(
    <App/>,
    document.getElementById('app')
);
