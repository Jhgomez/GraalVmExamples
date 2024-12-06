package okik.tech;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.concurrent.atomic.AtomicInteger;


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

        var vBox = new VBox(console, inputArea);
        VBox.setVgrow(console, Priority.ALWAYS);

        var scene = new Scene(vBox, 640, 420);

        inputArea.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                console.appendText("> ");
                console.appendText(inputArea.getText());

                inputArea.clear();

                // use instead of Thread.sleep(100) from the JavaFx application thread
                // new PauseTransition(Duration.millis(1500)).play();
            }
        });

//        Context context = GraalPyResources.contextBuilder().allowHostAccess(HostAccess.ALL).build();
//        context.initialize("python");
//        Value pythonBindings = context.getBindings("python");
//        pythonBindings.putMember("book", Book.class);
//        pythonBindings.putMember("books", BookRepository.books);
//        pythonBindings.putMember("window", stage);
//
//        context.eval("python",
//                // language=python
//                """
//                        import sys, os, time, math
//
//                        def add_book(isbn, name, author, genre, ranking):
//                            book = Book(isbn, name, author, genre, ranking)
//                            return book
//                        """);

        stage.setTitle("GraalPy Console");
        stage.setScene(scene);
        stage.show();

    }

    private void animate(Stage stage) {
        double x = stage.getX();
        double y = stage.getY();

        AtomicInteger count = new AtomicInteger();
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(40), _ -> {
                    int c = count.getAndIncrement();
                    if (c == 49) {
                        stage.setX(x);
                        stage.setY(y);
                    } else {
                        var vX = x + 50 * Math.cos(c / 2.0);
                        var vY = y + 50 * Math.sin(c / 2.0);

                        stage.setX(vX);
                        stage.setY(vY);
                    }
                })
        );

        timeline.setCycleCount(50);
        timeline.play();
    }

    public static void main(String[] args) {
        launch();
    }

}