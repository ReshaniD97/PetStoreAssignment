package com.microprofile.petstore;

import java.util.ArrayList;
import java.util.List;

public class PetSingleton {
    private static PetSingleton uniqInstance;

    public List<Pet> pets = new ArrayList<Pet>();

    private PetSingleton() {
        Pet pet1 = new Pet();
        pet1.setPetId(1);
        pet1.setPetAge(3);
        pet1.setPetName("Boola");
        pet1.setPetType("Dog");

        Pet pet2 = new Pet();
        pet2.setPetId(2);
        pet2.setPetAge(4);
        pet2.setPetName("Sudda");
        pet2.setPetType("Cat");

        Pet pet3 = new Pet();
        pet3.setPetId(3);
        pet3.setPetAge(2);
        pet3.setPetName("Peththappu");
        pet3.setPetType("Bird");

        pets.add(pet1);
        pets.add(pet2);
        pets.add(pet3);
    }

    public static PetSingleton getInstance() {
        if (uniqInstance == null)
            uniqInstance = new PetSingleton();
        return uniqInstance;
    }
    public void setArrayList(List<Pet> pets)
    {
        this.pets = pets;

    }
    public List<Pet> getPetList()
    {
        return this.pets;

    }
}
