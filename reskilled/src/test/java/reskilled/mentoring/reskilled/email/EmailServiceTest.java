package reskilled.mentoring.reskilled.email;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ActiveProfiles;
import reskilled.mentoring.reskilled.registration.model.entity.ResetOperations;
import reskilled.mentoring.reskilled.registration.service.ResetOperationService;
import reskilled.mentoring.reskilled.user.model.entity.User;

import java.io.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
class EmailServiceTest {


    @Mock(name = "activeTemplate")
    private ClassPathResource mockActiveTemplate;

    @Mock(name = "recoveryTemplate")
    private ClassPathResource mockRecoveryTemplate;


    @Test
    void testSendActivationEmail() throws IOException {
        // Mock user object
        User user = new User();
        user.setEmail("test@example.com");
        user.setUuid("123e4567-e89b-12d3-a456-426614174000");
        File sampleFile = new File("sample_file.txt");
        try (Writer writer = new BufferedWriter(new FileWriter(sampleFile))) {
            writer.write("Sample template content");
        }
        when(mockActiveTemplate.getFile()).thenReturn(sampleFile);


        ResetOperationService resetOperationService = mock(ResetOperationService.class);

        // Mock email configuration
        EmailConfiguration emailConfiguration = mock(EmailConfiguration.class);

        // Set expectations for collaborators
        Mockito.doNothing().when(emailConfiguration).sendMail(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyBoolean());

        // Inject mocks
        EmailService emailService = new EmailService(resetOperationService, emailConfiguration);
        emailService.setActiveTemplate(mockActiveTemplate);

        // Call the method
        emailService.sendMail(user, true);

        // Verify interactions
        Mockito.verify(emailConfiguration).sendMail(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyBoolean());
    }

    @Test
    void testSendRecoveryEmail() throws IOException {
        // Mock user object
        User user = new User();
        user.setEmail("test@example.com");
        user.setUuid("123e4567-e89b-12d3-a456-426614174000");
        File sampleFile = new File("sample_file.txt");
        try (Writer writer = new BufferedWriter(new FileWriter(sampleFile))) {
            writer.write("Sample template content");
        }
        when(mockRecoveryTemplate.getFile()).thenReturn(sampleFile);


        ResetOperationService resetOperationService = mock(ResetOperationService.class);
        ResetOperations resetOperations = new ResetOperations();
        resetOperations.setUuid("reset-uuid");
        Mockito.when(resetOperationService.initResetOperation(user)).thenReturn(resetOperations);
        // Mock email configuration
        EmailConfiguration emailConfiguration = mock(EmailConfiguration.class);

        // Set expectations for collaborators
        Mockito.doNothing().when(emailConfiguration).sendMail(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyBoolean());

        // Inject mocks
        EmailService emailService = new EmailService(resetOperationService, emailConfiguration);
        emailService.setRecoveryTemplate(mockRecoveryTemplate);

        // Call the method
        emailService.sendMail(user, false);

        // Verify interactions
        Mockito.verify(emailConfiguration).sendMail(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyBoolean());
    }

}