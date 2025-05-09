 – a full-stack **Spring Boot + React** application with **JWT Authentication** and **Form Validation**.

---

### 📄 `README.md`

```markdown
# Form Validation App

A full-stack web application built with **React** (frontend) and **Spring Boot** (backend), featuring:

- JWT-based authentication
- Form validation
- MySQL database integration
- CORS configuration
- Production-ready build setup

---

## 📦 Tech Stack

| Layer       | Technology              |
|-------------|--------------------------|
| Frontend    | React, Vite, Tailwind (optional) |
| Backend     | Spring Boot, Spring Security, Spring Data JPA |
| Database    | MySQL |
| Auth        | JWT (JSON Web Token) |
| Build Tool  | Maven |

---

## 🚀 Getting Started

### Prerequisites

- Java 17+
- Node.js + npm
- MySQL
- Maven

---

### 🔧 Backend Setup (Spring Boot)

1. Clone the project:
   ```bash
   git clone https://github.com/your-username/form-validation-app.git
   cd form-validation-app
   ```

2. Update MySQL credentials in `application.properties`.

3. Run the Spring Boot application:
   ```bash
   mvn spring-boot:run
   ```

---

### 💻 Frontend Setup (React)

1. Navigate to frontend directory (if separate) and install dependencies:
   ```bash
   npm install
   ```

2. Build the React app:
   ```bash
   npm run build
   ```

3. Copy the contents of the `build/` folder to:
   ```
   src/main/resources/static/
   ```


---

## 🔐 Authentication Flow

- User logs in and receives a JWT token.
- The token is stored in the browser (localStorage).
- All protected API calls include `Authorization: Bearer <token>` header.

---

## 🛠 API Endpoints

| Method | Endpoint       | Description               |
|--------|----------------|---------------------------|
| POST   | `/register`    | Register new users        |
| POST   | `/login`       | Authenticate and get token |
| GET    | `/`            | Secured endpoint          |

---

## 📄 Features

- 🔒 JWT Auth
- ✅ Form validation
- 🌐 CORS enabled for frontend dev
- 📁 React served from Spring Boot

---


backend/
│
├── config/
├── controller/
├── entity/
├── repository/
├── service/
└── static/          <-- React build goes here
```

---

## 📬 Contact

Made with 💙 by **Pavan Dhote**  






