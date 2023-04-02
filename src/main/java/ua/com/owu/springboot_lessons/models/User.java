package ua.com.owu.springboot_lessons.models;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;
import ua.com.owu.springboot_lessons.views.Views;

@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity // generated id
public class User {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @JsonView(value = Views.Admin.class)
    private int id;

    @NotBlank(message = "name can`t be empty") //string is not null and has at least one non-whitespace character
    @Size(min = 2,message = "name is too short")
    @Size(max = 6,message = "name is too long")
    @JsonView(value = {Views.Client.class,Views.Admin.class})
//    @Pattern(regexp = "^[A-Za-z0-9]+$",message = "invalid username") - is used to specify a regular expression that a field must match in order to be considered valid.
    private String name;

    @Min(value = 0,message = "age can`t be lt zero")
    @Max(value = 123,message = "too old")
    @JsonView(value = {Views.Client.class,Views.Admin.class})
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
