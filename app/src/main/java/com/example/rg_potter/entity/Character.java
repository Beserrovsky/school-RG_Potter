package com.example.rg_potter.entity;

public class Character{

    public int id = 0;
    public String name = null;
    public String birth = null;
    public String death = null;
    public String species = null;
    public String ancestry = null;
    public String gender = null;
    public String hair_color = null;
    public String eye_color = null;
    public String wand = null;
    public String patronus = null;
    public String house = null;
    public String[] associated_groups = null;
    public int[] books_featured_in = null;

    public House House = null;
    public Gender Gender = null;

    public Character(){
        this.Gender = new Gender(this.gender);
        this.House = new House(this.house);
    }

}
