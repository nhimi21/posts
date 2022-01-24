package com.betaplan.himi.posts.repositories;

import com.betaplan.himi.posts.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    User findByEmail(String email);
    User findByFullName(String fullName);
}
