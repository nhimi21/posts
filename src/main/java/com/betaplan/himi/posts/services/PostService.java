package com.betaplan.himi.posts.services;

import com.betaplan.himi.posts.models.Post;
import com.betaplan.himi.posts.models.User;
import com.betaplan.himi.posts.repositories.PostRepository;
import com.betaplan.himi.posts.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    //Create new post and edit or update
    public void createPost(Post post, Long id){

        User u = userRepository.findById(id).orElseThrow(null);
        post.setUserID(u);
        postRepository.save(post);
    }

    public void updatePost(Post post, Long id){
        User u = userRepository.findById(id).orElseThrow(null);
        Post result = postRepository.findById(post.getId()).orElseThrow(null);
        Long userId = result.getUserID().getId();
        if (u.getId() != userId)
            throw new RuntimeException("You aren`t author for edit this post!");
        else
            postRepository.save(post);
    }
    //Find all Posts in yor db table
    public List<Post> findAllPosts(){
        List<Post> list = postRepository.findAll();
        for (Post p: list){
            if (p.getNrOfLikes() == null){
                p.setNrOfLikes(0);
            }
        }
        return list;
    }

    //Find one post by id
    public Post findByIdPost(Long id){
        return postRepository.findById(id).orElse(null);
    }

    //Delete one post by id
    public void deletePost(Long id, Long userId) {
        boolean exists = postRepository.existsById(id);
        User u = userRepository.findById(userId).orElseThrow(null);
        Post post = new Post();
        if (exists){
            post = postRepository.findById(id).orElseThrow(null);
            Long user = post.getUserID().getId();
            if (u.getId() != user){
                throw new IllegalStateException("You can`t delete this post because you aren`t author!");
            } else
                postRepository.deleteById(id);
        } else
            throw new IllegalStateException("Post with id" + id + "does not exists");
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
