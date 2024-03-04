package org.example.thread;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.channel.Channel;
import org.springframework.stereotype.Repository;

@Repository
public class ThreadRepository {
    @PersistenceContext
    EntityManager entityManager;

    public Thread insertChannel(Thread thread) {
        entityManager.persist(thread);
        return thread;
    }

    public Thread selectChannel(Long id) {
        return entityManager.find(Thread.class, id);
    }

    public Channel selectThread(Long id) {
        return entityManager.find(Channel.class, id);
    }
}
