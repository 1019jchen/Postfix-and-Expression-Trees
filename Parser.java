
public abstract class Parser {
	protected SinglyLinkedList<String> parse(String s){
		SinglyLinkedList<String> parsed = new SinglyLinkedList<String>();
		for (int i=0; i<s.length(); i++) {
			char c = s.charAt(i);
			if (Character.isDigit(c)) {
				String number = c+"";
				for (int j = i + 1; j < s.length(); ++j) {
	                if (Character.isDigit(s.charAt(j))) {
	                    number += s.charAt(j);
	                    i = j;
	                } else {
	                    break;
	                }
				}
	            parsed.addLast(number);
			}
				else if (c == '*' || c == '/' || 
		                   c == '+' || c == '^' || 
		                   c == '-' || c == '(' || c == ')') {
		            parsed.addLast(c + "");
		        }
		}
		return parsed;
	}

}
