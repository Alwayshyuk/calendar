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

    //과제 내용에서 벗어난 영역으로 Long으로 유저 구분
    @Column(name = "userNo")
    private Long userNo;

    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Column(name = "content", length = 300)
    private String content;

    //1:Red, 2:Blue, 3:Green 등으로 일정의 성격에 따른 색상 표현
    @ManyToOne
    @JoinColumn(name = "color")
    private Color color;

    @Temporal(TemporalType.DATE)
    @Column(name = "scheduledate")
    private Date scheduledate;

    //일정 1시간 미루기, 시간 증가 등의 연산의 편의성을 위해 int로 구현
    @Column(name = "scheduletime")
    private int scheduletime;

    //1:수행 전 일정, 2: 미뤄진 일정, 3: 수행 후 일정 등으로 일정 상태
    @ManyToOne
    @JoinColumn(name="checked")
    private Checked checked;

}
