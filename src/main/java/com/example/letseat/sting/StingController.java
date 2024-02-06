package com.example.letseat.sting;

import com.example.letseat.auth.AuthMember;
import com.example.letseat.auth.argumentresolver.Auth;
import com.example.letseat.sting.data.StingDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@AllArgsConstructor
@Controller
public class StingController {
    private final StingRepository stingRepository;
    private final StingService stingService;

    @GetMapping("/message/sting")
    @ResponseBody
    public ResponseEntity<List<StingDTO>> Sting(@Auth AuthMember authMember) {
        if(authMember==null){
            throw  new RuntimeException("authMember가 없음");
        }
        Long userId = authMember.getId();
        List<Sting> stingList = stingRepository.findByReceiverId(userId);
        List<StingDTO> stingDTOS = stingService.factoringData(stingList);
        return ResponseEntity.ok(stingDTOS);
    }
}
