# Task Activity Planner

### Prerequisites
In order to run this container you'll need Docker installed on your machine.

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

Build the MySQL container
```shell
docker-compose up -d
```

Build the Spring container
```shell
docker-compose -f spring.yaml up --build -d
```

Verify that the two containers are running (optional)
```shell
docker ps
```

Verify that the application builds successfully (optional) 
```shell
docker log -f <container id>
```

Access the application
```shell
localhost:8080
```

