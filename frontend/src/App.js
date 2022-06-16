import React, {Component} from 'react'
import './App.css'
import UserList from "./UserList";
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import GameList from "./GameList";
import Main from "./Main";
import Game from "./Game";

class App extends Component {
    render() {
        return (
            <Router>
                <Switch>
                    <Route path={'/'} exact={true} component={Main}/>
                    <Route path={'/user'} exact={true} component={UserList}/>
                    <Route path={'/user/:id'} component={GameList}/>
                    <Route path={'/game/:id'} component={Game}/>
                </Switch>
            </Router>
        )
    }
}

export default App
