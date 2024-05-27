package de.fhws.fiw.fds.project.server.api.services;

import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.Exceptions.SuttonWebAppException;
import de.fhws.fiw.fds.sutton.server.api.services.AbstractJerseyService;
import de.fhws.fiw.fds.project.server.api.models.Module;
import de.fhws.fiw.fds.project.server.api.models.PartnerUni;
import de.fhws.fiw.fds.project.server.api.queries.QueryByFirstAndLastName;
import de.fhws.fiw.fds.project.server.api.queries.QueryByLocationName;
import de.fhws.fiw.fds.project.server.api.states.person_locations.*;
import de.fhws.fiw.fds.project.server.api.states.persons.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("persons")
public class PersonJerseyService extends AbstractJerseyService {

    public PersonJerseyService() {
        super();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAllPersons(
            @DefaultValue("") @QueryParam("firstname") final String firstName,
            @DefaultValue("") @QueryParam("lastname") final String lastName,
            @DefaultValue("0") @QueryParam("offset") int offset,
            @DefaultValue("20") @QueryParam("size") int size) {
        try {
            return new GetAllPersons(
                    this.serviceContext,
                    new QueryByFirstAndLastName<>(firstName, lastName, offset, size)
            ).execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(e.getExceptionMessage(), e.getStatus().getCode());
        }
    }

    @GET
    @Path("{id: \\d+}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getSinglePerson(@PathParam("id") final long id) {
        try {
            return new GetSinglePerson(this.serviceContext, id).execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response
                    .status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build()
            );
        }
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createSinglePerson(final Person personModel) {
        try {
            return new PostNewPerson(this.serviceContext, personModel).execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build());
        }
    }

    @PUT
    @Path("{id: \\d+}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateSinglePerson(@PathParam("id") final long id, final Person personModel) {
        try {
            return new PutSinglePerson(this.serviceContext, id, personModel).execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build());
        }
    }

    @DELETE
    @Path("{id: \\d+}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteSinglePerson(@PathParam("id") final long id) {
        try {
            return new DeleteSinglePerson(this.serviceContext, id).execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build());
        }
    }

    @GET
    @Path("{personId: \\d+}/locations")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getLocationsOfPerson(@PathParam("personId") final long personId,
                                         @DefaultValue("") @QueryParam("cityname") final String cityName,
                                         @DefaultValue("0") @QueryParam("offset") int offset,
                                         @DefaultValue("20") @QueryParam("size") int size) {
        try {
            return new GetAllLocationsOfPerson(this.serviceContext, personId, new QueryByLocationName<>(personId, cityName, offset, size)).execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build());
        }
    }

    @GET
    @Path("{personId: \\d+}/locations/{locationId: \\d+}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getLocationByIdOfPerson(@PathParam("personId") final long personId,
                                            @PathParam("locationId") final long locationId) {
        try {
            return new GetSingleLocationOfPerson( this.serviceContext, personId, locationId ).execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build());
        }
    }

    @POST
    @Path("{personId: \\d+}/locations")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createNewLocationOfPerson(@PathParam("personId") final long personId, final Location location) {
        try {
            return new PostNewLocationOfPerson( this.serviceContext, personId, location ).execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build());
        }
    }

    @PUT
    @Path("{personId: \\d+}/locations/{locationId: \\d+}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateNewLocationOfPerson(@PathParam("personId") final long personId,
                                              @PathParam("locationId") final long locationId, final Location location) {
        try {
            return new PutSingleLocationOfPerson( this.serviceContext, personId, locationId, location ).execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build());
        }
    }

    @DELETE
    @Path("{personId: \\d+}/locations/{locationId: \\d+}")
    public Response deleteLocationOfPerson(@PathParam("personId") final long personId,
                                           @PathParam("locationId") final long locationId) {
        try {
            return new DeleteSingleLocationOfPerson( this.serviceContext, locationId, personId ).execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build());
        }
    }

}
