package me.qihao.algorithm.list;

public class DoublyLinkedList<E> {

    /*
     * Node definition with two pointers
     */
    class Node<T> {
        public T e;
        public Node<T> next;   //指向下一结点
        public Node<T> prev;   //指向上一结点

        public Node(T e) {
            this.e = e;
            prev = next = null;
        }
    }

    private int size = 0;
    Node<E> head, tail;

    public DoublyLinkedList() {
        this.head = this.tail = null;
    }

    public void add(E e) {
        Node<E> node = new Node<>(e);
        if (size == 0) {
            head = node;
            tail = node;
        } else {
            node.prev = tail; // 前趋后继引用都要赋值
            tail.next = node;
            tail = node;
        }
        size++;
    }

    public Node<E> query(int index) {
        Node<E> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    public void insert(int index, E e) {
        Node<E> newNode = new Node<>(e);
        if (index == 0) {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        } else {
            Node<E> node = query(index);   //优化后使用这一行
            newNode.next = node.next;
            newNode.prev = node;
            node.next.prev = newNode;
            node.next = newNode;
        }
        size++;
    }

    public int size() {
        return this.size;
    }

    public static void main(String[] args) {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        list.add("a");
        list.add("b");
        System.out.println(list.size());
    }
}
