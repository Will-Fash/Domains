package com.company;


import java.util.ArrayList;
import java.util.HashMap;


public class Graph {

    //Nested Node class
    class Node{

        //Value of the Node
        private String value;

        //Arraylist to store adjacentlist of nodes
        protected ArrayList<Node> connected = new ArrayList<Node>();


        //Constructor for Node class
        public Node(String value) {
            this.value = value;
        }


        public ArrayList<Node> getConnected() {
            return connected;
        }//Generated getter method to get Node attribute connected


        public void addConnected(Node node){
            this.connected.add(node);
        }//Adds a node to connected node list essentially connects the nodes.


        public String getValue() {
            return value;
        }//Generated getter method to get value of Node attribute value.

    }


    private Node node;
    protected HashMap<String,Node> adjList;//Create a HashMap for Node(trying out a different implementation)
    protected ArrayList<Node> visited = new ArrayList<Node>();//Adds Nodes that have been visited in the graph so traversal doesn't go back to those nodes
    private ArrayList<Node> ePath = new ArrayList<Node>();//The current path from a source node to a destination node
    private int editDistance = -1;
    private boolean isFound = false;
    private int numNearbyCount = -1;

    public Graph(){
        this.adjList = new HashMap<String, Node>();
    }//HashMap of String Key and Value Nodes

    //Calls TraversalEditPath method and instantiates helper variables for the traversal
    public String [] startEditPathTraversal(String dn1 , String dn2){

        isFound = false;
        ePath = new ArrayList<Node>();
        visited = new ArrayList<Node>();
        this.TraversalEditPath(adjList.get(dn2),adjList.get(dn1),adjList.get(dn1));
        String[] result = new String[ePath.size()];
        int count = 0;
        for(Node childNode : ePath){
            result[count] = childNode.value;
            count++;
        }

        return result;

    }

    //Method to traverse the Graphs edges to get edit path
    private void TraversalEditPath(Node searchDn, Node startNode, Node savePoint){
        if(!visited.contains(startNode)){
            visited.add(startNode);
            if(startNode == searchDn){
                isFound = true;
                ePath.add(startNode);

            }else{
                if(!isFound){
                    if(NodeEnd(startNode)){
                        ePath.add(startNode);
                        delEditPaths(savePoint,startNode);
                    }else{
                        if(startNode.getConnected().size() == 1){
                            ePath.add(startNode);
                            for(Node child : startNode.getConnected()){
                                TraversalEditPath(searchDn,child,savePoint);
                            }

                        }else{
                            ePath.add(startNode);
                            for(Node child : startNode.getConnected()){
                                TraversalEditPath(searchDn,child,child);
                            }
                        }
                    }

                }

            }
        }

    }

    //calls TraversalEditDistance method and instantiates helper variables
    public int getEditDistance(String dn1, String dn2){
        Node Searchdn = adjList.get(dn1);
        Node Startdn = adjList.get(dn2);

        visited = new ArrayList<Node>();
        TraversalEditDistance(Searchdn,Startdn,0);
        return editDistance;
    }

    //Method to traverse the Graphs edges to get edit distance between 2 nodes
    private void TraversalEditDistance(Node searchDn, Node startNode, int currentEditDistance){
        if(!visited.contains(startNode)){
            visited.add(startNode);
            if(startNode == searchDn){
                currentEditDistance = currentEditDistance + 1;
                if(editDistance == -1){
                    editDistance = currentEditDistance;
                }else if(editDistance < currentEditDistance){
                    editDistance = currentEditDistance;
                }
            }else{
                    if(!NodeEnd(startNode)){
                        for(Node child : startNode.getConnected()){
                            currentEditDistance = currentEditDistance + 1;
                            TraversalEditDistance(searchDn,child,currentEditDistance);
                        }
                    }
                }


        }
    }

    //Method  to traverses through graph to get node within an edit distance
    private void numNearbyTraverse(Node numNode, int distance){

        if(!visited.contains(numNode)){
            numNearbyCount = numNearbyCount + 1;
            if(distance > 0){
                if(!NodeEnd(numNode)){
                    visited.add(numNode);
                    for(Node child : numNode.getConnected()){
                        distance = distance - 1;
                        numNearbyTraverse(child, distance);
                    }
                }
            }
        }
    }

    //calls method numNearbyTraverse and instantiates helper variables
    public int numNearby(String dn1, int dis){
        visited = new ArrayList<Node>();
        numNearbyCount = 0;
        Node Numdn = adjList.get(dn1);
        numNearbyTraverse(Numdn,dis);
        return numNearbyCount;
    }

    //To delete already established path if the endpoint isn't the searh node.
    private void delEditPaths(Node savepoint , Node endpoint){
        int savePoint = this.ePath.indexOf(savepoint) + 1;
        int endPoint = this.ePath.indexOf(endpoint);
        for(int num = savePoint; num <= endPoint; num++){
            this.ePath.remove(num);
        }
    }

    //Checks if the node is at the end
    private boolean NodeEnd(Node node){
        boolean result = true;
        for(Node childNode : node.getConnected()){
            if(!visited.contains(childNode)){
                result = false;
            }
        }
        return result;
    }


}
