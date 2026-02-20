# 🪙 Gold Price Alert System (Live)

A production-grade backend application that monitors live gold prices and sends **automatic email alerts** when the price drops below a user-defined target.

Built using **Java 21 (LTS)** and **Spring Boot**, following clean MVC architecture, secure JWT authentication, scheduler-based background processing, and cloud deployment best practices.

---

## 🚀 Live Features

- 🔐 JWT-based Authentication (Register / Login)
- 📈 Live gold price fetching from external API
- ⏱️ Scheduler-based real-time price monitoring
- 🎯 User-defined gold buy alerts
- 📧 Automated **Email notifications** (SMTP)
- 🗄️ PostgreSQL persistence (cloud database)
- ⚙️ Transaction-safe, idempotent alert engine
- 📄 Interactive API documentation using **Swagger (OpenAPI)**
- ☁️ Deployed on **Render** with **Neon PostgreSQL**

---

## 🧠 System Architecture (High Level)
User
↓
Auth API (JWT)
↓
Alert API
↓
Scheduler (15 min)
↓
Gold Price API
↓
Alert Engine
↓
Email Notification (SMTP)


---

## 🛠️ Tech Stack

### Backend
- Java **21 (LTS)**
- Spring Boot **3.5.x**
- Spring Security **6+**
- Spring Data JPA
- Spring Scheduler
- Spring Mail (SMTP)
- Lombok
- JWT (jjwt 0.13.0)

### Database
- PostgreSQL
- Neon (Cloud – Free Tier)

### Deployment
- Render (Backend hosting)
- Environment-based configuration

---

## 🔐 Security

- Password hashing using **BCrypt**
- Stateless authentication using **JWT**
- Protected APIs using Spring Security
- Secrets managed via **environment variables**

---

## 📬 Notification System

- Email notifications using **Gmail SMTP**
- Asynchronous delivery using `@Async`
- Non-blocking scheduler execution
- No duplicate alerts (one-time trigger per alert)

> WhatsApp notifications are **designed and planned** (Meta Cloud API) and can be added later without changing core logic.

---

## 🗃️ Database Schema (Simplified)

### users
| Column | Description |
|------|------------|
| id | Primary key |
| email | Unique user email |
| password | Encrypted password |
| role | USER |

### alerts
| Column | Description |
|------|------------|
| id | Primary key |
| user_id | FK → users |
| target_price | User-defined buy price |
| triggered | Alert status |
| triggered_at | Timestamp |

### gold_price_history
| Column | Description |
|------|------------|
| id | Primary key |
| price_per_gram | Gold price |
| source | API source |
| fetched_at | Timestamp |

---

## 🧪 How to Test Locally (Step-by-Step)

### 1️⃣ Start Application
```bash
mvn spring-boot:run
```
Check:
```
GET http://localhost:8080/health
```

### 2️⃣ Register User

POST /auth/register
```
{
  "email": "yourmail@gmail.com",
  "password": "Test@123"
}
```

### 3️⃣ Login
POST /auth/login

➡️ Copy JWT token from response.

### 4️⃣ Create Alert
POST /alerts
Authorization: Bearer <JWT>
```
{
  "targetPrice": 100000
}
```

### 5️⃣ Trigger Price Fetch (Manual)
GET /price/fetch
Authorization: Bearer <JWT>

## ✅ Email notification will be sent automatically.

---

### ☁️ Deployment (Live)

Backend deployed on Render

PostgreSQL hosted on Neon

Configured using environment variables:

- DB_URL

- DB_USERNAME

- DB_PASSWORD

- MAIL_USERNAME

- MAIL_PASSWORD

- JWT_SECRET

⚠️ Render free tier may sleep on inactivity (scheduler pauses during sleep).

---

## 📌 Future Enhancements

- WhatsApp notifications (Meta Cloud API)

- Price history charts

- Multi-currency support (INR / USD)

- Rate limiting

- Frontend using Next.js (SEO-friendly)

- Docker & CI/CD pipeline

---

# 👨‍💻 Author

### Nagendharreddy Kondapu,
### Java Full-Stack Developer,
### (Backend focused – Spring Boot, Security, Cloud)
