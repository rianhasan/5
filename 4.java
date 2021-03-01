class LinkedList 
{ 
    Node head;  // head of list 
  /
    class Node 
    { 
        int data; 
        Node next; 
        Node(int d) { data = d; next = null; } 
    } 

    boolean areIdenticalRecur(Node a, Node b) 
{ 
    
    if (a == null && b == null) 
        return true; 
  
     
    if (a != null && b != null) 
        return (a.data == b.data) && 
               areIdenticalRecur(a.next, b.next); 
  
    
    return false; 
} 
  

boolean areIdentical(LinkedList listb) 
{ 
    return areIdenticalRecur(this.head, listb.head); 
} 
