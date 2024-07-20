package reskilled.mentoring.reskilled.gate;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class WebSocketEventListener extends TextWebSocketHandler {

    private Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        String payload = message.getPayload();

        try {
            ObjectMapper mapper = new ObjectMapper();
            Event event = mapper.readValue(payload, Event.class);

            event.validate();

            String employeeLastName = event.getEmail();
            String eventName = event.getEventName();
            String outgoingMessage = employeeLastName;

            sessions.values().stream().forEach(client -> {
                try {
                    client.sendMessage(new TextMessage(outgoingMessage));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

        } catch (Exception e) {
            log.error("Error processing message: ", e);
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.put(session.getId(), session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session.getId());
    }
}
