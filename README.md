   Restaurants 
   =
   ### Java Enterprise Online Project

This is a graduation project for the [Topjava](https://topjava.ru/topjava) course.
The task for graduation project was:

---------------------------------------------------------------------------------------------------
Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) without frontend.
Build a voting system for deciding where to have lunch.

- 2 types of users: admin and regular users
- Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
- Menu changes each day (admins do the updates)
- Users can vote on which restaurant they want to have lunch at
- Only one vote counted per user
- If user votes again the same day:
   1. If it is before 11:00 we assume that he changed his mind.
   2. If it is after 11:00 then it is too late, vote can't be changed
Each restaurant provides a new menu each day.
  
---------------------------------------------------------------------------------------------------
- Run: `mvn spring-boot:run` in root directory. Then Swagger will be available [here](http://localhost:8080/swagger-ui.html) , 
test credentials:
```
Admin: admin@gmail.com / admin
User:  user@gmail.com / password
Guest: guest@gmail.com / guest
```