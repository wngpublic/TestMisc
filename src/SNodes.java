public class SNodes {
	public static class Pair<U,V> {
		public U u;
		public V v;
		public Pair(U u, V v) {
			this.u = u;
			this.v = v;
		}
	}
	public static class LinkedNode<E> {
		public LinkedNode<E> prev = null;
		public LinkedNode<E> next = null;
		public E e;
		public LinkedNode(E e) {
			this.e = e;
		}
		public LinkedNode<E> prev(LinkedNode<E> prev) {
			this.prev = prev;
			return this;
		}
		public LinkedNode<E> next(LinkedNode<E> next) {
			this.next = next;
			return this;
		}
	}
	public static class Node<E> {
		public Node<E> p = null;
		public Node<E> l = null;
		public Node<E> r = null;
		public E e = null;
		public Node(E e) {
			this.e = e;
		}
		public Node<E> l(Node<E> l) {
			this.l = l;
			return this;
		}
		public Node<E> r(Node<E> r) {
			this.r = r;
			return this;
		}
		public Node<E> p(Node<E> p) {
			this.p = p;
			return this;
		}
	}
}
