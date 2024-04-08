package com.inkwell.contentfulservice.model;

import com.contentful.java.cda.CDAAsset;
import com.contentful.java.cda.TransformQuery.ContentfulEntryModel;
import com.contentful.java.cda.TransformQuery.ContentfulField;
import com.contentful.java.cda.TransformQuery.ContentfulSystemField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.inkwell.contentfulservice.tool.CDAAssetSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@ContentfulEntryModel("product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @ContentfulSystemField
    private String id;

    @ContentfulField("brand")
    private String brand;

    @ContentfulField("slug")
    private String slug;

    @ContentfulField("thumbnail")
    @JsonSerialize(using = CDAAssetSerializer.class)
    private CDAAsset thumbnail;

}
