package itu.s6.tpseo.controller;

import itu.s6.tpseo.framework.springutils.controller.CrudController;
import itu.s6.tpseo.model.Author;
import itu.s6.tpseo.service.AuthorService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static itu.s6.tpseo.framework.springutils.util.ControllerUtil.returnSuccess;

@RestController
@RequestMapping("/author")
public class AuthorController extends CrudController<Author, AuthorService,Object> {

    public AuthorController(AuthorService service) {
        super(service);
    }

    @GetMapping("")
    @Cacheable(value = "authors", key = "'authors'")
    public ResponseEntity<?> findAll(Object filter, @RequestParam(required = false) Integer page, @RequestParam(required = false) Object order) throws Exception {
        return returnSuccess(service.search(filter, page,order), HttpStatus.OK);
    }
}
