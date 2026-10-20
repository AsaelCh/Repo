package Appointment.Exception;

public class InvalidAppointmentException extends RuntimeException {
  public InvalidAppointmentException(String message) {
    super(message);
  }
}
