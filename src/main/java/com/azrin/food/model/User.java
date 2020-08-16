package com.azrin.food.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Document(collection = "user")
public class User {

    @Id
    private String id;
    private String email;
    private String password;
    private String roleName;
    private boolean active;

}
