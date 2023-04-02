package ua.com.owu.springboot_lessons.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.com.owu.springboot_lessons.models.User;

import java.util.List;

public interface UserDAO extends JpaRepository<User,Integer>, JpaSpecificationExecutor<User> {
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

//JpaSpecificationExecutor is an interface in Spring Data JPA that provides a way to build complex queries using the JPA criteria API. It extends the JpaRepository interface and allows for
// creating more complex queries by specifying various conditions using a Specification object.
//
//The Specification interface provides two methods, toPredicate and and, that allow the creation of complex queries. The toPredicate method is used to convert a Specification into a Predicate,
// which can be used in a query. The and method allows combining multiple Specification objects into a single Specification.
//
//Using JpaSpecificationExecutor, we can create queries that involve multiple entities and multiple conditions. It provides a way to dynamically build queries at runtime based on various parameters.
// This makes it very useful for building complex search queries where the user can specify various search criteria.
//Тобто наприклад щоб відібрати юзерів за якимось критеріями, а не всіх.Він розширює інтерфейс JpaRepository і дозволяє створювати складніші запити, вказуючи різні умови за допомогою об’єкта Specification.