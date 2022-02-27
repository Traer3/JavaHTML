package com.hillel.user.controllers;

import com.hillel.user.models.Post;
import com.hillel.user.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/user")
    public String userMain(Model model){
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("post",posts);
        return "user-main";
    }
    @GetMapping("/user/add")
    public String userAdd(Model model){
        return "user-add";
    }
    @PostMapping("/user/add")
    public String userPostAdd(@RequestParam String title, @RequestParam String anons, @RequestParam String full_text, Model model){
        Post post = new Post(title,anons,full_text);
        postRepository.save(post);
        return "redirect:/user";
    }
}
