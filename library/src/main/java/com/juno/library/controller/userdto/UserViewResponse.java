package com.juno.library.controller.userdto;

import com.juno.library.domain.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserViewResponse {

    private Long id;
    private String name;
    private String address;
    private String phone;

    public UserViewResponse(UserEntity entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.address = entity.getAddress();
        this.phone = entity.getPhone();
    }
}
