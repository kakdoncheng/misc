package def;


public interface BinaryTree<E extends Comparable<E>> {
    public void insert(E item); //inserts an E item to the BST
    public E find(E item); //returns the E item, else return null
    public void delete(E item); // deletes the E item
    public int height(E item); // returns the height of the E item, else return null
    public int maxHeight(); //returns the max height the BST
    public int leftSubtree(E item); //returns the size of the left subtree
    public int rightSubtree(E item); //returns the size of the right subtree
    public int status (E item); //retun 0 if root, 1 if left child, 2 if right child
    public void printInOrderTraversal(); //prints out the contents of the BST in order
}

//BST.java
//or is it implement BinaryTree< E extends Comparable<E>>????

//end BST.java








//Node.java
