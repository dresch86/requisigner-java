package org.requisigner;

import java.io.IOException;

import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;

import io.vertx.core.json.JsonObject;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.apache.commons.cli.Options;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.CommandLineParser;

public class Configuration {
    private JsonObject joConfig;

    private static final Logger lMainLogger = LogManager.getLogger(Configuration.class.getName());

    public Configuration(String[] args, Options opCLIOps) {
        try {
            CommandLineParser clpCLIHandler = new DefaultParser();
            CommandLine clCmdInput = clpCLIHandler.parse(opCLIOps, args);

            if (clCmdInput.hasOption("c")) {
                Path pConfigPath = Paths.get(clCmdInput.getOptionValue("c"));

                if (Files.exists(pConfigPath)) {
                    String sJSONCfg = Files.readString(pConfigPath);
                    joConfig = new JsonObject(sJSONCfg);
                } else {
                    lMainLogger.fatal("Configuration file not found");
                    System.exit(1);
                }
            } else {
                lMainLogger.fatal("Configuration file not supplied");
                System.exit(1);
            }
        } catch (ParseException e) {
            lMainLogger.fatal("CLI Error - " + e.getMessage());
            System.exit(1);
        } catch (IOException ioe) {
            lMainLogger.fatal("File System Error - " + ioe.getMessage());
            System.exit(1);
        }
    }

    public JsonObject getSslConfig() {
        return joConfig.getJsonObject("http").getJsonObject("ssl");
    }

    public int getPort() {
        return joConfig.getJsonObject("http").getInteger("port");
    }

    public String getAddress() {
        return joConfig.getJsonObject("http").getString("address");
    }

    public String getTemplatesDir() {
        return joConfig.getJsonObject("filesystem").getString("templates");
    }
}