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
                idG: 0,
                trueComb: '',
                combination: [
                    {
                        combinationId: 0,
                        combStep: '',
                        timeOfGame: ''
                    }
                ]
            }
        ]
    };

    constructor(props) {
        super(props);
        this.state = {
            item: this.emptyItem,
            time: ''
        };

        this.averageTime()
        this.addGame = this.addGame.bind(this)
    }

    async componentDidMount() {
        const user = await (await fetch(`/user/${this.props.match.params.id}`)).json();
        this.setState({item: user});
    }

    async addGame(item) {
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
        this.props.history.push(`/game/` + buff.gameId)
    }

    async averageTime() {
        const a = await fetch(`AverageTime/${this.props.match.params.id}`)
            .then((response) => {
            return response
                .json()
                .then((data) => {
                    return data
                })
                .catch((err) => {
                    console.log(err)
                })
        });
        console.log(a)
        this.setState({time: a});
    }

    render() {
        const {item} = this.state;
        const gameList = item.games.map(game => {
            return <tr key={game.gameId}>
                <td style={{whiteSpace: 'nowrap'}} width="40%">№{game.idG}</td>
                <td width="40%">True combination = {game.trueComb}</td>
                <td width="60%">Кол-во комбинаций = {game.combination.length}</td>
            </tr>
        })

        let averageComb = 0;
        item.games.map(game => averageComb += game.combination.length)
        averageComb /= item.games.length

        return <div>
            <AppNavbar/>
            <Container>
                <Form onSubmit={this.handleChange}>
                    <FormGroup>
                        <Label for="name">Все игры</Label>
                    </FormGroup>
                    <tbody>

                    {gameList}

                    <p align={"center"}> Среднее количество шагов = {averageComb}</p>

                    <p align={"center"}>Среднее время прохождения: {this.state.time}</p>

                    </tbody>
                    <FormGroup>
                        <Button color="primary" type="submit" tag={Link}
                                onClick={() => this.addGame(item)}>Играть</Button>
                    </FormGroup>
                </Form>
            </Container>
        </div>
    }
}

export default withRouter(GameList);