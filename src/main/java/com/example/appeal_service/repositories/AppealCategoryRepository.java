package com.example.appeal_service.repositories;

import com.example.appeal_service.entities.AppealCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppealCategoryRepository extends JpaRepository<AppealCategory,Long> {
}
