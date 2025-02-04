package gr.aueb.cafe.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Contains generic methods, which can be used
 * from any service classes
 */
public class CafeUtils {

    private CafeUtils(){

    }

    public static ResponseEntity<String> getResponseEntity(String responseMessage , HttpStatus httpStatus){
        return new ResponseEntity<String>("{\"message\":\"" +responseMessage +"\"}", httpStatus);
    }
}
