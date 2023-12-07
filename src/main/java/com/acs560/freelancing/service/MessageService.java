package com.acs560.freelancing.service;


import com.acs560.freelancing.model.UserMessages;
import com.acs560.freelancing.repository.JobRepository;
import com.acs560.freelancing.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    JobRepository jobRepository;

    public List<UserMessages> getMessageOfRoom(Long jobId) {
        return messageRepository.findByJobOrderByCreatedAtAsc(jobRepository.findById(jobId).get());
    }
    public UserMessages save(UserMessages userMessages) {
        return messageRepository.save(userMessages);
    }

}
