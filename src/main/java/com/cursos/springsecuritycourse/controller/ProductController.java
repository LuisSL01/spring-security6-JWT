package com.cursos.springsecuritycourse.controller;

import com.cursos.springsecuritycourse.entity.Product;
import com.cursos.springsecuritycourse.repository.ProductRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @PreAuthorize("hasAuthority('READ_ALL_PRODUCTS')")
    @GetMapping
    public ResponseEntity<List<Product>> findAll(){
        List<Product> products = productRepository.findAll();
        if(!products.isEmpty()) return ResponseEntity.ok(products);

        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasAuthority('SAVE_ONE_PRODUCT')")
    @PostMapping
    public ResponseEntity<Product> createOne(@RequestBody @Valid Product product){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                productRepository.save(product)
        );
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,String>> handleGenericException(Exception exception, HttpServletRequest httpServletRequest){
        Map<String,String> apiError = new HashMap<>();
        apiError.put("message",exception.getLocalizedMessage());
        apiError.put("timestamp", LocalDateTime.now().toString());
        apiError.put("url",httpServletRequest.getRequestURL().toString());
        apiError.put("http-method", httpServletRequest.getMethod());

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if(exception instanceof AccessDeniedException){
            status = HttpStatus.FORBIDDEN;
        }

        return ResponseEntity.status(status).body(apiError);
    }

}
