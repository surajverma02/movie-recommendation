# Movie Recommendation System

## Overview
This is a movie recommendation system built using **Spring Boot** and **JWT** for secure user authentication and authorization. The system allows users to search, rate, add movies to their favorites, create watch lists, and view details about movies, producers, and actors. It also includes robust CRUD operations for movie management.

## Database Schema
<img src="/Movie Database.png" alt="database image"/>

## Features
- **User Authentication & Authorization**: Secure login and registration using **JWT** tokens.
- **Movie Collection**: CRUD operations for movies (name, IDENTITY id, genre, release year, rating).
- **Watchlist & Favorites**: Users can add movies to their personal watch lists and favorites.
- **Producer & Actor Details**: Information about movie producers and actors.
- **Search Functionality**: Search movies by genre, title, release year, and more.
- **Spring Security Integration**: Protects endpoints and ensures role-based access control.
- **Database**: Using **MySQL** for storing movie, user, and watchlist data.

## Authorisation
.requestMatchers("/", "/home", "/auth/**").permitAll()<br/>
.requestMatchers("/user/**", "/favourite/**", "/movie/**", "/wishlist/**").hasRole("USER")<br/>
.requestMatchers("/admin/**", "/actor/**", "/producer/**").hasRole("ADMIN")<br/>
.anyRequest().authenticated())

## Technologies Used
- **Backend**: Spring Boot, Spring Security, MySQL, JPA, JWT(Yet to implement, code is ready and implemented on different project )
- **Frontend**: React (optional)(Yet not prepared)
- **Database**: MySQL
- **Testing**: JUnit, Mockito

### Prerequisites
- Java 21
- MySQL
- Maven
- Postman (for testing APIs)

### Installation & Setup

1. Clone the repository::
   ```bash
   git clone https://github.com/your-username/movie-recommendation-system.git
   cd movie-recommendation-system

2. Create a MySQL database::
   ```bash
   CREATE DATABASE movie_recommendation;

3. Update MySQL credentials in application.properties::
   ```bash
    spring.datasource.url=jdbc:mysql://localhost:3306/movie_recommendation
    spring.datasource.username=your_username
    spring.datasource.password=your_password
   
4. Run the application::
    ```bash
   mvn spring-boot:run

5. Access the API via Postman or browser at:::
    ```bash
    http://localhost:8080

## Future Enhancements
1. Add recommendation algorithms based on user preferences and ratings.
2. Implement social sharing features for watch lists and favorites.