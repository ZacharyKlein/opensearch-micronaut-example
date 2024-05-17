package com.example.model;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record Widget(String id, String name, String brandName, int manufactureYear, String serialNumber) {
}

