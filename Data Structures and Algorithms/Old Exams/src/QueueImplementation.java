import java.util.Scanner;

public class QueueImplementation {

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		int n = console.nextInt();
		int x;
		Queue queue = new Queue();
		for (int i = 0; i < n; i++) {
			x = console.nextInt();
			if (x == -1) {
				System.out.println(queue.pop());
			} else if (x != -1) {
				queue.push(x);
			}
		}

	}

}

class Queue {
	Nodee first;
	Nodee last;

	public Queue() {
	}

	public int pop() {
		Nodee x = first;
		this.first = first.next;
		this.first.prev = null;
		return x.value;
	}

	public void push(int x) {
		Nodee node = new Nodee(x, null);
		if (first == null) {
			first = node;
			last = first;
		} else {
			Nodee prev = last;
			last = node;
			node.prev = prev;
			prev.next = node;
			while (prev.value > node.value && prev != first) {
				swap(prev, node);
			}
			if (prev == first && prev.value > node.value) {
				first = node;
				prev.next = node.next;
				node.next = prev;
				prev.prev = node;
				node.prev = null;

			}
		}

	}

	public void swap(Nodee front, Nodee back) {
		Nodee ff = front.prev;
		Nodee bb = back.next;
		ff.next = back;
		bb.prev = front;
		back.prev = ff;
		front.next = bb;
		back.next = front;
		front.prev = back;
	}

}

class Nodee implements Comparable<Nodee> {
	int value;
	Nodee prev;
	Nodee next;

	public Nodee(int value, Nodee next) {
		this.value = value;
		this.next = next;
	}

	@Override
	public int compareTo(Nodee o) {
		return this.value - o.value;
	}
}
