package com.example.letseat.user;

import com.example.letseat.user.data.*;
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
    public ResponseEntity<List<ListResponse>> lists(@RequestBody @Valid ListRequest request) {
        List<ListResponse> planList = userService.findPlanByUserId(request.getUser_id());
        return ResponseEntity.ok(planList);
    }

    @PostMapping("/sign-up")
    @ResponseBody // device id 중복 기능 추가해야함.
    public Map<String, Object> saveMember(@RequestBody @Valid SignUpRequest request) {
        User user = new User();
        user.setName(request.getUsername());
        user.setDeviceId(request.getDevice_id());
        Long userId = userService.join(user);
        Map<String, Object> response = new HashMap<>();
        response.put("user_id", userId);
        return response;
    }


//    @GetMapping("/login")
//    @ResponseBody
//    public  Map<String, Long> login(@RequestBody LoginRequest loginRequest){
//        Map<String, Long> response = new HashMap<>();
//        Long deviceId = userService.login(loginRequest.getDevice_id());
//        response.put("loginResult", deviceId);
//        return response;
//    }
    @GetMapping("/login")
    public ResponseEntity<TokenDto> newLogin(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(userService.newLogin(loginRequest.getDevice_id()));
    }

}
