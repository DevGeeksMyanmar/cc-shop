# cc-shop

A winter-semester project — cc-shop is a Java-based web application with a modern front-end (SCSS/CSS/JavaScript). This repository contains the source code for the project, including backend Java code and frontend styles and scripts.

> Repository description: winter semester project 🐼

## Table of contents

- [About](#about)
- [Features](#features)
- [Tech stack](#tech-stack)
- [Prerequisites](#prerequisites)
- [Getting started](#getting-started)
  - [Clone](#clone)
  - [Build backend](#build-backend)
  - [Build frontend (SCSS/CSS/JS)](#build-frontend-scsscssjs)
  - [Run the application](#run-the-application)
- [Configuration](#configuration)
- [Database](#database)
- [Testing](#testing)
- [Development tips](#development-tips)
- [Deployment](#deployment)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)
- [Acknowledgements](#acknowledgements)

## About

cc-shop is a sample e-commerce/web application created as a winter semester project. The backend is implemented in Java; the frontend is styled using SCSS/CSS and uses a small amount of JavaScript for client-side behavior. The goal of the project is to demonstrate a full-stack workflow: backend APIs, templating or front-end static pages, styling, and integration.

Language composition: Java (70.1%), SCSS (15.1%), CSS (11.1%), JavaScript (3.7%).

## Features

- Product catalog (list, view details)
- Shopping cart
- Checkout scaffolding (may include sample payment integration or mocked flow)
- Admin or management pages for products (if included)
- Responsive UI with SCSS-based styles
- Modular Java backend (APIs, services, data access)

(Adjust the list above to reflect the exact features implemented in your codebase.)

## Tech stack

- Backend: Java (core language)
- Frontend: SCSS, CSS, JavaScript
- Build tools: Maven or Gradle (use whichever wrapper/build files are present)
- Optional: Node/NPM for frontend bundling or SCSS compilation
- Database: (see Configuration / Database below — choose PostgreSQL, MySQL, or an embedded DB based on the project files)

## Prerequisites

- Java JDK 17 (recommended) or JDK 11 — install a supported LTS version for development
- Git (to clone the repo)
- Node.js and npm (only required if frontend build tools are present or you want to build SCSS assets locally)
- Maven or Gradle — depending on which build files exist in the repo (you can often use the wrapper: `./mvnw` or `./gradlew`)

## Getting started

### Clone

```bash
git clone https://github.com/DevGeeksMyanmar/cc-shop.git
cd cc-shop
```

### Build backend

Check whether the repository contains a Gradle wrapper (`gradlew`) or a Maven wrapper (`mvnw`) and use the appropriate commands. If neither wrapper exists, use your system Maven/Gradle.

Using Gradle (wrapper):

```bash
# On macOS/Linux
./gradlew build
# Run (if Spring Boot or similar)
./gradlew bootRun
```

Using Maven (wrapper):

```bash
# On macOS/Linux
./mvnw clean package
# Run (if Spring Boot)
./mvnw spring-boot:run
```

If the project is a plain Java app (not Spring Boot), build it with the selected tool and run the produced JAR:

```bash
java -jar build/libs/your-app.jar   # Gradle
java -jar target/your-app.jar       # Maven
```

Replace `your-app.jar` with the real artifact name produced by the build.

### Build frontend (SCSS/CSS/JS)

If the repo contains a `package.json` or a frontend build pipeline, build the frontend assets with npm or yarn.

```bash
# install frontend dependencies
npm install

# build (common script names)
npm run build
# or for development
npm run dev
```

If SCSS files must be compiled manually and there is no Node setup, use Dart Sass (install via npm or brew):

```bash
# using sass CLI
sass src/main/resources/static/scss:src/main/resources/static/css --no-source-map
```

Adjust paths to your repository layout.

### Run the application

Set up configuration (see next section) and then run the backend via the chosen command. The app will typically listen on a port such as 8080.

```bash
# example with Spring Boot
./mvnw spring-boot:run
# or
./gradlew bootRun
```

Open your browser at http://localhost:8080 (or the configured port).

## Configuration

The application may use environment variables or properties files for configuration. Common values:

- PORT or server.port — port to run the backend
- DATABASE_URL or DB_* variables — connection for the database
- SPRING_PROFILES_ACTIVE — active profile (if using Spring)

Create a `.env` or `application.properties` (or `application.yml`) based on templates in the repository. Example .env variables:

```env
# .env.example
PORT=8080
DATABASE_URL=jdbc:postgresql://localhost:5432/ccshop
DATABASE_USER=ccshop_user
DATABASE_PASSWORD=changeme
```

Update the configuration to match your local environment.

## Database

If the project uses a relational database, set up one of the common choices:

- PostgreSQL
- MySQL
- H2 (in-memory for quick dev/testing)

If there are migration scripts (Flyway/Liquibase) in the repository, run them automatically during app startup or run migrations manually:

- Flyway (Gradle/Maven): `./gradlew flywayMigrate` or `./mvnw flyway:migrate`

Check the `src/main/resources` or `db` folders for migration scripts and adjust configurations as needed.

## Testing

Run unit and integration tests:

Using Maven:

```bash
./mvnw test
```

Using Gradle:

```bash
./gradlew test
```

Add or update tests under `src/test/java` as needed.

## Development tips

- Use the wrapper scripts (`./gradlew`, `./mvnw`) if available to avoid local tool version mismatches.
- If SCSS changes are frequent, use a watcher: `sass --watch scss:css` or use your frontend tooling's dev server.
- Use an IDE with Java support (IntelliJ IDEA, Eclipse, VS Code + extensions) for quick navigation and debugging.

## Deployment

There are multiple deployment approaches:

- Build a fat JAR and run on a VM or container:
  - `./mvnw package` / `./gradlew bootJar`
  - `java -jar build/libs/cc-shop.jar`
- Dockerize the app (if a Dockerfile exists):
  - `docker build -t cc-shop:latest .`
  - `docker run -p 8080:8080 --env-file .env cc-shop:latest`
- Use a platform-as-a-service (Heroku, Railway, Render) and configure environment variables and persistent database.

If you want, I can add a sample Dockerfile and docker-compose.yml for a basic deployment.

## Contributing

Contributions are welcome! Suggested workflow:

1. Fork the repository
2. Create a feature branch: `git checkout -b feat/your-feature`
3. Make changes and commit with clear messages
4. Open a pull request describing your change

Please follow the code style used in the project. If you want, add a CONTRIBUTING.md with guidelines.

## License

This project does not currently include a license file. If you'd like to open-source it, consider adding an OSI-approved license such as MIT, Apache-2.0, or GPL-3.0. Example: add a `LICENSE` file with the MIT license.

## Contact

Project maintained by DevGeeksMyanmar.
For questions or contributions, open an issue or pull request via the GitHub repository: https://github.com/DevGeeksMyanmar/cc-shop

## Acknowledgements

- Course/instructor that guided this winter semester project
- Any libraries/frameworks used in the project (list them in this section)
