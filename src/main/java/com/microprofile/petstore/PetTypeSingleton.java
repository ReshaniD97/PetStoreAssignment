package com.microprofile.petstore;

import java.util.ArrayList;
import java.util.List;

public class PetTypeSingleton {

    private static PetTypeSingleton Instance;

    public List<PetType> petsType = new ArrayList<PetType>();

    private PetTypeSingleton() {
        PetType pet1 = new PetType();
        pet1.setPetTypeId(1);
        pet1.setPetType("Dog");

        PetType pet2 = new PetType();
        pet2.setPetTypeId(2);
        pet2.setPetType("Cat");

        PetType pet3 = new PetType();
        pet3.setPetTypeId(3);
        pet3.setPetType("Bird");

        petsType.add(pet1);
        petsType.add(pet2);
        petsType.add(pet3);
    }

    public static PetTypeSingleton getInstance() {
        if (Instance == null)
            Instance = new PetTypeSingleton();
        return Instance;
    }
    public void setPetsTypeList(List<PetType> petsType)
    {
        this.petsType = petsType;

    }
    public List<PetType> getPetsTypeList()
    {
        return this.petsType;

    }
}
