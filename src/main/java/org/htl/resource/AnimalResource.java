package org.htl.resource;


import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.htl.entity.Animal;
import org.htl.entity.Fact;
import org.htl.service.FactService;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Path("/funfact")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AnimalResource {

    @RestClient
    @Inject
    FactService service;

    @GET
    public Response getAllFunfacts(){

        //Paging
        PanacheQuery<Fact> factQuery = Fact.findAll();
        factQuery.page(Page.ofSize(25));

        List<Fact> facts = factQuery.list();

        if(facts == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok().entity(facts).build();
    }

    @GET
    @Path("/new")
    @Transactional
    public Response getNewFunfact(){
        JsonObject obj = service.getRandomFact();

        Animal animal = new Animal();
        animal.setName(obj.getString("type"));
        animal.persist();

        Fact fact = new Fact();
        fact.setAnimal(animal);
        fact.setSource(obj.getString("source"));
        fact.setText(obj.getString("text"));
        fact.setUpdatedAt(LocalDate.ofInstant(Instant.parse(obj.getString("updatedAt")), ZoneId.systemDefault()));

        fact.persist();

        return Response.ok().entity(fact).build();
    }

}
