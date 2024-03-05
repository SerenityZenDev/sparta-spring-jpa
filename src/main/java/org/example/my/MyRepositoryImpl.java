package org.example.my;


import jakarta.persistence.EntityManager;
import java.util.List;
import org.example.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class MyRepositoryImpl implements MyRepository<User> {

    @Autowired
    EntityManager em;


    @Override
    public void delete(User user) {
        em.remove(user);
    }

    @Override
    public List<String> findNameAll() {
        return em.createQuery("select u.username from User AS u", String.class).getResultList();
    }
}
