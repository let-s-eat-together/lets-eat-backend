package com.example.letseat.user;

import com.example.letseat.plan.Plan;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@AllArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    @GetMapping("/list/{id}")
    @ResponseBody
    public List<Plan> lists(@PathVariable Long id) {
        return userService.findPlanByUserId(id);
    }

}
