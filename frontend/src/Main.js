import React, {Component} from 'react'
import './App.css'

class Main extends Component {
    constructor(props) {
        super(props)
        this.state = {
            value: '',
        }

        this.handleChange = this.handleChange.bind(this)
        this.handleSubmit = this.handleSubmit.bind(this)
    }

    async handleChange(event) {
        this.setState({value: event.target.value})
    }

    async handleSubmit(event) {
        const user = {nickname: this.state.value}
        // нужно написать проверку без обновления страницы с переходом
        /*if (
            user.nickname.equal('') ||
            user.nickname.value === undefined ||
            user.nickname.value === null
        ) {
            alert('Некорректно введены данные')
            return
        }*/

        console.log(user)
        event.preventDefault()
        const buff = await fetch('/user', {
            method: 'POST',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(user),
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
        this.props.history.push('/user/' + buff.id)
    }

    render() {
        return (
            <form onSubmit={this.handleSubmit}>
                <label>
                    Name:
                    <div className="input-group">
                        <input
                            type="text"
                            className="form-control"
                            placeholder="Username"
                            aria-label="Username"
                            aria-describedby="input-group-right"
                            value={this.state.value}
                            onChange={this.handleChange}
                        />
                    </div>
                </label>
                <input type="submit" value="Enter"/>
            </form>
        )
    }
}

export default Main
