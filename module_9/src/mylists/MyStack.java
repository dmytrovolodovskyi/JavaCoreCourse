package mylists;

import java.util.Arrays;

public class MyStack<T> {
    private int top = -1;
    private int ARRAY_SIZE = 16;
    private Object[] array = new Object[ARRAY_SIZE];

    public void push(T value){
        if (top == array.length - 1) {
            ARRAY_SIZE *= 1.5;
        }
        array[++top] = value;
    }

    public void remove(int index) {
        if (index < 0 || index > top) {
            throw new IndexOutOfBoundsException();
        }

        for (int i = index; i < top; i++) {
            array[i] = array[i + 1];
        }
        top--;
    }

    public void clear() {
        top = -1;
    }

    public Object peek() {
        if (top == -1) {
            throw new IllegalStateException("Stack is empty");
        }
        return array[top];
    }

    public Object pop() {
        if (top == -1) {
            throw new IllegalStateException("Stack is empty");
        }
        Object value = array[top];
        top--;
        return value;
    }

    public String toString(){
        int counter = 0;
        for (Object o : array) {
            if (o != null) counter++;
        }

        Object[] resultArray = new Object[counter];
        System.arraycopy(array, 0, resultArray, 0, counter);
        return Arrays.toString(resultArray);
    }

}
