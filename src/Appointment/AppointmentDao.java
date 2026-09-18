package Appointment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdpc_hospital.DBConnection;

public class AppointmentDao {

    // 1. Check whether appointment already exists
    public boolean appointmentExists(int appointmentId) throws SQLException {

        String sql = "SELECT appointment_id FROM appointments WHERE appointment_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, appointmentId);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }


    // 2. Book Appointment
    public boolean bookAppointment(Appointment appointment) throws SQLException {

        String sql = "INSERT INTO appointments "
                   + "(appointment_id, patient_id, doctor_id, appointment_date, appointment_time, status) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, appointment.getAppointmentId());
            ps.setInt(2, appointment.getPatientId());
            ps.setInt(3, appointment.getDoctorId());
            ps.setString(4, appointment.getAppointmentDate());
            ps.setString(5, appointment.getAppointmentTime());
            ps.setString(6, appointment.getStatus());

            int rows = ps.executeUpdate();

            return rows > 0;
        }
    }


    // 3. Get Appointment By ID
    public Appointment getAppointmentById(int appointmentId) throws SQLException {

        String sql = "SELECT * FROM appointments WHERE appointment_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, appointmentId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    return new Appointment(
                        rs.getInt("appointment_id"),
                        rs.getInt("patient_id"),
                        rs.getInt("doctor_id"),
                        rs.getString("appointment_date"),
                        rs.getString("appointment_time"),
                        rs.getString("status")
                    );
                }
            }
        }

        return null;
    }


    // 4. Get All Appointments
    public List<Appointment> getAllAppointments() throws SQLException {

        List<Appointment> appointments = new ArrayList<>();

        String sql = "SELECT * FROM appointments";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Appointment appointment = new Appointment(
                    rs.getInt("appointment_id"),
                    rs.getInt("patient_id"),
                    rs.getInt("doctor_id"),
                    rs.getString("appointment_date"),
                    rs.getString("appointment_time"),
                    rs.getString("status")
                );

                appointments.add(appointment);
            }
        }

        return appointments;
    }


    // 5. Cancel Appointment
    public boolean cancelAppointment(int appointmentId) throws SQLException {

        String sql = "UPDATE appointments "
                   + "SET status = 'CANCELLED' "
                   + "WHERE appointment_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, appointmentId);

            int rows = ps.executeUpdate();

            return rows > 0;
        }
        
        
        
        
    }
    
    
    public boolean isDoctorAvailable(int doctorId, String date, String time) throws SQLException {

        String sql = "SELECT appointment_id FROM appointments "
                   + "WHERE doctor_id = ? "
                   + "AND appointment_date = ? "
                   + "AND appointment_time = ? "
                   + "AND status = 'BOOKED'";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, doctorId);
            ps.setString(2, date);
            ps.setString(3, time);

            try (ResultSet rs = ps.executeQuery()) {

                return !rs.next();
            }
        }
        
        
        public boolean isPatientAvailable(int patientId, String date, String time)
                throws SQLException {

            String sql = "SELECT appointment_id FROM appointments "
                       + "WHERE patient_id = ? "
                       + "AND appointment_date = ? "
                       + "AND appointment_time = ? "
                       + "AND status = 'BOOKED'";

            try (Connection con = DBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setInt(1, patientId);
                ps.setString(2, date);
                ps.setString(3, time);

                try (ResultSet rs = ps.executeQuery()) {

                    // No appointment found = patient is available
                    return !rs.next();
                }
            }
        }
        
        public List<Appointment> getAppointmentsByPatient(int patientId)
                throws SQLException {

            List<Appointment> appointments = new ArrayList<>();

            String sql = "SELECT * FROM appointments "
                       + "WHERE patient_id = ?";

            try (Connection con = DBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setInt(1, patientId);

                try (ResultSet rs = ps.executeQuery()) {

                    while (rs.next()) {

                        Appointment appointment = new Appointment(
                            rs.getInt("appointment_id"),
                            rs.getInt("patient_id"),
                            rs.getInt("doctor_id"),
                            rs.getString("appointment_date"),
                            rs.getString("appointment_time"),
                            rs.getString("status")
                        );

                        appointments.add(appointment);
                    }
                }
            }

            return appointments;
        }
    
        
        
        public List<Appointment> getAppointmentsByDoctor(int doctorId)
                throws SQLException {

            List<Appointment> appointments = new ArrayList<>();

            String sql = "SELECT * FROM appointments "
                       + "WHERE doctor_id = ?";

            try (Connection con = DBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setInt(1, doctorId);

                try (ResultSet rs = ps.executeQuery()) {

                    while (rs.next()) {

                        Appointment appointment = new Appointment(
                            rs.getInt("appointment_id"),
                            rs.getInt("patient_id"),
                            rs.getInt("doctor_id"),
                            rs.getString("appointment_date"),
                            rs.getString("appointment_time"),
                            rs.getString("status")
                        );

                        appointments.add(appointment);
                    }
                }
            }

            return appointments;
        }
        
        //reschdule
        public boolean rescheduleAppointment(
                int appointmentId,
                String newDate,
                String newTime) throws SQLException {

            String sql = "UPDATE appointments "
                       + "SET appointment_date = ?, appointment_time = ? "
                       + "WHERE appointment_id = ?";

            try (Connection con = DBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, newDate);
                ps.setString(2, newTime);
                ps.setInt(3, appointmentId);

                int rows = ps.executeUpdate();

                return rows > 0;
            }
        }
    }
