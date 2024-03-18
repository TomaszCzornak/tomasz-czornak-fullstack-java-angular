package reskilled.mentoring.reskilled.email;


import com.google.common.io.Files;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import reskilled.mentoring.reskilled.user.model.entity.User;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final EmailConfiguration emailConfiguration;

    @Value("${front.url}")
    private String frontendUrl;

    @Value("classpath:static/mail-aktywuj.html")
    private Resource activeTemplate;
    @Value("classpath:static/resetuj-haslo.html")
    private Resource recoveryTemplate;

    public void sendActivation(User user) {
        log.info("--START sendActivation");
        try {
            String html = Files.toString(activeTemplate.getFile(), StandardCharsets.UTF_8);
            html = html.replace("https://google.com", frontendUrl + "/v1/activate?uuid=" + user.getUuid());
            log.info("aktywacja użytkownika "+user.getUuid());
            emailConfiguration.sendMail(user.getEmail(), html, "Aktywacja konta", true);
        } catch (IOException e) {
            log.info("Cant send mail");
            throw new RuntimeException(e);
        }
        log.info("--STOP sendActivation");
    }

    public void sendPasswordRecovery(User user, String uuid) {
        try {
            log.info("--START sendPasswordRecovery");
            String html = Files.toString(recoveryTemplate.getFile(), StandardCharsets.UTF_8);
            html = html.replace("https://google.com", frontendUrl + "/v1/reset-password/" + uuid);
            emailConfiguration.sendMail(user.getEmail(), html, "Odzyskanie hasła", true);
        } catch (IOException e) {
            log.info("Cant send mail");
            throw new RuntimeException(e);
        }
        log.info("--STOP sendPasswordRecovery");
    }
}
