# ktor-hexagonal-benchmark <img align="right" width="150" height="150" src="https://avatars1.githubusercontent.com/u/28214161?s=280&v=4">
> a experimental ktor application using hexagonal architecture

#### About the Hexagonal Architecture
The hexagonal architecture was invented by [Alistair Cockburn](https://en.wikipedia.org/wiki/Alistair_Cockburn) in an attempt to avoid known structural pitfalls in object-oriented software design, such as undesired dependencies between layers and contamination of user interface code with business logic, and published in 2005.

The term "hexagonal" comes from the graphical conventions that shows the application component like a hexagonal cell. The purpose was not to suggest that there would be six borders/ports, but to leave enough space to represent the different interfaces needed between the component and the external world.

![](https://miro.medium.com/max/1276/1*EJUMMag-_MvUP1GuDRJHEA.jpeg)

> you can find a Hexagonal Architecture article [here](https://jmgarridopaz.github.io/content/hexagonalarchitecture.html#tc1).

#### Docker
All necessary infrastructure is in the `docker-compose.yml`

``` shell
$ docker-compose up
```

#### Tech in this project
- [ktor](https://ktor.io/) to rest layer
- [Exposed](https://github.com/JetBrains/Exposed) to database layer
- [Kodein](https://kodein.org/Kodein-DI/) to dependency injection
- [HikariCP](https://github.com/brettwooldridge/HikariCP) to connection pool optimization
- [MySql](https://www.mysql.com/) to store data
- [Kotlin](https://kotlinlang.org/) as lang

##### todo
- [ ] fix Dockerfile
- [ ] add tests with [Kluent](https://github.com/MarkusAmshove/Kluent)
