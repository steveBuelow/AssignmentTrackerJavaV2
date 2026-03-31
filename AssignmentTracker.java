package assignmenttracker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class AssignmentTracker {

    public static void main(String[] args) {

        String file = "output.txt";
        ArrayList<Assignment> s = Assignment.loadAssignments(file);

        try (Scanner scan = new Scanner(System.in)) {
            boolean run = true;

            System.out.println("\nWelcome to the Assingment Tracker!\n");

            while (run) {
                System.out.println("1. Add assignment");
                System.out.println("2. View assignments");
                System.out.println("3. Mark complete");
                System.out.println("4. Remove assignment");
                System.out.println("5. Edit due date");
                System.out.println("6. Edit notes");
                System.out.println("7. Sort settings");
                System.out.println("'e' to exit\n");
                String choice = scan.nextLine();

                switch (choice) {
                    case "1" -> {
                        System.out.println("\nEnter assignment name: ");
                        String name = scan.nextLine();
                        System.out.println("Enter course: ");
                        String course = scan.nextLine();
                        System.out.println("Enter due date: ");
                        String dueDate = scan.nextLine();
                        System.out.println("Enter priority (1-10)");
                        int p;
                        try {
                            p = Integer.parseInt(scan.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid priority, defaulting to LOW");
                            p = 1;
                        }

                        Assignment.Priority priority;
                        if (p >= 6) {
                            priority = Assignment.Priority.HIGH;
                        } else if (p >= 4) {
                            priority = Assignment.Priority.MEDIUM;
                        } else {
                            priority = Assignment.Priority.LOW;
                        }

                        System.out.println("Enter notes (optional)");
                        String notes = scan.nextLine();

                        Assignment a = new Assignment(name, course, dueDate, priority, notes);
                        s.add(a);
                        System.out.println("Assignment added!\n");
                        Assignment.saveAssignments(file, s);
                    }
                    case "2" -> {
                        if (!s.isEmpty()) {
                            System.out.println("Assignments:\n");
                            for (int i = 0; i < s.size(); i++) {
                                Assignment a = s.get(i);

                                String priorityLabel = switch (a.getPriority()) {
                                    case HIGH ->
                                        "High priority!";
                                    case MEDIUM ->
                                        "Medium priority";
                                    case LOW ->
                                        "Low priority";
                                };

                                System.out.println((i + 1) + ". " + a.getName() + " - " + a.getCourse() + " | "
                                        + a.getDueDate() + " | " + priorityLabel
                                        + " | " + (a.isCompleted() ? "Completed" : "Incomplete")
                                        + (a.getNotes().isEmpty() ? "" : " | Notes: " + a.getNotes()));
                                System.out.println("");
                            }
                        } else {
                            System.out.println("You have zero assignments!\n");
                        }
                    }
                    case "3" -> {
                        System.out.println("Enter assignment number to mark complete: ");
                        int num;
                        try {
                            num = Integer.parseInt(scan.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid number");
                            break;
                        }
                        if (num > 0 && num <= s.size()) {
                            s.get(num - 1).setCompleted(true);
                            System.out.println("Marked completed!\n");
                            Assignment.saveAssignments(file, s);
                        } else {
                            System.out.println("Invalid number.");
                        }
                    }
                    case "4" -> {
                        System.out.println("Enter assignment to remove: ");
                        int num;
                        try {
                            num = Integer.parseInt(scan.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid number");
                            break;
                        }
                        if (num > 0 && num <= s.size()) {
                            Assignment removed = s.remove(num - 1);
                            System.out.println("Removed assignment: " + removed.getName() + "\n");
                            Assignment.saveAssignments(file, s);
                        } else {
                            System.out.println("Invalid number. \n");
                        }
                    }
                    case "5" -> {
                        System.out.println("Enter assignment number to edit due date: ");
                        int num;
                        try {
                            num = Integer.parseInt(scan.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid number");
                            break;
                        }
                        if (num > 0 && num <= s.size()) {
                            System.out.println("Enter new due date: ");
                            String newDate = scan.nextLine();
                            s.get(num - 1).setDueDate(newDate);
                            System.out.println("Due date updated!\n");
                            Assignment.saveAssignments(file, s);
                        } else {
                            System.out.println("Invalid number.\n");
                        }

                    }
                    case "6" -> {
                        System.out.println("Enter assignment number to edit notes: ");
                        int num;
                        try {
                            num = Integer.parseInt(scan.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid number");
                            break;
                        }
                        if (num > 0 && num <= s.size()) {
                            System.out.println("Enter new notes: ");
                            String newNotes = scan.nextLine();
                            s.get(num - 1).setNotes(newNotes);
                            Assignment.saveAssignments(file, s);
                            System.out.println("Notes updated!\n");
                        } else {
                            System.out.println("Invalid number.\n");
                        }
                    }
                    case "7" -> {
                        System.out.println("Sort by:\n1. Priority (high-low)\n2. Due date (earliest first)");
                        String sortChoice = scan.nextLine();

                        switch (sortChoice) {
                            case "1" ->
                                Collections.sort(s, (a1, a2) -> Integer.compare(a2.getPriority().ordinal(), a1.getPriority().ordinal()));
                            case "2" ->
                                Collections.sort(s, (a1, a2) -> a1.getDueDate().compareTo(a2.getDueDate()));
                            default ->
                                System.out.println("Invalid choice, no sorting done. ");
                        }

                        System.out.println("Assignments sorted!\n");
                        Assignment.saveAssignments(file, s);
                    }
                    case "e" ->
                        run = false;
                    default ->
                        System.out.println("Invalid choice. ");
                }
            }
        }
        System.out.println("Goodbye!");

    }

}
