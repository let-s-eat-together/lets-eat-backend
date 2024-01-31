package com.example.letseat.friend_relation;


import com.example.letseat.friend_relation.data.AcceptResponse;
import com.example.letseat.friend_request.FriendRequest;
import com.example.letseat.friend_request.data.AlarmResponse;
import com.example.letseat.user.User;
import com.example.letseat.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

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
        List<FriendRelation> friendRelations = friendRelationRepository.findByUser2_Id(userId);

        // Extract user1 from the first matching friend relation (if any)
        Long user1Id = friendRelations.isEmpty() ? null : friendRelations.get(0).getUser1().getId();

            User user2 = userRepository.findNameById(user1Id).orElseThrow();
            AcceptResponse acceptResponse = new AcceptResponse();
            acceptResponse.setFriendName(user2.getUsername());
        return acceptResponse;
    }

    public List<AcceptResponse> getFriendList(Long userId) {


        List<FriendRelation> friendRelations = friendRelationRepository.findByUser1IdOrUser2Id(userId, userId);

        // Extract unique user IDs from friend relations
        Set<Long> uniqueFriendIds = new HashSet<>();
        for (FriendRelation friendRelation : friendRelations) {
            uniqueFriendIds.add(friendRelation.getUser1().getId());
            uniqueFriendIds.add(friendRelation.getUser2().getId());
        }

        List<AcceptResponse> friendList = new ArrayList<>();
        for (Long friendId : uniqueFriendIds) {
            if (!friendId.equals(userId)) {
                User friendUser = userRepository.findById(friendId).orElseThrow(); // Adjust as needed

                AcceptResponse acceptResponse = new AcceptResponse();
                acceptResponse.setFriendName(friendUser.getUsername()); // Assuming User has a getName() method

                friendList.add(acceptResponse);
            }
        }

        return friendList;
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
