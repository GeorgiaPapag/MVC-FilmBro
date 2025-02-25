# MVC-FilmBro Project

## Overview

This is a Spring Boot project that involves a locally hosted server application, designed to serve the "Customize My Profile" use case as part of the larger application FilmBro (team's project name). The server is managed locally and written in Java by using Maven as the building tool.

## Utilizes

1. Spring Boot
2. Java
3. Database (name of the database in MySQL Workbench "ismgroup20")

## Set up and Build

1. Clone the repository:
    ```bash
        git clone https://github.com/GeorgiaPapag/MVC-FilmBro.git
    ```
2. Navigate to the project resources:
    ```bash
        cd MVC-FilmBro/src/main/resources
    ```
   
3. Build the project:

    ```bash
        mvn clean install
    ```

4. Run the application:

    ```bash
        java -jar target\MVC-FilmBro-0.0.1-SNAPSHOT.jarjar-with-dependencies.jar
    ```
   **You can also run the project by Intellij.

5. The connection with the database have already done in the file "application.properties" 

## Use Case

The use case allows users to edit their profile, update their personal details/information and choose their visibility to other users (to all users or only to followers), to modify or delete their lists and reviews/ratings.

## Scenario

1. Open the project.
2. Click on login button and add the credentials:
        username: Georgia
        password: 123456
3. Now you can view your own profile (buttons: Seen, Favorites, Watchlist, My Reviews and My Movie Lists are static).
4. Click on the button "Edit Profile".
5. Now you can edit your profile.
6. You can change the name (click the pencil to write), the email, the info and the profile visibility (hover over the two radios buttons to see the messages). You can click on the camera icon and see the available avatars or the country to see the list of the countries.
        For example, change the name to GeorgiaPap.
7. If you want to keep the changes choose save, in this case you will return to the profile page but the details will have changed. Otherwise, if you do not want to keep the changes choose cancel which will sent you back to the profile page without any change in your details.
8. You can log out from the profile by choosing the option "logout" in the dropdown that opens when you click on the profile picture in the header.

** In the edit profile does not exit the streaming services. Also, I do not use the Api we have used in the team project the countries are manually passed. 

## Test

1. Try to log in by using invalid credentials. It will show a message.
2. Delete the name from the form and try to save. It will not let you. 

## ChatGTP

1. 3 tier architecture in spring boot
   In a 3-tier architecture for a Spring Boot application, we divide the application into three distinct layers or tiers: the Presentation Layer, the Business Logic Layer, and the Data Access Layer. This separation of concerns helps in organizing the application better and makes it scalable, maintainable, and easier to manage.

Here’s how you can structure each layer in Spring Boot:

a. Presentation Layer (Controller Layer)
   This is the layer that interacts with the user and handles HTTP requests.
   It processes input from the user and returns the output after interacting with the business logic layer.
   In Spring Boot, controllers are usually annotated with @RestController or @Controller.

   Example:
       @RestController
       @RequestMapping("/api")
       public class UserController {
    
       @Autowired
       private UserService userService;
    
       @GetMapping("/users")
       public List<User> getAllUsers() {
       return userService.getAllUsers();
       }
    
       @PostMapping("/users")
       public User createUser(@RequestBody User user) {
       return userService.createUser(user);
       }
       }


b. Business Logic Layer (Service Layer)
   This layer contains the business logic of the application. It acts as an intermediary between the presentation and data access layers.
   It is responsible for processing data, applying business rules, and returning results to the controller layer.
   In Spring Boot, services are typically annotated with @Service.
    
    Example:
       @Service
       public class UserService {
    
       @Autowired
       private UserRepository userRepository;
    
       public List<User> getAllUsers() {
       return userRepository.findAll();
       }
    
       public User createUser(User user) {
       return userRepository.save(user);
       }
       }


c. Data Access Layer (Repository Layer)
   This layer is responsible for interacting with the database or any other data storage.
   It abstracts the details of data persistence and retrieval, making it easier to interact with data sources.
   In Spring Boot, repositories are typically interfaces that extend JpaRepository or CrudRepository (for working with databases).

    Example:
       @Repository
       public interface UserRepository extends JpaRepository<User, Long> {
       // Custom queries can be defined here
       }

2. Cannot resolve symbol 'RedirectAttributes'

You're seeing the "Cannot resolve symbol 'RedirectAttributes'" error because you haven't imported the necessary class.

✅ Solution: Add the Import Statement
At the top of your controller file, add this import:

    import org.springframework.web.servlet.mvc.support.RedirectAttributes;

3. Help me write the method for getting all the countries

       public String getCountryNameByCode(String code) {
       return countries.stream()
       .filter(country -> country.getCode().equalsIgnoreCase(code))
       .map(Country::getName)
       .findFirst()
       .orElse("Choose a Country"); // Default text
       }

4. Error :Whitelabel Error Page
   This application has no explicit mapping for /error, so you are seeing this as a fallback.
    Help me with debbuging


   Check the Form Submission in HTML
   Ensure that your HTML form (or wherever the form is being submitted) includes the userId parameter. For example, your form might look like this:

    Steps to Debug and Fix the Issue:
    a. Check for Missing Parameters or Invalid Data:
    <form method="POST" action="/updateProfile">
        <input type="hidden" name="userId" value="${user.userId}" />
        <input type="text" name="name" value="${user.userName}" />
        <input type="email" name="userEmail" value="${user.email}" />
        <input type="text" name="country" value="${user.country}" />
        <textarea name="bio">${user.bio}</textarea>
        <input type="text" name="avatarUrl" value="${user.profilePic}" />
        <input type="number" name="profileVisibility" value="${user.accountStatus}" />
        <button type="submit">Update Profile</button>
    </form>

    b. Print User Data Before Update
    System.out.println("Executing update query for user: " + user);
        System.out.println("User Data - Username: " + user.getUserName());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Country: " + user.getCountry());
        System.out.println("Profile Pic: " + user.getProfilePic());
        System.out.println("Account Status: " + user.getAccountStatus());
        System.out.println("Bio: " + user.getBio());
        System.out.println("User ID: " + user.getUserId());

# Appreciation for UI Contributors

1. Hlias Mpourdakos (https://github.com/HliasMpGH)
2. Panos Daskalopoulos (https://github.com/panos1b)
3. Dimitris Papathanasiou (https://github.com/dimitriospapathanasiou)