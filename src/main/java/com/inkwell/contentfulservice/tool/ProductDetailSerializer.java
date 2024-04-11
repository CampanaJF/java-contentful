package com.inkwell.contentfulservice.tool;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.inkwell.contentfulservice.model.ProductDetail;

import java.io.IOException;

public class ProductDetailSerializer extends JsonSerializer<ProductDetail> {

    @Override
    public void serialize(ProductDetail productDetail, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(productDetail.getId());
    }
}
