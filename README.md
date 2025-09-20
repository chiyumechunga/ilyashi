# Ilyashi

*A Spring Boot backend for anonymous and pseudonymous blogging.*

---

**Ilyashi** is a Bemba word meaning *“voice”* or *“word.”*  
This project is a backend API designed to power a blogging platform where users have complete control over their identity, empowering them to share their voice freely.  

It’s built on the principle that a user should be able to maintain a consistent public identity (a pseudonym) or post with complete anonymity, on a per-post basis.

---

## ✨ Key Features

- **Secure User Registration**  
  Users sign up with a private username for login and a unique public alias for pseudonymous posts. Passwords are securely hashed using **Spring Security**.

- **Hybrid Anonymity** *(core feature)*  
  When creating a post, a user can choose:  
  - Publish under their **public alias**.  
  - Publish **anonymously**, detaching the post from their public identity.

- **Post Management**  
  Posts can be saved as `DRAFT` or `PUBLISHED`, giving authors control over visibility.

- **RESTful API**  
  A clean, logical API for easy integration with any frontend (React, Angular, Vue, etc.).

- **JPA-backed Persistence**  
  Built with **Spring Data JPA** for robust database interaction with **PostgreSQL**.

---

## 🛠️ Technology Stack

**Backend**
- Java 17  
- Spring Boot 3.x  
- Spring Web  
- Spring Data JPA  
- Spring Security  

**Database**
- PostgreSQL  

**Build & Tooling**
- Gradle  
- Lombok  

---

## 🚀 Getting Started

Follow these steps to set up a local development environment.

### Prerequisites

Make sure you have the following installed:

- **JDK 17** or later  
- **Gradle 7.x** or later  
- **PostgreSQL 14+**

### Installation & Setup

1. **Clone the repository**  
   ```bash
   git clone https://github.com/chiyumechunga/ilyashi.git
   cd ilyashi
