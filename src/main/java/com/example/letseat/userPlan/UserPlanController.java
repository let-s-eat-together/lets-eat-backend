package com.example.letseat.userPlan;

import com.example.letseat.plan.PlanService;
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

}
