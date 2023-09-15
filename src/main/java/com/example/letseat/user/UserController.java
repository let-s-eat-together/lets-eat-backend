package com.example.letseat.user;

import com.example.letseat.plan.PlanDto;
import com.example.letseat.user.data.ListRequest;
import com.example.letseat.user.data.SignUpRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

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
    public ResponseEntity<?> saveMember(@RequestBody @Valid SignUpRequest request){
        User user = new User();
        user.setName(request.getName());
        user.setDevice_id(request.getDevice_id());
        Long id = userService.join(user);
        return ResponseEntity.ok("user_id: " + id);
    }
    @PutMapping("/rename")

    public ResponseEntity<String> changeUserName(
            @RequestParam("user_name") String userName,
            @RequestParam("user_id") Long userId) {
        try {
            if (userName != null && !userName.trim().isEmpty()) {
                User user = new User();
                userService.updateUserName(user, userName);
                return ResponseEntity.ok(userName);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("사용자 이름은 공백일 수 없습니다.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("업데이트 작업에 실패했습니다.");
        }
    }


}
