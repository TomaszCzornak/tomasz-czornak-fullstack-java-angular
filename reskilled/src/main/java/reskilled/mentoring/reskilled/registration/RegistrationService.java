package reskilled.mentoring.reskilled.registration;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import reskilled.mentoring.reskilled.email.EmailService;
import reskilled.mentoring.reskilled.user.exceptions.UserNotFoundException;
import reskilled.mentoring.reskilled.user.model.entity.User;
import reskilled.mentoring.reskilled.user.service.UsersService;


@Service
@RequiredArgsConstructor
public class RegistrationService {


    private final RegistrationRepository registrationRepository;
    private final UsersService usersService;
    private final ResetOperationService resetOperationService;
    private final EmailService emailService;
    private final ResetOperationsRepository resetOperationsRepository;

    public User register(User user) {
        user.setPassword(hashPassword(user.getPassword()));
        return registrationRepository.save(user);
    }

    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public void activateUser(String uid) throws UserNotFoundException {
        User user = usersService.getUserByUuid(uid).orElse(null);
        if (user != null) {
            user.setLock(false);
            user.setEnabled(true);
            usersService.saveUser(user);
            return;
        }
        throw new UserNotFoundException();
    }

    public void recoveryPassword(String email) throws UserNotFoundException {
        User user = usersService.getUsersByEmail(email).orElse(null);
        if (user != null) {
            ResetOperations resetOperations = resetOperationService.initResetOperation(user);
            emailService.sendPasswordRecovery(user, resetOperations.getUuid());
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