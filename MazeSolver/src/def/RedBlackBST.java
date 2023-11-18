package def;

public class RedBlackBST< E extends Comparable <E>>{
	
	public Node<E> root;//every tree has a root value

    public RedBlackBST(){
    	root = null;
    }
    
	/*Insertion*/
	public void insert(E item) {
		if(root == null){//check if it's root element
			root = new Node<E>(item, null);//make it new node
          	root.setBlack();
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
                      	fixTree(parent.getLeftChild());
						return;//done
					}
				}
				else{//otherwise send new node goes right
					focusNode = focusNode.getRightChild();//change focus to right based on IF
					if(focusNode == null){//check if rightChild has no children
						parent.setRightChild(new Node<E>(item, parent));//place new node into right
                      	fixTree(parent.getRightChild());
						return;//done
					}
				}
			}
		}//end else on not root element
	}
	
	private void fixTree(Node<E> node) {
    	//case 0: if the node inserted (n) had to be the root node;
      		//color n black;
      	//case 1: n.uncle is red;
      		//re-color n.parent, n.parent.parent, n.uncle;
      		//re-point the focused node to n.parent.parent;
      	//case 2: n.uncle is black && n is zig-zagged;
      		//rotate n;
      		//if the uncle is left child;
      			//re-point the focused node to n.rightChild
      		//else
      			//re-point the focused node to n.leftChild
      	//case 3: n.uncle is black && n is zig-zigged;
      		//rotate n.parent;
      		//recolor n.parent, n.parent.parent;
      	while(node.getParentNode().isRed){
          	if(node.getParentNode().getData().compareTo(node.getParentNode().getParentNode().getLeftChild().getData()) == 0){
              	//uncle is the right child (parent is left child);
              	if(node.getParentNode().getParentNode().getRightChild().isRed){
                  	//this is case 1: if uncle is red
                  	node.getParentNode().switchColor();
                  	node.getParentNode().getParentNode().switchColor();
                  	node.getParentNode().getParentNode().getRightChild().switchColor();
                  	node = node.getParentNode().getParentNode();
                }else{
                  	//else case 2&3;
                  	if(node.getData().compareTo(node.getParentNode().getLeftChild().getData()) == 0){
                      	//case 3; zig-zig case;
                      	rotate(node.getParentNode().getData(), node.getParentNode().getParentNode());
                      	node.getParentNode().switchColor();
                      	node.getParentNode().getParentNode().switchColor();
                    }else{
                      	//case 2; zig-zag case;
                      	rotate(node.getData(), node.getParentNode());
                      	node = node.getLeftChild();
                    }
                }
            }else{
              	//uncle is the left child (parent is right child);
              	if(node.getParentNode().getParentNode().getLeftChild().isRed){
                  	//this is case 1: if uncle is red
                  	node.getParentNode().switchColor();
                  	node.getParentNode().getParentNode().switchColor();
                  	node.getParentNode().getParentNode().getLeftChild().switchColor();
                  	node = node.getParentNode().getParentNode();
                }else{
                  	//else case 2&3;
                  	if(node.getData().compareTo(node.getParentNode().getRightChild().getData()) == 0){
                      	//case 3; zig-zig case;
                      	rotate(node.getParentNode().getData(), node.getParentNode().getParentNode());
                      	node.getParentNode().switchColor();
                      	node.getParentNode().getParentNode().switchColor();
                    }else{
                      	//case 2; zig-zag case;
                      	rotate(node.getData(), node.getParentNode());
                      	node = node.getRightChild();
                    }
                }
            }
        }
      	//case 0;
      	root.setBlack();
    }
	
	/*Rotations*/	
	private Node<E> rotate(E item, Node<E> parent)
    {
        if(item.compareTo(parent.getData()) < 0) 
        {		
        		if (item.compareTo(parent.getLeftChild().getData()) < 0) 
        			parent.setLeftChild(rotateWithLeftChild(parent.getLeftChild()));
        		else 
        			parent.setLeftChild(rotateWithRightChild(parent.getLeftChild()));
        		return parent.getLeftChild();
        } 
        else 
        {
        		if (item.compareTo(parent.getRightChild().getData()) < 0) 
        			parent.setRightChild(rotateWithLeftChild(parent.getRightChild()));
        		else
        			parent.setRightChild(rotateWithRightChild(parent.getRightChild()));
        		return parent.getRightChild();
        }
    }
	
	private Node<E> rotateWithLeftChild(Node<E> k2)
    {	
        Node<E> k1 = k2.getLeftChild();
        k2.setLeftChild(k1.getRightChild());
        k1.setRightChild(k2);
        return k1;
    }
	
	private Node<E> rotateWithRightChild(Node<E> k1)
    {
        Node<E> k2 = k1.getRightChild();
        k1.setRightChild(k2.getLeftChild());
        k2.setLeftChild(k1);
        return k2;
    }
	
  	/*Finding*/	
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
	
	protected Node<E> findNode(E item) {
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
  
	/*Deletion Time*/
	public void delete(E item){
		//Node<E> focusNode = findNode(item);
	}
	
	public void printInOrderTraversal(){//Traversal by In Order
		printInOrderTraversal(root);
	}

	private void printInOrderTraversal(Node<E> node){//Traversal
		if(node != null){//if it doesn't exist
			/*Recursively*/
			printInOrderTraversal(node.getLeftChild());
			System.out.println(node.toString());
			printInOrderTraversal(node.getRightChild());

		}
	}
	
	public int redNodes() {
		return 0;
	}
	
	public int blackHeight() {
		return 0;
	}
	
	
}