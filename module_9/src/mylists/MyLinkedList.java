package mylists;


public class MyLinkedList<T> {

    private int size = 0;
    private Node first;
    private Node last;

    private class Node {
        private Object value;
        private Node previous;
        private Node next;

        public Node(T value) {
            this.value = value;
            this.previous = null;
            this.next = null;
        }
    }
    public void add(T value){
        Node node = new Node(value);

        if (first == null) {
            first = node;
            last = node;
        } else {
            last.next = node;
            node.previous = last;
            last = node;
        }
        size++;
    }

    public void remove(int index){
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }

        Node currentNode = first;

        if (index == 0) {
            first = currentNode.next;
            if (first != null) {
                first.previous = null;
            } else {
                last = null;
            }
        } else if (index == size - 1) {
            currentNode = last;
            last = currentNode.previous;
            last.next = null;
        } else {
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.next;
            }
            currentNode.previous.next = currentNode.next;
            currentNode.next.previous = currentNode.previous;
        }

        size--;
    }

    public void clear(){
        first = null;
        last = null;
        size = 0;
    }

    public int size(){
        return size;
    }

    public T get(int index){
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }

        Node currentNode = first;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }

        return (T) currentNode.value;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        Node currentNode = first;
        while (currentNode != null) {
            builder.append(currentNode.value);
            if (currentNode.next != null) {
                builder.append(", ");
            }
            currentNode = currentNode.next;
        }
        builder.append("]");
        return builder.toString();
    }
}

