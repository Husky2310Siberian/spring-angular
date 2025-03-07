package gr.aueb.cafe.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RequestMapping(path = "/user")
public interface IUserRest {

    @PostMapping(path = "/signup")
    public ResponseEntity<String> signUp(@RequestBody Map<String , String> requestMap);
}
