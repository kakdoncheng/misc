package def;

import java.util.ArrayList;

public class BST<E extends Comparable<E>> implements BinaryTree<E>{

	Node<E> root;//every tree has a root value
	
	public BST(){
	    root = null;
	}
	
	public void insert(E item) {
		if(root == null){//check if it's root element
			root = new Node<E>(item, null);//make it new node
		}//end check of root element
		else{//if it isn't
			Node<E> focusNode = root;//make the root our focus
			Node<E> parent;

			while(true){//indicating true for now
				parent = focusNode;
				if(item.compareTo(focusNode.getKey()) < 0){//check if new node goes left
					focusNode = focusNode.getLeftChild();//change focus to left based on IF
					if(focusNode == null){//check if leftchild has no children
						parent.setLeftChild(new Node<E>(item, parent)); //place new node into left
						return;//done
					}
				}
				else{//otherwise send new node goes right
					focusNode = focusNode.getRightChild();//change focus to right based on IF

					if(focusNode == null){//check if rightChild has no children
						parent.setRightChild(new Node<E>(item, parent));//place new node into right
						return;//done
					}
				}
			}
		}//end else on not root element
	}

	@Override
	public E find(E item) {
	    Node<E> focusNode = root;

		while(!focusNode.getKey().equals(item)){//search while haven't found key
			if(item.compareTo(focusNode.getKey()) < 0){
				focusNode = focusNode.getLeftChild();
			}
			else{//now look right
				focusNode = focusNode.getRightChild();
			}
			if(focusNode == null){//focusNode not found
				return null;
			}
			
		}//end while, after while loop create return.
		return focusNode.getKey();
	}
	
	private Node<E> findNode(E item) {
	    Node<E> focusNode = root;

		while(!focusNode.getKey().equals(item)){//search while haven't found key
			if(item.compareTo(focusNode.getKey()) < 0){
				focusNode = focusNode.getLeftChild();
			}
			else{//now look right
				focusNode = focusNode.getRightChild();
			}
			if(focusNode == null){//focusNode not found
				return null;
			}
			
		}//end while, after while loop create return.
		return focusNode;
	}


	@Override
	public void delete(E item) { //save this for last
		Node<E> focusNode = findNode(item); //this is the node we want to delete;
		if(focusNode == null){ //if the node doesnt exist
		    return;
		}
		if(focusNode.getLeftChild() == null && focusNode.getRightChild() == null){ //case 1: if no children exist
		    //deleting a node
		    if(focusNode.getKey().compareTo(focusNode.getParentNode().getKey()) < 0){ //if the node we want to delete is the left child
		        focusNode.getParentNode().setLeftChild(null);
		    }else{ //what if its the right child?
		        focusNode.getParentNode().setRightChild(null);
		    }
		}else if(focusNode.getLeftChild() != null && focusNode.getRightChild() == null){ //case 2: if only left child exists
		    //delete the node;
		    //move the child up one level;
		    if(focusNode.getKey().compareTo(focusNode.getParentNode().getKey()) < 0){ //if the node we want to delete is the left child
		        focusNode.getParentNode().setLeftChild(focusNode.getLeftChild());
		    }else{ //what if its the right child?
		        focusNode.getParentNode().setRightChild(focusNode.getLeftChild());
		    }
		    
		}else if(focusNode.getLeftChild() != null && focusNode.getRightChild() != null){//Case 3: if both childs exist
			if(focusNode.getKey().compareTo(focusNode.getParentNode().getKey()) < 0){//comparing left child
				focusNode.getParentNode().setRightChild(focusNode.getRightChild());//deletion of left, then goes up
			}else{//if right child
				focusNode.getParentNode().setLeftChild(focusNode.getRightChild());//goes up
			}
		}else{ //case 4: if both children exist;
		    //do case 4 on your own;
		    //use the node list to help delete and calculate moving children up level
		    clearNodeList();
		    updateNodeList(focusNode.getLeftChild());
		    updateNodeList(focusNode.getRightChild());
		    //this bolew if statement deletes the focusNode
		    if(focusNode.getKey().compareTo(focusNode.getParentNode().getKey()) < 0){ //if the node we want to delete is the left child
		        focusNode.getParentNode().setLeftChild(null);
		    }else{ //what if its the right child?
		        focusNode.getParentNode().setRightChild(null);
		    }
		    for(Node<E> n : list){ //this for loop loops though al the children below the node we want to delete
		        insert(n.getKey()); //adds all the children back into the BST
		    }
		}
	}

	@Override
	public int height(E item) {
	    Node<E> focusNode = root;
	    int height = 0;
		while(!focusNode.getKey().equals(item)){//search while haven't found key
			if(item.compareTo(focusNode.getKey()) < 0){
				focusNode = focusNode.getLeftChild();
				height++;
			}
			else{//now look right
				focusNode = focusNode.getRightChild();
				height++;
			}
			if(focusNode == null){//focusNode not found
				return -1;
			}
			
		}//end while, after while loop create return.
		return height;
	}

	@Override
	public int maxHeight(){//return max height of BST
		int finalHeight = 0; //initalizing the current maxHieght
		
		clearNodeList(); //refreshes the list
		updateNodeList(root); //refreshes the list
		
		//enhanced for loops
		for(Node<E> node:list){ //loop through all objects in the ArrayList named 'list', and access the objects with the name 'node';
		    int h = height(node.getKey()); 
		    if(h > finalHeight){
		        finalHeight = h;
		    }
		}
		return finalHeight;
	}
	
	private ArrayList<Node<E>> list = new ArrayList<Node<E>>(); //<<<<<< this list can store nodes temporarily; similar to RAM
    private void updateNodeList(Node<E> node) { //<<<<<<<<<<< this method is what updates the list;
	    if(node != null){
	        updateNodeList(node.getLeftChild());
	        list.add(node);
	        updateNodeList(node.getRightChild());
	    }
	    return;
	}
	private void clearNodeList(){ //high-level method which clears the list
	    list = new ArrayList<Node<E>>();
	}
    
    private int InOrderTraversal(Node<E> node) {
        int count = 0;
	    if(node != null){
	        count += InOrderTraversal(node.getLeftChild());
	        count++;
	        count += InOrderTraversal(node.getRightChild());
	    }
	    return count;
	}

	@Override
	public int leftSubtree(E item) {
	    //returns the size of the left subtree
	    Node<E> focusNode = findNode(item);
	    if(focusNode == null)
	        return -1;
	    focusNode = focusNode.getLeftChild();
	    if(focusNode == null){
	        return 0;
	    }
	    else{
	        return InOrderTraversal(focusNode);
	    }
	}

	@Override
	public int rightSubtree(E item){//return size rightsubtree
		Node<E> focusNode = findNode(item);
		if(focusNode == null){//check
			return -1;
		}
		focusNode = focusNode.getRightChild();//access rightSubtree
		if(focusNode == null){
			return 0;
		}
		else{
			return InOrderTraversal(focusNode);//
		}
	}

	@Override
	public int status(E item) {
	    //return 0 if root, 1 if left child, 2 if right child
	    Node<E> focusNode = findNode(item);
	    if(focusNode == null)
	        return -1;
	    if(focusNode.getParentNode() == null){//if focusNode is root 
	        return 0;
	    }
	    else{//if not a root, then it has to be a child
	        Node<E> parent = focusNode.getParentNode();
	        if(parent.getLeftChild().getKey().equals(focusNode.getKey())){// if focusNode is left child
	            return 1;
	        }else{// if focusNode is right child
	            return 2;
	        }
	    }
	}

	@Override
	public void printInOrderTraversal() {
		printInOrderTraversal(root);
	}
	
	private void printInOrderTraversal(Node<E> node) {
	    if(node != null){
	        printInOrderTraversal(node.getLeftChild());
	        System.out.println(node.toString());
	        printInOrderTraversal(node.getRightChild());
	    }
	}

	
}