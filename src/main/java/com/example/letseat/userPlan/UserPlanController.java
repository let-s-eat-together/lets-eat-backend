package com.example.letseat.userPlan;

import com.example.letseat.auth.AuthMember;
import com.example.letseat.auth.argumentresolver.Auth;
import com.example.letseat.sting.Sting;
import com.example.letseat.sting.StingRepository;
import com.example.letseat.userPlan.dto.StingRequestDto;
import com.example.letseat.userPlan.dto.StingResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Controller
@AllArgsConstructor
public class UserPlanController {
    private final UserPlanService userPlanService;
    private final StingRepository stingRepository;

    @PostMapping("/sting/{planId}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> newSting(@Auth AuthMember authMember, @PathVariable Long planId){
        //authMember가 sender, plan id를 비교해서 없으면 하나 새로 생성
        //반대편의 receiverId 찾아서 추가해놓기
        try{
            Map<String, Object> responseData = new HashMap<>();
            if(authMember==null){
                throw  new RuntimeException("authMember가 없음");
            }
            Long senderId = authMember.getId();

            Optional<Sting> sting=stingRepository.findBySenderIdAndPlanId(senderId,planId);
            if(sting.isPresent()){
                Sting existSting=sting.get();
                existSting.setCountSting(sting.get().getCountSting()+1);
                existSting.setStingDate(LocalDateTime.now());
                stingRepository.save(existSting);
            }else{
                userPlanService.createSting(senderId, planId);
            }
            responseData.put("status","success");

            return ResponseEntity.ok(responseData);

        }catch(Exception e){
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("status","fail");
            return ResponseEntity.ok(responseData);
        }
    }


}
