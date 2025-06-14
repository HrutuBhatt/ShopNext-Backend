package com.project.ecommerce_app.service;

import com.project.ecommerce_app.entity.ProductImage;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface ProductImageService {
    ProductImage saveImage(Integer productId, MultipartFile file);
    List<ProductImage> getImagesByProductId(Integer productId);
    void deleteImage(Integer imageId);
}
