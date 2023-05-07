package itu.s6.tpseo.controller;

import itu.s6.tpseo.framework.springutils.controller.LoginController;
import itu.s6.tpseo.model.Author;
import itu.s6.tpseo.service.AuthorLoginService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static itu.s6.tpseo.framework.springutils.util.ControllerUtil.returnSuccess;

@RestController
@RequestMapping("/author")
public class AuthorLoginController extends LoginController<Author, AuthorLoginService> {
    public AuthorLoginController(AuthorLoginService service) {
        super(service);
    }

    @Override
    public String getRequestHeaderKey() {
        return "Authorization";
    }

    @PostMapping("/login")
    @CacheEvict(value = "author", key = "'author'")
    public ResponseEntity<?> login(@RequestBody Author entity) throws Exception {
//        return ResponseEntity.ok("Mety");
        return returnSuccess(service.login(entity), HttpStatus.OK);
    }
}
