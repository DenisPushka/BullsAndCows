import React, {Component} from 'react'
import {Button, ButtonGroup, Container, Table} from 'reactstrap'
import AppNavbar from './AppNavbar'
import {Link} from 'react-router-dom'

class UserList extends Component {

	constructor(props) {
		super(props)
		this.state = {users: []}
		this.remove = this.remove.bind(this)
	}

	componentDidMount() {
		fetch('/user')
			.then(response => response.json())
			.then(data => this.setState({users: data}))
	}

	async remove(id) {
		await fetch(`/user/${id}`, {
			method: 'DELETE',
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			}
		}).then(() => {
			let updatedUser = [...this.state.users].filter(i => i.id !== id)
			this.setState({users: updatedUser})
		})
	}

	render() {
		const {users, isLoading} = this.state

		if (isLoading) {
			return <p>Loading...</p>
		}

		const userList = users.map(user => {
			return <tr key={user.id}>
				<td style={{whiteSpace: 'nowrap'}}>{user.nickname}</td>
				{/*<td>{user.gameId.count()}</td>*/}
				<td>
					<ButtonGroup>
						<Button size="sm" color="primary" tag={Link} to={"/user/" + user.id}>Edit</Button>
						<Button size="sm" color="danger" onClick={() => this.remove(user.id)}>Delete</Button>
					</ButtonGroup>
				</td>
			</tr>
		})

		return (
			<div>
				<AppNavbar/>
				<Container fluid>
					<div className="float-right">
						<Button color="success" tag={Link} to="/user">Войти как участник</Button>
					</div>
					<h3>Пользователи</h3>
					<Table className="mt-4">
						<thead>
						<tr>
							<th width="30%">Name</th>
							{/*<th width="40%">Actions</th>*/}
						</tr>
						</thead>
						<tbody>
						{userList}
						</tbody>
					</Table>
				</Container>
			</div>
		)
	}
}

export default UserList