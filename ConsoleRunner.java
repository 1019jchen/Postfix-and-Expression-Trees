import java.util.Scanner;
public class ConsoleRunner {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String input = "";
		while(!input.equals("close")) {
			System.out.println("type \"close\" to end the program");
			System.out.println("type your infix expression:");
			input = sc.nextLine();
			if (input.equals("close")) {
				break;
			}
			Converter c = new Converter(input);
			String postfix = c.toPostfix();
			ExpressTree<String> e = new ExpressTree<String>();
			e.convert(postfix);
			System.out.print("prefix: ");
			System.out.println(e.prefix(e.getRoot()));
			System.out.print("infix: ");
			System.out.println(e.infix(e.getRoot()));
			System.out.print("postfix: ");
			System.out.println(e.postfix(e.getRoot()));
			System.out.println();
			PostfixCalculator p = new PostfixCalculator(c);
			System.out.println(p.calculate());
		}
		sc.close();
	}
}
