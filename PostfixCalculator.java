
public class PostfixCalculator extends Parser{
	private SinglyLinkedList<String> postfix;
	public PostfixCalculator(Converter c) {
		postfix = parse(c.toPostfix());
	}
	public double calculate() {
		LinkedStack<Double> stack = new LinkedStack<Double>();
		while (!postfix.isEmpty()) {
			String first = postfix.first();
			if (Character.isDigit(first.charAt(0))) {
				stack.push((double)Integer.parseInt(first));
				postfix.removeFirst();
			}
			else {
				double temp = stack.pop();
				if (first.equals("+")) {
					double result = stack.pop() + temp;
					stack.push(result);
				}
				else if (first.equals("-")) {
					double result = stack.pop() - temp;
					stack.push(result);
				}
				else if (first.equals("*")) {
					double result = stack.pop() * temp;
					stack.push(result);
				}
				else if (first.equals("/")) {
					double result = stack.pop() / temp;
					stack.push(result);
				}
				else if (first.equals("^")) {
					double result = Math.pow(stack.pop(),temp);
					stack.push(result);
				}
				postfix.removeFirst();
			}
		}
		return stack.pop();
	}
}
