package com.company;


import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
	// write your code here

        Set<String> u = new HashSet<String>();
        u.add("google.ca");
        u.add("gogle.ca");
        u.add("g0gle.ca");
        u.add("g00gle.ca");
        u.add("g00g1e.ca");
        u.add("goog1e.ca");
        u.add("gooogle.ca");


        Domains name = new Domains(u);
        //name.print();

        String [] editPathStore = name.editPath("google.ca", "g00g1e.ca");
        for(String path  : editPathStore){
            System.out.println(path);
        }
        System.out.println(name.editDistance("google.ca","g0gle.ca"));
        System.out.println(name.numNearby("google.ca",3));

    }
}
