import React, {Component} from 'react';
import {Link, withRouter} from 'react-router-dom';
import {Button, Container, Form, FormGroup, Label} from 'reactstrap'
import AppNavbar from './AppNavbar';

class GameList extends Component {

    emptyItem = {
        nickname: '',
        games: [
            {
                gameId: 0,
                trueComb: '',
                combination:[

                ]
            }
        ]
    };

    constructor(props) {
        super(props);
        this.state = {
            item: this.emptyItem
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    async componentDidMount() {
        if (this.props.match.params.id !== 'new') {
            const users = await (await fetch(`/user/${this.props.match.params.id}`)).json();
            this.setState({item: users});
        }
    }

    async handleChange(event) {
        const target = event.target;
        const value = target.value;
        const nickname = target.nickname;
        let item = {...this.state.item};
        item[nickname] = value;
        this.setState({item});
    }

    async update(item){
        console.log(item)
        const buff = await fetch(`/user/addGame/${item.id}`, {
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
        this.props.history.push(`/game/${buff.id}`)
    }

    // Не использую, оставил на всякий случай
    async handleSubmit(event) {
        event.preventDefault();
        const {item} = this.state.target.value;

        await fetch('/user' + (item.id ? '/' + item.id : ''), {
            method: (item.id) ? 'PUT' : 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(item),
        });
        this.props.history.push('/user');
    }

    render() {
        const {item} = this.state;
        console.log(item)
        const gameList = item.games.map(game => {
            return <tr key={game.id}>
                <td style={{whiteSpace: 'nowrap'}} width="30%">№{game.gameId}</td>
                <td width="40%">True combination = {game.trueComb}</td>
                {/*<td width={"60%"}>Комбинации = {game.combination.map(*/}
                {/*    comb=>{*/}
                {/*        comb*/}
                {/*    }*/}
                {/*)}</td>*/}
            </tr>
        })

        return <div>
            <AppNavbar/>
            <Container>
                <Form onSubmit={this.handleChange}>
                    <FormGroup>
                        <Label for="name">Все игры</Label>
                    </FormGroup>
                    <tbody>
                    {gameList}
                    </tbody>
                    <FormGroup>
                        <Button color="primary" type="submit" tag={Link} to={this.update(item)}>Играть</Button>{' '}
                    </FormGroup>
                </Form>
            </Container>
        </div>
    }
}

export default withRouter(GameList);