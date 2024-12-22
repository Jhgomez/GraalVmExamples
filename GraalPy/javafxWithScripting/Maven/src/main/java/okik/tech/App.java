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
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.HostAccess;
import org.graalvm.polyglot.Value;
import org.graalvm.python.embedding.utils.GraalPyResources;

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
                - `window`
                - `animate()` or `move()`
                
                """;

        var console = new TextArea(consoleLog);
        console.setEditable(false);

        var inputArea = new TextArea();
        inputArea.setPrefRowCount(2);

        var vBox = new VBox(console, inputArea);
        VBox.setVgrow(console, Priority.ALWAYS);

        var scene = new Scene(vBox, 640, 420);

        stage.setTitle("GraalPy Console");
        stage.setScene(scene);
        stage.show();

        Context context = GraalPyResources
                .contextBuilder()
                .allowHostAccess(HostAccess.ALL)
                .allowAllAccess(true)           // https://github.com/graalvm/graal-languages-demos/blob/main/graalpy/graalpy-jython-guide/src/main/java/org/example/GraalPyInputCallback.java
                .option("python.EmulateJython", "true")
                .build();

        context.initialize("python");
        Value pythonBindings = context.getBindings("python");
        pythonBindings.putMember("Book", Book.class);
        pythonBindings.putMember("books", BookRepository.books);
        pythonBindings.putMember("stage", stage);

        // imports in jython https://github.com/oracle/graalpython/blob/master/docs/user/Python-on-JVM.md
        // imports alternative https://github.com/oracle/graalpython/blob/master/docs/user/Interoperability.md
        // debuggin https://github.com/oracle/graalpython/blob/master/docs/user/Tooling.md
        context.eval("python",
                // language=python
                """
                        import sys, os, time, math
                        import java.util.concurrent.atomic.AtomicInteger as AtomicInteger
                        import javafx.animation.Timeline as Timeline
                        import javafx.animation.KeyFrame as KeyFrame
                        import javafx.util.Duration as Duration
                        
                        x = 0
                        y = 0

                        def add_book(isbn, name, author, genre, ranking):
                            book = Book(isbn, name, author, genre, ranking)
                            books.add(book)
                            return book
                        
                        def animate():
                            atomic = AtomicInteger()
                            global x
                            x = stage.getX()
                            global y
                            y = stage.getY()
                            timeline = Timeline(KeyFrame(Duration.millis(40), lambda a: animateCallback(atomic)))
                            timeline.setCycleCount(50)
                            timeline.play()
                        
                        def animateCallback(c):
                            c = c.getAndIncrement()
                            if (c == 49):
                                stage.setX(x)
                                stage.setY(y)
                            else:
                                vX = x + 50 * math.cos(c / 2.0)
                                vY = y + 50 * math.sin(c / 2.0)
    
                                stage.setX(vX)
                                stage.setY(vY)
                        
                        def move():
                            atomic = AtomicInteger()
                            global x
                            x = stage.getX()
                            global y
                            y = stage.getY()
                            timeline = Timeline(KeyFrame(Duration.millis(40), lambda a: moveCallback(atomic)))
                            timeline.setCycleCount(50)
                            timeline.play()
                        
                        def moveCallback(c):
                            c = c.getAndIncrement();
                            vX = x +  c * 10;
                            vY = y + 50 * math.sin(c / 2.0);
    
                            stage.setX(vX);
                            stage.setY(vY);
                        """);

        inputArea.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                console.appendText("> ");
                console.appendText(inputArea.getText());

                try {
                    Value result = context.eval("python", inputArea.getText());

                    if (result.isString()) {
                        console.appendText(result.asString());
                    } else {
                        console.appendText(result.toString());
                    }

                    console.appendText("\n");

                } catch (Exception e) {

                    console.appendText(String.format("Syntax error: %1$s %2$s %3$s %4$s\n", e.fillInStackTrace(), e.getStackTrace(), e.getCause(), e.getSuppressed()));
                }

                inputArea.clear();

                // use instead of Thread.sleep(100) from the JavaFx application thread
                // new PauseTransition(Duration.millis(1500)).play();
            }
        });



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

    private void move(Stage stage) {
        double x = stage.getX();
        double y = stage.getY();

        AtomicInteger count = new AtomicInteger();
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(40), _ -> {
                    int c = count.getAndIncrement();
                    var vX = x +  c * 10;
                    var vY = y + 50 * Math.sin(c / 2.0);

                    stage.setX(vX);
                    stage.setY(vY);

                })
        );

        timeline.setCycleCount(50);
        timeline.play();
    }

    public static void main(String[] args) {
        launch();
    }

}