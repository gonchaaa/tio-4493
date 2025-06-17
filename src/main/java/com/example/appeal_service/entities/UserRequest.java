package com.example.appeal_service.entities;


import com.example.appeal_service.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_request")
public class UserRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String description;

    private String voiceMessage;

    private String attachmentPath;

    private LocalDateTime createdDate;

    @ManyToOne
    private AppealCategory category;

    @ManyToOne
    private AppealPurpose purpose;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Long userId;
    private Long cardId;

}
