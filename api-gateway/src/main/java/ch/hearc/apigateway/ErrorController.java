package ch.hearc.apigateway;

import ch.hearc.apigateway.model.Error401ResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ErrorController {

    @GetMapping("error401/{path}")
    public ResponseEntity<Error401ResponseBody> error401(@PathVariable String path){

        return ResponseEntity.status(401).body(new Error401ResponseBody(path));
    }
}
