
# Includify - Job Platform for Blue Collar Workers (Spring Boot Backend)

Includify is a job platform designed to help **blue-collar workers** find employment opportunities easily.
This repository contains the **complete backend** built using **Java Spring Boot**, following a clean, modular architecture.
It includes features like **authentication**, **role-based access**, **job posting**, **job applications**, and **admin management**.

---

## 🏗️ Project Overview

This backend powers the Includify job platform, connecting **Job Posters** and **Job Seekers**.
It also includes an **Admin Dashboard** to manage users, jobs, and applications.

### Key Roles:
- **Admin:** Manage users, delete posts, and moderate content.
- **Job Poster:** Post new job openings, view applicants.
- **Job Seeker:** Browse jobs, apply, and save jobs.

---

## ⚙️ Tech Stack

| Layer | Technology |
|-------|-------------|
| Language | Java 17 |
| Framework | Spring Boot 3.x |
| Database | MySQL |
| ORM | Hibernate / JPA |
| Authentication | Spring Security + JWT |
| Build Tool | Maven |
| API Docs | Swagger / OpenAPI |
| Testing | JUnit 5, Mockito |

---

## 🧩 Features

### 👤 Authentication & Authorization
- JWT-based login/signup
- Role-based access (`ADMIN`, `JOB_POSTER`, `JOB_SEEKER`)
- Passwords hashed using BCrypt

### 💼 Job Management
- Job Poster can:
  - Post new jobs
  - View own job listings
  - Edit or delete jobs

### 🔍 Job Discovery
- Job Seeker can:
  - Browse all jobs
  - Apply for a job
  - Save favorite jobs

### 🛠️ Admin Dashboard
- View all users
- Delete any job post
- Manage user roles

---

## 🔐 API Endpoints

### **1️⃣ Authentication**
**Roles:** PUBLIC

| Method | Endpoint | Description |
|--------|---------|-------------|
| POST   | `/api/auth/signup` | Register as JobSeeker or JobPoster |
| POST   | `/api/auth/login`  | Authenticate and get JWT token |

---

### **2️⃣ Job Management (Job Poster)**
**Roles:** JOB_POSTER

| Method | Endpoint | Description |
|--------|---------|-------------|
| POST   | `/api/jobs`        | Create a new job post |
| PUT    | `/api/jobs/{id}`   | Update job |
| DELETE | `/api/jobs/{id}`   | Delete own job |
| GET    | `/api/jobs/my`     | List poster’s own jobs |

---

### **3️⃣ Job Browsing & Application (Job Seeker)**
**Roles:** JOB_SEEKER

| Method | Endpoint | Description |
|--------|---------|-------------|
| GET    | `/api/jobs`             | Browse all active jobs |
| GET    | `/api/jobs/{id}`        | View specific job |
| POST   | `/api/jobs/{id}/apply`  | Apply for a job |
| POST   | `/api/jobs/{id}/save`   | Save job |
| GET    | `/api/jobs/saved`       | View saved jobs |
| GET    | `/api/jobs/recommend`   | Get recommended jobs |

---

### **4️⃣ Admin**
**Roles:** ADMIN

| Method | Endpoint | Description |
|--------|---------|-------------|
| GET    | `/api/admin/users`      | Get all users |
| DELETE | `/api/admin/user/{id}`  | Delete a user |
| DELETE | `/api/admin/job/{id}`   | Delete a job post |


##  🔐 Authentication Flow (JWT)
1.User logs in with email & password

2.Spring Security authenticates credentials

3.JWT is generated and returned

4.Each API call after login must include:
```
Authorization: Bearer <jwt-token>
```
## 🧠 Recommendation Service (ML Model)
 Engineered a hybrid recommendation engine by combining content-based filtering (TF-IDF) with
 collaborative filtering (SVD-based matrix factorization).



---

## 🧰 Setup Instructions

### 1️⃣ Clone the Repository
```bash
git clone [https://github.com/akhilesh/includify-backend.git](https://github.com/akhilesh/includify-backend.git)
cd includify-backend
```

### 2️⃣ Configure Database & Environment

Create `src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/includify?useSSL=false&serverTimezone=UTC
    username: root
    password: your_db_password
  jpa:
    database-platform: org.hibernate.dialect.MySQLDialect
    hibernate:
      ddl-auto: update
    show-sql: true

server:
  port: 8080

jwt:
  secret: your_jwt_secret_key_here
  expiration-ms: 86400000 # 1 day
```
### 3️⃣ Build & Run
```
mvn clean install
mvn spring-boot:run
```
### 3️⃣ Testing
```
mvn test
```
##  🧩 Future Improvements
1.Email verification and OTP-based signup

2.Resume upload (S3 integration)

3.Notification system for new jobs
## 🧑‍💻 Author
Akhilesh Patil
💼 Software Engineer | ML & Backend Developer

