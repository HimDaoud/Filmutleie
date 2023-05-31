module com.example.filmutleie {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.filmutleie to javafx.fxml;
    exports com.example.filmutleie;
}