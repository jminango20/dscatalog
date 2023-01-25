package com.jminango.dscatalog.resources;

import com.jminango.dscatalog.entities.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

    @GetMapping
    public ResponseEntity<List<Category>> findAll(){
        List<Category> listCategory = new ArrayList<>();
        listCategory.add(new Category(1L, "notebook"));
        listCategory.add(new Category(2L, "phone"));
        return ResponseEntity.ok().body(listCategory);
    }


}
