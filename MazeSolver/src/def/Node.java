package def;

public class Node<E extends Comparable<E>>{
	private E key;
	//String name;

	private Node<E> leftChild;//can only have 1
	private Node<E> rightChild;//can only have 1
	private Node<E> parent;
	public boolean isRed;

	public Node(E e, Node<E> parent) {
		this.key = e;
		this.leftChild = null;
		this.rightChild = null;
        this.parent = parent;
		//this.isBlack = false;
		this.isRed = true;
	}
	
	public void setRed() {
		this.isRed = true;
		//this.isBlack = false;
	}
	public void setBlack() {
		this.isRed = false;
		//this.isBlack = true;
	}
    public void switchColor(){
    	if(isRed){
          	isRed = false;
        }else{
          	isRed = true;
        }
    }
    public E getKey(){
        return key;
    }
    
    public Node<E> getLeftChild(){
        return leftChild;
    }
    public Node<E> getRightChild(){
        return rightChild;
    }
    public Node<E> getParentNode(){
        return parent;
    }
    
    public void setLeftChild(Node<E> newLeftNode){
        leftChild = newLeftNode;
    }
    public void setRightChild(Node<E> newRightNode){
        rightChild = newRightNode;
    }
    
	public String toString(){
		return key.toString();
	}

	public E getData() {
		// TODO Auto-generated method stub
		return getKey();
	}

}