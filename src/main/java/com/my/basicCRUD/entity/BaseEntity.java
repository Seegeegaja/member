package com.my.basicCRUD.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Data
public class BaseEntity {
    //입력시 자동으로 현재 날짜 시각을 저장
    @CreatedDate
    //저장 이후에 수정 불가
    @Column(updatable = false)
    private LocalDateTime createdAt;
    //수정 발생시 수정된 시각을 자동저장
    @LastModifiedDate
    //만들때 그시각을 저장하지 않게 하려고 insert 임
    @Column(insertable = false)
    private LocalDateTime updatedAt;
}
