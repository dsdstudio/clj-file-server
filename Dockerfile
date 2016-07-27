FROM java:8-alpine
MAINTAINER Your Name <you@example.com>

ADD target/uberjar/clj-file-server.jar /clj-file-server/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/clj-file-server/app.jar"]
