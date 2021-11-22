module spring.tp4_front {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;


    opens spring.tp4_front to javafx.fxml;
    exports spring.tp4_front;
}