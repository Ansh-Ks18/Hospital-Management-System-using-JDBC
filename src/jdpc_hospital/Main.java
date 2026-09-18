package jdpc_hospital;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import Appointment.Appointment;
import Appointment.AppointmentService;
import Doctor_Jdbc.Doctor;
import  Doctor_Jdbc.DoctorService;
import jdpc_hospital.Patient;
import jdpc_hospital.PatientService;
import prescription_jdbc.Prescription;
import prescription_jdbc.PrescriptionService;


public class Main {

    static Scanner sc = new Scanner(System.in);

    static PatientService patientService = new PatientService();
    static DoctorService doctorService = new DoctorService();
    static AppointmentService appointmentService = new AppointmentService();
    static PrescriptionService prescriptionService =
            new PrescriptionService();

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n================================");
            System.out.println("     HOSPITAL MANAGEMENT SYSTEM");
            System.out.println("================================");

            System.out.println("1. Patient Management");
            System.out.println("2. Doctor Management");
            System.out.println("3. Appointment Management");
            System.out.println("4. Prescription Management");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            try {

                switch (choice) {

                case 1:
                    patientManagement();
                    break;

                case 2:
                    doctorManagement();
                    break;

                case 3:
                    appointmentManagement();
                    break;

                case 4:
                    prescriptionManagement();
                    break;

                case 5:
                    System.out.println("Thank you!");
                    sc.close();
                    return;
                    
                default:
                    System.out.println("Invalid choice!");
                }

            } catch (SQLException e) {
                System.out.println("Database Error: " + e.getMessage());
            }
        }
    }


    // ================= PATIENT MANAGEMENT =================

    public static void patientManagement() throws SQLException {

        while (true) {

            System.out.println("\n------ PATIENT MANAGEMENT ------");

            System.out.println("1. Register Patient");
            System.out.println("2. View Patient");
            System.out.println("3. View All Patients");
            System.out.println("4. Update Patient");
            System.out.println("5. Delete Patient");
            System.out.println("6. Back");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {

            case 1:

                System.out.print("Patient ID: ");
                int id = sc.nextInt();
                sc.nextLine();

                System.out.print("Name: ");
                String name = sc.nextLine();

                System.out.print("Age: ");
                int age = sc.nextInt();
                sc.nextLine();

                System.out.print("Gender: ");
                String gender = sc.nextLine();

                System.out.print("Phone: ");
                String phone = sc.nextLine();

                System.out.print("Address: ");
                String address = sc.nextLine();

                System.out.print("Blood Group: ");
                String bloodGroup = sc.nextLine();

                Patient patient = new Patient(
                        id, name, age, gender,
                        phone, address, bloodGroup);

                patientService.registerPatient(patient);
                break;


            case 2:

                System.out.print("Enter Patient ID: ");
                int patientId = sc.nextInt();

                Patient p = patientService.getPatientById(patientId);

                if (p != null) {
                    System.out.println(p);
                }

                break;


            case 3:

                List<Patient> patients =
                        patientService.getPatientAllService();

                for (Patient pt : patients) {
                    System.out.println("\n" + pt);
                }

                break;


            case 4:

                System.out.print("Enter Patient ID: ");
                int updateId = sc.nextInt();
                sc.nextLine();

                Patient existingPatient =
                        patientService.getPatientById(updateId);

                if (existingPatient == null) {
                    break;
                }

                System.out.print("New Name: ");
                existingPatient.setName(sc.nextLine());

                System.out.print("New Age: ");
                existingPatient.setAge(sc.nextInt());
                sc.nextLine();

                System.out.print("New Gender: ");
                existingPatient.setGender(sc.nextLine());

                System.out.print("New Phone: ");
                existingPatient.setPhone(sc.nextLine());

                System.out.print("New Address: ");
                existingPatient.setAddress(sc.nextLine());

                System.out.print("New Blood Group: ");
                existingPatient.setBloodGroup(sc.nextLine());

                patientService.updatePatient(existingPatient);

                break;


            case 5:

                System.out.print("Enter Patient ID: ");
                int deleteId = sc.nextInt();

                patientService.deletePatient(deleteId);

                break;


            case 6:
                return;

            default:
                System.out.println("Invalid choice!");
            }
        }
    }


    // ================= DOCTOR MANAGEMENT =================

    public static void doctorManagement() throws SQLException {

        while (true) {

            System.out.println("\n------ DOCTOR MANAGEMENT ------");

            System.out.println("1. Register Doctor");
            System.out.println("2. View Doctor");
            System.out.println("3. View All Doctors");
            System.out.println("4. Update Doctor");
            System.out.println("5. Delete Doctor");
            System.out.println("6. Back");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {

            case 1:

                System.out.print("Doctor ID: ");
                int doctorId = sc.nextInt();
                sc.nextLine();

                System.out.print("Name: ");
                String name = sc.nextLine();

                System.out.print("Specialization: ");
                String specialization = sc.nextLine();

                System.out.print("Phone: ");
                String phone = sc.nextLine();

                System.out.print("Experience: ");
                int experience = sc.nextInt();

                Doctor doctor = new Doctor(
                        doctorId, name, specialization,
                        phone, experience);

                doctorService.registerDoctor(doctor);

                break;


            case 2:

                System.out.print("Enter Doctor ID: ");
                int searchDoctorId = sc.nextInt();

                Doctor d =
                        doctorService.getDoctorById(searchDoctorId);

                if (d != null) {
                    System.out.println(d);
                }

                break;


            case 3:

                List<Doctor> doctors =
                        doctorService.getAllDoctors();

                for (Doctor doc : doctors) {
                    System.out.println("\n" + doc);
                }

                break;


            case 4:

                System.out.print("Enter Doctor ID: ");
                int updateDoctorId = sc.nextInt();
                sc.nextLine();

                Doctor existingDoctor =
                        doctorService.getDoctorById(updateDoctorId);

                if (existingDoctor == null) {
                    break;
                }

                System.out.print("New Name: ");
                existingDoctor.setName(sc.nextLine());

                System.out.print("New Specialization: ");
                existingDoctor.setSpecialization(sc.nextLine());

                System.out.print("New Phone: ");
                existingDoctor.setPhone(sc.nextLine());

                System.out.print("New Experience: ");
                existingDoctor.setExperience(sc.nextInt());

                doctorService.updateDoctor(existingDoctor);

                break;


            case 5:

                System.out.print("Enter Doctor ID: ");
                int deleteDoctorId = sc.nextInt();

                doctorService.deleteDoctor(deleteDoctorId);

                break;


            case 6:
                return;

            default:
                System.out.println("Invalid choice!");
            }
        }
    }


    // ================= APPOINTMENT MANAGEMENT =================

    public static void appointmentManagement() throws SQLException {

        while (true) {

            System.out.println("\n------ APPOINTMENT MANAGEMENT ------");

            System.out.println("1. Book Appointment");
            System.out.println("2. View Appointment");
            System.out.println("3. View All Appointments");
            System.out.println("4. Cancel Appointment");
            System.out.println("5. View Patient Appointments");
            System.out.println("6. View Doctor Appointments");
            System.out.println("7. Reschedule Appointment");
            System.out.println("8. Back");
 

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {

            // -------- BOOK APPOINTMENT --------
            case 1:

                System.out.print("Appointment ID: ");
                int appointmentId = sc.nextInt();

                System.out.print("Patient ID: ");
                int patientId = sc.nextInt();

                System.out.print("Doctor ID: ");
                int doctorId = sc.nextInt();

                sc.nextLine();

                System.out.print("Appointment Date (YYYY-MM-DD): ");
                String date = sc.nextLine();

                System.out.print("Appointment Time (HH:MM:SS): ");
                String time = sc.nextLine();

                Appointment appointment = new Appointment(
                        appointmentId,
                        patientId,
                        doctorId,
                        date,
                        time,
                        "BOOKED"
                );

                appointmentService.bookAppointment(appointment);

                break;


            // -------- VIEW APPOINTMENT --------
            case 2:

                System.out.print("Enter Appointment ID: ");
                int searchAppointmentId = sc.nextInt();

                Appointment a =
                        appointmentService.getAppointmentById(
                                searchAppointmentId);

                if (a != null) {
                    System.out.println("\n" + a);
                }

                break;


            // -------- VIEW ALL APPOINTMENTS --------
            case 3:

                List<Appointment> appointments =
                        appointmentService.getAllAppointments();

                for (Appointment ap : appointments) {
                    System.out.println("\n" + ap);
                }

                break;


            // -------- CANCEL APPOINTMENT --------
            case 4:

                System.out.print("Enter Appointment ID: ");
                int cancelId = sc.nextInt();

                appointmentService.cancelAppointment(cancelId);

                break;


            // -------- BACK --------
            case 5:

                System.out.print("Enter Patient ID: ");
                int patientI = sc.nextInt();

                List<Appointment> patientAppointments =
                        appointmentService.getAppointmentsByPatient(patientI);

                for (Appointment ap : patientAppointments) {
                    System.out.println("\n" + ap);
                }

                break;

            case 6:
            	
            	 System.out.print("Enter Doctor ID: ");
                 int docId = sc.nextInt();

                 List<Appointment> DoctorAppointments =
                         appointmentService.getAppointmentsByDoctor(docId);

                 for (Appointment app : DoctorAppointments) {
                     System.out.println("\n" + app);
                 }
                 
                 break;

            case 7:

                System.out.print("Enter Appointment ID: ");
                int apId = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter New Date (YYYY-MM-DD): ");
                String newDate = sc.nextLine();

                System.out.print("Enter New Time (HH:MM:SS): ");
                String newTime = sc.nextLine();

                appointmentService.rescheduleAppointment(
                        apId,
                        newDate,
                        newTime
                );

                break;

            case 8:
                return;


            default:
                System.out.println("Invalid choice!");
            }
        }
    }




public static void prescriptionManagement() throws SQLException {

    while (true) {

        System.out.println("\n================================");
        System.out.println("    PRESCRIPTION MANAGEMENT");
        System.out.println("================================");

        System.out.println("1. Add Prescription");
        System.out.println("2. View Prescription");
        System.out.println("3. View Prescriptions By Patient");
        System.out.println("4. View Prescriptions By Doctor");
        System.out.println("5. Update Prescription");
        System.out.println("6. Delete Prescription");
        System.out.println("7. Back");

        System.out.print("Enter choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {

        // =================================================
        // 1. ADD PRESCRIPTION
        // =================================================

        case 1:

            System.out.print("Prescription ID: ");
            int prescriptionId = sc.nextInt();

            System.out.print("Patient ID: ");
            int patientId = sc.nextInt();

            System.out.print("Doctor ID: ");
            int doctorId = sc.nextInt();

            System.out.print("Appointment ID: ");
            int appointmentId = sc.nextInt();

            sc.nextLine();

            System.out.print("Medicine Name: ");
            String medicineName = sc.nextLine();

            System.out.print("Dosage: ");
            String dosage = sc.nextLine();

            System.out.print("Duration: ");
            String duration = sc.nextLine();

            System.out.print("Instructions: ");
            String instructions = sc.nextLine();


            Prescription prescription =
                    new Prescription(
                            prescriptionId,
                            patientId,
                            doctorId,
                            appointmentId,
                            medicineName,
                            dosage,
                            duration,
                            instructions
                    );

            prescriptionService.addPrescription(prescription);

            break;


        // =================================================
        // 2. VIEW PRESCRIPTION
        // =================================================

        case 2:

            System.out.print("Enter Prescription ID: ");
            int searchId = sc.nextInt();

            Prescription p =
                    prescriptionService
                    .getPrescriptionById(searchId);

            if (p != null) {
                System.out.println("\n" + p);
            }

            break;


        // =================================================
        // 3. VIEW PRESCRIPTIONS BY PATIENT
        // =================================================

        case 3:

            System.out.print("Enter Patient ID: ");
            int searchPatientId = sc.nextInt();

            List<Prescription> patientPrescriptions =
                    prescriptionService
                    .getPrescriptionsByPatient(searchPatientId);

            for (Prescription pr : patientPrescriptions) {
                System.out.println("\n" + pr);
            }

            break;


        // =================================================
        // 4. VIEW PRESCRIPTIONS BY DOCTOR
        // =================================================

        case 4:

            System.out.print("Enter Doctor ID: ");
            int searchDoctorId = sc.nextInt();

            List<Prescription> doctorPrescriptions =
                    prescriptionService
                    .getPrescriptionsByDoctor(searchDoctorId);

            for (Prescription pr : doctorPrescriptions) {
                System.out.println("\n" + pr);
            }

            break;


        // =================================================
        // 5. UPDATE PRESCRIPTION
        // =================================================

        case 5:

            System.out.print("Enter Prescription ID: ");
            int updateId = sc.nextInt();
            sc.nextLine();

            Prescription existingPrescription =
                    prescriptionService
                    .getPrescriptionById(updateId);

            if (existingPrescription == null) {
                break;
            }

            System.out.print("New Medicine Name: ");
            existingPrescription.setMedicineName(
                    sc.nextLine());

            System.out.print("New Dosage: ");
            existingPrescription.setDosage(
                    sc.nextLine());

            System.out.print("New Duration: ");
            existingPrescription.setDuration(
                    sc.nextLine());

            System.out.print("New Instructions: ");
            existingPrescription.setInstructions(
                    sc.nextLine());


            prescriptionService
                    .updatePrescription(existingPrescription);

            break;


        // =================================================
        // 6. DELETE PRESCRIPTION
        // =================================================

        case 6:

            System.out.print("Enter Prescription ID: ");
            int deleteId = sc.nextInt();

            prescriptionService
                    .deletePrescription(deleteId);

            break;


        // =================================================
        // 7. BACK
        // =================================================

        case 7:
            return;


        default:
            System.out.println("Invalid choice!");
        }
    }
}}