
public class Converter extends Parser{
	private String infix;
	public Converter() {
		infix = "";
	}
	public Converter(String infix) {
		this.infix = infix;
	}
	public String toPostfix() {
		SinglyLinkedList<String> stringList = parse(infix);
		LinkedStack<String> stack = new LinkedStack<String>();
		String postfix = "";
		String first;
		while (!stringList.isEmpty()) {
			first = stringList.first();
			if (Character.isDigit(first.charAt(0))) {
				postfix += first + " ";
			}
			else if (first.equals("(")) {
				stack.push(first);
			}
			else if (first.equals(")")) {
				while(!stack.top().equals("(")) {
					postfix += stack.pop()+ " ";
				}
				stack.pop();
			}
			else {
				if (!stack.isEmpty()&&operatorPrecedence(first)>operatorPrecedence(stack.top())) {
					stack.push(first);
				}
				else if (stack.isEmpty()) {
					stack.push(first);
				}
				else{
					while (!stack.isEmpty()&& operatorPrecedence(first)<=operatorPrecedence(stack.top())){
					postfix += stack.pop()+ " ";
					}
					stack.push(first);
				}
			}
			stringList.removeFirst();
		}
		while (!stack.isEmpty()) {
			if (stack.top().equals("(")) {
				stack.pop();
			}
			else {
				postfix += stack.pop();
			}
		}
		return postfix;
	}
	public int operatorPrecedence(String a) {
		final int ADDSUBTRACT = 1;
		final int MULTIPLYDIVIDE = 2;
		final int EXPONENT = 3;
		if (a.equals("+")||a.equals("-")) {
			return ADDSUBTRACT;
		}
		else if (a.equals("*")||a.equals("/")) {
			return MULTIPLYDIVIDE;
		}
		else if (a.equals("^")) {
			return EXPONENT;
		}
		return 0;
	}
}
