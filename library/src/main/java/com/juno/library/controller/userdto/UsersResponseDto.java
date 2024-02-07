package com.juno.library.controller.userdto;


import com.juno.library.domain.user.UserEntity;
import lombok.Getter;

@Getter
public class UsersResponseDto {

    private final String name;
    private final String address;
    private final String phone;

    public UsersResponseDto(UserEntity entity){
        this.name = entity.getName();
        this.address = entity.getAddress();
        this.phone = entity.getPhone();
    }
}
