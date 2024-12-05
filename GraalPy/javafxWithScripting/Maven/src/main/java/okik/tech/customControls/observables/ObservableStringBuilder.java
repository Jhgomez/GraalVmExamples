package okik.tech.customControls.observables;

import okik.tech.customControls.observers.Observer;

import javax.swing.plaf.SplitPaneUI;
import java.util.ArrayList;
import java.util.List;

public class ObservableStringBuilder implements Observable {
    private final List<Observer> observers = new ArrayList<Observer>();
    private final StringBuilder string;

    public ObservableStringBuilder(String initVal) {
        string = new StringBuilder(initVal);
    }

    @Override
    public String toString() {
        return string.toString();
    }

    public void append(Object obj) {
        this.string.append(obj);;
        notifyObservers();
    }

    public void append(String str) {
        string.append(str);
        notifyObservers();
    }

    public void append(StringBuffer sb) {
        string.append(sb);
        notifyObservers();
    }

    public void append(CharSequence s) {
        string.append(s);
        notifyObservers();
    }

    public void append(CharSequence s, int start, int end) {
        string.append(s, start, end);
        notifyObservers();
    }

    public void append(char[] str) {
        string.append(str);
        notifyObservers();
    }

    public void append(char[] str, int offset, int len) {
        string.append(str, offset, len);
        notifyObservers();
    }

    public void append(boolean b) {
        string.append(b);
        notifyObservers();
    }

    public void append(char c) {
        string.append(c);
        notifyObservers();
    }

    public void append(int i) {
        string.append(i);
        notifyObservers();
    }

    public void append(long lng) {
        string.append(lng);
        notifyObservers();
    }

    public void append(float f) {
        string.append(f);
        notifyObservers();
    }

    public void append(double d) {
        string.append(d);
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
        observer.update(string);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        var string = this.string.toString();
        for (Observer observer : observers) {
            observer.update(string);
        }
    }
}
