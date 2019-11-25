package org.htl.resource;

import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.htl.service.FactService;

import javax.inject.Inject;

public class HealthCheck implements org.eclipse.microprofile.health.HealthCheck {

    @RestClient
    @Inject
    private FactService service;

    @Override
    public HealthCheckResponse call() {
        HealthCheckResponseBuilder responseBuilder = HealthCheckResponse.named("Funfact Server Status");

        try {
            service.getRandomFact();
            responseBuilder.up();
        } catch (Exception e){
            responseBuilder.down();
        }
        
        return responseBuilder.build();
    }
}
