package okik.tech.customControls.observables;

import okik.tech.customControls.observers.Observer;

public interface Observable {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
