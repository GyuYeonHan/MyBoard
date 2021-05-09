package spring.board.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public abstract class BaseTimeEntity {

    @CreatedDate
    protected String createdTime;

    @LastModifiedDate
    protected String modifiedTime;

    protected BaseTimeEntity(String createdTime, String modifiedTime) {
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
    }

    protected BaseTimeEntity() {

    }
}
