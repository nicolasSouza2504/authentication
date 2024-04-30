package org.services.login;

import com.google.gson.Gson;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.CharSet;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.dto.Session;
import org.model.UserLogin;
import org.repositories.UserRepository;
import org.services.redis.RedisService;
import org.utils.UtilErrorRest;

import java.util.UUID;

@ApplicationScoped
public class LoginService {

    @Inject
    UserRepository userRepository;

    @Inject
    RedisService redisService;

    @Transactional
    public Session login(String jsonUser) {

        UserLogin userLogin = new Gson().fromJson(jsonUser, UserLogin.class);

        validateLogin(userLogin);

        UserLogin userDb = userRepository.findByUserName(userLogin.getUserName());

        if (userDb != null) {

            Boolean rigthPassword = validatePassword(userLogin, userDb);

            if (rigthPassword) {

                Session session = new Session(UUID.randomUUID().toString(), userDb.getUserName());

                redisService.set(session.getAuthToken(), new Gson().toJson(session));

                return session;

            } else {
                UtilErrorRest.throwResponseError("Wrong password");
            }

        } else {
            UtilErrorRest.throwResponseError("User Not Found", 404);
        }

        return null;

    }

    private Boolean validatePassword(UserLogin userLogin, UserLogin userDb) {

        String salt = userDb.getSaltPassword();

        String concatenedPassSalt = userLogin.getPassword() + salt;

        String hashedPasswordLogin = DigestUtils.md5Hex(concatenedPassSalt);

        return hashedPasswordLogin.equalsIgnoreCase(userDb.getPassword());

    }

    private void validateLogin(UserLogin userLogin) {

        if (userLogin != null) {

            if (StringUtils.isEmpty(userLogin.getPassword())) {
                UtilErrorRest.throwResponseError("Password is required");
            }

            if (StringUtils.isEmpty(userLogin.getUserName())) {
                UtilErrorRest.throwResponseError("User name is required");
            }

        } else {
            UtilErrorRest.throwResponseError("Password and name are required");
        }

    }

}
