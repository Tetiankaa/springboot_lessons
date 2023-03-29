package ua.com.owu.springboot_lessons.controllers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;
import ua.com.owu.springboot_lessons.dao.UserDAO;
import ua.com.owu.springboot_lessons.models.User;

import java.util.List;

@RestController
@AllArgsConstructor
public class MainController {

    private UserDAO userDAO;
    @PostMapping("/users")
    public void save(@RequestBody User user){

        userDAO.save(user);
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        List<User> allUsers = userDAO.findAll();
        return allUsers;
    }

    @GetMapping("/users/{id}")
    public User getUsers(@PathVariable int id){
        User user = userDAO.findById(id).get();
        return user;
    }

    @DeleteMapping("/users/{id}")
    public List<User> deleteUser(@PathVariable int id){
        userDAO.deleteById(id);
        return userDAO.findAll();
    }

    @PatchMapping("/users/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user){
        User u = userDAO.findById(id).get();
        u.setName(user.getName());
        userDAO.save(u);
        return u;
    }

    @GetMapping("/users/name/{nameValue}")
    public List<User> usersByName(@PathVariable String nameValue){
        List<User> userByNAme = userDAO.getUsersByNAme(nameValue);
        return userByNAme;
    }


}
