package com.inkwell.contentfulservice.service;

import com.inkwell.contentfulservice.model.Product;
import com.inkwell.contentfulservice.model.ProductCollection;
import com.inkwell.contentfulservice.model.ProductDetail;

import java.util.List;

public interface ProductService {

    ProductCollection getProductCollection(String productId);

    List<ProductCollection> getProductCollections();

    Product getProduct(String productId);

    List<Product> getProducts();

    ProductDetail getProductDetails(String productId);

    String richProcessor(String productId);

}
