package ressources;

import entities.Option;
import metiers.OptionBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/options")
public class OptionRessouces {

    private OptionBusiness optionBusiness = new OptionBusiness();



    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOption(Option option) {

        boolean resultat = optionBusiness.addOption(option);

        if (resultat) {
            return Response.status(Response.Status.OK)
                    .build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {

        List<Option> list = optionBusiness.getListeOptions();

        return Response.status(Response.Status.OK)
                .entity(list)
                .build();
    }


    @GET
    @Path("/domaine")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByDomaine(
            @QueryParam("domaine") String domaine) {

        List<Option> list = optionBusiness.getOptionsByDomaine(domaine);

        return Response.status(Response.Status.OK)
                .entity(list)
                .build();
    }



    @DELETE
    @Path("/{code}")
    public Response deleteOption(
            @PathParam("code") int code) {

        boolean resultat = optionBusiness.deleteOption(code);

        if (resultat) {
            return Response.status(Response.Status.NO_CONTENT)
                    .build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }
    }



    @PUT
    @Path("/{code}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateOption(
            @PathParam("code") int code,
            Option option) {

        boolean resultat =
                optionBusiness.updateOption(code, option);

        if (resultat) {
            return Response.status(Response.Status.OK)
                    .build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }
    }


    @GET
    @Path("/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByCode(
            @PathParam("code") int code) {

        Option option =
                optionBusiness.getOptionByCode(code);

        if (option != null) {

            return Response.status(Response.Status.OK)
                    .entity(option)
                    .build();

        } else {

            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }
    }
}