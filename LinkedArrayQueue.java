public class LinkedArrayQueue<E> {
	private static class InnerArray<E> {
		private E[] entries;
		private int begin;
		private int end;

		public InnerArray() {
			this(8);
		}

		@SuppressWarnings("unchecked")
		public InnerArray(int capacity) {
			this.entries = (E[])(new Object[capacity]);
			begin = 0;
			end = 0;
		}

		public boolean add(E e) {
			if (end >= entries.length) {
				return false;
			}
			entries[end++] = e;
			return true;
		}

		public E first() {
			if (this.size() == 0) return null;
			return entries[begin];
		}

		public E last() {
			if (this.size() == 0) return null;
			return entries[end - 1];
		}

		public E remove() {
			if (begin < 0 || begin >= end) {
				return null;
			}
			E ret = entries[begin];
			entries[begin++] = null;
			return ret;
		}

		public int size() {
			return end-begin;
		}
	}

	private static class Node<E> {
		public InnerArray<E> elem;
		public Node<E> next;

		public Node() {
			this(new InnerArray<>(), null);
		}

		public Node(InnerArray<E> elem) {
			this(elem, null);
		}

		public Node(InnerArray<E> elem, Node<E> next) {
			this.elem = elem;
			this.next = next;
		}
	}

	private Node<E> head;
	private Node<E> tail;
	private int sz = 0;
	private int narr = 0;

	public LinkedArrayQueue() {
		head = new Node<>();
		tail = head;
		sz = 0;
		narr = 1;
	}

	public int size() {
		return sz;
	}

	public int numArrays() {
		return narr;
	}

	public boolean isEmpty() {
		return sz == 0;
	}

	public E first() {
		if (this.head.elem.size() == 0) {
			if (this.numArrays() <= 1) {
				return null;
			}
			head = head.next;
			narr--;
			return first();
		}
		return head.elem.first();
	}

	public E last() {
		if (this.tail.elem.size() == 0) {
			if (this.numArrays() <= 1) {
				return null;
			}
		}
		return tail.elem.last();
	}

	public void enqueue(E e) {
		if (!this.tail.elem.add(e)) {
			narr++;
			Node<E> n = new Node<E>();
			tail.next = n;
			tail = n;
			tail.elem.add(e);
		}
		sz++;
	}

	public E dequeue(){
		E ret = this.head.elem.remove();
		if (ret != null) {
			sz--;
			return ret;
		}

		if (this.numArrays() <= 1) {
			return null;
		}

		head = head.next;
		narr--;
		return this.dequeue();
	}
}