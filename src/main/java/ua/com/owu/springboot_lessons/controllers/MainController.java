package ua.com.owu.springboot_lessons.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.owu.springboot_lessons.dao.UserDAO;
import ua.com.owu.springboot_lessons.models.User;
import ua.com.owu.springboot_lessons.models.UserDTO;
import ua.com.owu.springboot_lessons.queryFilters.UserSpecifications;
import ua.com.owu.springboot_lessons.views.Views;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/users") //щоб не писати кожного разу /users, а тільки тут 1 раз
public class MainController {

    private UserDAO userDAO;

    @PostMapping("")
    @ResponseStatus(HttpStatus.OK) // якщо метод нічого не повертає
    public void save(@RequestBody @Valid User user){
        userDAO.save(user);
    }

    @GetMapping("")
    @JsonView(value = Views.Client.class)
//  public ResponseEntity<List<User>> getUsers(){
//        Specification<User> userSpecificationByName = (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("name"),"pizza");
//        Specification<User> userSpecificationByAge = (root, query, criteriaBuilder) -> criteriaBuilder.gt(root.get("age"),31);
//        Specification<User> filter = userSpecificationByName.and(userSpecificationByAge);
//        List<User> all = userDAO.findAll(filter);
//        return new ResponseEntity<>(all, HttpStatus.OK);

    public ResponseEntity<List<User>> getUsers(){
        List<User> all = userDAO.findAll(UserSpecifications.byId(5)
                .and(UserSpecifications.byAge(8))
                .and(UserSpecifications.byName("kokos"))
        );
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public UserDTO getUsers(@PathVariable int id){
        User user = userDAO.findById(id).get();
        UserDTO userDTO = new UserDTO(user);
        return userDTO;
    }

    @DeleteMapping("/{id}")
    public List<User> deleteUser(@PathVariable int id){
        userDAO.deleteById(id);
        return userDAO.findAll();
    }

    @PatchMapping("/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user){
        User u = userDAO.findById(id).get();
        u.setName(user.getName());
        userDAO.save(u);
        return u;
    }

    @GetMapping("/name/{nameValue}")
    @JsonView(value = Views.Admin.class)
    public List<User> usersByName(@PathVariable String nameValue){
        List<User> userByNAme = userDAO.getUsersByNAme(nameValue);
        return userByNAme;
    }


}
