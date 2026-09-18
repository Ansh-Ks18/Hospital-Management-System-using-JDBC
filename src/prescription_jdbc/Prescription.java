package prescription_jdbc;

public class Prescription {

    private int prescriptionId;
    private int patientId;
    private int doctorId;
    private int appointmentId;
    private String medicineName;
    private String dosage;
    private String duration;
    private String instructions;

    public Prescription(int prescriptionId, int patientId, int doctorId,
                        int appointmentId, String medicineName,
                        String dosage, String duration,
                        String instructions) {

        this.prescriptionId = prescriptionId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentId = appointmentId;
        this.medicineName = medicineName;
        this.dosage = dosage;
        this.duration = duration;
        this.instructions = instructions;
    }

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public int getPatientId() {
        return patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public String getDosage() {
        return dosage;
    }

    public String getDuration() {
        return duration;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    @Override
    public String toString() {

        return "Prescription ID : " + prescriptionId +
               "\nPatient ID      : " + patientId +
               "\nDoctor ID       : " + doctorId +
               "\nAppointment ID  : " + appointmentId +
               "\nMedicine        : " + medicineName +
               "\nDosage          : " + dosage +
               "\nDuration        : " + duration +
               "\nInstructions    : " + instructions;
    }
}