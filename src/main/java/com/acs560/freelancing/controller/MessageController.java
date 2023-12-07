package com.acs560.freelancing.controller;
import com.acs560.freelancing.dto.MessageRequest;
import com.acs560.freelancing.dto.MessageResponse;
import com.acs560.freelancing.model.*;
import com.acs560.freelancing.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    MessageService messageService;

    @Autowired
    JobService jobService;

    @Autowired
    UserService userService;

    @Autowired
    BidService bidService;


    @GetMapping("/job_room/{jobId}")
    public ResponseEntity<List<MessageResponse>> getMessages(@PathVariable Long jobId) {
        List<UserMessages> userMessages = messageService.getMessageOfRoom(jobId);
        List<MessageResponse> messageResponses = new ArrayList<>();
        for(UserMessages eachmessage: userMessages) {
            MessageResponse messageResponse = new MessageResponse();
            messageResponse.setContent(eachmessage.getText());
            messageResponse.setDate(eachmessage.getCreatedAt());
            messageResponse.setSenderId(eachmessage.getSender().getUserId());
            messageResponse.setSenderRole(eachmessage.getSender().getRole().name());
            messageResponse.setMedia(eachmessage.getFiles());
            messageResponses.add(messageResponse);
        }
        return ResponseEntity.ok(messageResponses);
    }
    @PostMapping("/job_room/send")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> sendMessage(@RequestBody MessageRequest messageRequest) {
        UserMessages userMessages = new UserMessages();
        userMessages.setCreatedAt(LocalDateTime.now());
        userMessages.setText(messageRequest.getText());
        userMessages.setFiles(messageRequest.getMedia());
        userMessages.setJob(jobService.getJob(messageRequest.getJobId()));
        Bid bid = bidService.getBid(messageRequest.getBidId());
        User sender = getUser();
        User receiver;
        if(sender.getRole().equals(User.Role.CLIENT)){
            receiver = userService.findByUserId(bid.getUser().getUserId()).get();
        } else {
            receiver = userService.findByUserId(bid.getJob().getAuthor().getUserId()).get();
        }
        userMessages.setSender(sender);
        userMessages.setReceiver(receiver);
        messageService.save(userMessages);
        return ResponseEntity.ok("sent");
    }


    private User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userService.findByUsername(username).get();
    }
}
