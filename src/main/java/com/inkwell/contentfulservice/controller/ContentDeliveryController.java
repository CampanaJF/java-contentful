package com.inkwell.contentfulservice.controller;

import com.inkwell.contentfulservice.model.Product;
import com.inkwell.contentfulservice.model.ProductCollection;
import com.inkwell.contentfulservice.model.ProductDetail;
import com.inkwell.contentfulservice.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@Log4j2
@RestController
@RequestMapping("v1/content")
@AllArgsConstructor
public class ContentDeliveryController {

    private final ProductService service;

    @GetMapping("/getProducts")
    public ResponseEntity<Collection<Product>> fetchAllProducts() {

        Collection<Product> found = service.getProducts();

        return new ResponseEntity<>(found,HttpStatus.OK);
    }

    @GetMapping("/getProduct/{productId}")
    public ResponseEntity<Product> fetchAProduct(
            @PathVariable("productId") String productId) {

        Product found = service.getProduct(productId);

        return new ResponseEntity<>(found,HttpStatus.OK);
    }

    @GetMapping("/getProductCollection/{productId}")
    public ResponseEntity<ProductCollection> getProductCollection(
            @PathVariable("productId") String productId) {

        ProductCollection found = service.getProductCollection(productId);

        return new ResponseEntity<>(found,HttpStatus.OK);
    }

    @GetMapping("/getProductCollections")
    public ResponseEntity<Collection<ProductCollection>> getProductCollections() {

        Collection<ProductCollection> found = service.getProductCollections();

        return new ResponseEntity<>(found,HttpStatus.OK);
    }

    @GetMapping("/productDetails/{productId}")
    public ResponseEntity<ProductDetail> getProductDetails(
            @PathVariable("productId") String productId) {

        ProductDetail found = service.getProductDetails(productId);

        return new ResponseEntity<>(found,HttpStatus.OK);
    }

    @GetMapping("/rich/{productId}")
    public ResponseEntity<String> richTermsOfUse(
            @PathVariable("productId") String productId) {

        String found = service.richProcessor(productId);

        return new ResponseEntity<>(found,HttpStatus.OK);
    }



}
