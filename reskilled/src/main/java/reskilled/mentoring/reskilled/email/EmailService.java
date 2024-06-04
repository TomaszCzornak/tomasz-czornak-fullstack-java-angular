package reskilled.mentoring.reskilled.email;

import com.google.common.io.Files;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import reskilled.mentoring.reskilled.registration.model.entity.ResetOperations;
import reskilled.mentoring.reskilled.registration.service.ResetOperationService;
import reskilled.mentoring.reskilled.user.model.entity.User;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
@Slf4j
@RequiredArgsConstructor
@Setter
public class EmailService {

    public static final String ACTIVATION_URL = "/v1/activate?uuid=";
    public static final String RESET_PASSWORD = "/v1/reset-password/{uuid}";
    public static final String GOOGLE_URL = "https://google.com";

    private final ResetOperationService resetOperationService;

    private final EmailConfiguration emailConfiguration;


    @Value("${front.url}")
    private String frontendUrl;


    @Value("classpath:static/mail-aktywuj.html")
    private Resource activeTemplate;
    @Value("classpath:static/resetuj-haslo.html")
    private Resource recoveryTemplate;

    public void sendMail(User user, boolean isActivationMail) throws IOException {
        try {
            log.info("--START sendMail");
            Resource template = isActivationMail ? activeTemplate : recoveryTemplate;
            ResetOperations resetOperations = resetOperationService.initResetOperation(user);
            String actionPath = isActivationMail ? ACTIVATION_URL + user.getUuid() : RESET_PASSWORD;
            if (!isActivationMail) {
                actionPath = actionPath.replace("{uuid}", resetOperations.getUuid());
            }
            String subject = isActivationMail ? "Aktywacja konta" : "Odzyskanie hasła";

            String link = frontendUrl + actionPath;
            String html = Files.toString(template.getFile(), StandardCharsets.UTF_8);
            html = html.replace(GOOGLE_URL, link);

            emailConfiguration.sendMail(user.getEmail(), html, subject, true);
            log.info("{} for user {}", subject, user.getUuid());

        } catch (IOException e) {
            log.info("Cant send mail");
            throw new IOException(e);
        }
        log.info("--STOP sendMail");
    }


}