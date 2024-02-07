package com.juno.library.controller.userdto;


import com.juno.library.domain.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddUserDto {

    private String name;
    private String address;
    private String phone;
    public UserEntity toEntity(){
        return new UserEntity(null, name, address, phone);
    }
}
