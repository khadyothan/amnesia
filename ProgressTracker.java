import java.time.LocalDate;
import java.util.*;

class Note {
    private LocalDate date;
    private String topic;
    private String notes;

    public Note(LocalDate date, String topic, String notes) {
        this.date = date;
        this.topic = topic;
        this.notes = notes;
    }

    public LocalDate getDate(){
        return date;
    }

    public String getTopic(){
        return topic;
    }

    public String getNotes(){
        return notes;
    }
}


public class ProgressTracker {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        List<Note> notesList = new ArrayList<>();

        while(true){
            System.out.println("Enter topic (type 'exit' if you want to finish adding notes): ");
            String topic = sc.nextLine();
            if ("exit".equals(topic)) {
                break;
            }

            System.out.println("Enter notes if any: ");
            String notes = sc.nextLine();
            
            notesList.add(new Note(LocalDate.now(), topic, notes));

        }

        sc.close();

        System.out.println("\nAll Notes:\n");

        for (Note note : notesList) {
            System.out.println("Date: " + note.getDate() + "\nTopic: " + note.getTopic() + "\nNotes: " + note.getNotes() + "\n\n");
        }
    }
}