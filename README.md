# Inbank Loan Decision Engine

A loan decision system that evaluates loan applications based on customer credit segments and business rules.

## Tech Stack

- Java 21
- Spring Boot 4
- Maven
- Swagger
- H2
- React
- TypeScript
- TailwindCSS
- shadcn/ui

## Running the Application

### Backend

**Required**:
- Java 21
- Maven

**Build project:**
```bash
cd backend
mvn clean compile
```

**Start application:**
```bash
mvn spring-boot:run
```

**Default port:** 8080

**Verify application is running:**
- Check for startup logs showing "Started BackendApplication"
- Access health endpoint at http://localhost:8080/actuator/health

### Frontend

**Required**:
- Node.js

**Install dependencies:**
```bash
cd frontend
npm install
```

**Start dev server:**
```bash
npm run dev
```

**Frontend URL:** http://localhost:5173

## API Documentation

**Swagger UI URL:** http://localhost:8080/swagger-ui.html

All endpoints can be tested directly through the Swagger UI interface with interactive request/response examples.

## API Overview

### POST `/api/1.0/loans/decision`

Evaluates a loan request and returns an approval decision.

#### DecisionRequest

| Field        | Type    | Description                            |
|--------------|---------|----------------------------------------|
| personalCode | String  | Person identifier (11 digits)          |
| amount       | Integer | Requested loan amount (2000-10000 €)   |
| period       | Integer | Loan duration in months (12-60 months) |

#### DecisionResponse

| Field  | Type    | Description                  |
|--------|---------|------------------------------|
| status | String  | POSITIVE or NEGATIVE         |
| amount | Integer | Maximum approved loan amount |

## Data

- Mock data includes 4 test customers:
  - `49002010965`: debt = true (automatic rejection)
  - `49002010976`: credit modifier = 100
  - `49002010987`: credit modifier = 300  
  - `49002010998`: credit modifier = 1000