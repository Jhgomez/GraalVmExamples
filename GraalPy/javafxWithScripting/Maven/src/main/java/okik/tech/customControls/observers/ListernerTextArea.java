package okik.tech.customControls.observers;

import javafx.scene.control.TextArea;

public class ListernerTextArea extends TextArea implements Observer {

    public ListernerTextArea(String text) {
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
