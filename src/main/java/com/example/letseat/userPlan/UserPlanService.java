package com.example.letseat.userPlan;

import com.example.letseat.plan.PlanRepository;
import com.example.letseat.user.User;
import com.example.letseat.user.UserRepository;
import com.example.letseat.userPlan.dto.StingResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;


@Service
@Transactional
@AllArgsConstructor
public class UserPlanService {

    private final UserRepository userRepository;
    private final UserPlanRepository userPlanRepository;

    public StingResponse stingInfo(StingRequestDto stingRequestDto) {
        Long myId = stingRequestDto.getUser_id();
        Long planId = stingRequestDto.getPlan_id();
        Optional<User> findMember  = userRepository.findNameById(myId);
        User sendUser = findMember.get();

        Date now = new Date();
        // 출력 형식을 지정할 SimpleDateFormat 객체를 생성합니다.
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        // 현재 시간을 원하는 형식으로 문자열로 변환합니다.
        String formattedDate = sdf.format(now);

        UserPlan receiverPlan= userPlanRepository.findByPlanIdAndUserIdNot(planId, myId)
                .orElseThrow(() -> new RuntimeException("해당하는 약속이 없습니다."));

        User receiver = receiverPlan.getUser();
        return new StingResponse(sendUser.getName(), receiver.getName(), formattedDate);
    }
}
