import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class HelloFX extends Application implements EventHandler<ActionEvent>{

    int count = 0;
    Button b = new Button("Counter");

    @Override
    public void start(Stage stage) {
        b.setOnAction(this);
        b.setPrefSize(100, 30);
        FlowPane fp = new FlowPane(b);
        Scene sc = new Scene(fp, 300, 300);
        stage.setScene(sc);
        stage.show();
    }

    @Override
    public void handle(ActionEvent ae) {
        count++;
        b.setText(""+count);
    }
    public static void main(String[] args) {
        launch();
    }
}