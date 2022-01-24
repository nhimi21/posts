package com.betaplan.himi.posts.services;

import com.betaplan.himi.posts.models.Post;
import com.betaplan.himi.posts.models.User;
import com.betaplan.himi.posts.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    //Create new post and edit or update
    public void createPost(Post post){
        this.postRepository.save(post);
    }
    //Find all Posts in yor db table
    public List<Post> findAllPosts(){
        return this.postRepository.findAll();
    }
    //Find one post by id
    public Post findByIdPost(Long id){
        return this.postRepository.findById(id).orElse(null);
    }
    //Delete one post by id
    public void deletePost(Long id) {
        boolean exists = postRepository.existsById(id);
        if (!exists){
            throw new IllegalStateException("Post with id" + id + "does not exists");
        }
        this.postRepository.deleteById(id);
    }
    //like a Post
    public void likePost(User user,Post post){
        List<User> userWhoLiked = post.getUserLikes();
        userWhoLiked.add(user);
        this.postRepository.save(post);
    }
    //unlike a Post
    public void unlikePost(User user,Post post){
        List<User> userWhoLiked = post.getUserLikes();
        userWhoLiked.remove(user);
        this.postRepository.save(post);
    }
}
