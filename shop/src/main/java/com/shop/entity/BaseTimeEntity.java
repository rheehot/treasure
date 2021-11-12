package com.shop.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@EntityListeners(value = {AuditingEntityListener.class}) //Auditing 적용을 위한 어노테이션 추가.
@MappedSuperclass // 공통 매핑 정보가 필요할 때 사용하는 어노테이션.
@Getter
@Setter
public class BaseTimeEntity {
    
    @CreatedDate //저장될 때 시간을 자동으로 저장.
    @Column(updatable = false)
    private LocalDateTime regTime;

    @LastModifiedDate // 엔티티의 값을 변경할 때 시간을 자동으로 저장.
    private LocalDateTime updateTime;

}
