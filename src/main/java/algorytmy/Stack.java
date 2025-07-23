package algorytmy;

public class Stack<T> {

    private final T[] stack;
    private int pointer;

    public Stack(int size) {
        //noinspection unchecked
        this.stack = (T[]) new Object[size];
        pointer = -1;
    }

    public int getSize() {
        return pointer + 1;
    }

    public void push(T element) throws FullStackException {
        if (pointer == stack.length - 1) {
            throw new FullStackException("Stack is full!");
        }
        stack[++pointer] = element;
    }

    public T pop() throws EmptyStackException {
        if (pointer == -1) {
            throw new EmptyStackException("Stack is empty!");
        }
        return stack[pointer--];
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>(3);
        System.out.println("Size: " + stack.getSize());

        try {
            System.out.println(stack.pop());
        } catch (EmptyStackException e) {
            System.out.println(e.getMessage());
        }

        try {
            stack.push(10);
        } catch (FullStackException e) {
            System.out.println(e.getMessage());
        }

        try {
            stack.push(20);
        } catch (FullStackException e) {
            System.out.println(e.getMessage());
        }

        try {
            stack.push(30);
        } catch (FullStackException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println(stack.pop());
        } catch (EmptyStackException e) {
            System.out.println(e.getMessage());
        }

        try {
            stack.push(40);
        } catch (FullStackException e) {
            System.out.println(e.getMessage());
        }

        try {
            stack.push(50);
        } catch (FullStackException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println(stack.pop());
        } catch (EmptyStackException e) {
            System.out.println(e.getMessage());
        }
    }
}

class FullStackException extends Exception {

    public FullStackException(String message) {
        super(message);
    }
}

class EmptyStackException extends Exception {

    public EmptyStackException(String message) {
        super(message);
    }
}
