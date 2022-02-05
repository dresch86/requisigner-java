package org.quillsigner;

import io.vertx.core.json.JsonObject;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Configuration {
    private int iPort;
    private String sAddress;
    private JsonObject joConfig;

    private static final Logger lMainLogger = LogManager.getLogger(Configuration.class.getName());

    public Configuration() {}

    public JsonObject getSslConfig() {
        return joConfig.getJsonObject("system").getJsonObject("http").getJsonObject("ssl");
    }

    public int getPort() {
        return iPort;
    }

    public String getAddress() {
        return sAddress;
    }   
}