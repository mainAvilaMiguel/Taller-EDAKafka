package co.edu.uptc.edamicrokafka.service.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import co.edu.uptc.edamicrokafka.model.login.Login;
import co.edu.uptc.edamicrokafka.model.login.LoginEvent;
import co.edu.uptc.edamicrokafka.utils.JsonUtils;
import java.util.List;

@Service
public class LoginEventConsumer {

    @Autowired
    private LoginService loginService;
    private JsonUtils jsonUtils = new JsonUtils();

    @KafkaListener(topics = "login_events", groupId = "login_group")
    public void handleLoginEvents(String eventMessage) {
        LoginEvent event = jsonUtils.fromJson(eventMessage, LoginEvent.class);
        
        switch (event.getEventType()) {
            case ADD_LOGIN:
                Login loginToAdd = jsonUtils.fromJson(event.getData(), Login.class);
                loginService.save(loginToAdd);
                break;
                
            case EDIT_LOGIN:
                Login loginToEdit = jsonUtils.fromJson(event.getData(), Login.class);
                loginService.save(loginToEdit);
                break;
                
            case FIND_LOGIN_BY_ID:
                Integer idToFind = Integer.parseInt(event.getData());
                Login foundLogin = loginService.findById(idToFind);
                break;
                
            case FIND_ALL_LOGINS:
                List<Login> logins = loginService.findAll();
                break;
        }
    }
}