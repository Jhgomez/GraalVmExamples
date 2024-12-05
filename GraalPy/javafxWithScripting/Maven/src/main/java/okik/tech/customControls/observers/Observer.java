package okik.tech.customControls.observers;

import java.util.function.Consumer;

public interface Observer {
    <T> void onChange(Consumer<T> onChange);
}
