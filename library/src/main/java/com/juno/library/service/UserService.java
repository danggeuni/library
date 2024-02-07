package com.juno.library.service;

import com.juno.library.controller.userdto.AddUserDto;
import com.juno.library.controller.userdto.PaginatedResult;
import com.juno.library.controller.userdto.UpdateUserDto;
import com.juno.library.domain.user.UserEntity;
import com.juno.library.domain.user.repositroy.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserEntity save(AddUserDto dto){
        return userRepository.save(dto.toEntity());
    }

    public List<UserEntity> findAll(){
        return userRepository.findAll();
    }

    public UserEntity findById(Long id){
        return userRepository.findById(id);
    }

    public List<UserEntity> findByName(String name){
        return userRepository.findByName(name);
    }

    public void deleteById(Long id){
        userRepository.deleteUser(id);
    }

    public UserEntity updateUser(Long id, UpdateUserDto dto){
        return userRepository.updateUser(id, dto);
    }

    public PaginatedResult<UserEntity> paging(int pageSize, int pageNumber){
        int totalPage = userRepository.getTotalPage(pageSize);
        List<UserEntity> data = userRepository.paging(pageSize, pageNumber);

        return new PaginatedResult<>(data, totalPage);
    }
}
