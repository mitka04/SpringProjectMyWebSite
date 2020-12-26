package com.example.SpringProject.controllers;

import com.example.SpringProject.models.Post;
import com.example.SpringProject.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class ProductsController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/products")
    public String product(Model model){
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "products";
    }

    @GetMapping("/product/add")
    public String productAdd(Model model){
        return "product-add";
    }

    @PostMapping("/product/add")
    public String productPostAdd(@RequestParam String title,
                                 @RequestParam String anons,
                                 @RequestParam String full_text,
                                 @RequestParam int price, Model model){
        Post post = new Post(title, anons, full_text, price);
        postRepository.save(post);
        return "redirect:/products";
    }

    @GetMapping("/product/{id}")
    public String productInfo(@PathVariable(value = "id") long id, Model model){
        if(!postRepository.existsById(id)){
            return "redirect:/products";
        }
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "product-details";
    }

    @GetMapping("/product/{id}/edit")
    public String productEdit(@PathVariable(value = "id") long id, Model model){
        if(!postRepository.existsById(id)){
            return "redirect:/products";
        }
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "product-edit";
    }

    @PostMapping("/product/{id}/edit")
    public String productPostUpdate(@PathVariable(value = "id") long id,
                                    @RequestParam String title,
                                    @RequestParam String anons,
                                    @RequestParam String full_text, Model model){
        Post post = postRepository.findById(id).orElseThrow();
        post.setTitle(title);
        post.setAnons(anons);
        post.setFull_text(full_text);
        postRepository.save(post);
        return "redirect:/products";
    }

    @PostMapping("/product/{id}/remove")
    public String productPostDelete(@PathVariable(value = "id") long id, Model model){
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
        return "redirect:/products";
    }
}
