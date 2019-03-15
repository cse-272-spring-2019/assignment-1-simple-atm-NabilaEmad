package atm;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
    public static void display (String title, String message) {
        
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(300);
        window.setMinHeight(200);
        
        Label L = new Label(message);
        L.setStyle("-fx-font: normal bold 20px 'serif' ");
        StackPane layout = new StackPane();
        layout.getChildren().add(L);
        Scene error = new Scene(layout);
        
        window.setScene(error);
        window.showAndWait();
    }
}
