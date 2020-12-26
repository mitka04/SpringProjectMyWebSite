package com.example.SpringProject.controllers;

import com.example.SpringProject.models.Post;
import com.example.SpringProject.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class PriceControllers {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/price")
    public String price(Model model){
        model.addAttribute("price", "Цены");
        return "price";
    }

    @GetMapping("/priceId/{id}")
    public String priceId(@PathVariable(value = "id") long id, Model model){
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "priceId";
    }
}
