package com.microprofile.petstore;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "PetType")
public class PetType {
    @Schema(required = true, description = "Pet type id")
    @JsonProperty("pet_type_id")
    private Integer petTypeId;

    @Schema(required = true, description = "Pet type")
    @JsonProperty("pet_type")
    private String petType;

    public PetType()
    {
    }

    public PetType( Integer petTypeId, String petType )
    {
        this.petTypeId = petTypeId;
        this.petType = petType;
    }

    public Integer getPetTypeId() {
        return petTypeId;
    }

    public void setPetTypeId(Integer petTypeId) {
        this.petTypeId = petTypeId;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }
}
