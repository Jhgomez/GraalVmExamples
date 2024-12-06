package okik.tech;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import okik.tech.customControls.observables.ObservableStringBuilder;
import okik.tech.customControls.observers.ListernerTextArea;

public class App extends Application {

    @Override
    public void start(Stage stage) {

        var consoleLog = """
                This is a demo console using JavaFx and Graalpy
                
                Things to try:
                - `books`
                - `dir(books.getLast())`
                - `add_book("29874", "Design Patterns", "Eric Freeman & Elizabeth Freeman", "educational", 1)`
                - `sys.exit(0)` or `os.listdir()`
                - `stage`
                - `animate()` or `move()`
                
                """;

        var console = new TextArea(consoleLog);
        console.setEditable(false);

        var inputArea = new TextArea();
        inputArea.setPrefRowCount(2);

        inputArea.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                console.appendText("> ");
                console.appendText(inputArea.getText());

                inputArea.clear();
            }
        });

        var vBox = new VBox(console, inputArea);
        VBox.setVgrow(console, Priority.ALWAYS);

        var scene = new Scene(vBox, 640, 420);

        stage.setTitle("GraalPy Console");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}