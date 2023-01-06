<h1 align="center">
    Pokemon Battle API
</h1>

<h4 align="center">
  A simple battle simulator made with Spring Boot
</h4>

<p align="center">
  <a href="#rocket-technologies">Technologies</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-project">Project</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-endpoints">Endpoints</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
</p>

<br>

## :rocket: Technologies

Techs used in this project: 

- [Spring Boot](https://spring.io/)
- Spring Data JPA
- Rest template
- [Database h2](https://www.h2database.com/html/main.html)
- [Postman](https://www.postman.com/)

## 💻 Project

<p align="center">
  <img alt="model" src="https://github.com/dbastosdev/spring-pokemon-api/blob/main/model.png" width="50%">
</p>

The project was made to study how access external services with Rest template. For this, I built a system that can search pokemons by name and get the main informations about them. With this data, I wrote a simple game logic based in RPG to create this app. 

The game use the pokemons associated a each Pokemon master and simulate a battle in turns called rounds. Dices d100 are used to make attacks and defenses with sucess if them are minor than values of pokemons. D10 are used to make damage.

A complete use case can see below. Enjoy it!

## 🔖 Endpoints

REST:
| Method | Description |
|---|---|
| `GET` | Return information about one or more registers. |
| `POST` | Create a new register. |
| `PUT` | Update a register. |
| `DELETE` | Remove a register. |

Data format: (application/json)

---

| Method | url | body | reponse
|---|---|---|---|
| `GET` | /pokemon/1 | null | List of pokemons
| `POST` | /pokemon-master| Pokemon master data and set of pokemons | Pokemon Master data
| `GET` | /battles | null | List of battles
| `POST` | /battles | Pokemon Masters and Pokemons id | Battle result
| `GET` | /rounds | null | List of rounds

A postman collection can be tested - [here](https://github.com/dbastosdev/spring-pokemon-api/tree/main/postman). 
---

Coded with ☕️ by dbastos.dev@gmail.com 

[![Linkedin Badge](https://img.shields.io/badge/-LinkedIn-blue?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/douglas-b-5a7413219/)]( https://www.linkedin.com/in/douglas-b-5a7413219/)
