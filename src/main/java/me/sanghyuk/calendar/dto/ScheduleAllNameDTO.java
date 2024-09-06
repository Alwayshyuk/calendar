package me.sanghyuk.calendar.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import me.sanghyuk.calendar.entity.Checked;
import me.sanghyuk.calendar.entity.Color;
import me.sanghyuk.calendar.entity.Schedule;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ScheduleAllNameDTO {

    @Schema(description = "일정번호")
    private Long sno;

    @Schema(description = "유저번호")
    private Long userNo;

    @Schema(description = "일정 제목")
    private String title;

    @Schema(description = "일정 내용")
    private String content;

    @Schema(description = "일정 색상")
    private int cno;

    @Schema(description = "색상 명")
    private String colorName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "일정 날짜")
    private Date scheduledate;

    @Schema(description = "일정 시간")
    private int scheduletime;

    @Schema(description = "일정 상태")
    private int chno;

    @Schema(description = "상태 명")
    private String checkedName;

    public Schedule toScheduleEntity() {
        return Schedule.builder()
                .sno(sno)
                .userNo(userNo)
                .title(title)
                .content(content)
                .chno(chno)
                .cno(cno)
                .scheduledate(scheduledate)
                .scheduletime(scheduletime)
                .build();
    }
    public Color toColorEntity() {
        return Color.builder()
                .cno(cno)
                .colorName(colorName)
                .build();
    }
    public Checked toCheckedEntity(){
        return Checked.builder()
                .chno(chno)
                .checkedName(checkedName)
                .build();
    }
}
