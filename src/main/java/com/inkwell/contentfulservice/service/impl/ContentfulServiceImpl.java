package com.inkwell.contentfulservice.service.impl;

import com.contentful.java.cda.CDAClient;
import com.inkwell.contentfulservice.client.ContentfulClient;
import com.inkwell.contentfulservice.model.Product;
import com.inkwell.contentfulservice.model.ProductCollection;
import com.inkwell.contentfulservice.model.ProductDetail;
import com.inkwell.contentfulservice.service.ContentfulService;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.contentful.java.cda.QueryOperation.IsEqualTo;

@Service
public class ContentfulServiceImpl implements ContentfulService {

    private final CDAClient client = ContentfulClient.get();
    @Override
    public ProductCollection getProductCollection(String productId) {
        return client
                .observeAndTransform(ProductCollection.class)
                .where("sys.id", IsEqualTo, productId)
                .all()
                .blockingFirst()
                .iterator()
                .next();
    }

    @Override
    public List<ProductCollection> getProductCollections() {
        return client
                .observeAndTransform(ProductCollection.class)
                .all()
                .blockingFirst()
                .stream()
                .toList();
    }

    @Override
    public Product getProduct(String productId) {
        return client
                .observeAndTransform(Product.class)
                .where("sys.id", IsEqualTo, productId)
                .all()
                .blockingFirst()
                .iterator()
                .next();
    }

    @Override
    public List<Product> getProducts() {
        return client
                .observeAndTransform(Product.class)
                .all()
                .blockingFirst()
                .stream()
                .toList();
    }

    @Override
    public ProductDetail getProductDetails(String productId) {
        return client
                .observeAndTransform(ProductDetail.class)
                .where("sys.id", IsEqualTo, productId)
                .all()
                .blockingFirst()
                .iterator()
                .next();
    }
}
