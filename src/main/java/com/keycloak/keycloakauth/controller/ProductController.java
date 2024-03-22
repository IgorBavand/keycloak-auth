package com.keycloak.keycloakauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/products")
public class ProductController {

    @GetMapping
    public String viewProduct() {
        return "view product";
    }

    @GetMapping("create")
    public String createProduct() {
        return "create product";
    }
}
