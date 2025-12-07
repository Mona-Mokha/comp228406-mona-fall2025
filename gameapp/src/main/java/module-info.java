module com.mona.gameapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;       // for PostgreSQL JDBC
    requires org.postgresql.jdbc; // optional if you use org.postgresql

    opens com.mona.gameapp to javafx.fxml;
    opens com.mona.gameapp.controller to javafx.fxml;

    exports com.mona.gameapp;
    exports com.mona.gameapp.controller;
}
