# UserManager
User manager service for managing user information in a web application
## Getting Started
### Installing

* Clone the git repository:

```
git clone git@github.com:ATS88GR/UserManager.git
```

Go to the directory /UserManager

To create .jar of the project, run the command in the terminal:
```
mvn package
```
After creating .jar file, run the command: 
```
mvn spring-boot:run
```
WARNING! The service does not run standalone without a running database and PlaceManager service.

* To pull the PlaceManager image, run the command in the terminal:

```
docker pull artsyom88/spring-boot-place-manager:0.0.1-SNAPSHOT
```

* To running the database and images:
1. Install Docker Engin
```
   https://docs.docker.com/engine/install/ubuntu/
   ```
2. Follow the steps after installing Linux for Docker Engine

```
   https://docs.docker.com/engine/install/linux-postinstall/
   ```
3. Clone BirdwatchingCI/CD:
```
git@github.com:ATS88GR/BirdwatchingCICD.git  
```
4. Go to the folder named BirdwatchingCI/CD and launch the terminal.

   Execute the command on the command line:
```
   docker compose up
```

### Running

After running the docker compose up command in the terminal, images of the database, UserManager and PlaceManager services will launch.

The application's REST API can be accessed via:

1. Swagger UI

   http://localhost:8080/swagger-ui/index.html#/

2. Postman API Platform 

    https://www.postman.com/downloads/