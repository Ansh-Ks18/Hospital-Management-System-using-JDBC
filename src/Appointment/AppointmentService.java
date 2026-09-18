package Appointment;

import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import Doctor_Jdbc.DoctorDAO;
import jdpc_hospital.PatientDAO;

public class AppointmentService {

    private AppointmentDao dao = new AppointmentDao();
    private PatientDAO patientDAO = new PatientDAO();
    private DoctorDAO doctorDAO = new DoctorDAO();


    // 1. Book Appointment
    public void bookAppointment(Appointment appointment) throws SQLException {

        // Validate Appointment ID
        if (appointment.getAppointmentId() <= 0) {
            System.out.println("Invalid Appointment ID!");
            return;
        }

        // Check Appointment already exists
        if (dao.appointmentExists(appointment.getAppointmentId())) {
            System.out.println("Appointment already exists!");
            return;
        }

        // Check Patient exists
        if (!patientDAO.patientExists(appointment.getPatientId())) {
            System.out.println("Patient does not exist!");
            return;
        }

        // Check Doctor exists
        if (!doctorDAO.doctorExists(appointment.getDoctorId())) {
            System.out.println("Doctor does not exist!");
            return;
        }
        
        //check doctor availability
        boolean available = dao.isDoctorAvailable(
                appointment.getDoctorId(),
                appointment.getAppointmentDate(),
                appointment.getAppointmentTime()
        );

        if (!available) {
            System.out.println("Doctor is already booked at this time!");
            return;
        }
        
        // Set default status
        appointment.setStatus("BOOKED");

        // Book appointment
        boolean result = dao.bookAppointment(appointment);

        boolean patientAvailable = dao.isPatientAvailable(
                appointment.getPatientId(),
                appointment.getAppointmentDate(),
                appointment.getAppointmentTime()
        );

        if (!patientAvailable) {
            System.out.println("Patient already has an appointment at this time!");
            return;
        }
        
        if (result) {
            System.out.println("Appointment booked successfully!");
        } else {
            System.out.println("Failed to book appointment!");
        }
    }


    // 2. Get Appointment By ID
    public Appointment getAppointmentById(int appointmentId) throws SQLException {

        Appointment appointment = dao.getAppointmentById(appointmentId);

        if (appointment == null) {
            System.out.println("Appointment not found!");
        }

        return appointment;
    }


    // 3. Get All Appointments
    public List<Appointment> getAllAppointments() throws SQLException {

        List<Appointment> appointments = dao.getAllAppointments();

        if (appointments.isEmpty()) {
            System.out.println("No appointments found!");
        }

        return appointments;
    }


    // 4. Cancel Appointment
    public void cancelAppointment(int appointmentId) throws SQLException {

        Appointment appointment = dao.getAppointmentById(appointmentId);

        // Appointment doesn't exist
        if (appointment == null) {
            System.out.println("Appointment not found!");
            return;
        }

        // Already cancelled
        if (appointment.getStatus().equalsIgnoreCase("CANCELLED")) {
            System.out.println("Appointment is already cancelled!");
            return;
        }

        boolean result = dao.cancelAppointment(appointmentId);

        if (result) {
            System.out.println("Appointment cancelled successfully!");
        } else {
            System.out.println("Failed to cancel appointment!");
        }
    }

    //show all appointments from patients
    public List<Appointment> getAppointmentsByPatient(int patientId)
            throws SQLException {

        // Check patient exists
        if (!patientDAO.patientExists(patientId)) {
            System.out.println("Patient does not exist!");
            return new ArrayList<>();
        }

        List<Appointment> appointments =
                dao.getAppointmentsByPatient(patientId);

        if (appointments.isEmpty()) {
            System.out.println("No appointments found for this patient!");
        }

        return appointments;
    }
    
    //show all the appointments of doctor
    public List<Appointment> getAppointmentsByDoctor(int doctorId)throws SQLException
        {

        // Check patient exists
        if (!doctorDAO.doctorExists(doctorId)) {
            System.out.println("doctor does not exist!");
            return new ArrayList<>();
        }

        List<Appointment> appointments =
                dao.getAppointmentsByDoctor(doctorId);

        if (appointments.isEmpty()) {
            System.out.println("No appointments found for you doctor!");
        }

        return appointments;
    }
    
    //reschedule
    public void rescheduleAppointment(
            int appointmentId,
            String newDate,
            String newTime) throws SQLException {

        // Check appointment exists
        Appointment appointment =
                dao.getAppointmentById(appointmentId);

        if (appointment == null) {
            System.out.println("Appointment not found!");
            return;
        }

        // Check appointment status
        if (!appointment.getStatus().equalsIgnoreCase("BOOKED")) {
            System.out.println(
                    "Only booked appointments can be rescheduled!");
            return;
        }

        // Check doctor availability
        boolean doctorAvailable =
                dao.isDoctorAvailable(
                        appointment.getDoctorId(),
                        newDate,
                        newTime
                );

        if (!doctorAvailable) {
            System.out.println(
                    "Doctor is already booked at this time!");
            return;
        }

        // Check patient availability
        boolean patientAvailable =
                dao.isPatientAvailable(
                        appointment.getPatientId(),
                        newDate,
                        newTime
                );

        if (!patientAvailable) {
            System.out.println(
                    "Patient already has an appointment at this time!");
            return;
        }

        // Reschedule
        boolean result =
                dao.rescheduleAppointment(
                        appointmentId,
                        newDate,
                        newTime
                );

        if (result) {
            System.out.println(
                    "Appointment rescheduled successfully!");
        } else {
            System.out.println(
                    "Failed to reschedule appointment!");
        }
    }
   }