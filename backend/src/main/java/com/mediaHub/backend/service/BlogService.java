package com.mediaHub.backend.service;

import com.mediaHub.backend.dto.BlogCreateReq;
import com.mediaHub.backend.dto.BlogCreateRes;
import com.mediaHub.backend.model.Blog;
import com.mediaHub.backend.repository.BlogRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;

    public String createBlog(BlogCreateReq req, HttpServletResponse response){
        Blog blog  = new Blog();
        blog.setTitle(req.getTitle());
        blog.setDescription(req.getDescription());
        blog.setContent(req.getContent());
        blog.setCategory(req.getCategory());
        blog.setAuthor(req.getAuthor());
        blog.setImages(req.getImages());

        Blog savedBlog = blogRepository.save(blog);
        return "Blog created successfully.";
    }

}
