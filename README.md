*******************************************************************************

# Assignment Tracker v2 (Java CLI)

A command-line assignment manager built in Java to help users organize, 
prioritize, and track coursework. Data is saved between sessions using file
persistence.


## Key Features

- Add, view, edit, and remove assignments
- Mark assignments as complete
- Edit due dates and notes
- Priority system using enums (HIGH, MEDIUM, LOW)
- Sort by priority or due date
- Persistent storage using file I/O (auto-save/load)

## Concepts Demonstrated
- Object-Oriented Programming(encapsulation, classes, mutable objects)
- Collections (ArrayList) for dynamic data storage
- Sorting with Collections.sort() for dynamic data storage
- Enums for structured data representation
- File I/O using BufferedReader and BufferedWriter
- Input validation with try-catch (prevents crashes)

## Tech used

- Java (core libraries)
- ArrayList, Collections, Enums
- File I/O (BufferedReader, BufferedWriter)
        
# How to Run
        
1. Open the project folder in VS Code.
2. Compile all files:
   AssignmentTracker.java   Assignment.java
3. Run the program:
   javac AssignmentTracker

## Future Improvements (v3)
- GUI version (JavaFX)
- Use LocalDate for better data handling

*******************************************************************************
