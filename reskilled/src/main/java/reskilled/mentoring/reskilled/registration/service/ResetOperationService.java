package reskilled.mentoring.reskilled.registration.service;


import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import reskilled.mentoring.reskilled.registration.model.entity.ResetOperations;
import reskilled.mentoring.reskilled.registration.repository.ResetOperationsRepository;
import reskilled.mentoring.reskilled.user.model.entity.User;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
@EnableScheduling
@Slf4j
public class ResetOperationService {


    private ResetOperationsRepository resetOperationsRepository;
    @Autowired
    public ResetOperationService(ResetOperationsRepository resetOperationsRepository) {
        this.resetOperationsRepository = resetOperationsRepository;
    }

    public ResetOperationService() {
    }

    @Transactional
    public ResetOperations initResetOperation(User user){
        ResetOperations resetOperations = new ResetOperations();

        resetOperations.setUuid(UUID.randomUUID().toString());
        resetOperations.setCreateDate(new Timestamp(System.currentTimeMillis()).toString());
        resetOperations.setUser(user);

        resetOperationsRepository.deleteAllByUser(user);
        return resetOperationsRepository.saveAndFlush(resetOperations);
    }

    public void endOperation(String uid){
        resetOperationsRepository.findByUuid(uid).ifPresent(resetOperationsRepository::delete);
    }

    @Scheduled(cron = "0 0/1 * * * *")
    protected void deleteExpireOperation(){
      List<ResetOperations> resetOperations = resetOperationsRepository.findExpiredOperations();
      log.info("Find {} expired operations to delete",resetOperations.size());
      if (resetOperations != null && !resetOperations.isEmpty()){
          resetOperationsRepository.deleteAll(resetOperations);
      }
    }

}
