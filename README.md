# COMP6000 - Group Project
Final year group project.

[![Build Status](https://ci.dgrinbergs.com/api/badges/dgrinbergs/comp6000/status.svg?ref=refs/heads/develop)](https://ci.dgrinbergs.com/dgrinbergs/comp6000)

## Contents
* [Google Drive](https://drive.google.com/drive/folders/1956QOpzN265OdxG60flO1qk3QptO6gZQ)
* [API Specification](./documentation/api-specification.md)
* [Services](./documentation/services.md)
* [Running the project](#how-to-run-everything-in-this-project)

### How to run everything in this project
This project relies on Docker to bring all the services together.
Docker can be installed using the following instructions [here](https://docs.docker.com/get-docker/).
Once you have docker installed, you can enter the following commands and all the services should start up.

#### Building the jars necessary for the project
To build the jars, you will need to use the gradle commands which are provided below. They will take the source code and
package it into jar files which can be run inside a docker container.

UNIX
```shell
chmod +x ./build.sh
./build.sh
```

#### Starting the services
```shell
docker network create public
docker-compose build --no-cache
docker-compose up -d
```

#### Checking logs
Show logs for every service
```shell
docker-compose logs -f
```

Show logs only for the backend Spring Java REST API
```shell
docker-compose logs -f backend
```

Show logs only for the minecraft server
```shell
docker-compose logs -f minecraft-server
```