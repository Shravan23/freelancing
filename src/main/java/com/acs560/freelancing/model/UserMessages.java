package com.acs560.freelancing.model;

import javax.persistence.*;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
@Entity
@Table(name="user_messages")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserMessages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User sender;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User receiver;

    @ManyToOne
    @JoinColumn(name="job_id", nullable = false)
    private Job job;

//    @Column(length = 64000)
//    @Size(min = 2)
    private String text;

    private LocalDateTime createdAt;

    private String files;
}
