package Doctor_Jdbc;

import java.sql.SQLException;
import java.util.List;

public class DoctorService {

    private DoctorDAO dao;

    public DoctorService() {

        dao = new DoctorDAO();
    }

    // =========================
    // REGISTER DOCTOR
    // =========================

    public void registerDoctor(Doctor doctor) throws SQLException {

        if (doctor.getDoctorId() <= 0) {

            System.out.println("Invalid Doctor ID!");
            return;
        }

        if (doctor.getName() == null ||
            doctor.getName().trim().isEmpty()) {

            System.out.println("Doctor name cannot be empty!");
            return;
        }

        if (doctor.getSpecialization() == null ||
            doctor.getSpecialization().trim().isEmpty()) {

            System.out.println("Specialization cannot be empty!");
            return;
        }

        if (doctor.getExperience() < 0) {

            System.out.println("Invalid experience!");
            return;
        }

        if (dao.doctorExists(doctor.getDoctorId())) {

            System.out.println("Doctor already exists!");
            return;
        }

        boolean added = dao.addDoctor(doctor);

        if (added) {

            System.out.println("Doctor registered successfully!");

        } else {

            System.out.println("Doctor registration failed!");
        }
    }

    // =========================
    // GET DOCTOR
    // =========================

    public Doctor getDoctorById(int doctorId) throws SQLException {

        Doctor doctor = dao.getDoctorById(doctorId);

        if (doctor == null) {

            System.out.println("Doctor not found!");
        }

        return doctor;
    }

    // =========================
    // GET ALL DOCTORS
    // =========================

    public List<Doctor> getAllDoctors() throws SQLException {

        List<Doctor> doctors = dao.getAllDoctors();

        if (doctors.isEmpty()) {

            System.out.println("No doctors found!");
        }

        return doctors;
    }

    // =========================
    // UPDATE DOCTOR
    // =========================

    public void updateDoctor(Doctor doctor) throws SQLException {

        if (!dao.doctorExists(doctor.getDoctorId())) {

            System.out.println("Doctor not found!");
            return;
        }

        boolean updated = dao.updateDoctor(doctor);

        if (updated) {

            System.out.println("Doctor updated successfully!");

        } else {

            System.out.println("Doctor update failed!");
        }
    }

    // =========================
    // DELETE DOCTOR
    // =========================

    public void deleteDoctor(int doctorId) throws SQLException {

        if (!dao.doctorExists(doctorId)) {

            System.out.println("Doctor not found!");
            return;
        }

        boolean deleted = dao.deleteDoctor(doctorId);

        if (deleted) {

            System.out.println("Doctor deleted successfully!");

        } else {

            System.out.println("Doctor deletion failed!");
        }
    }
}