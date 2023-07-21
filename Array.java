@SuppressWarnings("unchecked")
public class Array<T> implements Iterable<T> {
    private T[] arr;
    private int len = 0;
    private int capacity = 0; // actual array size

    public Array() { this(16); }
    public Array(int capacity) {
        if (capacity < 0) throw new IllegalArgumentException("Illegal capacity" + capacity);
        this.capacity = capacity;
        arr = (T[]) new Object[capacity];
    }

    public int size() { return len; }
    public boolean isEmpty() { return len == 0; }

    public T get(int index) { return arr[index];  }
    public void set(int index, T elem) { arr[index] = elem; }

    public void clear() {
        for (int i = 0; i < capacity; ++i) {
            arr[i] = null;
        len = 0;
        }
    }
    public void add(T elem) {
        if (len + 1 >= capacity) {
            if (capacity == 0) capacity = 1;
            else capacity *= 2;
            T[] new_arr = (T[]) new Object[capacity];
            for (int i = 0; i < len; ++i) {
                new_arr[i] = arr[i];
            }
            arr = new_arr;
        }
        arr[len++] = elem;
    }

    public T removeAt(int rm_index) {
        if (rm_index >= len || rm_index < 0) throw new IndexOutOfBoundsException();
        T data = arr[rm_index];
        T[] new_arr = (T[]) new Object[len - 1];
        for (int i = 0, j = 0; i < len; ++i, ++j) {
            if (i == rm_index) --j;
            else new_arr[j] = arr[i];
        }
        arr = new_arr;
        capacity = --len;
        return data;
    }

    public boolean remove(Object obj) {
        for (int i = 0; i < len; ++i) {
            if (arr[i].equals(obj)) {
                removeAt(i); return true;
            }
        }
        return false;
    }

    public int indexOf(Object obj) {
        for (int i = 0; i < len; ++i) {
            if (arr[i].equals(obj)) return i;
        }
        return -1;
    }

    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override
    public java.util.Iterator<T> iterator() {
        return new java.util.Iterator<T>() {
            int index = 0;
            public boolean hasNext() { return index < len; }
            public T next() { return arr[index++]; }
        };
    }

    @Override
    public String toString() {
        if (len == 0) return "[]";
        else {
            StringBuilder sb = new StringBuilder(len).append("[");
            for (int i = 0; i < len - 1; ++i) {
                sb.append(arr[i] + ", ");
            }
            return sb.append(arr[len - 1] + "]").toString();
        }
    }

    public static void main(String[] args) {
        Array<Integer> ar = new Array<Integer>(50);
        ar.add(3);
        ar.add(4);
        ar.add(5);
        ar.add(7);
        System.out.println(ar);

        DoublyLinkedList<Integer> dl = new DoublyLinkedList<Integer> ();
        dl.add(10);
        dl.add(100);
        dl.add(200);
        System.out.println(dl);

        Stack<Integer> st = new Stack<Integer>(3);
        st.push(100);
        st.push(300);
        st.push(200);
        System.out.println(st);
        st.pop();
        System.out.println(st);

        Queue<Integer> q = new Queue<Integer> (1);
        q.offer(25);
        q.offer(35);
        q.offer(45);
        System.out.println(q);
        int elem = q.poll();
        System.out.println("poll queue: " + elem);
        System.out.println(q);

    }
}

