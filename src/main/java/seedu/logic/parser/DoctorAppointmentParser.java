package seedu.logic.parser;


import seedu.logic.command.AppointmentActions;
import seedu.logic.command.Command;
import seedu.logic.command.doctorappointment.*;
import seedu.model.DoctorAppointment;
import seedu.model.staff.Staff;
import seedu.storage.DoctorAppointmentStorage;
import seedu.ui.DoctorAppointmentUI;
import seedu.ui.UI;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class DoctorAppointmentParser {

    public static Command parse(String input, AppointmentActions details) throws Exception {
        String[] inputArray = input.split("/");
        Command c = null;

        switch (inputArray[0]) {
        case "add":
            c = new DoctorAppointmentAdd(inputArray);
            break;
        case "list":
            c = new DoctorAppointmentList(inputArray);
            break;
        case "delete":
            c = new DoctorAppointmentDelete(inputArray);
            break;
        case "return":
            c = new DoctorAppointmentReturn();
            break;
        case "help":
            c = new DoctorAppointmentHelp();
            break;
        default:
            DoctorAppointmentUI.invalidCommandPrompt();
        }
        return c;
    }

    public static boolean isValidDocID(String doctorID) {
        try {
            if (doctorID.equals("D")) {
                ArrayList<Staff> doctorList;
                doctorList = DoctorAppointmentStorage.loadDoctorFile();
                for (Staff id : doctorList) {
                    if (id.getId().equals(doctorID)) {
                        return true;
                    }
                    System.out.println(id.getId());
                }
                DoctorAppointmentUI.printDoctorNotFound();
            } else {
                DoctorAppointmentUI.invalidDoctorID();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isValidAppointmentID(String appointmentID) {
        return appointmentID.equals("A");
    }

    public static boolean isValidGender(String gender) {
        return gender.equals("M") || gender.equals("F");
    }


}
