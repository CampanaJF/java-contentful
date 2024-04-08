package com.inkwell.contentfulservice.model;

import com.contentful.java.cda.TransformQuery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@TransformQuery.ContentfulEntryModel("productCollection")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCollection {

    @TransformQuery.ContentfulSystemField
    private String id;

    @TransformQuery.ContentfulField("title")
    private String title;

    // the documentation is wrong, this needs to be ContentfulField, not EntryModel
    @TransformQuery.ContentfulField("products")
    private List<Product> products;
}
