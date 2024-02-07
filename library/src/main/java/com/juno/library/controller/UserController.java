package com.juno.library.controller;

import com.juno.library.controller.userdto.PaginatedResult;
import com.juno.library.controller.userdto.UserViewResponse;
import com.juno.library.domain.user.UserEntity;
import com.juno.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users/{id}")
    public String getUser(@PathVariable(name = "id") Long id, Model model){
        UserEntity entity = userService.findById(id);
        model.addAttribute("user", new UserViewResponse(entity));

        return "user";
    }

    @GetMapping("/users")
    public String userList(@RequestParam(defaultValue = "15") int pageSize, @RequestParam(defaultValue = "1") int pageNumber, Model model){
        PaginatedResult<UserEntity> result = userService.paging(pageSize, pageNumber);

        model.addAttribute("data", result.getData());
        model.addAttribute("totalPages", result.getTotalPages());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("pageNumber", pageNumber);

        return "userList";
    }
}
