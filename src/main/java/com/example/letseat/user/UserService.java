package com.example.letseat.user;

import com.example.letseat.plan.Plan;
import com.example.letseat.plan.PlanDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<PlanDto> findPlanDtoByUserId(Long id) {
        User findUser = userRepository.findById(id).orElse(null);
        if (findUser != null) {
            return convertDto(findUser).getPlans();
        }
        return Collections.emptyList();
    }

    private UserDto convertDto(User user) {
        List<Plan> planList = user.getPlans();
        List<PlanDto> planDtoList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (Plan plan : planList) {
            Long user_id = user.getId();
            String name;
            if (!user_id.equals(plan.getUsers().get(0).getId()))
                name = plan.getUsers().get(0).getName();
            else
                name = plan.getUsers().get(1).getName();
            PlanDto planDto = PlanDto.builder()
                    .id(plan.getId())
                    .expiration_date(plan.getExpiration_date().format(formatter))
                    .creation_date(plan.getCreation_date().format(formatter))
                    .otherUserName(name)
                    .build();
            planDtoList.add(planDto);
        }
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .device_id(user.getDevice_id())
                .plans(planDtoList)
                .build();
    }

    @Transactional
    public Long join(User user) {
        userRepository.save(user);
        return user.getId();
    }
//    public Optional<com.example.letseat.user.User> findById(Long id) {
//        return userRepository.findById(id);
//    }
//    public String login(String device_id) {
//        Optional<com.example.letseat.user.User> findMember = userRepository.findByDeviceId(device_id);
//        if (findMember.isPresent()) {
//            User user = findMember.get();
//            return "user_id=" + user.getId();
//        } else {
//            return "-1";
//        }
//
//    }
}
