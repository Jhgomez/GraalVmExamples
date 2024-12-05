package okik.tech.customControls.observers;

import javafx.scene.control.Label;

public class ListernerLabel extends Label implements Observer {

    public ListernerLabel(String text) {
        super(text);
    }

    @Override
    public <T> void update(T onChange) {
        super.setText(onChange.toString());
    }

    public void setLabelText(String text) {
        super.setText(text);
    }
}
