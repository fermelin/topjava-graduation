package com.github.fermelin.restaurants.service;

import com.github.fermelin.restaurants.exception.DataConflictException;
import com.github.fermelin.restaurants.exception.IllegalRequestDataException;
import com.github.fermelin.restaurants.model.Vote;
import com.github.fermelin.restaurants.repository.VoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@AllArgsConstructor
public class VoteService {
    public static LocalTime DEADLINE_TO_CHANGE_VOTE = LocalTime.of(11, 0, 0);
    private VoteRepository repository;

    public void update(int userId, int newRestId) {
        Vote vote = repository.checkForToday(userId);
        vote.setTimeVote(LocalTime.now());
        if (vote.getTimeVote().isBefore(DEADLINE_TO_CHANGE_VOTE)) {
            vote.setRestId(newRestId);
        } else {
            throw new DataConflictException("Vote id=" + vote.id() + " can't be updated after " + DEADLINE_TO_CHANGE_VOTE + " o'clock");
        }
    }

    public Vote create(int userId, int restId) {
        try {
            return repository.save(new Vote(null, LocalDateTime.now(), userId, restId));
        } catch (DataIntegrityViolationException e) {
            throw new IllegalRequestDataException("User id=" + userId + " can only have one vote for today");
        }
    }
}
