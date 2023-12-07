package com.acs560.freelancing.repository;

import com.acs560.freelancing.model.Job;
import com.acs560.freelancing.model.UserMessages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<UserMessages, Long> {
    List<UserMessages> findByJobOrderByCreatedAtAsc(Job job);

}
