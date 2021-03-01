
لاضافة عقده في نهايه

static Node addBegin(Node last, int data)
{

    if (last == null)

        return addToEmpty(last, data);

   

      // Creating a node dynamically

    Node temp = new Node();

      

      // Assigning the data

    temp.data = data;

   

      // Adjusting the links

    temp.next = last.next;

    last.next = temp;

  

    return last;
}