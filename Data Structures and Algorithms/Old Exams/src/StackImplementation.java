import java.util.Scanner;

public class StackImplementation {

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		int n = console.nextInt();
		int x;
		Stack stack = new Stack();
		for (int i = 0; i < n; i++) {
			x = console.nextInt();
			if (x == -1) {
				System.out.println(stack.pop());
			} else if (x != -1) {
				stack.push(x);
			}
		}

	}

}

class Stack {
	int Size;
	Node first;

	public Stack() {
		this.Size = 0;
	}

	public int pop() {
		Node x = first;
		this.first = first.next;
		return x.value;
	}

	public void push(int x) {
		Node next = first;
		first = new Node(x, next);
	}

}

class Node {
	int value;
	Node next;

	public Node(int value, Node next) {
		this.value = value;
		this.next = next;
	}
}
