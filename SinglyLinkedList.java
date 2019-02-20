public class SinglyLinkedList<E> {
	private static class Node<E> {
		public E elem;
		public Node<E> next;

		public Node(E elem) {
			this(elem, null);
		}

		public Node(E elem, Node<E> next) {
			this.elem = elem;
			this.next = next;
		}
	}

	private Node<E> head;
	private Node<E> tail;
	private int size;

	public SinglyLinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	public int size() {
		return size;
	}

	public E front() {
		return head == null ? null : head.elem;
	}

	public E back() {
		return tail == null ? null : tail.elem;
	}

	public void addFront(E elem) {
		Node<E> tmp = new Node<>(elem);
		tmp.next = head;
		head = tmp;
		if (tail == null) {
			tail = head;
		}
		size++;
	}

	public void addBack(E elem) {
		Node<E> tmp = new Node<>(elem);
		if (tail != null) {
			tail.next = tmp;
			tail = tmp;
		}
		else {
			tail = tmp;
			if (head == null) {
				head = tmp;
			}
		}
		size++;
	}

	public E rmFront() {
		if (head == null) {
			return null;
		}
		E ret = head.elem;
		head = head.next;
		if (head == null) {
			tail = null;
		}
		size--;
		return ret;
	}
}
