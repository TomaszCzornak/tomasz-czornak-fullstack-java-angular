package reskilled.mentoring.reskilled.gate;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import reskilled.mentoring.reskilled.user.service.UsersService;

@Component
@Controller
@RequiredArgsConstructor
public class WebSocketController {


    private final UsersService usersService;

    @MessageMapping("/read")
    @SendTo("/topic/events")
    public Event greeting(Event event){
        event.validate();
        boolean ifUserExists = usersService.getActivatedUserByEmail(event.getEmail());

        if (ifUserExists) {
            return event;
        } else {
            return Event.builder().isUserExist(false).build();
        }
    }
}
