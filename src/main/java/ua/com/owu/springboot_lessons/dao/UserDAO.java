package ua.com.owu.springboot_lessons.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.com.owu.springboot_lessons.models.User;

import java.util.List;

public interface UserDAO extends JpaRepository<User,Integer> {
    @Query("select u from User u where u.name =:name")
    List<User> getUsersByNAme(@Param("name") String name);
    //@Query annotation is used to define a custom JPQL query.
    //select u from User u specifies that we want to select all User entities.
    //where u.name = :name specifies a condition to filter User entities based on the name attribute.
    //@Param("name") binds the value of the method parameter name to the named parameter name in the JPQL query.
}
//Here, the UserDAO interface specifically defines an interface for performing CRUD operations on the User entity, which has a primary key of type Integer.
// The JpaRepository interface takes care of the boilerplate code involved in performing these operations, such as connecting to the database, executing queries, and returning results.
//
//By extending JpaRepository, the UserDAO interface inherits all of its methods, including methods for saving, updating, deleting, and querying User entities.
// This makes it easy to write code for interacting with the database without having to write low-level SQL queries or database connection code.
