package org.services.login;

import com.google.gson.Gson;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import org.model.UserLogin;
import org.repositories.UserRepository;

@ApplicationScoped
public class LoginService {

    @Inject
    UserRepository userRepository;

    @Transactional
    public void login(String jsonUser) {

        UserLogin user = new Gson().fromJson(jsonUser, UserLogin.class);

        if (user != null) {

            user = userRepository.findByUserName(user.getUserName());

            if (user != null) {

                if (user.getPassword().equals(user.getPassword())) {

                    System.out.println("LOGED IN");

                } else {
                    throw new WebApplicationException("WRONG PASSWORD", 500);
                }

            } else {
                throw new WebApplicationException("USER NOT FOUND", 404);
            }

        }

    }

}
