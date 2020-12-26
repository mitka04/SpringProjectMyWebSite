package com.example.SpringProject.repo;

import com.example.SpringProject.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
}
