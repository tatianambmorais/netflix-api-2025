# Netflix API - Technology Stack

## Programming Languages and Versions

### Primary Language
- **Java 17**: Modern LTS version with enhanced features
- **Maven**: Build automation and dependency management

### Framework and Libraries
- **Spring Boot 3.5.7**: Main application framework
- **Spring Data JPA**: Database abstraction and ORM
- **Spring Web**: RESTful web services support
- **Spring Security**: Authentication and authorization
- **Spring Boot Mail**: Email service integration

## Database Technology
- **H2 Database**: In-memory database for development
- **JPA/Hibernate**: Object-relational mapping
- **Database Location**: `data/netflix.mv.db`

## Development Dependencies
- **Lombok**: Code generation for boilerplate reduction
- **Spring Boot Test**: Testing framework integration
- **Maven Compiler Plugin**: Java compilation configuration

## Build System Configuration

### Maven Configuration (pom.xml)
```xml
<properties>
    <java.version>17</java.version>
    <maven.compiler.source>17</maven.compiler.source>
    <maven.compiler.target>17</maven.compiler.target>
</properties>
```

### Key Dependencies
- `spring-boot-starter-data-jpa`: Database operations
- `spring-boot-starter-web`: Web application support
- `spring-boot-starter-security`: Security features
- `spring-boot-starter-mail`: Email functionality
- `h2`: Database engine
- `lombok`: Development productivity

## Development Commands

### Build and Run
```bash
# Build the project
./mvnw clean compile

# Run the application
./mvnw spring-boot:run

# Run tests
./mvnw test

# Package the application
./mvnw clean package
```

### Maven Wrapper
- **mvnw** (Unix/Linux/macOS): Maven wrapper script
- **mvnw.cmd** (Windows): Maven wrapper for Windows

## Application Configuration
- **Configuration File**: `src/main/resources/application.properties`
- **Main Class**: `com.avengers.netflix.NetflixApplication`
- **Package Structure**: `com.avengers.netflix.*`

## Development Environment
- **IDE Support**: Compatible with IntelliJ IDEA, Eclipse, VS Code
- **Lombok Integration**: Requires IDE plugin for annotation processing
- **Database Console**: H2 console available in development mode

## Security Features
- Spring Security integration
- Custom cryptography utilities
- Token-based authentication
- Email verification system

## Testing Framework
- Spring Boot Test starter
- JUnit integration
- Test class: `NetflixApplicationTests`

## Deployment Considerations
- Spring Boot embedded server (Tomcat)
- Executable JAR packaging
- H2 database files in data/ directory
- Java 17 runtime requirement