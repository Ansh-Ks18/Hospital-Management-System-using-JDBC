package Doctor_Jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jdpc_hospital.DBConnection;
import java.util.ArrayList;
import java.util.List;


public class DoctorDAO {

    // =========================
    // CHECK DOCTOR
    // =========================

    public boolean doctorExists(int doctorId) throws SQLException {

        String sql = "SELECT doctor_id FROM doctors WHERE doctor_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, doctorId);

            try (ResultSet rs = ps.executeQuery()) {

                return rs.next();
            }
        }
    }

    // =========================
    // ADD DOCTOR
    // =========================

    public boolean addDoctor(Doctor doctor) throws SQLException {

        String sql = "INSERT INTO doctors " +
                     "(doctor_id, name, specialization, phone, experience) " +
                     "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, doctor.getDoctorId());
            ps.setString(2, doctor.getName());
            ps.setString(3, doctor.getSpecialization());
            ps.setString(4, doctor.getPhone());
            ps.setInt(5, doctor.getExperience());

            int rows = ps.executeUpdate();

            return rows > 0;
        }
    }

    // =========================
    // GET DOCTOR BY ID
    // =========================

    public Doctor getDoctorById(int doctorId) throws SQLException {

        String sql = "SELECT * FROM doctors WHERE doctor_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, doctorId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    return new Doctor(
                            rs.getInt("doctor_id"),
                            rs.getString("name"),
                            rs.getString("specialization"),
                            rs.getString("phone"),
                            rs.getInt("experience")
                    );
                }
            }
        }

        return null;
    }

    // =========================
    // GET ALL DOCTORS
    // =========================

    public List<Doctor> getAllDoctors() throws SQLException {

        List<Doctor> doctors = new ArrayList<>();

        String sql = "SELECT * FROM doctors";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Doctor doctor = new Doctor(
                        rs.getInt("doctor_id"),
                        rs.getString("name"),
                        rs.getString("specialization"),
                        rs.getString("phone"),
                        rs.getInt("experience")
                );

                doctors.add(doctor);
            }
        }

        return doctors;
    }

    // =========================
    // UPDATE DOCTOR
    // =========================

    public boolean updateDoctor(Doctor doctor) throws SQLException {

        String sql = "UPDATE doctors SET " +
                     "name = ?, " +
                     "specialization = ?, " +
                     "phone = ?, " +
                     "experience = ? " +
                     "WHERE doctor_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, doctor.getName());
            ps.setString(2, doctor.getSpecialization());
            ps.setString(3, doctor.getPhone());
            ps.setInt(4, doctor.getExperience());
            ps.setInt(5, doctor.getDoctorId());

            int rows = ps.executeUpdate();

            return rows > 0;
        }
    }

    // =========================
    // DELETE DOCTOR
    // =========================

    public boolean deleteDoctor(int doctorId) throws SQLException {

        String sql = "DELETE FROM doctors WHERE doctor_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, doctorId);

            int rows = ps.executeUpdate();

            return rows > 0;
        }
    }
}