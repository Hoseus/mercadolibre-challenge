# Mercadolibre challenge

### Diagrama de arquitectura

Esta en el root, se llama architecture.jpg
Tambien esta la opcion de abrirlo en draw.io con architecture.drawio

### Build de proyectos

Hay instrucciones en cada proyecto pero se puede armar la imagen de docker siguiendo estos pasos:

``` shell
cd countries
./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true
docker build -f src/main/docker/Dockerfile.native-micro -t mercadolibre.rodrigo/countries .

cd ..

cd stats
./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true
docker build -f src/main/docker/Dockerfile.native-micro -t mercadolibre.rodrigo/stats .

cd ..
```

### Ejecucion:

Antes de ejecutar es necesario abrir el docker-compose.yaml y setear 2 variables de entorno:
- COUNTRIES_IP_API_API_KEY
- COUNTRIES_COUNTRY_API_API_KEY


``` shell
cd docker-compose
docker compose up -d
```

Suele pasar que a pesar de que puse depends_on los microservicios inician antes que kafka asi que por ahi hay que bajarlos y volver a subirlos cuando este levante.
