package com.juno.library.controller;


import com.juno.library.controller.categorydto.AddCategoryDto;
import com.juno.library.domain.category.CategoryEntity;
import com.juno.library.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CategoryApiController {

    private final CategoryService categoryService;


    @PostMapping("/api/category")
    public ResponseEntity<CategoryEntity> addCategory(@RequestBody AddCategoryDto dto){
        CategoryEntity entity = categoryService.save(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(entity);
    }

    @GetMapping("/api/category")
    public ResponseEntity<List<CategoryEntity>> findCategories(){
        List<CategoryEntity> entities = categoryService.findAll();

        return ResponseEntity.ok().body(entities);
    }

    @GetMapping("/api/category/{code}")
    public ResponseEntity<CategoryEntity> findCategory(@PathVariable Long code){
        CategoryEntity entity = categoryService.findById(code);

        return ResponseEntity.ok().body(entity);
    }

    @DeleteMapping("/api/category/{code}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long code){
        categoryService.deleteByCode(code);

        return ResponseEntity.ok().build();
    }
}
