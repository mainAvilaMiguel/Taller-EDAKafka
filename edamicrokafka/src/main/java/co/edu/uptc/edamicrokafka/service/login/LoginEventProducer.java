package co.edu.uptc.edamicrokafka.service.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import co.edu.uptc.edamicrokafka.model.login.Login;
import co.edu.uptc.edamicrokafka.model.login.LoginEvent;
import co.edu.uptc.edamicrokafka.model.login.EventLoginType;
import co.edu.uptc.edamicrokafka.utils.JsonUtils;

@Service
public class LoginEventProducer {
    private static final String TOPIC = "login_events";
    
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    private JsonUtils jsonUtils = new JsonUtils();

    private void sendLoginEvent(EventLoginType eventType, String data) {
        LoginEvent event = new LoginEvent();
        event.setEventType(eventType);
        event.setData(data);
        kafkaTemplate.send(TOPIC, jsonUtils.toJson(event));
    }

    public void sendAddLoginEvent(Login login) {
        sendLoginEvent(EventLoginType.ADD_LOGIN, jsonUtils.toJson(login));
    }

    public void sendEditLoginEvent(Login login) {
        sendLoginEvent(EventLoginType.EDIT_LOGIN, jsonUtils.toJson(login));
    }

    public void sendFindByLoginIDEvent(String id) {
        sendLoginEvent(EventLoginType.FIND_LOGIN_BY_ID, id);
    }

    public void sendFindAllLoginsEvent() {
        sendLoginEvent(EventLoginType.FIND_ALL_LOGINS, "");
    }
}