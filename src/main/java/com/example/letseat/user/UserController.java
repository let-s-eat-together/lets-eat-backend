package com.example.letseat.user;

import com.example.letseat.auth.AuthMember;
import com.example.letseat.auth.argumentresolver.Auth;
import com.example.letseat.user.data.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@RestController
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/list")
    @ResponseBody
    public ResponseEntity<List<ListResponse>> lists(@Auth AuthMember authMember) {
        System.out.println("authMember = " + authMember);
        if(authMember==null){
            throw  new RuntimeException("authMember가 없음");
        }
        Long user_id = authMember.getId();
        List<ListResponse> planList = userService.findPlanByUserId(user_id);
        return ResponseEntity.ok(planList);
    }

    @PostMapping("/sign-up")
    @ResponseBody // device id 중복 기능 추가해야함.
    public Map<String, Object> saveMember(@RequestBody @Valid SignUpRequest request) {
        Optional<User> checkExist = userRepository.findByDeviceId(request.getDevice_id());
        if(checkExist.isPresent()){
            throw new RuntimeException("이미 존재하는 기기 id입니다.");
        }
        User user = new User();
        user.setName(request.getUsername());
        user.setDeviceId(request.getDevice_id());
        Long userId = userService.join(user);
        Map<String, Object> response = new HashMap<>();
        response.put("user_id", userId);
        return response;
    }
    @PostMapping("/login")
    public ResponseEntity<TokenDto> newLogin(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(userService.newLogin(loginRequest.getDevice_id()));
    }
    @PutMapping("/rename")
    public ResponseEntity<RenameResponse> changeUserName(
            @Auth AuthMember authMember, @RequestBody RenameRequest renameRequest) {
        Long receiverId = authMember.getId();

        return ResponseEntity.ok(userService.updateUserName(receiverId, renameRequest.getUser_name()));


    }

    @PutMapping("/deleteUser")
    @ResponseBody
    public void deleteUser(@Auth AuthMember authMember) {
        if(authMember==null){
            throw  new RuntimeException("authMember가 없음");
        }
        Long user_id = authMember.getId();
        userService.deleteUser(user_id);
    }

    @GetMapping("/search-name/{userId}")
    public ResponseEntity<SearchNameResponse> searchName(@Auth AuthMember authMember, @PathVariable Long userId){
        if(authMember==null){
            throw  new RuntimeException("authMember가 없음");
        }
        SearchNameResponse searchNameResponse = userService.searchName(userId);
        return ResponseEntity.ok(searchNameResponse);
    }
}
