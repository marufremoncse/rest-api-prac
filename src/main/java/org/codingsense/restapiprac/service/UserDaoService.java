package org.codingsense.restapiprac.service;

import org.codingsense.restapiprac.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class UserDaoService {
    private static List<User> users = new ArrayList<>();
    private static int userCount = 0;
    static {
        users.add(new User(++userCount,"Remon", LocalDate.now().minusYears(30)));
        users.add(new User(++userCount,"Tomon", LocalDate.now().minusYears(28)));
        users.add(new User(++userCount,"Tonu", LocalDate.now().minusYears(15)));
        users.add(new User(++userCount,"Sadia Sultana", LocalDate.now().minusYears(20)));
    }

    public List<User> getUsers() {
        return users;
    }

    public User getUser(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }

    public void createUser(User user) {
        user.setId(++userCount);
        users.add(user);
    }

    public void updateUser(User user) {
        users.removeIf(u -> u.getId() == user.getId());
        users.add(user);
    }

    public void deleteUser(int id) {
        users.removeIf(user -> user.getId() == id);
    }
}
