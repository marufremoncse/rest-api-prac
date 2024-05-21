package org.codingsense.restapiprac.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Size(min = 2, max = 50, message = "Name should be in 2 to 50 characters")
    @JsonProperty("user_name")
    private String name;
    @Past(message = "Birth date should be in the past")
    @NotNull(message = "Birth date must not be null")
    @JsonProperty("birth_date")
    private LocalDate birthdate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Post> posts;

    public User() {
    }

    public User(int id, String name, LocalDate birthdate) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
