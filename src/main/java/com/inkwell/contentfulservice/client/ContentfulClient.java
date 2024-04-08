package com.inkwell.contentfulservice.client;

import com.contentful.java.cda.CDAClient;
import io.github.cdimascio.dotenv.Dotenv;

public class ContentfulClient {

    private static final Object LOCK = new Object();

    private static CDAClient instance;

    private void ClientProvider() {
        throw new AssertionError();
    }

    public static CDAClient get() {

        Dotenv dotenv = Dotenv.load();

        synchronized (LOCK) {
            if (instance == null) {

                instance = CDAClient.builder()
                        .setSpace(dotenv.get("CONTENTFUL_SPACE"))
                        .setToken(dotenv.get("CONTENTFUL_TOKEN"))
                        .setEnvironment("dev")
                        .build();
            }

            return instance;
        }
    }

    public static void reset() {
        synchronized (LOCK) {
            instance = null;
        }
    }

}
