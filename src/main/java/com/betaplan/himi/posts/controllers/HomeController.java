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

    @GetMapping("/")
    public String index(@ModelAttribute("user") User user) {
        return "register";
    }

    @PostMapping("/register")
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

    @GetMapping("/login")
    public String logIn() {
        return "login";
    }

    @PostMapping(value = "/login")
    public String loginUser(@RequestParam("email") String email,
                            @RequestParam("password") String password,
                            Model model,
                            HttpSession session) {
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

    @RequestMapping("/posts")
    public String home(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId");
        User u = userService.findUserById(userId);
        model.addAttribute("user", u);
        model.addAttribute("posts", postService.findAllPosts());
        return "index";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/posts/new")
    public String newPost(Model model) {
        model.addAttribute("post", new Post());
        return "posts";
    }

    @PostMapping("/posts/save")
    public String addPost(@Valid @ModelAttribute("post") Post post, BindingResult result) {
        if (result.hasErrors()) {
            return "posts";
        }
        postService.createPost(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String editPost(@PathVariable("id") Long id, Model model) {
        model.addAttribute("post", this.postService.findByIdPost(id));
        return "edit";
    }

    @PutMapping("/posts/{id}/edit")
    public String update(@Valid @ModelAttribute("post") Post post, BindingResult result) {
        if (result.hasErrors()) {
            return "edit";
        } else {
            this.postService.createPost(post);
            return "redirect:/posts";
        }
    }
   @DeleteMapping("/posts/{id}/edit")
    public String deletePost(@PathVariable("id") Long id){
       this.postService.deletePost(id);
       return "redirect:/posts";
   }
   @GetMapping("/posts/details/{id}")
    public String showPost(@PathVariable("id") Long id,Model model){
        model.addAttribute("post", this.postService.findByIdPost(id));
        return "details";
    }
    @GetMapping("/like/{id}")
    public String like(@PathVariable("id")Long id, HttpSession session){
        User userToLikePost = this.userService.findUserById((Long) session.getAttribute("userId"));
        Post postToLike = this.postService.findByIdPost(id);
        this.postService.likePost(userToLikePost,postToLike);
    return "redirect:/posts/details/{id}";
    }
    @GetMapping("/unlike/{id}")
    public String unlike(@PathVariable("id")Long id, HttpSession session){
        User userToLikePost = this.userService.findUserById((Long) session.getAttribute("userId"));
        Post postToUnlike = this.postService.findByIdPost(id);
        this.postService.unlikePost(userToLikePost, postToUnlike);
        return "redirect:/posts/details/{id}";
    }
}