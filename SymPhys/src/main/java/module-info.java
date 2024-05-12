module symphys.symphys {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens symphys.symphys to javafx.fxml;
    exports symphys.symphys;
}