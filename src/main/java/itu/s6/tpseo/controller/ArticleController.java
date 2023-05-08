package itu.s6.tpseo.controller;

import itu.s6.tpseo.framework.springutils.controller.CrudController;
import itu.s6.tpseo.model.Article;
import itu.s6.tpseo.model.filter.ArticleFilter;
import itu.s6.tpseo.service.ArticleService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static itu.s6.tpseo.framework.springutils.util.ControllerUtil.returnSuccess;

@RestController
@RequestMapping("/article")
public class ArticleController extends CrudController<Article, ArticleService, ArticleFilter> {

    public ArticleController(ArticleService service) {
        super(service);
    }

    @PostMapping("")
    @CacheEvict(value = "articles", key = "'articles'")
    public ResponseEntity<?> create(@RequestBody Article obj) throws Exception {
        return returnSuccess(service.create(obj), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Cacheable(value = "article", key = "#id")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) throws Exception {
        return returnSuccess(service.findById(id), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> findAll(ArticleFilter filter, @RequestParam(required = false) Integer page, @RequestParam(required = false) Object order) throws Exception {
        return returnSuccess(service.search(filter, page, order), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @CachePut(value = "article", key = "#id")
    @CacheEvict(value = "articles", key = "'articles'")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Article obj) throws Exception {
        obj.setId(id);
        return returnSuccess(service.update(obj), HttpStatus.OK);
    }
}
