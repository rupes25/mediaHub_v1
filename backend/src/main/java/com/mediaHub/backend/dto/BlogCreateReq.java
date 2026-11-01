
package com.mediaHub.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogCreateReq {

    @NotBlank(message = "Blog title is required")
    @Size(min = 5, max = 150,
            message = "Title must be between 5 and 150 characters")
    private String title;

    @NotBlank(message = "Blog description is required")
    @Size(min = 10, max = 300,
            message = "Description must be between 10 and 300 characters")
    private String description;

    @NotBlank(message = "Blog content is required")
    @Size(min = 20,
            message = "Blog content must contain at least 20 characters")
    private String content;

    @NotBlank(message = "Category is required")
    private String category;

    @NotBlank(message = "Author name is required")
    @Size(min = 2, max = 100,
            message = "Author name must be between 2 and 100 characters")
    private String author;

    @NotNull(message = "Images are required")
    @NotEmpty(message = "At least 5 images are required")
    @Size(min = 5, message = "Upload at least 5 images")
    private List<
            @NotBlank(message = "Image URL cannot be empty")
                    String
            > images;
}
