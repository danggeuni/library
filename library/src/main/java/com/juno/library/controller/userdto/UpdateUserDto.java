package com.juno.library.controller.userdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class UpdateUserDto {

    private final String name;
    private final String address;
    private final String phone;
}
