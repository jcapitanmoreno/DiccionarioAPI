# DiccionarioAPI

DiccionarioAPI es una aplicación Spring Boot que proporciona una API para gestionar un diccionario con palabras y definiciones.

## Requisitos

- Java 17
- Maven
- MySQL

## Configuración

1. Clona el repositorio:
    ```bash
    git clone https://github.com/jcapitanmoreno/DiccionarioAPI.git
    cd DiccionarioAPI
    ```

2. Configura la base de datos en el archivo `src/main/resources/application.properties`:
    ```ini
    spring.datasource.url=jdbc:mysql://localhost:3306/diccionariodb?useSSL=false&serverTimezone=UTC
    spring.datasource.username=root
    spring.datasource.password=
    spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
    spring.jpa.hibernate.ddl-auto=none
    spring.jpa.show-sql=true
    ```

3. Ejecuta la aplicación:
    ```bash
    mvn spring-boot:run
    ```

## Endpoints

### Palabra

- **GET** `/palabra`: Obtener todas las palabras sin definiciones.
- **GET** `/palabra/definiciones`: Obtener todas las palabras con definiciones.
- **GET** `/palabra/{id}/definiciones`: Obtener una palabra por ID con definiciones.
- **GET** `/palabra/{id}`: Obtener una palabra por ID sin definiciones.
- **POST** `/palabra`: Crear una nueva palabra.
- **PUT** `/palabra`: Actualizar una palabra existente.
- **DELETE** `/palabra/{id}`: Eliminar una palabra por ID.
- **POST** `/palabra/condefiniciones`: Crear una nueva palabra con definiciones.
- **GET** `/palabra/categoria/{categoria}`: Obtener palabras por categoría gramatical.
- **GET** `/palabra/inicial/{letra}`: Obtener palabras por inicial.

### Definición

- **GET** `/definicion/palabra/{palabraId}`: Obtener definiciones por ID de palabra.
- **POST** `/definicion/palabra/{palabraId}`: Agregar una definición a una palabra.
- **DELETE** `/definicion/{id}`: Eliminar una definición por ID.

## Documentación de la API

La documentación de la API está disponible en [Swagger UI](http://localhost:8080/swagger-ui/index.html).

## Contribuir

1. Haz un fork del proyecto.
2. Crea una nueva rama (`git checkout -b feature/nueva-funcionalidad`).
3. Realiza tus cambios y haz commit (`git commit -am 'Añadir nueva funcionalidad'`).
4. Sube tus cambios (`git push origin feature/nueva-funcionalidad`).
5. Abre un Pull Request.

## Licencia

Este proyecto está licenciado bajo la Licencia MIT.
