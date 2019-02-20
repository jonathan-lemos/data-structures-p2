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

	private SinglyLinkedList<InnerArray<E>> list;
	private int sz = 0;

	public LinkedArrayQueue() {
		this.list = new SinglyLinkedList<>();
		this.list.addFront(new InnerArray<>());
		this.sz = 0;
	}

	public int size() {
		return sz;
	}

	public int numArrays() {
		if (list.size() == 1 && list.back().size() == 0) {
			return 0;
		}
		return list.size();
	}

	public boolean isEmpty() {
		return sz == 0;
	}

	public E first() {
		if (list.front().size() == 0) {
			if (this.numArrays() <= 1) {
				return null;
			}
			list.rmFront();
			return first();
		}
		return list.front().first();
	}

	public E last() {
		if (list.back().size() == 0) {
			if (this.numArrays() <= 1) {
				return null;
			}
		}
		return list.back().last();
	}

	public void enqueue(E e) {
		if (!list.back().add(e)) {
			list.addBack(new InnerArray<>());
			enqueue(e);
		}
		else {
			sz++;
		}
	}

	public E dequeue(){
		E ret = list.front().remove();
		if (ret != null) {
			sz--;
			return ret;
		}

		if (this.numArrays() <= 1) {
			return null;
		}

		list.rmFront();
		return this.dequeue();
	}
}