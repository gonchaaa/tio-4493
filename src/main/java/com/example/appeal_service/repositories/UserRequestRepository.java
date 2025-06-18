package com.example.appeal_service.repositories;

import com.example.appeal_service.entities.UserRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRequestRepository extends JpaRepository<UserRequest,Long> {

    List<UserRequest> findByClientCode(String clientCode);
}
