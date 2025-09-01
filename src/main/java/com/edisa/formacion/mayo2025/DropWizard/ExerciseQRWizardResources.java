package com.edisa.formacion.mayo2025.DropWizard;

//Clase con las funciones que llamará cada ruta

//Indicamos las caracteristicas de la ruta, como son enviados los parametros a dicha funcion
//Etc con annotaciones (usando @)

//necesario importar ej ws.rs para obtener dichas anotaciones
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;

@Path("/api/QR")
@Produces(MediaType.APPLICATION_JSON)
public class ExerciseQRWizardResources {

    @POST
    @Path("/decipherQR")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.MULTIPART_FORM_DATA})
    public Response decipherQR(File f){
        System.out.printf(f.toString());

        return Response.ok().entity(f).build();
    }

    @POST
    @Path("/decipherQR")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response decipherQR(ExerciseQRDropWizardBody body){
        try {
            ExerciseQRDropWizardLogic.processQR(body.getBase64());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return Response.ok().entity(f).build();
    }








}
