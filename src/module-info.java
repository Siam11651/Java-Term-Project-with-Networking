module JavaFxApplication
{
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    opens termproject to javafx.graphics, javafx.fxml;
}