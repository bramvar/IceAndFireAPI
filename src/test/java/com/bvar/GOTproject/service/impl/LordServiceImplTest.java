package com.bvar.GOTproject.service.impl;

import com.bvar.GOTproject.model.Lord;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;

import static utils.TestUtils.*;

@ExtendWith(MockitoExtension.class)
public class LordServiceImplTest {

    private static MockWebServer mockWebServer;
    private LordServiceImpl lordService;
    private String baseUrl;

    @BeforeAll
    static void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @BeforeEach
    void initialize() {
        baseUrl = String.format("http://localhost:%s", mockWebServer.getPort());
        lordService = new LordServiceImpl(WebClient.create(baseUrl));
    }

    @Test
    public void testGetLordByUrl(){
        mockWebServer.enqueue(new MockResponse()
                .setBody(getLord1Json())
                .addHeader("Content-Type", "application/json"));

        Mono<Lord> lord = Mono.just(lordService.getLordByUrl(baseUrl));

        StepVerifier.create(lord)
                .expectNext(getLord1())
                .verifyComplete();
    }

}
