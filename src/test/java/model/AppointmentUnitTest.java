package model;

import Appointment.Exception.InvalidAppointmentException;
import Appointment.model.Appointment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

public class AppointmentUnitTest {

    @Test
    public void instanceAppointment_validFields_success() {
        // Arrange
        UUID id = UUID.fromString(
                "7e8ad860-716d-4b86-9643-430870caefe7"
        );

        // Act
        Appointment appointment = Appointment.instance(
                id,
                "Juan Perez",
                "Robert Smith",
                "Cardiology",
                LocalDate.of(2026, 11, 10)
        );

        // Assert
        Assertions.assertNotNull(appointment);
        Assertions.assertEquals(id, appointment.getId());
        Assertions.assertEquals("Juan Perez", appointment.getPatient());
        Assertions.assertEquals("Robert Smith", appointment.getDoctor());
        Assertions.assertEquals("Cardiology", appointment.getSpecialty());
        Assertions.assertEquals(
                LocalDate.of(2026, 11, 10),
                appointment.getDate()
        );
    }


    @Test
    public void instanceAppointment_nullPatient_InvalidAppointmentException() {
        // Arrange
        UUID id = UUID.fromString(
                "7e8ad860-716d-4b86-9643-430870caefe7"
        );

        // Act & Assert
        Assertions.assertThrows(
                InvalidAppointmentException.class,
                () -> Appointment.instance(
                        id,
                        null,
                        "Robert Smith",
                        "Cardiology",
                        LocalDate.of(2026, 11, 10)
                )
        );
    }


    @Test
    public void instanceAppointment_nullDate_InvalidAppointmentException() {
        // Arrange
        UUID id = UUID.fromString(
                "7e8ad860-716d-4b86-9643-430870caefe7"
        );

        // Act & Assert
        Assertions.assertThrows(
                InvalidAppointmentException.class,
                () -> Appointment.instance(
                        id,
                        "Juan Perez",
                        "Robert Smith",
                        "Cardiology",
                        null
                )
        );
    }

    @Test
    public void instanceAppointment_nullDoctor_InvalidAppointmentException() {
        Assertions.assertThrows(
                InvalidAppointmentException.class,
                () -> Appointment.instance(
                        UUID.randomUUID(),
                        "Juan Perez",
                        null,
                        "Cardiologia",
                        LocalDate.of(2026, 11, 10)
                )
        );
    }


}
