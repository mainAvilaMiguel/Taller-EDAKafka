package co.edu.uptc.edamicrokafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import co.edu.uptc.edamicrokafka.model.login.Login;
import co.edu.uptc.edamicrokafka.service.login.LoginEventProducer;
import co.edu.uptc.edamicrokafka.utils.JsonUtils;

@RestController
public class LoginController {
    @Autowired
    private LoginEventProducer loginEventProducer;
    private JsonUtils jsonUtils = new JsonUtils();

   @PostMapping("/addLogin")
    public String createLogin(@RequestBody String loginJson) {
        Login login = jsonUtils.fromJson(loginJson, Login.class);
        loginEventProducer.sendAddLoginEvent(login);
        return "Creación de login enviada";
    }

    @PostMapping("/editLogin")
    public String updateLogin(@RequestBody String loginJson) {
        Login login = jsonUtils.fromJson(loginJson, Login.class);
        loginEventProducer.sendEditLoginEvent(login);
        return "Actualización de inicio de sesión enviada";
    }

   @GetMapping("/login/{customerId}")
    public String findLogin(@PathVariable String customerId) {
        loginEventProducer.sendFindByLoginIDEvent(customerId);
        return "Petición de información de login enviada";
    }
       @GetMapping("/logins")
    public String findAllOrders() {
        loginEventProducer.sendFindAllLoginsEvent();
        return "Solicitud de todos los logins enviada";
    }
}
