package me.sanghyuk.calendar.dto;

import lombok.*;
import me.sanghyuk.calendar.entity.Schedule;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ScheduleDTO {
    private Long sno;
    private Long user;
    private String title;
    private String content;
    private int color;
    private Date scheduledate;
    private int scheduletime;
    private int checked;

    public Schedule toEntity() {
        return Schedule.builder()
                .sno(sno)
                .user(user)
                .title(title)
                .content(content)
                .checked(checked)
                .color(color)
                .scheduledate(scheduledate)
                .scheduletime(scheduletime)
                .build();
    }
}
