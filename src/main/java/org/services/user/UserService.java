package org.services.user;

import com.google.gson.Gson;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.model.UserLogin;
import org.repositories.UserRepository;
import org.utils.UtilErrorRest;

@ApplicationScoped
public class UserService {

    @Inject
    EntityManager entityManager;

    @Inject
    UserRepository userRepository;

    @Transactional
    public UserLogin save(String jsonUser) {

        UserLogin userLogin = new Gson().fromJson(jsonUser, UserLogin.class);

        validateUser(userLogin);

        setSaltAndEncryptedPassword(userLogin);

        entityManager.persist(userLogin);

        return userLogin;

    }

    @Transactional
    public void setSaltAndEncryptedPassword(UserLogin userLogin) {

        userLogin.setSaltPassword(RandomStringUtils.random(8));

        String concatenedPassSalt = userLogin.getPassword() + userLogin.getSaltPassword();

        userLogin.setPassword(DigestUtils.md5Hex(concatenedPassSalt));

    }

    @Transactional
    public void validateUser(UserLogin userLogin) {

        if (userLogin != null) {

            if (StringUtils.isEmpty(userLogin.getPassword())) {
                UtilErrorRest.throwResponseError("Password is required");
            }

            if (StringUtils.isEmpty(userLogin.getUserName())) {
                UtilErrorRest.throwResponseError("User name is required");
            }

            UserLogin userSaved = userRepository.findByUserName(userLogin.getUserName());

            if (userSaved != null) {
                UtilErrorRest.throwResponseError("User already exists");
            }

        } else {
            UtilErrorRest.throwResponseError("Password and name are required");
        }

    }

}
