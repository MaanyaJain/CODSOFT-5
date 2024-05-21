import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class StudentManagementGUI {
    private JFrame frame;
    private StudentManagementSystem managementSystem;

    public StudentManagementGUI() {
        frame = new JFrame("Student Management System");
        managementSystem = new StudentManagementSystem();
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel(new GridLayout(5, 1));
        JButton addButton = new JButton("Add Student");
        JButton removeButton = new JButton("Remove Student");
        JButton searchButton = new JButton("Search Student");
        JButton displayButton = new JButton("Display All Students");
        JButton exitButton = new JButton("Exit");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(frame, "Enter student name:");
                String rollNumberStr = JOptionPane.showInputDialog(frame, "Enter student roll number:");
                int rollNumber = Integer.parseInt(rollNumberStr);
                String grade = JOptionPane.showInputDialog(frame, "Enter student grade:");
                Student student = new Student(name, rollNumber, grade);
                managementSystem.addStudent(student);
                JOptionPane.showMessageDialog(frame, "Student added successfully.");
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rollNumberStr = JOptionPane.showInputDialog(frame, "Enter student roll number to remove:");
                int rollNumber = Integer.parseInt(rollNumberStr);
                Student student = managementSystem.searchStudent(rollNumber);
                if (student != null) {
                    managementSystem.removeStudent(student);
                    JOptionPane.showMessageDialog(frame, "Student removed successfully.");
                } else {
                    JOptionPane.showMessageDialog(frame, "Student not found.");
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rollNumberStr = JOptionPane.showInputDialog(frame, "Enter student roll number to search:");
                int rollNumber = Integer.parseInt(rollNumberStr);
                Student student = managementSystem.searchStudent(rollNumber);
                if (student != null) {
                    JOptionPane.showMessageDialog(frame, "Student found:\nName: " + student.getName() + "\nGrade: " + student.getGrade());
                } else {
                    JOptionPane.showMessageDialog(frame, "Student not found.");
                }
            }
        });

        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Student> students = managementSystem.getAllStudents();
                StringBuilder message = new StringBuilder("All Students:\n");
                for (Student student : students) {
                    message.append("Name: ").append(student.getName()).append(", Roll Number: ").append(student.getRollNumber()).append(", Grade: ").append(student.getGrade()).append("\n");
                }
                JOptionPane.showMessageDialog(frame, message.toString());
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(displayButton);
        buttonPanel.add(exitButton);

        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentManagementGUI::new);
    }
}