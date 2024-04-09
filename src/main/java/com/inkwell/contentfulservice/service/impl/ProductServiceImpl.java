package com.inkwell.contentfulservice.service.impl;

import com.contentful.java.cda.CDAClient;
import com.contentful.java.cda.CDAEntry;
import com.contentful.java.cda.rich.CDARichDocument;
import com.contentful.rich.html.HtmlContext;
import com.contentful.rich.html.HtmlProcessor;
import com.inkwell.contentfulservice.client.ContentfulClient;
import com.inkwell.contentfulservice.model.Product;
import com.inkwell.contentfulservice.model.ProductCollection;
import com.inkwell.contentfulservice.model.ProductDetail;
import com.inkwell.contentfulservice.service.ProductService;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.contentful.java.cda.QueryOperation.IsEqualTo;

@Service
public class ProductServiceImpl implements ProductService {

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
        ProductDetail found = client
                .observeAndTransform(ProductDetail.class)
                .where("sys.id", IsEqualTo, productId)
                .all()
                .blockingFirst()
                .iterator()
                .next();

        found.setTermsOfUse(processRichDocument(found.getRichDocument()));

        return found;
    }

    @Override
    public String richProcessor(String productId) {
        CDAEntry entry = client
                .fetch(CDAEntry.class)
                .one(productId);

        CDARichDocument node = entry.getField("termsOfUse");

        final HtmlProcessor processor = new HtmlProcessor();
        final HtmlContext context = new HtmlContext();

        String html = processor.process(context, node);

        return StringEscapeUtils.unescapeHtml4(html);
    }

    @Override
    public String processRichDocument(CDARichDocument doc) {
        final HtmlProcessor processor = new HtmlProcessor();
        final HtmlContext context = new HtmlContext();

        String html = processor.process(context, doc);

        return StringEscapeUtils.unescapeHtml4(html);
    }
}
