package jdpc_hospital;

import java.sql.SQLException;
import java.util.List;

public class PatientService {

    private PatientDAO dao;

    public PatientService() {
        dao = new PatientDAO();
    }

    public void registerPatient(Patient patient) throws SQLException {

        if (patient.getPatientId() <= 0) {
            System.out.println("Invalid patient ID!");
            return;
        }

        if (patient.getName() == null || patient.getName().trim().isEmpty()) {
            System.out.println("Patient name cannot be empty!");
            return;
        }

        if (patient.getAge() <= 0) {
            System.out.println("Invalid age!");
            return;
        }

        if (dao.patientExists(patient.getPatientId())) {

            System.out.println("Patient already exists!");
            return;
        }

        dao.addPatient(patient);
    }
    
    
    // Challenge 2
    public Patient getPatientById(int patientId) throws SQLException {

        Patient patient = dao.getPatientById(patientId);

        if (patient == null) {
            System.out.println("Patient not found!");
        }

        return patient;
    }
    
    
    // Challenge 3
    public List<Patient> getPatientAllService() throws SQLException {

        List<Patient> result = dao.getPatientAll();

        if ( result.isEmpty()) {
            System.out.println("No patients found!");
        }
        

        return result;
    }
    
    // Challenge 4
    public void updatePatient(Patient patient) throws SQLException {

        if (!dao.patientExists(patient.getPatientId())) {

            System.out.println("Patient not found!");
            return;
        }

        boolean updated = dao.updatePatient(patient);

        if (updated) {
            System.out.println("Patient updated successfully!");
        } else {
            System.out.println("Patient update failed!");
        }
    }
    public void deletePatient(int patientId) throws SQLException {

        if (!dao.patientExists(patientId)) {

            System.out.println("Patient not found!");
            return;
        }

        boolean deleted = dao.deletePatient(patientId);

        if (deleted) {
            System.out.println("Patient deleted successfully!");
        } else {
            System.out.println("Patient deletion failed!");
        }
    }}