package gr.aueb.cafe.restImpl;

import gr.aueb.cafe.constants.CafeConstants;
import gr.aueb.cafe.rest.IUserRest;
import gr.aueb.cafe.serviceImpl.UserServiceImpl;
import gr.aueb.cafe.utils.CafeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserRestImpl implements IUserRest {

    @Autowired
    UserServiceImpl userService;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {

        try{
            return userService.signUp(requestMap);
        } catch (Exception e){
            e.printStackTrace();
        }
          return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
