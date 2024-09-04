package me.sanghyuk.calendar.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@Builder
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sno;

    @Column(name = "user")
    private Long user;

    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Column(name = "content", length = 300)
    private String content;

    @Column(name = "color")
    private int color;

    @Temporal(TemporalType.DATE)
    @Column(name="scheduledate")
    private Date scheduledate;

    @Column(name = "scheduletime")
    private int scheduletime;

    @Column(name = "checked")
    private int checked;

}
