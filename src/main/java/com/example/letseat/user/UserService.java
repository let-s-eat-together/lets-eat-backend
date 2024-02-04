package com.example.letseat.user;

import com.example.letseat.auth.jwt.JwtTokenProvider;
import com.example.letseat.plan.Plan;
import com.example.letseat.user.data.ListResponse;
import com.example.letseat.user.data.RenameResponse;
import com.example.letseat.user.data.SearchNameResponse;
import com.example.letseat.user.data.TokenDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public List<ListResponse> findPlanByUserId(Long id) {
        User findUser = userRepository.findById(id).orElseThrow();
        List<Plan> planList = findUser.getPlans();
        List<ListResponse> listResponses = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for(Plan plan : planList) {
            String name;
            if (!id.equals(plan.getUsers().get(0).getId()))
                name = plan.getUsers().get(0).getUsername();
            else if(!id.equals(plan.getUsers().get(1).getId()))
                name = plan.getUsers().get(1).getUsername();
            else throw new IllegalArgumentException("Plan doesn't have other user");
            ListResponse listResponse = ListResponse.builder()
                    .plan_id(plan.getId())
                    .expiration_date(plan.getExpiration_date().format(formatter))
                    .creation_date(plan.getCreation_date().format(formatter))
                    .other_user_name(name)
                    .build();
            listResponses.add(listResponse);
        }
        return listResponses;

    }

    @Transactional
    public Long join(User user) {
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new RuntimeException("Email cannot be null or empty");
        }

        userRepository.save(user);
        return user.getId();
    }

    public TokenDto newLogin(String ID_email, String Password){
        User findUser = userRepository.findByEmail(ID_email)
                .orElseThrow(()-> new RuntimeException("존재하지 않는 email 입니다."));
        if(!Objects.equals(findUser.getPassword(), Password)){
            throw new RuntimeException("아이디와 비밀번호가 일치하지 않습니다.");
        }
        return TokenDto.builder().token(jwtTokenProvider.createToken(findUser))
                .user_id(findUser.getId()).name(findUser.getUsername()).build();
    }

    public RenameResponse updateUserName(Long userId, String newName) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setUsername(newName);
        RenameResponse response = RenameResponse.builder().user_name(user.getUsername()).build();
        return response;
    }

    public void deleteUser(Long userId) {
        User findUser = userRepository.findById(userId).orElseThrow();
        findUser.setUsername("탈퇴한 회원");
        findUser.setEmail("");
        findUser.setPassword("");
        userRepository.save(findUser);
    }

    public List<SearchNameResponse> searchName(String username) {
        List<User> userList = userRepository.findByUsername(username);
        List<SearchNameResponse> resultList = new ArrayList<>();
        for(User user : userList) {
            SearchNameResponse searchNameResponse = new SearchNameResponse();
            searchNameResponse.setUser_id(user.getId());
            searchNameResponse.setEmail(user.getEmail());
            searchNameResponse.setUsername(user.getUsername());
            resultList.add(searchNameResponse);
        }
        return resultList;
    }
}
