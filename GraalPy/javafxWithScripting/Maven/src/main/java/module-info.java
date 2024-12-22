module org.openjfx {
    requires javafx.controls;
    requires java.desktop;
    requires org.graalvm.python.embedding;
    requires org.graalvm.polyglot;
    requires java.sql;
    exports okik.tech;
}