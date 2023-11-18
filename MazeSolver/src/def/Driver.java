package def;

public class Driver{
	public static void main(String[] args){
	    System.out.println("Welcome to my demo of Project 1:");
        BST<String> bst = new BST<String>();
        bst.insert("dog");
        System.out.println("The height of dog is: "+bst.height("dog"));
        System.out.println("The status of dog is: "+bst.status("dog"));
        bst.insert("cat");
        bst.insert("pig");
        bst.insert("horse");
        System.out.println("The current max height of the BST is: "+bst.maxHeight());
        bst.delete("pig");
        System.out.println("The height of horse is: "+bst.height("horse"));
        System.out.println("The current content of my BST :");
        bst.printInOrderTraversal();
        bst.insert("mango");
        bst.insert("apple");
        System.out.println("The height of apple is: "+bst.height ("apple"));
        System.out.println("The status of apple is: "+bst.status("apple"));
        bst.insert("mangrove");
        bst.insert("igloo");
        System.out.println("The current content of my BST: ");
        bst.printInOrderTraversal();
        System.out.println("The current max height of the BST is: "+bst.maxHeight());
        System.out.println("The left subtree of horse is size: "+bst.leftSubtree("horse"));
        System.out.println("The rigth subtree of horse is size: "+bst.rightSubtree("horse"));
	}
}