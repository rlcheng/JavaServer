##Java Server app

###About
This is a simple Java Server implementation built as part of the 8th Light apprenticeship program. It is a basic HTTP server that:
- conforms to HTTP 1.1 Protocol RFC 2616
- serves text and image content (jpeg, png, and gif)
- simple Get, Post, Put, and Delete requests
- Patch with If-Match Etag using SHA1
- loading of a directory on the server and displaying and linking its contents. Clicking on the link performs a Get request that displays the image or text in a browser.
- and other features as specified in [8th Light's Cob Spec integration test suite](https://github.com/8thlight/cob_spec)

Rules for building this server is simple:
- No Apache HTTP Server library or any other HTTP Server library, implement your own
- Core library calls only (File, Socket, etc)
- No mocking frameworks (i.e. Mockito). Use dependency injection to hand roll your own mocks. One has better appreciation and understanding of mocking frameworks when one roll their own mocks.
- use JUnit for testing framework
- use Maven, and no IDE dependencies (IntelliJ or Eclipse)

###To install
Make sure you have Java 8, Maven 3.3.9, JUnit 4.12

Clone this repo and at the root of the directory in console/terminal:
```sh
$ mvn package
```

###To run
To compile and build the jar file. Jar file will reside in `/path/JavaServer/target` as `JavaServer-1.0.jar`

To launch the app, in the target directory in console/terminal:
```
$ java -jar JavaServer-1.0 -p 5000 -d "path to a directory with txt and image files" 
```
Make sure the path typed ends with a forward slash. example: `/Users/richardcheng/Documents/cob_spec/public/`

-p stands for port and -d is for directory. if you do not have the -p option the server defaults to port 5000. If -d is not present then no directory is set as default and root will not have any files shown.

After the server starts you can either navigate via the browser to http://localhost:5000/ or use curl to send commands.

###To run with cob-spec
To see the integration test suite against the Java Server, first clone [Cob Spec](https://github.com/8thlight/cob_spec)

Follow the setup directions for Cob-spec. Do note that the section of the Readme on Configuring Cob Spec, the 3rd bullet on update the paths for the User-Defined Variables, is missing key information. Make sure you remove the dash in front of each line. Not doing so will causes errors!!

That is, from this:
```
-!define SERVER_START_COMMAND {java -jar /path/to/server.jar}
-!define PUBLIC_DIR {/path/to/cob_spec/public/}
```
To this:
```
!define SERVER_START_COMMAND {java -jar /path/to/server.jar}
!define PUBLIC_DIR {/path/to/cob_spec/public/}
```

And for the path to the jar file, you can use the path from the section above. For directory, you must use the cob_spec/public folder in order for Cob Spec to work correctly.

All tests within the Response Test Suite should be all green (as of this writing, subject to change as Cob Spec gets updated).
