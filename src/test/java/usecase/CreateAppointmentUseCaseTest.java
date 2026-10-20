
package usecase;

import Appointment.Exception.AppointmentAlreadyExistsException;
import Appointment.Exception.PersistenceException;
import Appointment.model.Appointment;
import Appointment.output.AppointmentRepository;
import Appointment.usecase.CreateAppointmentUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateAppointmentUseCaseUnitTest {

    @Mock
    private AppointmentRepository mockAppointmentRepository;

    @Test
    public void register_success_returnAppointment() {
        // Arrange
        CreateAppointmentUseCase createAppointmentUseCase =
                new CreateAppointmentUseCase(mockAppointmentRepository);

        when(mockAppointmentRepository.existsAppointment(
                "7e8ad860-716d-4b86-9643-430870caefe7"))
                .thenReturn(false);

        when(mockAppointmentRepository.saveAppointment(any(Appointment.class)))
                .thenReturn(true);

        // Act
        Appointment result = createAppointmentUseCase.register(
                UUID.fromString("7e8ad860-716d-4b86-9643-430870caefe7"),
                "Juan Perez",
                "Dr. Lopez",
                "Cardiologia",
                LocalDate.of(2026, 11, 10)
        );

        // Assert
        Assertions.assertNotNull(result);
        verify(mockAppointmentRepository).saveAppointment(any(Appointment.class));
    }

    @Test
    public void register_alreadyExists_AppointmentAlreadyExistsException() {
        // Arrange
        CreateAppointmentUseCase createAppointmentUseCase =
                new CreateAppointmentUseCase(mockAppointmentRepository);

        when(mockAppointmentRepository.existsAppointment(
                "7e8ad860-716d-4b86-9643-430870caefe7"))
                .thenReturn(true);

        // Act & Assert
        Assertions.assertThrows(
                AppointmentAlreadyExistsException.class,
                () -> createAppointmentUseCase.register(
                        UUID.fromString("7e8ad860-716d-4b86-9643-430870caefe7"),
                        "Juan Perez",
                        "Dr. Lopez",
                        "Cardiologia",
                        LocalDate.of(2026, 11, 10)
                )
        );

        verify(mockAppointmentRepository, never())
                .saveAppointment(any(Appointment.class));
    }

    @Test
    public void register_saveReturnsFalse_PersistenceException() {
        // Arrange
        CreateAppointmentUseCase createAppointmentUseCase =
                new CreateAppointmentUseCase(mockAppointmentRepository);

        when(mockAppointmentRepository.existsAppointment(
                "7e8ad860-716d-4b86-9643-430870caefe7"))
                .thenReturn(false);

        when(mockAppointmentRepository.saveAppointment(any(Appointment.class)))
                .thenReturn(false);

        // Act & Assert
        Assertions.assertThrows(
                PersistenceException.class,
                () -> createAppointmentUseCase.register(
                        UUID.fromString("7e8ad860-716d-4b86-9643-430870caefe7"),
                        "Juan Perez",
                        "Dr. Lopez",
                        "Cardiologia",
                        LocalDate.of(2026, 11, 10)
                )
        );
    }
}
