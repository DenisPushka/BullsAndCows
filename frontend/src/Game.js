import React, {Component} from 'react'
import './App.css'
import {Navbar, NavbarBrand} from "reactstrap"
import {Link, withRouter} from "react-router-dom"

class Game extends Component {
    emptyItem = {
        gameId: 0,
        trueComb: '',
        combination: [
            {
                combStep: ''
            }
        ]
    };

    constructor(props) {
        super(props)
        this.state = {
            item: this.emptyItem,
            value: ''
        };
        this.addGames = this.addGames.bind(this)
        this.handleSubmit = this.handleSubmit.bind(this)
        this.handleChange = this.handleChange.bind(this)
    }

    async componentDidMount() {
        const game = await (await fetch(`/game/${this.props.match.params.id}`)).json();
        this.setState({item: game});
    }

    // возможно убрать
    async addGames() {
        const games = await (await fetch(`/game`, {
            method: 'GET',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json',
            }
        }).then((response) => {
            return response
                .json()
                .then((data) => {
                    return data
                })
                .catch((err) => {
                    console.log(err)
                })
        }))
        this.setState({item: games});
    }

    async handleChange(event) {
        this.setState({value: event.target.value})
    }

    async handleSubmit(event) {
        event.preventDefault()

        const combination = {combStep: this.state.value}
        console.log(combination)
        const {item} = this.state
        console.log(item)
        console.log(JSON.stringify(combination))
        const buff = await fetch(`/game/addCombination/${item.gameId}`, {
            method: 'POST',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(combination),
        }).then((response) => {
            return response
                .json()
                .then((data) => {
                    return data
                })
                .catch((err) => {
                    console.log(err)
                })
        })

        console.log(buff)
        if (buff[0] === "4") {
            return <div>
                Вы выиграли!
            </div>
        } else return <div>
            {buff}
        </div>

    }


    render() {
        const {item} = this.state
        console.log(item)
        return (
            <div>
                <Navbar color="dark" dark expand="md">
                    <NavbarBrand tag={Link} to="/">Быки и коровы</NavbarBrand>
                </Navbar>
                <form onSubmit={this.handleSubmit}>
                    <label>
                        Игра началась!
                        <div className="input-group">
                            <input
                                type="text"
                                className="form-control"
                                placeholder="Введите комбинацию"
                                aria-label="Username"
                                aria-describedby="input-group-right"
                                value={this.state.value}
                                onChange={this.handleChange}
                            />
                        </div>
                    </label>
                    <input type="submit" value="Enter"/>
                </form>
            </div>
        )
    }
}

export default withRouter(Game)