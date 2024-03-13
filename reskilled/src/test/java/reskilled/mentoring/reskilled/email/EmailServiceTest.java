package reskilled.mentoring.reskilled.email;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.Resource;
import org.springframework.test.util.ReflectionTestUtils;
import reskilled.mentoring.reskilled.user.model.entity.User;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmailServiceTest {

    @Mock
    private EmailConfiguration emailConfiguration;

    private EmailService emailService;

    @Mock
    private Resource activeTemplate, recoveryTemplate;

    void setupRecoveryTemplate() throws IOException {
        File tempFile = File.createTempFile("tempRecoveryTemplate", ".txt");
        when(recoveryTemplate.getFile()).thenReturn(tempFile);
    }

    void setupActiveTemplate() throws IOException {
        File tempFile = File.createTempFile("tempActiveTemplate", ".txt");
        when(activeTemplate.getFile()).thenReturn(tempFile);
    }
    @BeforeEach
    void setUp() {
        emailService = new EmailService(emailConfiguration);
        ReflectionTestUtils.setField(emailService, "activeTemplate", activeTemplate);
        ReflectionTestUtils.setField(emailService, "recoveryTemplate", recoveryTemplate);
        ReflectionTestUtils.setField(emailService, "frontendUrl", "http://localhost:8080");

    }

    @Test
    void shouldSendActivationEmailSuccessfully() throws IOException {
        //given
        User user = new User();
        user.setEmail("test@test.com");
        user.setUuid("123e4567-e89b-12d3-a456-426614174000");
        //when
        setupActiveTemplate();
        emailService.sendActivation(user);
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
        emailService.sendPasswordRecovery(user, user.getUuid());
        //then
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
        Exception exception = assertThrows(RuntimeException.class, ()-> emailService.sendPasswordRecovery(user, user.getUuid()));
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
        Exception exception = assertThrows(RuntimeException.class, () -> emailService.sendActivation(user));
        assertInstanceOf(IOException.class, exception.getCause());    }
}