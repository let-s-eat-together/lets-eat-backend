package com.example.letseat.sting;

import com.example.letseat.sting.data.StingDTO;
import com.example.letseat.user.User;
import com.example.letseat.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class StingService {
    private final UserRepository userRepository;

    public List<StingDTO> factoringData(List<Sting> stingList) {
        List<StingDTO> factorizedStingDTOs = new ArrayList<>();
        for (Sting sting : stingList) {
            StingDTO dto = new StingDTO();
            dto.setPlan_id(sting.getPlanId());
            Optional<User> sender = userRepository.findNameById(sting.getSenderId());
            dto.setOther_user_name(sender.get().getUsername());
            dto.setCreation_date(sting.getStingDate());
            dto.setCountSting(sting.getCountSting());
            factorizedStingDTOs.add(dto);
        }
        return factorizedStingDTOs;

    }
}
