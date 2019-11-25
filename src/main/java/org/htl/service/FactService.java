package org.htl.service;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.htl.entity.Fact;

import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RegisterRestClient
@Path("/facts")
public interface FactService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/random")
    JsonObject getRandomFact();
}
