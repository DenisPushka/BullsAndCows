import React, {Component} from 'react'
import './App.css'
import {Navbar, NavbarBrand} from "reactstrap"
import {Link} from "react-router-dom"

class Game extends Component {
	constructor(props) {
		super(props)
		this.state = {
			games: [
				{
					gameId: 0,
					trueComb: '',
					combination: []
				}
			]
		}
		this.toggle = this.toggle.bind(this)

		this.handleChange = this.handleChange.bind(this)
		this.handleSubmit = this.handleSubmit.bind(this)
	}

	async handleChange(event) {
		this.setState({value: event.target.value})
	}

	async handleSubmit(event) {
		const combination = {combStep: this.state.value}
		event.preventDefault()

		const g = this.games[this.games.length - 1]
		if (g.trueComb.equals(combination.combStep)) {
			this.props.history.push('/user')
			return <div>
				Вы выиграли!
			</div>
		}

		const buff = await fetch('/game/addCombination/ + ' + this.props.match.params.id, {
			method: 'POST',
			headers: {
				Accept: 'application/json',
				'Content-Type': 'application/json',
			},
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
	}

	toggle() {
		this.setState({
			isOpen: !this.state.isOpen
		})
	}

	render() {
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

export default Game