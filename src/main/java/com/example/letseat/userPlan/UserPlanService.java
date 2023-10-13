package com.example.letseat.userPlan;

import com.example.letseat.sting.Sting;
import com.example.letseat.sting.StingRepository;
import com.example.letseat.user.User;
import com.example.letseat.user.UserRepository;
import com.example.letseat.userPlan.dto.StingRequestDto;
import com.example.letseat.userPlan.dto.StingResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;


@Service
@Transactional
@AllArgsConstructor
public class UserPlanService {

    private final UserRepository userRepository;
    private final UserPlanRepository userPlanRepository;
    private final StingRepository stingRepository;


    public void createSting(Long user_id, Long planId) {
        Sting newSting = new Sting();
        LocalDateTime currentDateTime = LocalDateTime.now();
        UserPlan receiverPlan = userPlanRepository.findByPlanIdAndUserIdNot(planId, user_id)
                .orElseThrow(() -> new RuntimeException("해당하는 약속이 없습니다."));

        User receiver = receiverPlan.getUser();

        newSting.setStingDate((currentDateTime));
        newSting.setSenderId(user_id);
        newSting.setReceiverId(receiver.getId());
        newSting.setPlanId(planId);
        stingRepository.save(newSting);

    }
}