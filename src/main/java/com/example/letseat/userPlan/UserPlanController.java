package com.example.letseat.userPlan;

import com.example.letseat.auth.AuthMember;
import com.example.letseat.auth.argumentresolver.Auth;
import com.example.letseat.userPlan.dto.StingRequestDto;
import com.example.letseat.userPlan.dto.StingResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@AllArgsConstructor
public class UserPlanController {
    private final UserPlanService userPlanService;

    @GetMapping("/sting")
    @ResponseBody
    public ResponseEntity<StingResponse> sting(@RequestBody @Valid StingRequestDto stingRequestDto){
        StingResponse stingResponse = userPlanService.stingInfo(stingRequestDto);
        return ResponseEntity.ok(stingResponse);

    }

    @ResponseBody
    public ResponseEntity<StingResponse> newSting(@Auth AuthMember authMember, @RequestBody @Valid StingRequestDto stingRequestDto){
        System.out.println("authMember = " + authMember);
        if(authMember==null){
            throw  new RuntimeException("authMember가 없음");
        }
        Long user_id = authMember.getId();
        System.out.println("user_id = " + user_id);

        StingResponse stingResponse = userPlanService.stingInfo(user_id, stingRequestDto);
        return ResponseEntity.ok(stingResponse);

    }


}
