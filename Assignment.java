package assignmenttracker;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Assignment {

    // enum
    public enum Priority {
        HIGH, MEDIUM, LOW
    }

    // Fields
    private String name;
    private String course;
    String dueDate;
    private Priority priority;
    private String notes;
    private boolean completed;

    // Constructor
    public Assignment(String name, String course, String dueDate, Priority priority, String notes) {
        this.name = name;
        this.course = course;
        this.dueDate = dueDate;
        this.priority = priority;
        this.completed = false;
        this.notes = notes;
    }

    // Static Methods
    public static ArrayList<Assignment> loadAssignments(String filename) {
        ArrayList<Assignment> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", -1);
                if (parts.length >= 6) {
                    String name = parts[0];
                    String course = parts[1];
                    String dueDate = parts[2];
                    boolean completed = Boolean.parseBoolean(parts[4]);
                    String notes = parts[5];
                    int p = Integer.parseInt(parts[3]);
                    Assignment.Priority priority;
                    if (p >= 6) {
                        priority = Assignment.Priority.HIGH;
                    } else if (p >= 4) {
                        priority = Assignment.Priority.MEDIUM;
                    } else {
                        priority = Assignment.Priority.LOW;
                    }

                    Assignment a = new Assignment(name, course, dueDate, priority, notes);
                    a.completed = completed;
                    list.add(a);
                }
            }
        } catch (IOException e) {
            System.out.println("No existing assignments found. Kick back! ");
        }
        return list;
    }

    public static void saveAssignments(String filename, ArrayList<Assignment> list) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Assignment a : list) {
                int p = 0;
                switch (a.priority) {
                    case HIGH ->
                        p = 7;
                    case MEDIUM ->
                        p = 5;
                    case LOW ->
                        p = 2;
                }
                writer.write(a.name + "," + a.course + "," + a.dueDate + "," + p + "," + a.completed + "," + a.notes);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Getters
    public String getName() {
        return this.name;
    }

    public String getCourse() {
        return this.course;
    }

    public String getDueDate() {
        return this.dueDate;
    }

    public Priority getPriority() {
        return this.priority;
    }

    public String getNotes() {
        return this.notes;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
