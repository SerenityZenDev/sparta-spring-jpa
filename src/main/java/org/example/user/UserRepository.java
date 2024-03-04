package org.example.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.channel.Channel;
import org.example.thread.Thread;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    @PersistenceContext
    EntityManager entityManager;

    public User insertUser(User user) {
        entityManager.persist(user);
        return user;
    }

    public User selectUser(Long id) {
        return entityManager.find(User.class, id);
    }
}
