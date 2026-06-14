package com.ecommerce.demo.client;

import lombok.Data;

@Data
public class ExternalPostDTO {
    private Long userId;
    private Long id;
    private String title;
    private String body;
}
