package com.inkwell.contentfulservice.model;

import com.contentful.java.cda.TransformQuery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@TransformQuery.ContentfulEntryModel("productDetail")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetail {

    @TransformQuery.ContentfulSystemField
    private String id;

    @TransformQuery.ContentfulField("brand")
    private String brand;

    @TransformQuery.ContentfulField("slug")
    private String slug;



}
