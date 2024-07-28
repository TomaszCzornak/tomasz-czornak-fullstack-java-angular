package reskilled.mentoring.reskilled.gate;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import reskilled.mentoring.reskilled.user.service.UsersService;
import org.springframework.messaging.simp.SimpMessageSendingOperations;

@Controller
@RequiredArgsConstructor
public class WebSocketController {


    private final UsersService usersService;
    private final SimpMessageSendingOperations messageTemplate;

    @MessageMapping("/read")
    @SendTo("/topic/events")
    public Event entryGate(Event event){
        event.validate();
        boolean ifUserExists = usersService.getActivatedUserByEmail(event.getEmail());

        return ifUserExists ? event : new Event(false);
    }
}
