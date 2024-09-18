package com.advanced.api.tests;

import com.advanced.api.mocks.MockSetup;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import com.advanced.api.config.Config;


public class BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    protected static WireMockServer wireMockServer;

    @BeforeClass
    public void setup() {
        if (Config.isUseWireMock()) {
            logger.info("Starting WireMock server on port 8989...");
            wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().port(8989));
            try {
                wireMockServer.start();
                WireMock.configureFor("localhost", 8989);
                MockSetup.setupUserMocks();
                MockSetup.setupMockApi();
                logger.info("WireMock server started and mocks are set up.");
            } catch (Exception e) {
                logger.error("Failed to start WireMock server or setup mocks", e);
                throw e;
            }
        } else {
            logger.info("WireMock is disabled.");
        }
    }

    @AfterClass
    public void teardown() {
        if (Config.isUseWireMock()) {
            logger.info("Stopping WireMock server...");
            try {
                wireMockServer.stop();
                logger.info("WireMock server stopped.");
            } catch (Exception e) {
                logger.error("Failed to stop WireMock server", e);
            }
        }
    }
}