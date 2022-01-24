package com.betaplan.himi.posts.repositories;

import com.betaplan.himi.posts.models.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post,Long> {
    List<Post> findAll();
    void deleteById(Long id);
}
