package net.lightcraftmc.fusebox.pet;

import java.util.ArrayList;

public class PetList {

	static ArrayList<Pet> pets = new ArrayList<Pet>();
	
	public static void addPet(Pet pet){
		if(!pets.contains(pet)){
			pets.add(pet);
		}
	}

}
