package reskilled.mentoring.reskilled.registration.service;

import lombok.RequiredArgsConstructor;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reskilled.mentoring.reskilled.email.EmailService;
import reskilled.mentoring.reskilled.registration.model.entity.ResetOperations;
import reskilled.mentoring.reskilled.registration.model.request.ChangePasswordData;
import reskilled.mentoring.reskilled.registration.repository.RegistrationRepository;
import reskilled.mentoring.reskilled.registration.repository.ResetOperationsRepository;
import reskilled.mentoring.reskilled.security.Role;
import reskilled.mentoring.reskilled.user.exceptions.UserNotFoundException;
import reskilled.mentoring.reskilled.user.model.entity.User;
import reskilled.mentoring.reskilled.user.service.UsersService;

import java.io.IOException;


@Service
@RequiredArgsConstructor
public class RegistrationService {


    private final RegistrationRepository registrationRepository;
    private final UsersService usersService;
    private final ResetOperationService resetOperationService;
    private final EmailService emailService;
    private final ResetOperationsRepository resetOperationsRepository;
    private final PasswordEncoder passwordEncoder;

    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return registrationRepository.save(user);
    }

    public void activateUser(String uid) throws UserNotFoundException {
        User user = usersService.getUserByUuid(uid).orElse(null);
        if (user != null) {
            user.setLock(false);
            user.setEnabled(true);
            user.setRole(Role.USER);
            usersService.saveUser(user);
            return;
        }
        throw new UserNotFoundException();
    }

    public void recoveryPassword(String email) throws UserNotFoundException, IOException {
        User user = usersService.getUsersByEmail(email).orElse(null);
        if (user != null) {

            emailService.sendMail(user, false);
            return;
        }
        throw new UserNotFoundException();
    }

    public void resetPassword(ChangePasswordData changePasswordData) throws UserNotFoundException {
        ResetOperations resetOperations = resetOperationsRepository.findByUuid(changePasswordData.getUuid()).orElse(null);
        if (resetOperations != null) {
            User user = usersService.getUserByUuid(resetOperations.getUser().getUuid()).orElse(null);

            if (user != null) {
                user.setPassword(changePasswordData.getPassword());
                register(user);
                resetOperationService.endOperation(resetOperations.getUuid());
                return;
            }
        }
        throw new UserNotFoundException();
    }

}