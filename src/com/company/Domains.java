package com.company;

import java.util.Set;


public class Domains {

    Graph g = new Graph();//Object of class graph

    public Domains(Set <String> d){
        this.load(d);
    }//Constructor calls the load function, takes in a set as parameter


    //Load function takes in a set and returns a boolean. First it sets it's feedback to false, then it iterates through the set parameter in the method,
    //then it adds a Node object with it's key to the hashmap defined in the graph class if the hashmap is empty and updates the variable feedback to true,
    //then once the hashMap isn't empty it compares the incoming node with already present nodes in the hashmap and if the edit distance between them is 1
    //it connects them.
    //It checks if the domainSet elements have been added correctly into the adjlist defined in class graph by returning a boolean variable.
    public boolean load(Set <String> domainSet){
        boolean feedBack = false;
        for(String i : domainSet){
            if(g.adjList.isEmpty()){
                Graph.Node in = g.new Node(i.toLowerCase());//Declaring and adding Node object from domainSet elelment
                g.adjList.put(i,in);
                feedBack = true;
            }else{
                Graph.Node in = g.new Node(i.toLowerCase());//Declaring and adding Node object from domainSet elelment
                g.adjList.put(i,in);
                for(String dn : g.adjList.keySet() )
                    if(compare(dn,i) == 1){
                        g.adjList.get(dn).addConnected(g.adjList.get(i));
                        g.adjList.get(i).addConnected(g.adjList.get(dn));
                    }
                feedBack = true;
            }
        }
        if(g.adjList.size() != domainSet.size()){
            feedBack = false;
        }

        for (String k : g.adjList.keySet()) {
            if(g.adjList.get(k).getConnected().size() == 0){
                feedBack = false;
            }
        }
        return feedBack;
    }

   //method uses both the insertDelete method and swap method to compare strings and connects string with a edit difference of 1
   //if the lengths of the strings are the same it calls the swap method and passes the result to the ed variable
   //if the lengths of the string are different it calls the insertDelete method and passes the result to the ed variable.
    public int compare(String dn1, String dn2){
        int ed = 0;
        if(dn1.length() == dn2.length()){
            ed = swap(dn1,dn2);
        }else{
            ed = insertDelete(dn1, dn2);
        }
        return ed;
    }


    //Method to check if difference is 1 based on insertion and deletion, essentially it ignores execution when difference is > 1
    //It works by comparing the lengths of the string; if difference in length > 1 it returns the editions variable as is.
    //If length between the strings is 1 it increments the edition variable and checks to see if the string of lesser length is in the string of
    //higher length and increments edition if the lesser string isn't in the higher String.
    public int insertDelete(String dn1, String dn2){
        int editions = 0;
        if(Math.abs(dn1.length() - dn2.length()) > 1){
            return editions;
        }
        if(dn1.length() - dn2.length() == 1){
            editions += 1;
            for(int i = 0; i < dn2.length(); i++){
                if(!(dn1.toLowerCase().contains(dn2.toLowerCase().substring(i,i+1)))){

                    editions += 1;
                }
            }
        }else if(dn2.length() - dn1.length() == 1){
            editions =+ 1;
            for(int i = 0; i < dn1.length(); i++){
                if(!dn2.toLowerCase().contains(dn1.toLowerCase().substring(i,i+1))){
                    editions += 1;
                }
            }
        }
        return editions;
    }

    //This handles the case where string lengths are the same, if the string are the same it returns editions as is else it checks to find the difference
    //and increments editions.
    public int swap(String dn1, String dn2){
        int editions = 0;
            if(dn1.equalsIgnoreCase(dn2)){
                return editions;
            }else {
                for (int i = 0; i < dn1.length(); i++) {
                    if(!dn1.toLowerCase().substring(i,i+1).equals(dn2.toLowerCase().substring(i,i+1))){
                        editions += 1;
                    }
                }
            }

        return editions;
    }

    //Method to print out adjList in class graph(just for testing purposes)
    public void print(){
        for(Graph.Node i : g.adjList.values()){
            System.out.println("\n--\n");
            System.out.println(i.getValue());
            for(Graph.Node k : i.getConnected()){
                System.out.print(k.getValue()+" ");
            }
            System.out.println("\n--\n");
        }
    }


    //Call helper method to return path between 2 nodes
    public String [] editPath(String dn1, String dn2){
        return g.startEditPathTraversal(dn1,dn2);
    }

    //Calls helper method from graph class to find shortest path between 2 nodes
    public int editDistance(String dn1, String dn2){

        return g.getEditDistance(dn1,dn2);
    }

    //Calls numNearby from graph class using graph object and returns nodes within an edit distance from a specified node
    public int numNearby(String dn1, int d){

        return g.numNearby(dn1, d);
    }

}
