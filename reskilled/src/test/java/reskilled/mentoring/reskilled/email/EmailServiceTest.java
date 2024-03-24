package reskilled.mentoring.reskilled.email;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.Resource;
import org.springframework.test.util.ReflectionTestUtils;
import reskilled.mentoring.reskilled.registration.model.entity.ResetOperations;
import reskilled.mentoring.reskilled.registration.repository.ResetOperationsRepository;
import reskilled.mentoring.reskilled.registration.service.ResetOperationService;
import reskilled.mentoring.reskilled.user.model.entity.User;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmailServiceTest {

    @Mock
    private EmailConfiguration emailConfiguration;
    @Mock
    private ResetOperationService resetOperationService;

    @InjectMocks
    private EmailService emailService;

    @Mock
    private ResetOperationsRepository repository;

    @Mock
    private Resource activeTemplate, recoveryTemplate;

    private File activateTempFile;
    private File recoveryTempFile;

    @BeforeEach
    void setupRecoveryTemplate() throws IOException {
        File recoveryTempFile = File.createTempFile("tempRecoveryTemplate", ".txt");
        when(recoveryTemplate.getFile()).thenReturn(recoveryTempFile);
    }

    @BeforeEach
    void setupActiveTemplate() throws IOException {
        File activateTempFile = File.createTempFile("tempActiveTemplate", ".txt");
        when(activeTemplate.getFile()).thenReturn(activateTempFile);
    }
    @BeforeEach
    void setUp() {
        emailService = new EmailService(resetOperationService, emailConfiguration);
        ReflectionTestUtils.setField(emailService, "activeTemplate", activeTemplate);
        ReflectionTestUtils.setField(emailService, "recoveryTemplate", recoveryTemplate);
        ReflectionTestUtils.setField(emailService, "frontendUrl", "http://localhost:8080");

    }

    @AfterEach
    void cleanupTempFiles() {
        if (activeTemplate != null) {
            activateTempFile.delete();
        }
        if (recoveryTemplate != null) {
            recoveryTempFile.delete();
        }
    }

    @Test
    void shouldSendActivationEmailSuccessfully() throws IOException {
        //given
        User user = new User();
        user.setEmail("test@test.com");
        user.setUuid("123e4567-e89b-12d3-a456-426614174000");
        //when
        setupActiveTemplate();
        emailService.sendMail(user, true);
        //then
        verify(emailConfiguration, times(1)).sendMail(anyString(), anyString(), anyString(), anyBoolean());
    }

    @Test
    void shouldSendRecoveryEmailSuccessfully() throws IOException {
        //given
        User user = new User();
        user.setEmail("test@test.com");
        user.setUuid("123e4567-e89b-12d3-a456-426614174000");
        //when
        setupRecoveryTemplate();
        emailService.sendMail(user, false);
        //then
        verify(emailConfiguration, times(1)).sendMail(anyString(), anyString(), anyString(), anyBoolean());
    }

    @Test
    void shouldVerifyIfMailSent() throws IOException {
        User user = new User();
        user.setEmail("test@example.com");
        user.setUuid("123e4567-e89b-12d3-a456-426614174000");

        // Mock the behavior of resetOperationService
        when(resetOperationService.initResetOperation(user)).thenReturn(new ResetOperations());

        // Call the method under test
        emailService.sendMail(user, true);

        verify(emailConfiguration, times(1)).sendMail(anyString(), anyString(), anyString(), anyBoolean());

    }

    @Test
    void shouldThrowRuntimeExceptionWhenSendingRecoveryEmail() throws IOException {
        // given
        User user = new User();
        user.setEmail("test@example.com");
        user.setUuid("123e4567-e89b-12d3-a456-426614174000");
        // when
        doThrow(new IOException("Mock IOException")).when(recoveryTemplate).getFile();
        // then
        Exception exception = assertThrows(IOException.class, ()-> emailService.sendMail(user, false));
        assertInstanceOf(IOException.class, exception.getCause());
    }

    @Test
    void shouldThrowRuntimeExceptionWhenSendingActivationEmail() throws IOException {
        // given
        User user = new User();
        user.setEmail("test@example.com");
        user.setUuid("123e4567-e89b-12d3-a456-426614174000");

        // when
        doThrow(new IOException("Mock IOException")).when(activeTemplate).getFile();

        // then
        Exception exception = assertThrows(IOException.class, () -> emailService.sendMail(user, true));
        assertInstanceOf(IOException.class, exception.getCause());
    }
}