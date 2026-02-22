# 🟡 Gold Price Alert System

A production-ready backend + lightweight frontend application that notifies users via **email alerts** when gold prices drop to their target value.  
Built with **Java 21**, **Spring Boot**, and a **SEO-friendly static frontend**.

---

## 🚀 Live Demo

- **Frontend**: https://gold-alert.netlify.app  
- **Backend API**: https://gold-alert-api.onrender.com/api

---

## 📌 Features

- 🔐 JWT-based authentication (stateless, secure)
- 👤 User registration & login
- 📉 Real-time gold price fetching
- ⏰ Alert engine to trigger notifications when price condition matches
- 📧 Email notifications (Brevo SMTP – production-safe)
- ⚡ Asynchronous notification processing
- 🌍 SEO-friendly static frontend (HTML/CSS/JS + Bootstrap)
- 🛡️ CORS & security properly configured for cloud deployment

---

## 🧱 Tech Stack

### Backend
- Java **21 (LTS)**
- Spring Boot **3.x**
- Spring Security **6**
- Spring Data JPA
- PostgreSQL (Neon)
- JWT Authentication
- Brevo SMTP (Transactional Email)
- Render (Deployment)

### Frontend
- HTML5, CSS3
- Vanilla JavaScript
- Bootstrap 5
- Netlify (Deployment)

---

## 🏗️ Architecture Overview

- **MVC Architecture**
- RESTful APIs
- Stateless authentication using JWT
- Notification system designed via `NotificationService` interface  
  (email implemented, WhatsApp planned as future enhancement)

---

## 📂 Project Structure (Backend)
```
com.goldalert
├── client # External API clients (gold price API, email/notification clients)
├── config # Application, CORS, async, and infrastructure configuration
├── controller # REST controllers (Auth, Alert, Gold Price APIs)
├── dto # Request & response DTOs
├── exception # Custom exceptions & global exception handlers
├── model # JPA entities (User, Alert, GoldPrice, etc.)
├── repository # Spring Data JPA repositories
├── scheduler # Scheduled jobs (price polling & alert trigger engine)
├── security # JWT, filters, security configuration
├── service # Business logic & notification services
├── template # Email/message templates
└── test # Unit & integration tests
```

### 📌 Package Responsibilities

- **client** – Handles communication with third-party services (gold price APIs, email providers)
- **config** – Centralized configuration (CORS, async, beans, environment setup)
- **controller** – Exposes REST endpoints
- **dto** – Keeps API contracts clean and decoupled from entities
- **exception** – Centralized error handling and custom exceptions
- **model** – Database entities mapped with JPA/Hibernate
- **repository** – Data access layer
- **scheduler** – Background jobs and alert engine triggers
- **security** – Authentication & authorization using JWT
- **service** – Core business logic
- **template** – Email and notification message builders
- **test** – Automated tests

---

## 🔐 Authentication Flow

1. User registers with email & password
2. Password is stored using BCrypt hashing
3. User logs in → JWT token generated
4. Token required for all secured APIs
5. Stateless session management (scalable & cloud-ready)

---

## 📧 Email Notification System

- Uses **Brevo SMTP** (cloud-safe alternative to Gmail SMTP)
- Emails are sent **asynchronously** using `@Async`
- Verified sender address is configured to ensure delivery
- Designed to avoid cloud SMTP port restrictions

> Note: Gmail SMTP works locally but is blocked on most cloud providers.  
> Brevo SMTP ensures reliable delivery in production.

---

## 🌐 Frontend Routing

Clean, SEO-friendly URLs using Netlify redirects:

```
/ → Home
/login → Login
/register → Register
/dashboard → Dashboard
```

No `.html` extensions exposed in URLs.

---

## 🧪 Testing & Validation

- Backend tested locally and on cloud (Render)
- Email alerts verified end-to-end in production
- SEO & performance validated using **Google Lighthouse**
- PageSpeed scores consistently high due to static frontend

---

## 🔧 Environment Variables (Backend)

```
PORT=8080
SERVER_ALLOWED_ORIGINS=https://gold-alert.netlify.app

DB_URL=jdbc:postgresql://...
DB_USERNAME=...
DB_PASSWORD=...

JWT_SECRET=your_jwt_secret

MAIL_HOST=smtp-relay.brevo.com
MAIL_PORT=2525
MAIL_USERNAME=brevo_smtp_login
MAIL_PASSWORD=brevo_smtp_password
```

---

## 📈 Future Enhancements

- 📲 WhatsApp alerts (Meta WhatsApp Cloud API)
- 📊 Gold price history charts
- ⏱️ Scheduler-based automatic price polling
- 🌍 Multi-currency support
- 🔁 Retry & fallback notification mechanism

---

## 🧠 Key Learnings

- Cloud platforms often block SMTP ports like 25/587
- Production email delivery requires verified senders
- Environment-based configuration is critical for security
- Static frontends provide excellent SEO & performance

---

## 👤 Author

**Nagendhar Reddy**  
Backend / Full Stack Java Developer  

---

## 📜 License

This project is for learning and demonstration purposes.
