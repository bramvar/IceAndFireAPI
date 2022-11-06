package com.bvar.GOTproject.service.impl;

import com.bvar.GOTproject.model.House;
import com.bvar.GOTproject.model.Lord;
import com.bvar.GOTproject.service.LordService;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;
import static utils.TestUtils.*;

@ExtendWith(MockitoExtension.class)
public class HouseServiceImplTest {

    @Mock
    private LordService lordService;

    private static MockWebServer mockWebServer;
    private HouseServiceImpl houseService;
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
            houseService = new HouseServiceImpl(WebClient.create(baseUrl), lordService);
        }

    @Test
    public void testGetHousesRaw() throws InterruptedException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(getHousesRawJson())
                .addHeader("Content-Type", "application/json"));

        Flux<House> houses = houseService.getHousesRaw(baseUrl);

        StepVerifier.create(houses)
                .expectNext(getHouse1())
                .expectNext(getHouse2())
                .verifyComplete();
    }

    @Test
    public void testGetHouseAndLord(){
        House currentHouse = getHouse2();
        Lord expectedLord = getLord1();
        when(lordService.getLordByUrl(currentHouse.getCurrentLordUrl())).thenReturn(expectedLord);

        Lord actualLord = houseService.getHouseAndLord(currentHouse);

        assertEquals(expectedLord.getUrl(),actualLord.getUrl());
        assertEquals(expectedLord.getName(),actualLord.getName());
    }

    @Test
    public void testGetHouseAndLordWithEmptyUrl(){
        House currentHouse = getHouse1();
        Lord actualLord = houseService.getHouseAndLord(currentHouse);

        assertNull(actualLord);
    }

}
