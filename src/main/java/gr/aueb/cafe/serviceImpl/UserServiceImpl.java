package gr.aueb.cafe.serviceImpl;

import gr.aueb.cafe.DAO.UserDAO;
import gr.aueb.cafe.constants.CafeConstants;
import gr.aueb.cafe.model.User;
import gr.aueb.cafe.service.IUserService;
import gr.aueb.cafe.utils.CafeUtils;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import java.util.Map;
import java.util.Objects;


/**
 * First of all needs to validate the signUp map , using a private method
 */

@Service
public class UserServiceImpl implements IUserService {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserDAO userDAO;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        LOGGER.info("Inside signup {}", requestMap);

        try {
            if (validateSignUpMap(requestMap)) {

                User user = userDAO.findByEmailId(requestMap.get("email"));
                if (Objects.isNull(user)) {
                    userDAO.save(getUserFromMap(requestMap));
                    return CafeUtils.getResponseEntity("Successfully registered", HttpStatus.OK);
                } else {
                    return CafeUtils.getResponseEntity("Email already exist", HttpStatus.BAD_REQUEST);
                }
            } else {
                return CafeUtils.getResponseEntity(CafeConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG , HttpStatus.INTERNAL_SERVER_ERROR);
    }
    private boolean validateSignUpMap(Map<String,String> requestMap){
        if(requestMap.containsKey("name") && requestMap.containsKey("contactNumber")
                                          && requestMap.containsKey("email")
                                          && requestMap.containsKey("password")){
            return true;
        }
        return false;
    }

    private User getUserFromMap(Map<String, String> requestMap) {
        User user = new User();
        user.setName(requestMap.get("name"));
        user.setContactNumber(requestMap.get("contactNumber"));
        user.setEmail(requestMap.get("email"));
        user.setPassword(requestMap.get("password"));
        user.setStatus(requestMap.get("false"));
        user.setRole(requestMap.get("user"));
        return user;
    }
}
