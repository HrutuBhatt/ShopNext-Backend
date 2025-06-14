package com.project.ecommerce_app.controller;

import com.project.ecommerce_app.entity.ProductImage;
import com.project.ecommerce_app.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/product-images")
public class ProductImageController {

    @Autowired
    private ProductImageService imageService;

    @PostMapping("/{productId}")
    public ResponseEntity<String> uploadImage(
            @PathVariable Integer productId,
            @RequestParam("image") MultipartFile file) {

        ProductImage image = imageService.saveImage(productId, file);
        return ResponseEntity.ok("image uploaded");
    }

    @GetMapping("/{productId}")
    public ResponseEntity<List<ProductImage>> getImagesByProductId(@PathVariable Integer productId) {
        return ResponseEntity.ok(imageService.getImagesByProductId(productId));
    }

    @DeleteMapping("/{imageId}")
    public ResponseEntity<Void> deleteImage(@PathVariable Integer imageId) {
        imageService.deleteImage(imageId);
        return ResponseEntity.noContent().build();
    }
}

