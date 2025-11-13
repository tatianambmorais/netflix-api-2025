# Netflix API 2025 - Technology Stack

## Programming Languages and Versions

### Primary Language
- **Java 17**: Main programming language with modern features and LTS support
- **Maven**: Build automation and dependency management

### Framework Versions
- **Spring Boot 3.5.7**: Main application framework
- **Spring Framework**: Underlying dependency injection and application context
- **Spring Data JPA**: Data persistence and repository abstraction
- **Spring Web**: RESTful web services and MVC support
- **Spring Security**: Authentication and authorization
- **Spring Boot Mail**: Email service integration

## Core Dependencies

### Database and Persistence
- **H2 Database**: In-memory database for development and testing
- **Hibernate**: JPA implementation for ORM
- **Spring Data JPA**: Repository pattern implementation

### Security and Utilities
- **Spring Security**: Comprehensive security framework
- **Lombok**: Code generation for reducing boilerplate
- **Custom Cryptography Utils**: Encryption and token management

### Email Services
- **Spring Boot Starter Mail**: Email functionality
- **Mailtrap SMTP**: Development email testing service

### Testing
- **Spring Boot Test**: Testing framework with auto-configuration
- **JUnit**: Unit testing framework (included with Spring Boot Test)

## Build System and Configuration

### Maven Configuration
```xml
<properties>
    <java.version>17</java.version>
    <maven.compiler.source>17</maven.compiler.source>
    <maven.compiler.target>17</maven.compiler.target>
</properties>
```

### Key Maven Plugins
- **Maven Compiler Plugin**: Java compilation with Lombok annotation processing
- **Spring Boot Maven Plugin**: Application packaging and execution

## Database Configuration

### H2 Database Setup
- **URL**: `jdbc:h2:file:./data/netflix`
- **Driver**: `org.h2.Driver`
- **Username/Password**: `sa/sa`
- **Console**: Enabled at `/h2-console`
- **DDL Strategy**: `update` (preserves data between restarts)

### JPA Configuration
- **Dialect**: `org.hibernate.dialect.H2Dialect`
- **Show SQL**: Disabled in production
- **Format SQL**: Enabled for debugging

## Development Commands

### Build and Run
```bash
# Clean and compile
mvn clean compile

# Run tests
mvn test

# Package application
mvn package

# Run application
mvn spring-boot:run

# Run with specific profile
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

### Database Management
```bash
# Access H2 Console
# Navigate to: http://localhost:8080/h2-console
# JDBC URL: jdbc:h2:file:./data/netflix
# Username: sa, Password: sa
```

### Development Tools
- **IDE**: Any Java IDE with Maven support (IntelliJ IDEA, Eclipse, VS Code)
- **Java Version**: Requires Java 17 or higher
- **Maven**: Version 3.6+ recommended

## Environment Configuration

### Application Properties
- Database connection settings
- H2 console configuration
- Email service configuration (Mailtrap)
- JPA and Hibernate settings
- Security configurations

### Email Service Setup
- **Host**: `sandbox.smtp.mailtrap.io`
- **Port**: `465`
- **Authentication**: Enabled with SMTP credentials
- **TLS**: Enabled for secure communication