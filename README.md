Example Java code for interfacing with the ECC API Server at https://github.com/rynobey/ECC-API

Two endpoints are requested:

1. `/isAlive/`
2. `/ec/add/`

This pattern may be followed for requesting any of the other ECC endpoints which may be needed.

## Dependencies
1. Follow the installation instructions of https://github.com/rynobey/ECC-API
2. JSON-simple (however the JAR is included in the repository)

## Installation + Running
1. Ensure https://github.com/rynobey/ECC-API is running
2. `git clone git@github.com:coeniebeyers/javaEcApi.git`
3. `cd javaEcApi`
4. javac -cp json-simple-1.1.jar:. ECCConnectionExample.java
5. java -cp json-simple-1.1.jar:. ECCConnectionExample

