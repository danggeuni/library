package com.juno.library.controller.categorydto;

import com.juno.library.domain.category.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddCategoryDto {

    private Long code;
    private String name;

    public CategoryEntity toEntity(){
        return new CategoryEntity(code, name);
    }
}
