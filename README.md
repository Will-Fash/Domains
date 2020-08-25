## What the program does:
The program takes a Set of domain names and loads them into a graph adding edges between
domain names based on the edit difference i.e. a difference of 1 between them. The edit
difference ought to be one and nothing greater than one. The programs is supposed to find the
domains that are close to one another. We can find more information on what the program
ought to do in the assignment 3 question.

## Program Approach:
I built 3 classes, A domain class that implements all the required methods, the domain class
takes a set of domain names and adds them to the graph and then connects them based on the
edit difference of 1. A node class that serves as a helper class to a graph class, Hence the node
class is nested in the graph class. The Node class has a string attribute and a connected array
attribute, the string attribute is the domain value and the connected attribute contains a list of
connected domains based on the edit difference of 1. The Graph class loads a node object into
a HashMap, the HashMap key is a string from the domainSet which equals the string value of
the node while the value of the HashMap is the Node object. The graph class also implements
traversal methods (Depth first) to find the editpaths, edit distance and int numNearby.

## DataStructures:
The dataStructures used were an HashMap and arrayList.
The HashMap stored the Nodes and their keys for easy access and manipulation while the
arraylist in class node represents an adjacency list used to build the graph.
Key Algorithms:
Depth first traversal to implement edit distance(), edit node() and numNearby();
Load function algorithm using compare to algorithm to determine how to load domain Set into
graph.

## Assumptions:
Domain elements are of type string.
Connection criteria between nodes is an edit difference of 1.
Graph is a unidirectional graph because edit difference is constant between nodes.

## Test Cases:

#### boolean load( Set<String> domainSet ):
• domainSet not of type string
• domainSet is empty
• domainSet contains only one element
• domainSet elements aren’t within edit difference of 1
• an element in domainSet is not of type string
• domainSet is not empty
• domainSet is of type string
• domainSet elements not actually a domain name i.e go1egleca instead of google.ca

#### int editDistance( String domainName1, String domainName2 ):
• Either of the domain names are empty
• Both domain names are empty
• Either of domain names are not of type string
• Both domain name aren’t of type string
• Both domain names are of type string

#### int numNearby( String domainName, int distance):
• Either of the parameters not of the declared type;
• Both of the parameters not of the declared type
• Both of the parameters are of the declared type
• Either of the parameters are empty
• Both of the parameters are empty
• Both of the parameters aren’t empty

#### String[] editPath( String domainName1, String domainName2 ):
• Either of the domain names are empty
• Both domain names are empty
• Either of domain names are not of type string
• Both domain name aren’t of type string
• Both domain names are of type string