# Repo: pushy-things

Experiments with Pusher: This repo was created to start exploring Pusher's services and learn how to use their libraries.

Pusher is a hosted service that makes it super-easy to add real-time data and functionality to web and mobile applications.

[Pusher.com](https://pusher.com) - Pusher Website

## Getting Started

At the moment, this project has two main components:
* Java Servlet Trigger Web App
* Simple Website (Web Frontend)

The Java Web App can be test run in an IDE like IntelliJ IDEA with the help of a test server plugin (e.g. [IDEA Jetty Runner](https://plugins.jetbrains.com/plugin/7505-idea-jetty-runner)) or it can be deployed by placing the WAR archive in the webapps folder of your local test server (e.g. [Tomcat](http://tomcat.apache.org/).
The website can simply be opened in a modern browser on a device with Internet access.

### Prerequisites

* Web browser
* Java IDE + test server plugin -or- (local) Java test server

The Servlet part can be accessed via
```
http://localhost:8080/pushy-trigger/trigger
```
after deployment. Protip: Change the port if it doesn't match your setup.

## Running the experiments

Open the website and the Java Servlet Web App side-by-side.

### Refresh and observe

Refresh the pages and observe the changes remotely triggered through the Pusher infrastructure.

### Broadcast a message

Enter a short text message in the Web App and see it appear within the website.

## Deployment

The easiest way of deploying the Java Web App is to place the WAR archive (go to: pushy-things/java-servlet-trigger-app/pushy-trigger/target/ pushy-trigger.war) in the webapp folder of a tomcat test server environment.
