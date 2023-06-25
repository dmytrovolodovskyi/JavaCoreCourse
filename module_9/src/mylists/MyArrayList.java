package mylists;

import java.util.Arrays;

public class MyArrayList<T> {
    private int ARRAY_SIZE = 16;
    private int counter = 0;
    private Object[] array = new Object[ARRAY_SIZE];
    private int size = 0;

    public void add(T value){
        if (array.length - 1 == size) {
            ARRAY_SIZE *= 1.5;
        }

        Object[] newArray = new Object[ARRAY_SIZE];
        System.arraycopy(array, 0, newArray, 0, counter);
        array = newArray;

        array[counter++] = value;
        size++;
    }

    public void remove(int index){
        for (int i = index; i < counter; i++)
            array[i] = array[i+1];
        array[counter] = null;
        counter--;
    }

    public void clear(){
        Arrays.fill(array, null);
    }

    public int size(){
        return size;
    }

    public T get(int index){
        return (T) array[index];
    }

    @Override
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
