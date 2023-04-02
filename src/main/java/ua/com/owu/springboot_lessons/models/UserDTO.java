package ua.com.owu.springboot_lessons.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private String name;
    private int age;

    public UserDTO(User user) {
        this.name=user.getName();
        this.age= user.getAge();
    }
}
//Using a DTO can be useful in situations where the client and server use different data models or when we want to control what data is sent to the client to reduce network traffic.
