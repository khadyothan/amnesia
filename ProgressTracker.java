import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Scanner;

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

    LinkedHashMap<LocalDate, Note> progressTracker = new LinkedHashMap<>();
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
        displayProgress.setPrefSize(400, 800);

        HBox dateBox = new HBox();
        dateBox.setAlignment(Pos.CENTER);

        HBox topicBox = new HBox();
        topicBox.setAlignment(Pos.CENTER);

        HBox notesBox = new HBox();
        notesBox.setAlignment(Pos.CENTER);

        HBox buttonBox = new HBox();
        buttonBox.setAlignment(Pos.CENTER);

        VBox inputForm = new VBox();
        inputForm.setAlignment(Pos.CENTER);
        inputForm.setSpacing(10);

        VBox output = new VBox();
        output.setSpacing(10);

        dateBox.getChildren().addAll(dateLabel, dp);
        topicBox.getChildren().addAll(topicLabel, topicText);
        notesBox.getChildren().addAll(notesLabel, notesText);
        buttonBox.getChildren().addAll(add);

        inputForm.getChildren().addAll(dateBox, topicBox, notesBox, buttonBox);
        output.getChildren().addAll(separator, displayProgress);

        SplitPane trackerForm = new SplitPane(inputForm, output);

        try(Scanner sc = new Scanner(new FileInputStream("ProgressTracker.txt"))){
            LocalDate d; 
            String t, n;
            while (sc.hasNextLine()) {
                d = LocalDate.parse(sc.nextLine());
                t = sc.nextLine();
                n = sc.nextLine();
                progressTracker.put(d, new Note(d, t, n));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Note n : progressTracker.values()) {
            displayProgress.appendText("Date: " + n.getDate() + "\n");
            displayProgress.appendText("Topic: " + n.getTopic() + "\n");
            displayProgress.appendText("Notes: " + n.getNotes() + "\n\n");
        }

        add.setOnAction(event -> {
            LocalDate date = dp.getValue();
            String topic = topicText.getText();
            String notes = notesText.getText();

            Note note = new Note(date, topic, notes);
            progressTracker.put(date, note);

            displayProgress.clear();
            for (Note n : progressTracker.values()) {
                displayProgress.appendText("Date: " + n.getDate() + "\n");
                displayProgress.appendText("Topic: " + n.getTopic() + "\n");
                displayProgress.appendText("Notes: " + n.getNotes() + "\n\n");
            }
                
            dp.setValue(null);
            topicText.clear();
            notesText.clear();

            try(PrintStream ps = new PrintStream(new FileOutputStream("ProgressTracker.txt"))) {
                for (Note n : progressTracker.values()){
                    ps.println(n.getDate());
                    ps.println(n.getTopic());
                    ps.println(n.getNotes());
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        });

        Scene sc = new Scene(trackerForm, 800, 800);

        stage.setScene(sc);
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }  
}