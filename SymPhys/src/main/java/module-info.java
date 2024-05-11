module symphys.symphys {
    requires javafx.controls;
    requires javafx.fxml;


    opens symphys.symphys to javafx.fxml;
    exports symphys.symphys;
}