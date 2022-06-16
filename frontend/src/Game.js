import React, {Component} from 'react'
import './App.css'
import {Navbar, NavbarBrand} from "reactstrap"
import {Link, withRouter} from "react-router-dom"


function Greeting(props) {
    const isWine = props.isWine;
    return isWine ? <div> Вы выиграли! </div> : <div> {props.cmb} </div>;
}

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
            value: '',
            isWine: false,
            cmb: ''
        };
        this.handleSubmit = this.handleSubmit.bind(this)
        this.handleChange = this.handleChange.bind(this)
    }

    async componentDidMount() {
        const game = await (await fetch(`/game/${this.props.match.params.id}`)).json();
        this.setState({item: game});
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
        this.state.cmb = buff
        this.state.isWine = buff[0] === "4";
    }

    renderValidate() {
        return <div> Вы выиграли! </div>
    }

    renderNotValidate() {
        return <div> {this.state.cmb} </div>
    }

    render() {
        const {item} = this.state
        console.log(item)
        const isWine = this.state.isWine;
        console.log(this.state.cmb)

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
                <Greeting isWine={isWine}/>
                <p align={"center"}>Результат: {isWine ? this.renderValidate() : this.renderNotValidate()} </p>
            </div>
        )
    }
}

export default withRouter(Game)