package com.inkwell.contentfulservice.model;

import com.contentful.java.cda.CDAAsset;
import com.contentful.java.cda.TransformQuery;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.inkwell.contentfulservice.tool.CDAAssetSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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

    @TransformQuery.ContentfulField("featuredImage")
    @JsonSerialize(using = CDAAssetSerializer.class)
    private CDAAsset url;

    @TransformQuery.ContentfulField("canBeUsedOn")
    private List<String> canBeUseOn;

    @TransformQuery.ContentfulField("methodOfUse")
    private String methodOfUse;

    @TransformQuery.ContentfulField("validFrom")
    private String validFrom;

    @TransformQuery.ContentfulField("validUntil")
    private String validUntil;

    @TransformQuery.ContentfulField("paymentType")
    private List<String> paymentType;

    @TransformQuery.ContentfulField("cashbackCap")
    private String cashbackCap;

    @TransformQuery.ContentfulField("cashbackPeriod")
    private String cashbackPeriod;

//    @TransformQuery.ContentfulField("termsOfUse")
//    private String terms;

}
