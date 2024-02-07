package com.juno.library.service;

import com.juno.library.controller.categorydto.AddCategoryDto;
import com.juno.library.domain.category.CategoryEntity;
import com.juno.library.domain.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryEntity save(AddCategoryDto dto){
        return categoryRepository.save(dto.toEntity());
    }

    public List<CategoryEntity> findAll(){
        return categoryRepository.findALl();
    }

    public CategoryEntity findById(Long code){
        return categoryRepository.findByCode(code);
    }

    public void deleteByCode(Long code){
        categoryRepository.deleteByCode(code);
    }
}
