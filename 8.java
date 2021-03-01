public class DoublyLinkedList<E> implements Cloneable {
    //------------nested Node class------------
    private static class Node<E> {
        private E element;      
        private Node<E> prev;    
        private Node<E> next;    

    /** The constructor that creates a node */
    public Node(E e, Node<E> p, Node<E> n) {
        element = e;
        prev = p;
        next = n;
    }

    // methods
    /** getter for the element */
    public E getElement() {
        return element;
    }

    public Node<E> getPrev() {
        return prev;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setPrev(Node<E> p) {
        prev = p;
    }

    public void setNext(Node<E> n) {
        next = n;
    }
} //------------end of nested node class------------


private Node<E> header;    
private Node<E> trailer;    
private int size = 0;      

/** List constructor */
public DoublyLinkedList() {
    header = new Node<E>(null, null, null);      
    trailer = new Node<E>(null, header, null);   
    header.setNext(trailer);                     
}



public int getSize() {
    return size;
}

/** Tests whether the linked list is empty */
public boolean isEmpty() {
    return size == 0;
}

public E first() {
    if (isEmpty()) {
        return null;
    } else {
        return header.getNext().getElement();  // return first node's element
    }
}


public E last() {
    if (isEmpty()) {
        return null;
    } else {
        return trailer.getPrev().getElement();  // return last node's element
    }
}



public void addFirst(E e) {
    addBetween(e, header, header.getNext());
}

/** Adds element e to the back of the list */
public void addLast(E e) {
    addBetween(e, trailer.getPrev(), trailer);
}

/** Removes and returns the first element of the list */
public E removeFirst() {
    if (isEmpty()) {
        return null;
    } else {
        return remove(header.getNext());
    }
}

/** Removes and returns the last element of the list */
public E removeLast() {
    if (isEmpty()) {
        return null;
    } else {
        return remove(trailer.getPrev());
    }
}

// private update helpers
private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
    // create and link a new node
    Node<E> newest = new Node<>(e, predecessor, successor);
    predecessor.setNext(newest);
    successor.setPrev(newest);
    size++;
}

private E remove(Node<E> node) {
    Node<E> predecessor = node.getPrev();
    Node<E> successor = node.getNext();
    predecessor.setNext(successor);
    successor.setPrev(predecessor);
    size--;
    return node.getElement();
}



    @SuppressWarnings({ "rawtypes" })
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        // at this point, the classes have to be the same.
        if (getClass() != o.getClass()) {
            return false;
        }

        DoublyLinkedList other = (DoublyLinkedList) o; 
        // the size must be the same for them to be equal
        if (size != other.size) {
            return false;
        }

        Node walkA = header;                             // traverse primary list
        Node walkB = other.header;                       // traverse secondary list

        // We don't want to compare the trailers, so size - 1
        for(int i = 0; i < size; i++) {
            if (!walkA.getElement().equals(walkB.getElement())) {
                return false; // mismatch
            }
            walkA = walkA.getNext();
            walkB = walkB.getNext();
        }
        return true;             // if we reach this, then they are equal.
    }



    @SuppressWarnings("unchecked")
    public DoublyLinkedList<E> clone() throws CloneNotSupportedException {
        
        DoublyLinkedList<E> other = (DoublyLinkedList<E>) super.clone(); // safe cast
        if (size > 0) {                      // we need independent node chain
            other.header = new Node<>(null, null, null);
            other.trailer = new Node<>(null, other.header, null);
            other.header.setNext(other.trailer);
            Node<E> walk = header.getNext();   // walk through remainder of original list
            Node<E> otherWalk = other.header;  
            for(int i = 0; i < size; i++) {           // make new node storing same element
                Node<E> newest = new Node<>(walk.getElement(), null, null);
                otherWalk.setNext(newest);   // link previous node to this one
                otherWalk = newest;
                otherWalk.setPrev(newest);   // link node back to the previous one
                walk = walk.getNext();
            }
        }
        return other;
    }