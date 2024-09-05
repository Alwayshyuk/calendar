package me.sanghyuk.calendar.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@Builder
public class Checked {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chno")
    private Long chno;

    @Column(name = "checkedName")
    private String checkName;
}
