package com.inkwell.contentfulservice.controller;

import com.contentful.java.cda.CDAArray;
import com.contentful.java.cda.CDAAsset;
import com.contentful.java.cda.CDAClient;
import com.contentful.java.cda.CDAEntry;
import com.inkwell.contentfulservice.model.Product;
import com.inkwell.contentfulservice.model.ProductCollection;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static com.contentful.java.cda.QueryOperation.IsEqualTo;

@Log4j2
@RestController
@RequestMapping("content")
public class ContentDeliveryController {

    Dotenv dotenv = Dotenv.load();

    CDAClient client = CDAClient.builder()
            .setSpace(dotenv.get("CONTENTFUL_SPACE"))
            .setToken(dotenv.get("CONTENTFUL_TOKEN"))
            .setEnvironment("dev")
            .build();

    // the way CDAArray fetches stuff is funny, within the array there are assets and entries, however
    // while these are associated, they don't come together, so it's necessary to fetch both and then join
    // them together for the response, regardless, with this method we can obtain all the assets and data
    // from an entry, this example uses the brand field to search for the product entry, but it's not necessary
    // one could search using its ID .where("sys.id", IsEqualTo ,"5fP5acTvqfsoOMlMaD8jnC")
    @GetMapping("/entryAndAssets")
    public ResponseEntity<Map<String,Object>> testing() {
        final CDAArray all =
                client
                        .fetch(CDAEntry.class)
                        .withContentType("product")
                        .where("fields.brand", IsEqualTo, "Cafe Martinez Thumbnail")
                        .all();

        Map<String, Object> responseData = new HashMap<>();

        Map<String, Object> assetsData = new HashMap<>();
        for (Map.Entry<String, CDAAsset> asset : all.assets().entrySet()) {
            String key = asset.getKey();
            CDAAsset value = asset.getValue();

            Map<String, Object> entryData = new HashMap<>();
            entryData.put("fields", value.rawFields());

            assetsData.put(key, entryData);
        }

        Map<String, Object> entriesData = new HashMap<>();
        for (Map.Entry<String, CDAEntry> entry : all.entries().entrySet()) {
            String key = entry.getKey();
            CDAEntry value = entry.getValue();

            Map<String, Object> entryData = new HashMap<>();
            entryData.put("fields", value.rawFields());

            entriesData.put(key, entryData);
        }

        responseData.put("entries", entriesData);
        responseData.put("assets",assetsData);

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    // this transforms the result into whatever the class in the paramater is, naturally it tries to make it fit
    // there are some complex reactions here but overall it was made to function as required, a little dirty
    // but as Todd would say, it just works
    @GetMapping("/fetchAllProducts")
    public ResponseEntity<Collection<Product>> fetchAllProducts() {

        Collection<Product> found = client
                .observeAndTransform(Product.class)
                .all()
                .blockingFirst();

        return new ResponseEntity<>(found,HttpStatus.OK);
    }

    // The search works like this: first observeAndTransforms lets us take the result and map the contents to a class of
    // our own, then the where clause lets us search for the specific field entry we want, all allows us to search all the entries
    // otherwise we would only get a specific one, blocking means it will only take the first result and only one result
    // otherwise it throws an exception iterator and next are necessary since the result is always a collection
    @GetMapping("/fetchAProduct")
    public ResponseEntity<Product> fetchAProduct() {

        Product found = client
                .observeAndTransform(Product.class)
                .where("fields.brand", IsEqualTo, "Cafe Martinez Thumbnail")
                .all()
                .blockingFirst()
                .iterator()
                .next();

        return new ResponseEntity<>(found,HttpStatus.OK);
    }

    @GetMapping("/productCollection")
    public ResponseEntity<ProductCollection> collection() {

        ProductCollection found = client
                .observeAndTransform(ProductCollection.class)
                .where("sys.id", IsEqualTo, "1bXPzEI8mfN3tNBs3U9b9d")
                .all()
                .blockingFirst()
                .iterator()
                .next();

        return new ResponseEntity<>(found,HttpStatus.OK);
    }

    @GetMapping("/productCollection2")
    public ResponseEntity<Collection<ProductCollection>> collection2() {

        Collection<ProductCollection> found = client
                .observeAndTransform(ProductCollection.class)
                .all()
                .blockingFirst();

        return new ResponseEntity<>(found,HttpStatus.OK);
    }



}
