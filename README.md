# address-microservice-poc

## Introduction
Welcome to Address 12-Factor Spring Microservice POC application. This project was an experiment to learn how to write a distributed spring boot application using Spring Boot and Spring Cloud libraries.

To see the POC in action, you will be building the following microservices:

1.  A Spring Cloud Config server that is deployed as Docker container and can manage a services configuration information using a file system or GitHub-based repository.
2.  A Eureka server running as a Spring-Cloud based service.  This service will allow multiple service instances to register with it.  Clients that need to call a service will use Eureka to lookup the physical location of the target service.
3.  A Zuul API Gateway.  All of our microservices can be routed through the gateway and have pre, response and
post policies enforced on the calls.
4.  An address service. This will function as our business logic microservice. Right now, its only responsibility is to forward validation calls to the validation service.
5.  A validation service.  This is the service that will perform the validation logic. You can think of this service being the Spectrum service. It was created to see if two microservices could communicate with one another. The only logic it currently enforces is that an address request has all required attributes
6.  An Authentication service. This is not currently hooked up to the rest of the application. While I was able to generate tokens and was successfully able to submit tokens to each service individually, I wasn't able to figure out how to get SSO to work between multiple clients.

## Software needed
1.	[Apache Maven] (http://maven.apache.org). I used version 3.5.0 of the Maven. This code has been been compiled with Java OpenJDK version 11.
2.	[Docker] (http://docker.com). I built the code using Docker V1.18. I am taking advantage of the embedded DNS server in Docker that came out in release V1.11.
3.	[Lombok] (https://projectlombok.org/). You'll need to configure your IDE to use Lombok. Lombok is a code generation tool to reduce the boilerplate java code. If you want the project to build in your IDE, you'll need to configure this. Otherwise, you can ignore this.

## Building the Docker Images for CIM Microservice POC
As of now, you will need to manually build the Jar with Maven. From the project root directory:
    **mvn clean package**

WORK IN PROGRESS - Spotify Docker Plugin

## Building and Running the Docker services

After you have built the .jars for each of the microservices, you will need to create the docker images for each microservice. The naming is important, as they are referenced in the docker-compose.yml file.

```
    $> cd address
    $> docker build --tag=cim-address .
    $> cd config
    $> docker build --tag=cim-config-server .
    $> cd eureka
    $> docker build --tag=cim-eureka-server .
    $> cd validationservice
    $> docker build --tag=cim-validationservice .
    $> cd zuul
    $> docker build --tag=cim-zuul .
```

Now we are going to use docker-compose to start the actual image.  To start the docker image,
change to the project's root directory.  Issue the following docker-compose command:

   **docker-compose -f docker/common/docker-compose.yml up**

If everything starts correctly you should see a bunch of Spring Boot information fly by on standard out.  At this point all of the services needed for the chapter code examples will be running.


## A Sample request

In theory, the Zuul service should be the only service exposed to external calls. To validate an address you can submit a REST request:

GET http://localhost:5555/api/address/address/validate

Headers:
  Content-Type application/json

Body:
```
{
	"line1": "500 W Madison",
	"line2": "Suite 3300",
	"city": "Chicago",
	"region": "IL",
	"postalCode": "60661",
	"countryCode": "US"
}
```

The expected result should be:
```
{
    "success": true
}
```

It takes several seconds for each microservice to register with the Eureka service. If you send a request immediately after all services have started up, wait a minute and try again.
