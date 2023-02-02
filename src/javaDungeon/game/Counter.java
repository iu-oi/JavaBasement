package javaDungeon.game;

import java.io.Serializable;

public class Counter implements Serializable{

    private volatile int value;

    public Counter() {
        value = 0;
    }

    public Counter(int value) {
        this.value = value;
    }

    public synchronized void set(int value) {
        this.value = value;
    }

    public synchronized int get() {
        return value;
    }

    public synchronized void add(int number) {
        value += number;
    }

    public synchronized void sub(int number) {
        value = value > number ? value - number : 0;
    }

    public synchronized boolean check() {
        return value == 0;
    }

    public synchronized void clear() {
        value = 0;
    }

    public synchronized void inc() {
        value++;
    }

    public synchronized void dec() {
        if (value > 0) {
            value--;
        }
    }
}