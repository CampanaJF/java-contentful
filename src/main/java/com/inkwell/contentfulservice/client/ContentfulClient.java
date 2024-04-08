package com.inkwell.contentfulservice.client;

import com.contentful.java.cda.CDAArray;
import com.contentful.java.cda.CDAClient;
import com.contentful.java.cda.CDAEntry;

public class ContentfulClient {

    CDAClient client = CDAClient.builder()
            .setSpace("0jscl3okaq78")
            .setEnvironment("dev")
            .setToken("rHXR0Lx_dZ_y3FIYchv8mSrXG6Hh2W0nwmqFrJyQHCU")
            .build();

    CDAArray array = client.fetch(CDAEntry.class).all();


}
