package prescription_jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Appointment.Appointment;
import Appointment.AppointmentDao;
import Doctor_Jdbc.DoctorDAO;
import jdpc_hospital.PatientDAO;

public class PrescriptionService {

    private PrescriptionDAO dao = new PrescriptionDAO();

    private PatientDAO patientDAO = new PatientDAO();

    private DoctorDAO doctorDAO = new DoctorDAO();

    private AppointmentDao appointmentDao = new AppointmentDao();

    // =========================================================
    // 1. ADD PRESCRIPTION
    // =========================================================

    public void addPrescription(Prescription prescription)
            throws SQLException {

        // Basic validation
        if (prescription.getPrescriptionId() <= 0) {

            System.out.println("Invalid Prescription ID!");
            return;
        }

        // Check duplicate prescription
        if (dao.prescriptionExists(
                prescription.getPrescriptionId())) {

            System.out.println("Prescription already exists!");
            return;
        }


        // -----------------------------------------------------
        // Check Patient
        // -----------------------------------------------------

        if (!patientDAO.patientExists(
                prescription.getPatientId())) {

            System.out.println("Patient does not exist!");
            return;
        }


        // -----------------------------------------------------
        // Check Doctor
        // -----------------------------------------------------

        if (!doctorDAO.doctorExists(
                prescription.getDoctorId())) {

            System.out.println("Doctor does not exist!");
            return;
        }


        // -----------------------------------------------------
        // Check Appointment
        // -----------------------------------------------------

        Appointment appointment =
                appointmentDAO.getAppointmentById(
                        prescription.getAppointmentId());

        if (appointment == null) {

            System.out.println("Appointment does not exist!");
            return;
        }


        // -----------------------------------------------------
        // Check Appointment belongs to Patient
        // -----------------------------------------------------

        if (appointment.getPatientId()
                != prescription.getPatientId()) {

            System.out.println(
                    "Appointment does not belong to this patient!");

            return;
        }


        // -----------------------------------------------------
        // Check Appointment belongs to Doctor
        // -----------------------------------------------------

        if (appointment.getDoctorId()
                != prescription.getDoctorId()) {

            System.out.println(
                    "Appointment does not belong to this doctor!");

            return;
        }


        // -----------------------------------------------------
        // Check Appointment Status
        // -----------------------------------------------------

        if (!appointment.getStatus()
                .equalsIgnoreCase("BOOKED")) {

            System.out.println(
                    "Prescription can only be added to a booked appointment!");

            return;
        }


        // -----------------------------------------------------
        // Add Prescription
        // -----------------------------------------------------

        boolean result =
                dao.addPrescription(prescription);

        if (result) {

            System.out.println(
                    "Prescription added successfully!");

        } else {

            System.out.println(
                    "Failed to add prescription!");
        }
    }


    // =========================================================
    // 2. GET PRESCRIPTION BY ID
    // =========================================================

    public Prescription getPrescriptionById(int prescriptionId)
            throws SQLException {

        Prescription prescription =
                dao.getPrescriptionById(prescriptionId);

        if (prescription == null) {

            System.out.println("Prescription not found!");
        }

        return prescription;
    }


    // =========================================================
    // 3. GET PRESCRIPTIONS BY PATIENT
    // =========================================================

    public List<Prescription> getPrescriptionsByPatient(
            int patientId) throws SQLException {

        if (!patientDAO.patientExists(patientId)) {

            System.out.println("Patient does not exist!");

            return new ArrayList<>();
        }

        List<Prescription> prescriptions =
                dao.getPrescriptionsByPatient(patientId);

        if (prescriptions.isEmpty()) {

            System.out.println(
                    "No prescriptions found for this patient!");
        }

        return prescriptions;
    }


    // =========================================================
    // 4. GET PRESCRIPTIONS BY DOCTOR
    // =========================================================

    public List<Prescription> getPrescriptionsByDoctor(
            int doctorId) throws SQLException {

        if (!doctorDAO.doctorExists(doctorId)) {

            System.out.println("Doctor does not exist!");

            return new ArrayList<>();
        }

        List<Prescription> prescriptions =
                dao.getPrescriptionsByDoctor(doctorId);

        if (prescriptions.isEmpty()) {

            System.out.println(
                    "No prescriptions found for this doctor!");
        }

        return prescriptions;
    }


    // =========================================================
    // 5. UPDATE PRESCRIPTION
    // =========================================================

    public void updatePrescription(
            Prescription prescription) throws SQLException {

        // Check prescription exists
        if (!dao.prescriptionExists(
                prescription.getPrescriptionId())) {

            System.out.println(
                    "Prescription does not exist!");

            return;
        }


        // Basic validation
        if (prescription.getMedicineName() == null
                || prescription.getMedicineName().trim().isEmpty()) {

            System.out.println("Medicine name cannot be empty!");
            return;
        }

        if (prescription.getDosage() == null
                || prescription.getDosage().trim().isEmpty()) {

            System.out.println("Dosage cannot be empty!");
            return;
        }

        if (prescription.getDuration() == null
                || prescription.getDuration().trim().isEmpty()) {

            System.out.println("Duration cannot be empty!");
            return;
        }


        // Update
        boolean result =
                dao.updatePrescription(prescription);

        if (result) {

            System.out.println(
                    "Prescription updated successfully!");

        } else {

            System.out.println(
                    "Failed to update prescription!");
        }
    }


    // =========================================================
    // 6. DELETE PRESCRIPTION
    // =========================================================

    public void deletePrescription(int prescriptionId)
            throws SQLException {

        if (!dao.prescriptionExists(prescriptionId)) {

            System.out.println(
                    "Prescription does not exist!");

            return;
        }

        boolean result =
                dao.deletePrescription(prescriptionId);

        if (result) {

            System.out.println(
                    "Prescription deleted successfully!");

        } else {

            System.out.println(
                    "Failed to delete prescription!");
        }
    }
    
   
}

//ride booking system where ask the user name ,mno , distance , 
//to be covered then provide the option with calculated pairs option can be fair or rent 
//allow user to select any type of vehicle so user can start the ride 
//or exit the app if user has booked the ride display all the rent details wit user details and end the application
//the above question must achieve constructor chaing ,method overriding ,super keyword,generalisation (upcasting ,downcasting) 


//