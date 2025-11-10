package com.example.lab4;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.List;
import java.util.ArrayList;

public class StudentController {

    @FXML private TextField fullNameField, addressField, cityField, provinceField, postalCodeField, phoneField, emailField;
    @FXML private RadioButton csRadio, businessRadio;
    @FXML private ComboBox<String> courseComboBox;
    @FXML private ListView<String> selectedCoursesList;
    @FXML private CheckBox sportsCheck, musicCheck, volunteeringCheck;
    @FXML private TextArea outputArea;

    @FXML
    public void initialize() {
        // Group radio buttons
        ToggleGroup majorGroup = new ToggleGroup();
        csRadio.setToggleGroup(majorGroup);
        businessRadio.setToggleGroup(majorGroup);

        // Load courses when major selected
        csRadio.setOnAction(e -> loadCourses("CS"));
        businessRadio.setOnAction(e -> loadCourses("BUS"));
    }

    private void loadCourses(String major) {
        courseComboBox.getItems().clear();

        if (major.equals("CS")) {
            courseComboBox.getItems().addAll(
                    "Java Programming", "Data Structures", "Operating Systems", "Algorithms");
        } else {
            courseComboBox.getItems().addAll(
                    "Marketing", "Accounting", "Business Law", "Economics");
        }
    }


    @FXML
    public void addCourse() {
        String course = courseComboBox.getSelectionModel().getSelectedItem();

        if (course != null && !selectedCoursesList.getItems().contains(course)) {
            selectedCoursesList.getItems().add(course);
        }
    }
    @FXML
    public void showStudentInfo() {
        String name = fullNameField.getText();
        String address = addressField.getText();
        String city = cityField.getText();
        String province = provinceField.getText();
        String postal = postalCodeField.getText();
        String phone = phoneField.getText();
        String email = emailField.getText();

        String major = csRadio.isSelected() ? "Computer Science" :
                businessRadio.isSelected() ? "Business" : "Not Selected";

        String activities = "";
        if (sportsCheck.isSelected()) activities += "Sports ";
        if (musicCheck.isSelected()) activities += "Music Club ";
        if (volunteeringCheck.isSelected()) activities += "Volunteering ";

        outputArea.setText(
                "Student Information:\n" +
                        "---------------------\n" +
                        "Full Name: " + name + "\n" +
                        "Address: " + address + ", " + city + ", " + province + ", " + postal + "\n" +
                        "Phone: " + phone + "\n" +
                        "Email: " + email + "\n" +
                        "Major: " + major + "\n" +
                        "Courses: " + selectedCoursesList.getItems() + "\n" +
                        "Activities: " + activities + "\n"
        );

    }

    }
