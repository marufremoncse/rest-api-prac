package org.codingsense.restapiprac.controller;

import jakarta.validation.Valid;
import org.codingsense.restapiprac.exception.UserNotFoundException;
import org.codingsense.restapiprac.model.Post;
import org.codingsense.restapiprac.model.User;
import org.codingsense.restapiprac.repository.PostRepository;
import org.codingsense.restapiprac.repository.UserRepository;
import org.codingsense.restapiprac.service.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {
    @Autowired
    UserDaoService userDaoService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @GetMapping("/users")
    public List<User> getUsers() {
        //return userDaoService.getUsers();
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> getById(@PathVariable int id) {
        //User user = userDaoService.getUser(id);
        User user = userRepository.findById(id).get();
        if(user==null){
            throw new UserNotFoundException("id: "+id+" not found");
        }
        EntityModel<User> model = EntityModel.of(user);
        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).getUsers());
        //model.add(linkTo.withSelfRel());
        model.add(linkTo.withRel("all-users"));
        WebMvcLinkBuilder linkToPerson = WebMvcLinkBuilder.linkTo(methodOn(PersonController.class).getSecondVersionOfPerson());
        model.add(linkToPerson.withRel("person"));
        return model;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        //userDaoService.createUser(user);
        userRepository.save(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/users")
    public void updateUser(@Valid @RequestBody User user) {
        //userDaoService.updateUser(user);
        userRepository.save(user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        //userDaoService.deleteUser(id);
        userRepository.deleteById(id);
    }

    @GetMapping("users/{id}/posts")
    public List<Post> getPosts(@PathVariable int id) {
        User user = userRepository.findById(id).get();
        if(user==null){
            throw new UserNotFoundException("id: "+id+" not found");
        }
        return user.getPosts();
    }

    @PostMapping("users/{id}/posts")
    public EntityModel<Post> savePosts(@PathVariable int id, @Valid @RequestBody Post post) {
        User user = userRepository.findById(id).get();
        if(user==null){
            throw new UserNotFoundException("id: "+id+" not found");
        }
        post.setUser(user);
        postRepository.save(post);

        EntityModel<Post> model = EntityModel.of(post);
        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).getPosts(id));
        //model.add(linkTo.withSelfRel());
        model.add(linkTo.withRel("all-posts"));

        return model;
    }
}
