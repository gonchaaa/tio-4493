package com.example.appeal_service.repositories;

import com.example.appeal_service.entities.AppealPurpose;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppealPurposeRepository extends JpaRepository<AppealPurpose,Long> {
}
