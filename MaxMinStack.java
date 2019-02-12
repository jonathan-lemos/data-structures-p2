public class MaxMinStack<E extends Comparable<E>> {
	private static class Stk<E extends Comparable<E>> {
		private E[] elems;
		int top = 0;
		public Stk() { this(10); }

		@SuppressWarnings("unchecked")
		public Stk(int ary_size) {
			// OOP at its finest
			elems = (E[])(new Comparable[ary_size]);
		}

		public E top() {
			if (top <= 0) return null;
			return elems[top - 1];
		}

		public int size() {
			return top;
		}

		public boolean isEmpty() {
			return top == 0;
		}

		public int capacity() {
			return elems.length;
		}

		public void push(E e) throws IllegalStateException {
			if (top >= elems.length) throw new IllegalStateException("stack is full");
			elems[top++] = e;
		}

		public E pop() {
			if (top <= 0) return null;
			return elems[--top];
		}
	}

	private Stk<E> elems;
	private Stk<E> min;
	private Stk<E> max;

	public MaxMinStack() {
		this(10);
	}

	public MaxMinStack(int ary_size) {
		elems = new Stk<>(ary_size);
		min = new Stk<>(ary_size);
		max = new Stk<>(ary_size);
	}

	public E top() {
		return elems.top();
	}

	public int size() {
		return elems.size();
	}

	public boolean isEmpty() {
		return elems.isEmpty();
	}

	public int capacity() {
		return elems.capacity();
	}

	public E maximum() {
		return max.top();
	}

	public E minimum() {
		return min.top();
	}

	public void push(E e) throws IllegalStateException {
		elems.push(e);
		if (max.isEmpty() || max.top().compareTo(e) < 0) max.push(e);
		if (min.isEmpty() || min.top().compareTo(e) > 0) min.push(e);
	}

	public E pop() {
		E ret = elems.pop();
		if (!max.isEmpty() && ret == max.top()) max.pop();
		if (!min.isEmpty() && ret == min.top()) min.pop();
		return ret;
	}
}