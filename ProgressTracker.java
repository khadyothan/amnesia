import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.util.HashMap;

class Note {
    private LocalDate date;
    private String topic;
    private String notes;

    public Note(LocalDate date, String topic, String notes) {
        this.date = date;
        this.topic = topic;
        this.notes = notes;
    }

    public LocalDate getDate() {return date;}
    public String getTopic() {return topic;}
    public String getNotes() {return notes;}
}


public class ProgressTracker extends Application{

    HashMap<LocalDate, Note> progressTracker = new HashMap<>();
    @Override
    public void start(Stage stage) throws Exception {

        Label dateLabel = new Label("Date");
        Label topicLabel = new Label("Topic");
        Label notesLabel = new Label("Notes");

        DatePicker dp = new DatePicker();

        TextField topicText = new TextField();
        topicText.setPrefColumnCount(25);

        TextArea notesText = new TextArea();
        notesText.setPrefSize(300, 50);

        Button add = new Button("Add");

        Separator separator = new Separator(Orientation.HORIZONTAL);

        TextArea displayProgress = new TextArea();
        displayProgress.setEditable(false);
        displayProgress.setPrefSize(400, 300);

        HBox dateBox = new HBox();
        dateBox.setAlignment(Pos.CENTER);

        HBox topicBox = new HBox();
        topicBox.setAlignment(Pos.CENTER);

        HBox notesBox = new HBox();
        notesBox.setAlignment(Pos.CENTER);

        HBox buttonBox = new HBox();
        buttonBox.setAlignment(Pos.CENTER);

        VBox trackerForm = new VBox();
        trackerForm.setSpacing(10);

        VBox output = new VBox();
        output.setSpacing(10);

        dateBox.getChildren().addAll(dateLabel, dp);
        topicBox.getChildren().addAll(topicLabel, topicText);
        notesBox.getChildren().addAll(notesLabel, notesText);
        buttonBox.getChildren().addAll(add);

        output.getChildren().addAll(separator, displayProgress);
        trackerForm.getChildren().addAll(dateBox, topicBox, notesBox, buttonBox, output);

        add.setOnAction(event -> {
            LocalDate date = dp.getValue();
            String topic = topicText.getText();
            String notes = notesText.getText();

            Note note = new Note(date, topic, notes);
            progressTracker.put(date, note);

            displayProgress.appendText("Date: " + date + "\n");
            displayProgress.appendText("Topic: " + topic + "\n");
            displayProgress.appendText("Notes: " + notes + "\n\n");

            dp.setValue(null);
            topicText.clear();
            notesText.clear();
        });

        Scene sc = new Scene(trackerForm, 600, 600);

        stage.setScene(sc);
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }  
}