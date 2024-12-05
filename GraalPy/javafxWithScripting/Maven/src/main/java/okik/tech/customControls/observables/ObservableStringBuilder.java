package okik.tech.customControls.observables;

import okik.tech.customControls.observers.Observer;

import java.util.ArrayList;
import java.util.List;

public class ObservableStringBuilder implements Observable {
    private final List<Observer> observers = new ArrayList<Observer>();
    private final StringBuilder string;

    public ObservableStringBuilder(String initVal) {
        string = new StringBuilder(initVal);
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
        observer.onChange(string);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        var string = this.string.toString();
        for (Observer observer : observers) {
            observer.onChange(string);
        }
    }
}
