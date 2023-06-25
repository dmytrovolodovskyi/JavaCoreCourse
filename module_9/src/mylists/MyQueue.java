package mylists;

import java.util.Arrays;

public class MyQueue<T> {
    private int ARRAY_SIZE = 16;
    private int head = 0;
    private int tail = -1;
    private int size = 0;
    private Object[] elements = new Object[ARRAY_SIZE];;


    public void add(T value) {
        if (tail == elements.length - 1){
            ARRAY_SIZE *= 1.5;
        }
        elements[++tail] = value;
        size++;

    }

    public void clear(){
        elements = new Object[ARRAY_SIZE];
        head = 0;
        tail = -1;
        size = 0;
    }

    public int size(){
        return size;
    }

    public Object peek(){
        if (size == 0){
            throw new IllegalStateException("Queue is empty");
        }
        return elements[head];
    }

    public Object poll() {
        if (size == 0) {
            throw new IllegalStateException("Queue is empty");
        }
        Object value = elements[head];
        elements[head++] = null;
        size--;
        return value;
    }

    @Override
    public String toString(){
        int counter = 0;
        for (Object o : elements) {
            if (o != null) counter++;
        }

        Object[] resultArray = new Object[counter];
        System.arraycopy(elements, 0, resultArray, 0, counter);
        return Arrays.toString(resultArray);
    }
}
