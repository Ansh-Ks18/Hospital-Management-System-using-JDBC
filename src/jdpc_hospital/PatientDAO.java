package jdpc_hospital;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class PatientDAO {

    public boolean patientExists(int patientId) throws SQLException {

        String sql = "SELECT patient_id FROM patients WHERE patient_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, patientId);

            try (ResultSet rs = ps.executeQuery()) {

                return rs.next();
            }
        }
    }

    public void addPatient(Patient patient) throws SQLException {

        String sql = "INSERT INTO patients " +
                "(patient_id, name, age, gender, phone, address, blood_group) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, patient.getPatientId());
            ps.setString(2, patient.getName());
            ps.setInt(3, patient.getAge());
            ps.setString(4, patient.getGender());
            ps.setString(5, patient.getPhone());
            ps.setString(6, patient.getAddress());
            ps.setString(7, patient.getBloodGroup());

            ps.executeUpdate();

            System.out.println("Patient added successfully!");
        }
    }
    
    // Challenge 2
    public Patient getPatientById(int patientId) throws SQLException {

        String sql = "SELECT * FROM patients WHERE patient_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, patientId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Patient patient = new Patient(
                            rs.getInt("patient_id"),
                            rs.getString("name"),
                            rs.getInt("age"),
                            rs.getString("gender"),
                            rs.getString("phone"),
                            rs.getString("address"),
                            rs.getString("blood_group")
                    );

                    return patient;
                }
            }
        }

        return null;
    }
    
    
    // Challenge 3
    public List<Patient> getPatientAll() throws SQLException {

    	List<Patient>patientAll = new ArrayList<>();
    	
        String sql = "SELECT * FROM patients";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {


            try (ResultSet rs = ps.executeQuery()) {

               while (rs.next()) {

                    Patient patient = new Patient(
                            rs.getInt("patient_id"),
                            rs.getString("name"),
                            rs.getInt("age"),
                            rs.getString("gender"),
                            rs.getString("phone"),
                            rs.getString("address"),
                            rs.getString("blood_group")
                    );

                   patientAll.add( patient);
                }
            }
        }

        return patientAll;
    }
    
    
    //Update 
    
    // Challenge 4
    public boolean updatePatient(Patient patient) throws SQLException {

        String sql = "UPDATE patients " +
                     "SET name = ?, " +
                     "age = ?, " +
                     "gender = ?, " +
                     "phone = ?, " +
                     "address = ?, " +
                     "blood_group = ? " +
                     "WHERE patient_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, patient.getName());
            ps.setInt(2, patient.getAge());
            ps.setString(3, patient.getGender());
            ps.setString(4, patient.getPhone());
            ps.setString(5, patient.getAddress());
            ps.setString(6, patient.getBloodGroup());
            ps.setInt(7, patient.getPatientId());

            int rows = ps.executeUpdate();

            return rows > 0;
        }
    }
    // Challenge 4
    public boolean deletePatient(int patientId) throws SQLException {

        String sql = "DELETE FROM patients WHERE patient_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, patientId);

            int rows = ps.executeUpdate();

            return rows > 0;
        }
    }

}