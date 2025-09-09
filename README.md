# Chat App

### 📌 Overview

This is a real-time chat application built using Spring Boot and WebSockets (STOMP over SockJS).
It provides:

* Secure user registration and authentication (Spring Security + BCrypt)
* Role-based access control (ROLE_USER, ROLE_ADMIN)
* Real-time chat messaging with WebSockets
* Contact list and chat UI (Thymeleaf + JavaScript)
* In-memory tracking of online users
* Responsive UI styled with CSS

### 🏗️ Architecture

#### 🔹 Backend (Spring Boot)

1. **WebSocket Config** (Config.java) – Configures STOMP endpoint /chat, app prefix /app, and broker /topic.


2. **Security** (SecurityConfig.java) – Manages authentication, login form, and role-based routes.


3. **User Management**

* UserDtls (entity, JPA)
* UserRepository (Spring Data JPA)
* UserService + UserServiceImpl (business logic)
* CustomUserDetails, UserDetailsServiceImpl (Spring Security integration)

4. **Controllers**

* HomeController – Login, register, and index routes
* UserController – Displays contacts, chat pages
* MessageController – Handles /app/chat/{to} WebSocket messages
* UsersController – Manages in-memory user registrations (active users)

#### 🔹 Frontend

1. **Thymeleaf templates:**

* index.html – Entry page
* login.html – User login
* register.html – User registration
* user/chat.html – Chat window

2. **JavaScript:**

* chat.js, custom.js – Manage WebSocket connection, sending/receiving messages, updating UI

3. **CSS:**

* design.css, style.css – Styling for chat interface

### ⚙️ Technologies Used

1. Java 17+
2. Spring Boot 3.x
3. Spring WebSocket (STOMP + SockJS)
4. Spring Security (form login + BCrypt)
5. Spring Data JPA + Hibernate
6. H2/MySQL (configurable)
7. Thymeleaf
8. JavaScript (SockJS, Stomp.js, jQuery, Handlebars.js)

### ▶️ Running the Application
1. **Clone the repo**

       git clone https://github.com/your-repo/viral-fission-chat.git
       cd viral-fission-chat

2. **Configure the database**

Default setup uses H2 (in-memory).

src/main/resources/application.properties:

    # H2 (default)
    spring.datasource.url=jdbc:h2:mem:chatdb
    spring.datasource.driverClassName=org.h2.Driver
    spring.datasource.username=sa
    spring.datasource.password=
    spring.jpa.hibernate.ddl-auto=update
    spring.h2.console.enabled=true

For MySQL, create application-mysql.properties:

    spring.datasource.url=jdbc:mysql://localhost:3306/chatdb
    spring.datasource.username=root
    spring.datasource.password=yourpassword
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

Run with:

    mvn spring-boot:run -Dspring-boot.run.profiles=mysql

3. **Start the application** 


    mvn spring-boot:run

4. **Access in browser**

* Home → http://localhost:8080/
* Register → http://localhost:8080/register
* Login → http://localhost:8080/signin
* User Dashboard → http://localhost:8080/user/

### 🌐 WebSocket Messaging

* **Endpoint:** /chat (SockJS enabled)
* **Application Prefix:** /app
* **Broker Prefix:** /topic

**Flow:**

1. User connects → SockJS('/chat')
2. Send message → /app/chat/{recipient}
3. Receive messages → /topic/messages/{username}

### 👤 User Roles

1. Admin: /admin/**
2. User: /user/**
3. Public: /, /signin, /register

Default role for new users: ROLE_USER

### 📂 Project Structure

    com.viralfission.chat
    │── ViralFissionChatAppApplication.java   # Main Spring Boot class
    │
    ├── config/       # WebSocket & Security configs
    ├── controller/   # Controllers (REST + WebSocket)
    ├── model/        # Entities (UserDtls, Message)
    ├── repository/   # JPA repositories
    ├── service/      # User service layer
    ├── storage/      # In-memory user tracking
    └── resources/
    ├── templates/ # Thymeleaf (login, register, chat)
    ├── static/    # CSS + JS files
    └── application.properties

### 🧪 Features to Try

1. Register two users (User A, User B)
2. Log in with User A → connect to chat
3. Log in with User B in another browser → connect to chat
4. Select User B in User A’s list and send a message
5. See real-time message delivery through WebSockets

### 📸 Screenshots

* Login Page
* Register Page
* Chat UI with contacts + messages

_(Add screenshots here if needed)_

### 👩‍💻 Author

Sayantika Kandar