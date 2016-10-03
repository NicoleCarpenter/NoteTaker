[![Build Status](https://travis-ci.org/NicoleCarpenter/NoteTaker.svg?branch=basic-functionality)](https://travis-ci.org/NicoleCarpenter/NoteTaker)

# Note Taker

Note Taker is an application that is made to run using a [Java Http Server](https://github.com/NicoleCarpenter/JavaHttpServer) as a dependency. The application is designed to utilize [Telnet](http://www.telnet.org/), which is an application that is used to connect to remote servers.

This project encorporates Gradle as a build tool. Gradle provides a wrapper feature which allows you to run Gradle commands without having Gradle installed by using the `gradlew` command when a wrapper is present.

## Dependencies

[Java](https://java.com/en/download/)


## Running the Server

From your desired file location, clone the repo

```
git clone git@github.com:NicoleCarpenter/NoteTaker.git

```

Then `cd` into the application's root directory

```
cd NoteTaker
```

From there, you will need to build the application, which will enable you to use the jar file provided as a dependency and stored in [Clojars](https://clojars.org/org.clojars.ncarpenter/java-http-server). This will also run the tests.

```
gradlew build
```

The server will need to run in the background while interacting with Telnet. To run the server from the application's root directory:

```
gradlew run
```


## Interacting with the application through Telnet

In a separate terminal tab and from the root directory, open telnet on port 4000

```
telnet localhost 4000
```

The app will allow you to read from existing files or write a new file. 


### Writing a new note

New files are written using HTTP format. Currently, the content length header must be included when writing new notes, and the length of the content must be at least the length given in the Content-Length header. You can use a POST request, along with the file name and HTTP version to create a new file. The connection is terminated once the request is sent. 

```
POST /index.txt HTTP/1.1
Content-Lenght: 11

Hello World
```

Per HTTP standards, the content of the note is separated from the header by a blank line, and the message is sent when "enter" is pressed. All new notes are saved in a file at the root called `resources`.

### Reading an existing note

New notes can be read using standard HTTP GET requests

```
GET /index.txt HTTP/1.1
```

The request is sent by hitting "enter" twice and the note is returned in the body of the response. The connection is terminated once the request is sent.

(_*NOTE: If you add a note, you need to restart the server in the first tab before it will be available to read*_)


## Running the Tests

To run the unit tests only from the root directory, type

```
./gradlew test
```