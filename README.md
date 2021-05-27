# Task Activity Planner
CO3202 Final Project

## Heroku
You can access Task Activity Planner from the link I have deployed to here:

https://task-activity-planner.herokuapp.com/

## Docker
Alternatively, run the application from a Docker container

In order to run this container, you'll need Docker installed on your machine.

[Get Docker](https://docs.docker.com/get-docker/)

## Usage

Clone this repository
```shell
git clone https://campus.cs.le.ac.uk/gitlab/ug_project/20-21/za103.git
```

Enter the new directory
```shell
cd za103
```

Build the MySQL image
```shell
docker-compose up -d
```

Build the Spring image
```shell
docker-compose -f spring.yaml up --build -d
```

Verify that the two images are running
```shell
docker ps
```

Verify that the application builds successfully (optional) 
```shell
docker logs -f <container id>
```

See the application running in a browser
```shell
localhost:8080
```

## Frameworks used
- Spring Boot
- Spring Security
- MySQL
- PostgreSQL
- Heroku
- Docker
- Maven
- Thymeleaf
- Bootstrap

