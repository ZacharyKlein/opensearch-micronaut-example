package com.example;

import com.example.model.Widget;
import io.micronaut.core.type.Argument;
import io.micronaut.http.*;
import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.uri.UriBuilder;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import jakarta.inject.Inject;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class LearnopeansearchmicronautTest {

    @Inject
    EmbeddedApplication<?> application;

    @Test
    void testItWorks() {
        Assertions.assertTrue(application.isRunning());
    }

    @Test
    void testSaveAndSearch(@Client("/") HttpClient httpClient) throws InterruptedException {
        BlockingHttpClient client = httpClient.toBlocking();

        String serialNumber = "1234";
        String name = "Pump Ultra";
        String brandName = "Acme";
        int manufactureYear = 2021;
        String id = "1";
        Map<String, Object> body = Map.of(
            "id", id,
            "name", name,
            "brandName", brandName,
            "manufactureYear", manufactureYear,
            "serialNumber", serialNumber
        );

        MutableHttpRequest<Map<String, Object>> request = HttpRequest.POST("/api/widget", body)
            .accept(MediaType.TEXT_PLAIN);
        assertDoesNotThrow(() -> {
            client.exchange(request);
        });

        URI searchUri = UriBuilder.of("/api/widget")
            .path("search")
            .queryParam("query", "pump")
            .build();
        Thread.sleep(1000);
        List<Widget> result = assertDoesNotThrow(() -> client.retrieve(HttpRequest.GET(searchUri), Argument.listOf(Widget.class)));

        assertEquals(1, result.size());

        Widget widget = result.get(0);
        assertNotNull(widget);
        assertEquals(id, widget.id());
        assertEquals(brandName, widget.brandName());


    }

}
