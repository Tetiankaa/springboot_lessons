package ua.com.owu.springboot_lessons.controllers;

import org.springframework.web.bind.annotation.*;
import ua.com.owu.springboot_lessons.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//@RestController is an annotation in Spring Framework used to mark a Java class as a controller where all methods return the representation of the resource using the HTTP response,
// instead of relying on a view technology to perform server-side rendering of the HTML.
@RestController
public class MainController {
//In this example, when a user makes a GET request to the root URL of the application (e.g. http://localhost:8080/), the homePage() method will be called and the text "hello" will be returned as the response.

    private List<User> users = new ArrayList<>();

    public MainController(){
        users.add(new User(1,"orange"));
        users.add(new User(2,"coconut"));
        users.add(new User(3,"banana"));
    }
    @GetMapping("/") //used to map HTTP GET requests to the root URL of a web application.
    private String homePage(){
        return "hello";
    }
    @GetMapping("/users")
    public List<User> getUsers(){
        return users;// will be returned array
    }

    @GetMapping("/users/{id}") //The {id} part is a path variable that can be used to specify which user to retrieve.
    public User getUser(@PathVariable("id") int id){
        //@PathVariable("id") - якщо int id називається наприклад int idx, то після PathVariable обов'язково id писати, бо тоді може не зрозуміти яке id мається на увазі
        User user1 = users
                .stream() //is a method available in Java 8 and above that is used to create a stream from a collection or an array. A stream is a sequence of elements that can be processed in parallel or sequentially.
                .filter(user -> user.getId() ==id)
                .collect(Collectors.toList()).get(0); //is used to get the first and only User object from the list of users that match the id passed in the URL path variable
        return user1;
    }

    //This is a method for adding a new user to the list of existing users. It uses the HTTP POST method to receive a new user object in the request body,
    // adds it to the list of existing users, and then returns the updated list of users.
    @PostMapping("/users")
    public List<User> saveUser(@RequestBody User user){ //indicates that the user object should be retrieved from the request body and mapped to the User class.
        users.add(user);
        return users;
    }
}
