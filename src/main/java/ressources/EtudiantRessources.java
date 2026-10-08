package ressources;

import entities.Etudiant;
import metiers.EtudiantBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import entities.Option;
import metiers.OptionBusiness;
@Path("/etudiants")
public class EtudiantRessources {

    EtudiantBusiness e = new EtudiantBusiness();
    OptionBusiness optionBusiness = new OptionBusiness();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response ajouterEtudiant(Etudiant etudiant) {

        boolean result = e.addEtudiant(etudiant);

        if (result) {
            return Response.ok().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEtudiants() {
        List<Etudiant> list = e.getAllEtudiants();

        return Response.status(Response.Status.OK).entity(list).build();
    }
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") String code) {

        Etudiant resultat = e.getEtudiantByIdentifiant(code);

        if (resultat != null) {
            return Response.status(Response.Status.OK).entity(resultat).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    @DELETE
    @Path("/{id}")
    public Response supprimeretud(@PathParam("id") String code) {

        boolean resultat = e.deleteEtudiant(code);

        if (resultat) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEtudiant(@PathParam("id") String code, Etudiant etudiant) {

        boolean resultat = e.updateEtudiant(code, etudiant);

        if (resultat) {
            return Response.status(Response.Status.OK)
                    .build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }
    }
    @GET
    @Path("/option")
    @Produces(MediaType.APPLICATION_XML)
    public Response getEtudiantsByOption(@QueryParam("codeOption") int codeOption) {

        Option option = optionBusiness.getOptionByCode(codeOption);

        if (option != null) {

            List<Etudiant> list = e.getEtudiantsByOption(option);

            return Response.status(Response.Status.OK)
                    .entity(list)
                    .build();

        } else {

            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }
    }





}