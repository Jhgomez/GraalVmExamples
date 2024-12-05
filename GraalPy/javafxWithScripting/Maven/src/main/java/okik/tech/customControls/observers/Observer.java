package okik.tech.customControls.observers;

public interface Observer {
    <T> void update(T onChange);
}
