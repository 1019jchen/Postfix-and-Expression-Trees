public class ExpressTree<E> extends Parser{

private static class Node<E>
{
	private E element;
	private Node<E> left;
	private Node<E> right;
	
	public Node(E o)
	{
		this (o, null, null);
	}

	public Node(E o, Node<E> l, Node<E> r)
	{
		element = o;
		left = l;
		right = r;
	}
	public String toString()
	{
		return element.toString();
	}
	public void setLeft(Node<E> n) {
		left = n;
	}
	public Node<E> getLeft() {
		return this.left;
	}
	public void setRight(Node<E> n) {
		right = n;
	}
	public Node<E> getRight() {
		return this.right;
	}
	public E getElement() {
		return element;
	}
}

	private Node<String> root;
	private String prefix = "";
	private String infix = "";
	private String postfix = "";
	public ExpressTree()
	{
		root = null;
	}
	public ExpressTree(Node<String> n){
		root = n;
	}
	public void leftChild(Node<String> t)
	{
		t.setLeft(t);
	}
	
	public void rightChild(Node<String> t)
	{
		t.setRight(t);
	}
	
	public Node<String> getRoot(){
		return root;
	}

	
	public void convert(String postfix) {
		SinglyLinkedList<String> stringList = new SinglyLinkedList<String>();
		stringList = parse(postfix);
		LinkedStack<Node<String>> stack = new LinkedStack<Node<String>>();
		while (!stringList.isEmpty()) {
			String first = stringList.first();
			if (Character.isDigit(first.charAt(0))) {
				stack.push(new Node<String>(first));
				stringList.removeFirst();
			}
			else {
				Node<String> right = stack.pop();
				Node<String> left = stack.pop();
				Node<String> n = new Node<String>(first, left, right);
				stack.push(n);
				stringList.removeFirst();
			}
		}
		this.root = stack.pop();
	}
	public String postfix(Node<E> t)
	{
		if(t != null)
		{
			postfix(t.getLeft());
			postfix(t.getRight());
			postfix += t.toString() + " ";
		}
		return postfix;
	}
	public String infix(Node<String> t)
	{
		if(t != null)
		{
			if (!Character.isDigit(t.getElement().charAt(0))) {
				infix += "( ";
			}
			infix(t.getLeft());
			infix += t.toString() + " ";
			infix(t.getRight());
			if (!Character.isDigit(t.getElement().charAt(0))) {
				infix += ") ";
			}
		}
		return infix;
	}
	public String prefix(Node<E> t)
	{
		if(t != null)
		{
			prefix += t.toString() + " ";
			prefix(t.getLeft());
			prefix(t.getRight());
		}
		return prefix;
	}
}
