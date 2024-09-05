package me.sanghyuk.calendar.service;

import lombok.RequiredArgsConstructor;
import me.sanghyuk.calendar.dto.ScheduleDTO;
import me.sanghyuk.calendar.entity.Schedule;
import me.sanghyuk.calendar.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public Schedule save(ScheduleDTO scheduleDTO) {
        return scheduleRepository.save(scheduleDTO.toEntity());
    }

    public Schedule modify(ScheduleDTO scheduleDTO) {
        separator(scheduleDTO.getUserNo(), scheduleDTO.getSno());
        return scheduleRepository.save(scheduleDTO.toEntity());
    }

    public void delete(Long userNo, Long sno) {
        if (separator(userNo, sno)) {
            scheduleRepository.deleteById(sno);
        }
    }

    public List<Schedule> findByUser(Long userNo) {
        return scheduleRepository.findByUser(userNo);
    }

    public List<Schedule> findByDate(Long userNo, Date date) {
        return scheduleRepository.findByScheduledate(userNo, date);
    }

    public List<Schedule> findByPeriod(Long userNo, Date start, Date end) {
        return scheduleRepository.findByPeriod(userNo, start, end);
    }

    public List<Schedule> findByKeyword(Long userNo, String keyword, int type) {
        if (type == 1) {
            return scheduleRepository.findByTitle(userNo, keyword);
        } else if (type == 2) {
            return scheduleRepository.findByContent(userNo, keyword);
        } else {
            return scheduleRepository.findByTitleAndContent(userNo, keyword);
        }
    }

    public List<Schedule> findByCheckedName(Long userNo, String checkedName) {
        return scheduleRepository.findByCheckedName(userNo, checkedName);
    }

    public List<Schedule> findByColorName(Long userNo, String colorName) {
        return scheduleRepository.findByColorName(userNo, colorName);
    }

    public Schedule shareSchcedule(Long userNo, Long sno) {
        Schedule schedule = scheduleRepository.findById(sno).orElseThrow(() -> new IllegalStateException("Schedule not found"));
        ScheduleDTO scheduleDTO = entituToDTO(schedule);
        scheduleDTO.setUserNo(userNo);
        scheduleDTO.setSno(null);
        return scheduleRepository.save(scheduleDTO.toEntity());
    }

    public boolean separator(Long userNo, Long sno) {
        Schedule schedule = scheduleRepository.findById(sno).orElseThrow(() -> new IllegalStateException("Schedule not found"));
        return schedule.getUserNo() == userNo;
    }

    public ScheduleDTO entituToDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = ScheduleDTO.builder()
                .sno(schedule.getSno())
                .userNo(schedule.getUserNo())
                .title(schedule.getTitle())
                .content(schedule.getContent())
                .chno(schedule.getChno())
                .cno(schedule.getCno())
                .scheduledate(schedule.getScheduledate())
                .scheduletime(schedule.getScheduletime())
                .build();
        return scheduleDTO;
    }
}
