package com.inkwell.contentfulservice.tool;

import com.contentful.java.cda.CDAAsset;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

public class CDAAssetSerializer extends JsonSerializer<CDAAsset> {

    @Override
    public void serialize(CDAAsset asset, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(asset.url());
    }
}
