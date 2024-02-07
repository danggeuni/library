package com.juno.library.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class UserEntity {

    private final Long id;
    private final String name;
    private final String address;
    private final String phone;

    public UserEntity(Long id, String name, String address, String phone){
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }
}
