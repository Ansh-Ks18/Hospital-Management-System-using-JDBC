package prescription_jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdpc_hospital.DBConnection;

public class PrescriptionDAO {


    // =========================================================
    // 1. CHECK PRESCRIPTION EXISTS
    // =========================================================

    public boolean prescriptionExists(int prescriptionId)
            throws SQLException {

        // TRICK:
        // SELECT → executeQuery() → ResultSet → rs.next()

        String sql = "SELECT prescription_id "
                   + "FROM prescriptions "
                   + "WHERE prescription_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // TRICK:
            // Match the Java variable with the ? position.
            ps.setInt(1, prescriptionId);

            try (ResultSet rs = ps.executeQuery()) {

                // One matching row = exists
                return rs.next();
            }
        }
    }


    // =========================================================
    // 2. ADD PRESCRIPTION
    // =========================================================

    public boolean addPrescription(Prescription prescription)
            throws SQLException {

        // TRICK:
        // INSERT → executeUpdate() → int rows
        //
        // NEVER use executeQuery() for INSERT.

        String sql = "INSERT INTO prescriptions "
                   + "(prescription_id, patient_id, doctor_id, "
                   + "appointment_id, medicine_name, dosage, "
                   + "duration, instructions) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // IMPORTANT TRICK:
            // Number of set methods = number of ?
            //
            // ?1 → prescriptionId
            // ?2 → patientId
            // ?3 → doctorId
            // ?4 → appointmentId
            // ?5 → medicineName
            // ?6 → dosage
            // ?7 → duration
            // ?8 → instructions

            ps.setInt(1, prescription.getPrescriptionId());
            ps.setInt(2, prescription.getPatientId());
            ps.setInt(3, prescription.getDoctorId());
            ps.setInt(4, prescription.getAppointmentId());
            ps.setString(5, prescription.getMedicineName());
            ps.setString(6, prescription.getDosage());
            ps.setString(7, prescription.getDuration());
            ps.setString(8, prescription.getInstructions());

            int rows = ps.executeUpdate();

            return rows > 0;
        }
    }


    // =========================================================
    // 3. GET PRESCRIPTION BY ID
    // =========================================================

    public Prescription getPrescriptionById(int prescriptionId)
            throws SQLException {

        // TRICK:
        // SELECT one row → if(rs.next())
        // Then create ONE Prescription object.

        String sql = "SELECT * FROM prescriptions "
                   + "WHERE prescription_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, prescriptionId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    return new Prescription(

                        rs.getInt("prescription_id"),
                        rs.getInt("patient_id"),
                        rs.getInt("doctor_id"),
                        rs.getInt("appointment_id"),
                        rs.getString("medicine_name"),
                        rs.getString("dosage"),
                        rs.getString("duration"),
                        rs.getString("instructions")
                    );
                }
            }
        }

        // No record found
        return null;
    }


    // =========================================================
    // 4. GET PRESCRIPTIONS BY PATIENT
    // =========================================================

    public List<Prescription> getPrescriptionsByPatient(int patientId)
            throws SQLException {

        // TRICK:
        // Multiple rows → while(rs.next())
        // Never return inside the while loop.

        List<Prescription> prescriptions = new ArrayList<>();

        String sql = "SELECT * FROM prescriptions "
                   + "WHERE patient_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, patientId);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Prescription prescription = new Prescription(

                        rs.getInt("prescription_id"),
                        rs.getInt("patient_id"),
                        rs.getInt("doctor_id"),
                        rs.getInt("appointment_id"),
                        rs.getString("medicine_name"),
                        rs.getString("dosage"),
                        rs.getString("duration"),
                        rs.getString("instructions")
                    );

                    prescriptions.add(prescription);
                }
            }
        }

        // IMPORTANT:
        // Return empty List if no records.
        // Don't return null.
        return prescriptions;
    }


    // =========================================================
    // 5. GET PRESCRIPTIONS BY DOCTOR
    // =========================================================

    public List<Prescription> getPrescriptionsByDoctor(int doctorId)
            throws SQLException {

        List<Prescription> prescriptions = new ArrayList<>();

        String sql = "SELECT * FROM prescriptions "
                   + "WHERE doctor_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, doctorId);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Prescription prescription = new Prescription(

                        rs.getInt("prescription_id"),
                        rs.getInt("patient_id"),
                        rs.getInt("doctor_id"),
                        rs.getInt("appointment_id"),
                        rs.getString("medicine_name"),
                        rs.getString("dosage"),
                        rs.getString("duration"),
                        rs.getString("instructions")
                    );

                    prescriptions.add(prescription);
                }
            }
        }

        return prescriptions;
    }


    // =========================================================
    // 6. UPDATE PRESCRIPTION
    // =========================================================

    public boolean updatePrescription(Prescription prescription)
            throws SQLException {

        // TRICK:
        // UPDATE → executeUpdate()
        // executeUpdate() returns number of affected rows.

        String sql = "UPDATE prescriptions "
                   + "SET medicine_name = ?, "
                   + "dosage = ?, "
                   + "duration = ?, "
                   + "instructions = ? "
                   + "WHERE prescription_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, prescription.getMedicineName());
            ps.setString(2, prescription.getDosage());
            ps.setString(3, prescription.getDuration());
            ps.setString(4, prescription.getInstructions());

            // WHERE parameter comes last
            ps.setInt(5, prescription.getPrescriptionId());

            int rows = ps.executeUpdate();

            return rows > 0;
        }
    }


    // =========================================================
    // 7. DELETE PRESCRIPTION
    // =========================================================

    public boolean deletePrescription(int prescriptionId)
            throws SQLException {

        // TRICK:
        // DELETE → executeUpdate()
        // No ResultSet required.

        String sql = "DELETE FROM prescriptions "
                   + "WHERE prescription_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, prescriptionId);

            int rows = ps.executeUpdate();

            return rows > 0;
        }
    }
}