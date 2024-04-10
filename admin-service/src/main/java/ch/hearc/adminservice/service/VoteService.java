package ch.hearc.adminservice.service;

import ch.hearc.adminservice.service.models.Vote;
import ch.hearc.adminservice.service.models.VoteBroadCast;
import ch.hearc.adminservice.service.models.actions.VoteSubmitedResult;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface VoteService {
    VoteSubmitedResult validateVote(Vote vote);

    void publishVote(VoteBroadCast voteMessage) throws JsonProcessingException;
}
