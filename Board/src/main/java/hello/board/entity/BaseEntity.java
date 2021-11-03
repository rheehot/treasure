package hello.board.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class}) // -> JPA 내부에서 엔티티 객체가 생성/변경되는 것을 감지하는 역할
@Getter
abstract class BaseEntity {

    /**
     * 추상클래스는 일반 클래스와 별 다를 것이 없다.
     * 단지, 추상 메서드를 선언하여 상속을 통해서 자손 클래스에서 완성하도록 유도하는 클래스
     */

    @CreatedDate // -> 엔티티의 생성 시간을 처리
    @Column(name = "regdate", updatable = false) // -> updatable = false 이기 때문에, 데이터베이스에 반영할 때 regdate 칼럼값은 변경되지 않는다.
    private LocalDateTime regDate;

    @LastModifiedDate // -> 최종 수정 시간을 자동으로 처리하는 용도.
    @Column(name = "updateDate")
    private LocalDateTime updateDate;
}
