package com.example.letseat.friend_relation;


import com.example.letseat.friend_relation.data.AcceptResponse;
import com.example.letseat.friend_request.FriendRequest;
import com.example.letseat.friend_request.data.AlarmResponse;
import com.example.letseat.user.User;
import com.example.letseat.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FriendRelationService {
    final FriendRelationRepository friendRelationRepository;
    final UserRepository userRepository;

    public void acceptFriend(Long User1_id, Long User2_id) {
        FriendRelation friendRelation = new FriendRelation();
        User user1 = userRepository.findById(User1_id).get();
        User user2 = userRepository.findById(User2_id).get();
        friendRelation.setUser1(user1);
        friendRelation.setUser2(user2);
        friendRelationRepository.save(friendRelation);
    }

    public AcceptResponse alarm(Long userId) {
        List<AcceptResponse> acceptResponses = new ArrayList<>();
        List<FriendRelation> acceptList = friendRelationRepository.findByUser1Id(userId);
        Long maxId = 0L;//기준점
        String returnname = "";
        for (FriendRelation friendRelation : acceptList) {
            Long compareId = friendRelation.getId();//이건 가장 비교.
            if (maxId < compareId) {
                returnname = friendRelation.getUser2().getUsername();

            }
            User user2 = userRepository.findNameById(friendRelation.getUser2().getId()).orElseThrow();
            AcceptResponse acceptResponse = new AcceptResponse();
            acceptResponse.setFriendName(user2.getUsername());
            acceptResponses.add(acceptResponse);
        }
        //가장 최근 요청만 보내야 하는건가?

        //return maxId;
        if (maxId == 0L) {
            throw new RuntimeException("친구 관련 데이터가 없음");
        } else {
            AcceptResponse acceptResponse = new AcceptResponse();
            acceptResponse.setFriendName(returnname);
            return acceptResponse;

        }
        //return returnname;
        //return acceptResponses;
    }

    public List<AcceptResponse> getFriendList(Long userId) {


        User user = userRepository.findById(userId).get();
        List<User> friends = user.getFriends();

        return convertToAcceptResponses(friends);
    }

    private List<AcceptResponse> convertToAcceptResponses(List<User> friends) {
        List<AcceptResponse> acceptResponses = new ArrayList<>();
        for (User friend : friends) {
            AcceptResponse acceptResponse = new AcceptResponse();
            acceptResponse.setFriendName(friend.getFriendRelationsAsUser2().toString());
            acceptResponses.add(acceptResponse);

        }
        return acceptResponses;

    }

}
