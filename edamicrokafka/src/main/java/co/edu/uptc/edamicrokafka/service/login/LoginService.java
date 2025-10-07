package co.edu.uptc.edamicrokafka.service.login;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uptc.edamicrokafka.model.login.Login;
import co.edu.uptc.edamicrokafka.repository.LoginRepository;

@Service
public class LoginService {
    @Autowired
    private LoginRepository loginRepository;

    public LoginService(LoginRepository loginRepository){
        this.loginRepository = loginRepository;
    }

    public boolean save(Login login){
        boolean flag = false;
        Login l = loginRepository.saveAndFlush(login);
        if (l != null) flag = true;
        return flag;
    }

    public Login findById(Integer id){
        Login login = null;
        Optional<Login> optionalLogin = loginRepository.findById(id);
        if(optionalLogin.isPresent()){
            login = optionalLogin.get();
        }
        return login;
    }

    public List<Login> findAll(){
        List<Login> listLogin = new ArrayList<Login>();
        Iterable<Login> logins = loginRepository.findAll();
        logins.forEach((o) -> {
            listLogin.add(o);
        });
        return listLogin;
    }
}
