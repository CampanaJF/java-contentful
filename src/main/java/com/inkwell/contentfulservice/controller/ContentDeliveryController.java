package com.inkwell.contentfulservice.controller;

import com.inkwell.contentfulservice.model.Product;
import com.inkwell.contentfulservice.model.ProductCollection;
import com.inkwell.contentfulservice.model.ProductDetail;
import com.inkwell.contentfulservice.service.ContentfulService;
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

    //CDAClient client = ContentfulClient.get();

    private final ContentfulService service;

    // this transforms the result into whatever the class in the paramater is, naturally it tries to make it fit
    // there are some complex reactions here but overall it was made to function as required, a little dirty
    // but as Todd would say, it just works
    @GetMapping("/getProducts")
    public ResponseEntity<Collection<Product>> fetchAllProducts() {

        Collection<Product> found = service.getProducts();

        return new ResponseEntity<>(found,HttpStatus.OK);
    }

    // The search works like this: first observeAndTransforms lets us take the result and map the contents to a class of
    // our own, then the where clause lets us search for the specific field entry we want, all allows us to search all the entries
    // otherwise we would only get a specific one, blocking means it will only take the first result and only one result
    // otherwise it throws an exception iterator and next are necessary since the result is always a collection
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



}
