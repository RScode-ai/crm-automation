# CRM Automation

A full-stack CRM system built with a Spring Boot backend and a React frontend. The project is organized as a multi-module workspace with a Java API and a frontend dashboard for managing leads, contacts, tasks, and users.

## Project Overview

This application appears to be designed for sales and customer management workflows, including:

- User registration and login
- Lead management
- Contact management
- Task tracking
- Dashboard analytics
- Role-based access control
- JWT-based authentication

## Current Project Structure

```text
crm-automation/
├── .idea/                     # IntelliJ IDEA project configuration
├── crm-backend/               # Spring Boot backend application
│   ├── src/main/java/com/crm/
│   │   ├── controller/        # REST API endpoints
│   │   ├── dto/               # Request/response models
│   │   ├── entity/            # JPA entities
│   │   ├── exception/         # Custom exceptions and global handler
│   │   ├── repository/        # DAO interfaces
│   │   ├── security/          # JWT and Spring Security config
│   │   ├── service/           # Business logic
│   │   └── CrmAutomationApplication.java
│   ├── src/main/resources/
│   │   └── application.properties
│   ├── pom.xml
│   └── HELP.md
├── crm-frontend/              # React + Vite frontend application
│   ├── src/
│   ├── package.json
│   └── tsconfig.json
├── .gitignore
└── README.md
```

## Backend Stack

- Java 21
- Spring Boot 3.5.15
- Spring Web
- Spring Data JPA
- Spring Security
- JWT Authentication
- MySQL Database
- Maven

## Frontend Stack

- React
- Vite
- TypeScript
- React Router
- Axios
- React Icons

## Main Backend Features Observed

The backend includes controllers for:

- User management (`/api/users`)
- Lead management (`/api/leads`)
- Contact management (`/api/contacts`)
- Task management (`/api/tasks`)
- Dashboard statistics (`/api/dashboard`)
- Lead notes (`/api/lead-notes`)

Security configuration currently permits public access to:

- `/api/users/register`
- `/api/users/login`

Other API endpoints are protected and require authentication, with role-based restrictions configured for ADMIN, MANAGER, and SALES roles.

## Frontend Structure Observed

The React app contains the following top-level sections:

- `src/pages` for screens such as Login, Dashboard, Leads, Contacts, Tasks
- `src/components` for reusable UI widgets
- `src/context` and `src/hooks` for app state and reusable logic
- `src/services` for API calls
- `src/routes` for routing setup

## Database Configuration

The backend is configured to use MySQL via `crm-backend/src/main/resources/application.properties`.

Before running the app, configure the database connection details, including the database URL, username, and password.

## Run Instructions

### Backend

```bash
cd crm-backend
mvn clean install
mvn spring-boot:run
```

### Frontend

```bash
cd crm-frontend
npm install
npm run dev
```

## Notes

- The frontend uses a route setup that includes Login, Dashboard, Leads, Contacts, and Tasks pages.
- The backend is already structured for JWT-based authentication and role-based authorization.
- The project appears to be in an active development stage and is set up as an IntelliJ-friendly workspace with `.idea` configuration files present.

## Suggested Next Step

The next logical step is to review the backend entities, security configuration, and frontend route flow before implementing or expanding functionality.
