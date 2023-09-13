package com.example.letseat.user;

import com.example.letseat.user.data.ListResponse;
import com.example.letseat.user.data.ListRequest;
import com.example.letseat.user.data.SignUpRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<?> saveMember(@RequestBody @Valid SignUpRequest request){
        User user = new User();
        user.setName(request.getName());
        user.setDevice_id(request.getDevice_id());
        Long id = userService.join(user);
        return ResponseEntity.ok("user_id: " + id);
    }
}
