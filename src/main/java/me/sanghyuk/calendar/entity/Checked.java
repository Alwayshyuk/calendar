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
    private int chno;

    @Column(name = "checkedName", nullable = false)
    private String checkedName;
}
