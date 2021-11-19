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

@Path("/pets")
@Produces("application/json")

public class PetResource {
    List<Pet> pets = new ArrayList<Pet>();


    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "All Pets", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
    @GET
    public Response getPets() {

        return Response.ok(PetSingleton.getInstance().getPetList()).build();
    }



    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Create a Pet", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
    @POST
    @Path("/create_pet")
    public Response createPet(Pet pet) {

        List<Pet> pets = new ArrayList<Pet>();
        List<Pet> tempPetVal = new ArrayList<Pet>();

        if( pet.getPetName() != null && pet.getPetAge() != null && pet.getPetType() != null){
            pet.setPetId(PetSingleton.getInstance().getPetList().get(PetSingleton.getInstance().getPetList().size()-1).getPetId()+1);
            pet.setPetName(pet.getPetName());
            pet.setPetType(pet.getPetType());
            pet.setPetAge(pet.getPetAge());
            pets.add(pet);
            tempPetVal.addAll(PetSingleton.getInstance().getPetList());
            tempPetVal.addAll(pets);
            PetSingleton.getInstance().setArrayList(tempPetVal);
            return Response.ok(pet).build();
        }else{
            return Response.ok("{\n" + "\"successful\":fail\n" + "}").build();

        }
    }

    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Update a Pet", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
            @APIResponse(responseCode = "404", description = "No Pet found for the id.")})
    @PUT
    @Path("/update_pet/{petId}")

    public Response updatePet(@PathParam("petId") int petId ,Pet petData){
        if (petId < 0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        boolean flagUpdated = false;
        int id =0;
        if(petData.getPetName() != null){
            for (int i=0;i<PetSingleton.getInstance().getPetList().size();i++){
                if(petId == PetSingleton.getInstance().getPetList().get(i).getPetId()){
                    PetSingleton.getInstance().getPetList().get(i).setPetName(petData.getPetName());
                    flagUpdated=true;
                    id = i;
                }
            }
        }
        if(petData.getPetAge() != null){
            for (int i=0;i<PetSingleton.getInstance().getPetList().size();i++){
                if(petId == PetSingleton.getInstance().getPetList().get(i).getPetId()){
                    PetSingleton.getInstance().getPetList().get(i).setPetAge(petData.getPetAge());
                    flagUpdated=true;
                    id = i;
                }
            }
        }
        if(petData.getPetType()  != null){
            for (int i=0;i<PetSingleton.getInstance().getPetList().size();i++){
                if(petId == PetSingleton.getInstance().getPetList().get(i).getPetId()){
                    PetSingleton.getInstance().getPetList().get(i).setPetType(petData.getPetType());
                    flagUpdated=true;
                    id = i;
                }
            }
        }
        if(flagUpdated){
            return Response.ok(PetSingleton.getInstance().getPetList().get(id)).build();
        }else{
            return Response.ok("{\n" + "\"successful\":fail\n" + "}").build();
        }

    }

    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Delete a Pet", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
            @APIResponse(responseCode = "404", description = "No Pet found for the id.")})
    @DELETE
    @Path("/delete_pet/{petId}")

    public Response deletePet(@PathParam("petId") int petId ){
        if (petId < 0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        boolean PetFlag = false;
        for (int i=0;i<PetSingleton.getInstance().getPetList().size();i++){
            if(petId == PetSingleton.getInstance().getPetList().get(i).getPetId()){
                PetSingleton.getInstance().getPetList().remove(i);
                PetFlag = true;
            }
        }
        if(PetFlag){
            return Response.ok("{\n" + "\"successful\":pass\n" + "}").build();
        }else{
            return Response.ok("{\n" + "\"successful\":fail\n" + "}").build();
        }

    }


    @Path("/search")
    @GET
    public Response searchPet(@DefaultValue("-1") @QueryParam("id") int petId,
                              @DefaultValue("null") @QueryParam("name") String petName,
                              @DefaultValue("0") @QueryParam("age") int petAge){

        boolean PetFlag = false;

        int id = 0;
        if(petId != -1 && petName.equals("null") && petAge == 0){
            if (petId < 0) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            for (int i=0;i<PetSingleton.getInstance().getPetList().size();i++){
                if(petId == PetSingleton.getInstance().getPetList().get(i).getPetId()){
                    PetFlag = true;
                    id = i;
                }
            }

            if(PetFlag){
                return Response.ok(PetSingleton.getInstance().getPetList().get(id)).build();
            }else{
                return Response.ok("There is a no pet that id = "+petId).build();
            }

        }else if(petId == -1 && !petName.equals("null") && petAge == 0){
            for (int i=0;i<PetSingleton.getInstance().getPetList().size();i++){
                if(petName.equals(PetSingleton.getInstance().getPetList().get(i).getPetName())){
                    PetFlag = true;
                    id = i;
                }
            }
            if(PetFlag){
                return Response.ok(PetSingleton.getInstance().getPetList().get(id)).build();
            }else{
                return Response.ok("There is a no pet that name = "+petName).build();
            }

        }else if(petId == -1 && petName.equals("null") && petAge != 0){
            List<Pet> temp = new ArrayList<Pet>();
            for (int i=0;i<PetSingleton.getInstance().getPetList().size();i++){
                if(petAge == PetSingleton.getInstance().getPetList().get(i).getPetAge()){
                    PetFlag = true;
                    id = i;
                    temp.add(PetSingleton.getInstance().getPetList().get(id));
                }
            }
            if(PetFlag){
                return Response.ok(temp).build();
            }else{
                return Response.ok("There is a no pet that age = "+petAge).build();
            }
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }

}

