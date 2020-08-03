# ktor-hexagonal-benchmark <img align="right" width="150" height="150" src="https://avatars1.githubusercontent.com/u/28214161?s=280&v=4">
> a experimental ktor application using hexagonal architecture

![Build](https://github.com/jgabrielfreitas/ktor-hexagonal-benchmark/workflows/Build/badge.svg?branch=master) ![License](https://img.shields.io/github/license/jgabrielfreitas/ktor-hexagonal-benchmark) ![Twitter](https://img.shields.io/twitter/url?style=social&url=https%3A%2F%2Ftwitter.com%2Fjgabrielfreitas) 


#### About
This is a simple user registration application by a kafka command or rest.

#### Architecture
The hexagonal architecture was invented by [Alistair Cockburn](https://en.wikipedia.org/wiki/Alistair_Cockburn) in an attempt to avoid known structural pitfalls in object-oriented software design, such as undesired dependencies between layers and contamination of user interface code with business logic, and published in 2005.

The term "hexagonal" comes from the graphical conventions that shows the application component like a hexagonal cell. The purpose was not to suggest that there would be six borders/ports, but to leave enough space to represent the different interfaces needed between the component and the external world.

![](https://miro.medium.com/max/1276/1*EJUMMag-_MvUP1GuDRJHEA.jpeg)

> you can find a Hexagonal Architecture article [here](https://jmgarridopaz.github.io/content/hexagonalarchitecture.html#tc1).

#### Docker
All necessary infrastructure is in the `docker-compose.yml`

``` shell
$ docker-compose up
```

#### Technologies in this project
- [ktor](https://ktor.io/) to rest layer
- [Exposed](https://github.com/JetBrains/Exposed) to database layer
- [Koin](https://insert-koin.io/) to dependency injection
- [HikariCP](https://github.com/brettwooldridge/HikariCP) to connection pool optimization
- [MySql](https://www.mysql.com/) to store data
- [JUnit5](https://junit.org/junit5/) as test framework
- [Kluent](https://junit.org/junit5/) for fluent assertions
- [Kotlin](https://kotlinlang.org/) as lang

#### License
```
MIT License

Copyright (c) 2020 João Freitas

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```