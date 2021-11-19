package com.microprofile.petstore;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/pets_type")
@Produces("application/json")

public class PetTypeResource {

    List<PetType> petsType = new ArrayList<PetType>();


    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "All Pets Type", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "PetType"))) })
    @GET
    public Response getPets() {

        return Response.ok(PetTypeSingleton.getInstance().getPetsTypeList()).build();
    }


    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Create a Pet Type", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "PetType"))) })
    @POST
    @Path("/create_pet_type")
    public Response createPetType(PetType petType) {

        List<PetType> petsType = new ArrayList<PetType>();
        List<PetType> tempPetTypeVal = new ArrayList<PetType>();

        if(petType.getPetType() != null){
            petType.setPetTypeId(PetTypeSingleton.getInstance().getPetsTypeList().get(PetTypeSingleton.getInstance().getPetsTypeList().size()-1).getPetTypeId()+1);
            petType.setPetType(petType.getPetType());
            petsType.add(petType);
            tempPetTypeVal.addAll(PetTypeSingleton.getInstance().getPetsTypeList());
            tempPetTypeVal.addAll(petsType);
            PetTypeSingleton.getInstance().setPetsTypeList(tempPetTypeVal);
            return Response.ok(petType).build();
        }else{
            return Response.ok("{\n" + "\"successful\":fail\n" + "}").build();

        }
    }

    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Update a Pet Type", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "PetType"))),
            @APIResponse(responseCode = "404", description = "No Pet Type found for the id.")})
    @PUT
    @Path("/update_pet_type/{petTypeId}")

    public Response updatePetType(@PathParam("petTypeId") int petTypeId ,PetType petTypeData) {
        if (petTypeId < 0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        boolean flagUpdated = false;
        int id = 0;
        if (petTypeData.getPetType() != null) {
            for (int i = 0; i < PetTypeSingleton.getInstance().getPetsTypeList().size(); i++) {
                if (petTypeId == PetTypeSingleton.getInstance().getPetsTypeList().get(i).getPetTypeId()) {
                    PetTypeSingleton.getInstance().getPetsTypeList().get(i).setPetType(petTypeData.getPetType());
                    flagUpdated = true;
                    id = i;
                }
            }
        }

        if (flagUpdated) {
            return Response.ok(PetTypeSingleton.getInstance().getPetsTypeList().get(id)).build();
        } else {
            return Response.ok("{\n" + "\"successful\":fail\n" + "}").build();
        }
    }

        @APIResponses(value = {
                @APIResponse(responseCode = "200", description = "Delete a Pet type", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "PetType"))),
                @APIResponse(responseCode = "404", description = "No Pet type found for the id.")})
        @DELETE
        @Path("/delete_pet_type/{petTypeId}")

        public Response deletePet(@PathParam("petTypeId") int petTypeId ){
            if (petTypeId < 0) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            boolean PetFlag = false;
            for (int i=0;i<PetTypeSingleton.getInstance().getPetsTypeList().size();i++){
                if(petTypeId == PetTypeSingleton.getInstance().getPetsTypeList().get(i).getPetTypeId()){
                    PetTypeSingleton.getInstance().getPetsTypeList().remove(i);
                    PetFlag = true;
                }
            }
            if(PetFlag){
                return Response.ok("{\n" + "\"successful\":pass\n" + "}").build();
            }else{
                return Response.ok("{\n" + "\"successful\":fail\n" + "}").build();
            }

    }


}
