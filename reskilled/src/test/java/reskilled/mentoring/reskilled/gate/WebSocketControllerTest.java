package reskilled.mentoring.reskilled.gate;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import reskilled.mentoring.reskilled.user.service.UsersService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
class WebSocketControllerTest {

    @Mock
    SimpMessageSendingOperations messagingOperationsMock;
    @Mock
    private UsersService usersService;
    @InjectMocks
    private WebSocketEventListener listener;
    @Mock
    private WebSocketSession webSocketSession;

    @Test
    public void handleTextMessageShouldInvokeSendMethodIfEventValid() throws Exception {
        //given
        ObjectMapper mapper = new ObjectMapper();
        Event validEvent = new Event("test@email.com", "validEventName");
        String jsonEvent = mapper.writeValueAsString(validEvent);
        //when
        webSocketSession.sendMessage(new TextMessage(jsonEvent));
        listener.handleTextMessage(webSocketSession, new TextMessage(jsonEvent));
        //then
        verify(webSocketSession, times(1)).sendMessage(any(TextMessage.class));
    }

    @Test
    public void handleTextMessageShouldNotInvokeSendMethodIfEventInvalid() throws Exception {
        //given
        String badJsonEvent = "This is not a valid JSON";
        //when
        listener.handleTextMessage(webSocketSession, new TextMessage(badJsonEvent));
        //then
        verify(webSocketSession, times(0)).sendMessage(any(TextMessage.class));
    }


    @Test
    public void testWebSocketController() {
        //given
        Event event = new Event("test@example.com", "Test Event");
        when(usersService.getActivatedUserByEmail(event.getEmail())).thenReturn(true);
        WebSocketController controller = new WebSocketController(usersService, messagingOperationsMock);
        //when
        Event resultEvent = controller.entryGate(event);
        //then
        verify(usersService).getActivatedUserByEmail(event.getEmail());
        assertEquals(event.getEmail(), resultEvent.getEmail());
        assertEquals(event.getEventName(), resultEvent.getEventName());
        assertEquals(true, resultEvent.getIsUserAvailable());
    }
}