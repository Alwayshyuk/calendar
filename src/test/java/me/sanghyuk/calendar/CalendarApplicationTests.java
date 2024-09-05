package me.sanghyuk.calendar;

import me.sanghyuk.calendar.dto.ScheduleDTO;
import me.sanghyuk.calendar.entity.Schedule;
import me.sanghyuk.calendar.service.ScheduleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.stream.IntStream;

@SpringBootTest
class CalendarApplicationTests {

    @Autowired
    private ScheduleService scheduleService;

    @DisplayName("스케줄 등록")
    @Test
    public void saveTest() {
        IntStream.rangeClosed(0, 12).forEach(i -> {

            ScheduleDTO dto = ScheduleDTO.builder()
                    .userNo(1L + i)
                    .title("title..." + i)
                    .content("content..." + i)
                    .checked(1)
                    .color(1)
                    .scheduledate(Date.valueOf("2022-02-03"))
                    .scheduletime(1600 + i)
                    .build();
            System.out.println(scheduleService.save(dto));
        });
    }

    @DisplayName("스케줄 변경")
    @Test
    public void modifyTest() {
        ScheduleDTO dto = ScheduleDTO.builder()
                .sno(3L)
                .userNo(1L)
                .title("titleModify")
                .content("contentModify")
                .checked(1)
                .color(1)
                .scheduledate(Date.valueOf("2022-02-03"))
                .scheduletime(1600)
                .build();
        System.out.println(scheduleService.modify(dto));
    }

    @DisplayName("스케줄 삭제")
    @Test
    public void deleteTest() {

        scheduleService.delete(1L, 1L);
    }

    @DisplayName("유저별 스케줄 검색")
    @Test
    public void searchUserTest() {
        System.out.println(scheduleService.findByUser(1L));
    }

    @DisplayName("날짜별 스케줄 검색")
    @Test
    public void searchScheduleTest() {
        System.out.println(scheduleService.findByDate(Date.valueOf("2022-02-01"), 1L));
    }

    @DisplayName("스케줄 기간 검색")
    @Test
    public void searchPeriodTest() {
        System.out.println(scheduleService.findByPeriod(Date.valueOf("2024-09-05"), Date.valueOf("2024-09-06"), 1L));
    }

    @DisplayName("타 사용자와 스캐줄 공유")
    @Test
    public void shareTest() {

        ScheduleDTO dto = ScheduleDTO.builder()
                .userNo(2L)
                .title("ShareTitle!!!")
                .content("ShareContent!!!")
                .checked(1)
                .color(1)
                .scheduledate(Date.valueOf("2022-02-10"))
                .scheduletime(1600)
                .build();
        Schedule schedule = scheduleService.save(dto);
        Long sno = schedule.getSno();

        scheduleService.shareSchcedule(sno, 104L);
    }


}
