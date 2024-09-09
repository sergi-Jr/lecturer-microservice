FROM gradle:latest AS builder
LABEL maintainer="Sergei Burashnikov <burashnikov.sergei@gmail.com>"
WORKDIR /application
COPY . .
RUN --mount=type=cache,target=/root/.gradle  gradle clean build -x test

FROM bellsoft/liberica-openjre-alpine:21 AS layers
WORKDIR /application
COPY --from=builder /application/build/libs/*.jar app.jar
RUN java -Djarmode=layertools -jar app.jar extract

FROM bellsoft/liberica-openjre-alpine:21
VOLUME /tmp
COPY --from=layers /application/dependencies/ ./
COPY --from=layers /application/spring-boot-loader/ ./
COPY --from=layers /application/snapshot-dependencies/ ./
COPY --from=layers /application/application/ ./

ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
