# stats service

### Native build:

``` shell
./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true
```

### Build docker image:

``` shell
docker build -f src/main/docker/Dockerfile.native-micro -t mercadolibre.rodrigo/countries .
```

### Standalone execution:

``` shell
docker run -i --rm -p 8080:8080 mercadolibre.rodrigo/countries
```

### Api docs

Once the application is running you can go to http://<host>:<port>/q/openapi for the api docs.

### Release and versioning:

I used [uplift](https://upliftci.dev/)

#### 1) Release
```shell
uplift release
```
