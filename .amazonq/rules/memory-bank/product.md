# Netflix API - Product Overview

## Project Purpose
A Spring Boot-based Netflix-like streaming platform API that provides comprehensive media management, user authentication, and subscription services. The system enables users to browse, favorite, and manage multimedia content while handling secure user registration, login, and payment processing.

## Key Features and Capabilities

### User Management
- User registration with email confirmation via token system
- Secure authentication and authorization with Spring Security
- User profile management and account verification
- Role-based access control with TipoUsuario enum

### Media Management
- Support for multiple media types (Filme, Serie) extending base Midia class
- Media catalog browsing and search functionality
- Favorite media management for personalized user experience
- Media metadata storage and retrieval

### Payment Processing
- Credit card (Cartao) registration and management
- Secure payment information handling with encryption utilities
- Card validation and update capabilities

### Security Features
- Token-based authentication system
- Password encryption using custom cryptography utilities
- Email verification for account activation
- Secure session management

### Communication Services
- Email service integration for notifications and confirmations
- Token generation and validation for secure operations

## Target Users and Use Cases

### Primary Users
- **End Users**: Individuals seeking streaming media content with personalized recommendations
- **Content Managers**: Administrators managing media catalog and user accounts
- **System Administrators**: Technical staff maintaining platform security and performance

### Core Use Cases
- User registration and account verification
- Secure login and session management
- Media browsing and content discovery
- Personal favorite lists management
- Payment method registration and updates
- Account security and token validation

## Value Proposition
Provides a secure, scalable foundation for streaming media platforms with robust user management, comprehensive media handling, and integrated payment processing, built on enterprise-grade Spring Boot architecture.