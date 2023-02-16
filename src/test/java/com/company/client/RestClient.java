package com.company.client;

import com.company.config.Environment;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public abstract class RestClient {

    public RequestSpecification basicRequestSpecification() {
        return given()
                .baseUri(Environment.BASE_URL.getUrl())
                .contentType(ContentType.JSON)
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    public RequestSpecification basicRequestSpecification(Object pojo) {
        return given()
                .baseUri(Environment.BASE_URL.getUrl())
                .contentType(ContentType.JSON)
                .body(serializePojo(pojo))
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    private String serializePojo(Object pojo) {
        ObjectMapper objectMapper = new ObjectMapper();

        if (pojo == null) {
            throw new RuntimeException("Null POJO provided");
        }

        String body = null;
        try {
            body = objectMapper.writeValueAsString(pojo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return body;
    }

}
