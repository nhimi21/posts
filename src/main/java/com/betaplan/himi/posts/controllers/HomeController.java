package com.betaplan.himi.posts.controllers;

import com.betaplan.himi.posts.models.Post;
import com.betaplan.himi.posts.models.User;
import com.betaplan.himi.posts.services.PostService;
import com.betaplan.himi.posts.services.UserService;
import com.betaplan.himi.posts.validator.UserValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class HomeController {
    private final UserService userService;
    private final UserValidator userValidator;
    private final PostService postService;

    public HomeController(
            UserService userService,
            UserValidator userValidator,
            PostService postService) {
        this.userService = userService;
        this.userValidator = userValidator;
        this.postService = postService;
    }

    @GetMapping(value = "/")
    public String index(@ModelAttribute("user") User user) {
        return "register";
    }

    @PostMapping(value = "/register")
    public String registerUser(@Valid @ModelAttribute("user") User user,
                               BindingResult result, HttpSession session) {
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            return "register";
        } else {
            User u = userService.registerUser(user);
            session.setAttribute("userId", u.getId());
        }
        return "redirect:/posts";
    }

    @GetMapping(value = "/login")
    public String logIn() {
        return "login";
    }

    @PostMapping(value = "/auth")
    public String loginUser(@RequestParam(name = "email") String email,
                            @RequestParam(name = "password") String password,
                            Model model, HttpSession session) {
        boolean isAuthenticated = userService.authenticateUser(email, password);
        if (isAuthenticated) {
            User user = userService.findByEmail(email);
            session.setAttribute("userId", user.getId());
            return "redirect:/posts";
        } else {
            model.addAttribute("error", "Invalid, please try again!");
            return "redirect:/login";
        }
    }

    @GetMapping(value = "/posts")
    public String home(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId");
        User u = userService.findUserById(userId);
        model.addAttribute("user", u);
        List<Post> postList = postService.findAllPosts();
        model.addAttribute("posts", postList);
        return "index";
    }

    @GetMapping(value = "/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping(value = "/new")
    public String newPost(Model model) {
        model.addAttribute("post", new Post());
        return "posts";
    }

    @PostMapping(value = "/post/save")
    public String addPost(
            @Valid @ModelAttribute("post") Post post,
            HttpSession session, BindingResult result) {
        if (result.hasErrors()) {
            return "posts";
        }
        Long userId = (Long) session.getAttribute("userId");
        postService.createPost(post, userId);

        return "redirect:/posts";
    }

    @GetMapping(value = "/post/{id}")
    public String editPost(@PathVariable("id") Long id, Model model) {
        Post post = postService.findByIdPost(id);
        model.addAttribute("post", post);
        return "edit";
    }

    @PutMapping(value = "/post/{id}/edit")
    public String update(@Valid @ModelAttribute("post") Post post,
                         HttpSession session, BindingResult result) {
        if (result.hasErrors()) {
            return "edit";
        } else {
            Long userId = (Long) session.getAttribute("userId");
            postService.updatePost(post, userId);
            return "redirect:/posts";
        }
    }

   @DeleteMapping(value = "/post/{id}/delete")
    public String deletePost(HttpSession session, @PathVariable("id") Long id){
       Long userId = (Long) session.getAttribute("userId");
       postService.deletePost(id, userId);
       return "redirect:/posts";
   }

   @GetMapping(value = "/post/{id}/details")
    public String showPost(
            @PathVariable("id") Long id,
            HttpSession session, Model model){

        Post post = postService.findByIdPost(id);
        Long sessionID = (Long) session.getAttribute("userId");
        User user = userService.findUserById(sessionID);

        model.addAttribute("user", user);
        model.addAttribute("post", post);

        return "details";
    }

    @GetMapping(value = "/like/{id}")
    public String like(@PathVariable("id")Long id, HttpSession session){
        Long sessionID = (Long) session.getAttribute("userId");
        postService.likePost(id, sessionID);
        return "redirect:/post/{id}/details";
    }

    @GetMapping(value = "/unlike/{id}")
    public String unlike(@PathVariable("id")Long id, HttpSession session){
        Long sessionID = (Long) session.getAttribute("userId");
        postService.unlikePost(id, sessionID);
        return "redirect:/post/{id}/details";
    }
}