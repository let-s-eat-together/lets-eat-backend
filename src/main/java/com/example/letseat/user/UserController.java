package com.example.letseat.user;

import com.example.letseat.plan.PlanDto;
import com.example.letseat.user.data.ListRequest;
import com.example.letseat.user.data.LoginRequest;
import com.example.letseat.user.data.SignUpRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    @GetMapping("/list")
    @ResponseBody
    public List<PlanDto> lists(@RequestBody @Valid ListRequest request) {
        return userService.findPlanDtoByUserId(request.getUser_id());
    }

    @PostMapping("/sign-up")
    @ResponseBody
    public Map<String, Object> saveMember(@RequestBody @Valid SignUpRequest request) {
        User user = new User();
        user.setName(request.getUsername());
        user.setDeviceId(request.getDevice_id());
        Long userId = userService.join(user);
        Map<String, Object> response = new HashMap<>();
        response.put("user_id", userId);
        return response;
    }


    @GetMapping("/login")
    @ResponseBody
    public  Map<String, Long> login(@RequestBody LoginRequest loginRequest){
        Map<String, Long> response = new HashMap<>();
        Long deviceId = userService.login(loginRequest.getDevice_id());
        response.put("loginResult", deviceId);
        return response;
    }
}
