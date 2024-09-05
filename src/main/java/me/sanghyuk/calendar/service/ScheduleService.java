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

    public Schedule findById(long sno) {
        return scheduleRepository.findById(sno).orElseThrow(() -> new IllegalStateException("Schedule not found"));
    }

    public List<Schedule> findByUser(Long userNo) {
        return scheduleRepository.findByUser(userNo);
    }

    public List<Schedule> findByDate(Date date, Long userNo) {
        return scheduleRepository.findByScheduledate(date, userNo);
    }

    public List<Schedule> findByPeriod(Date start, Date end, Long userNo) {
        return scheduleRepository.findByPeriod(start, end, userNo);
    }

    public Schedule shareSchcedule(Long sno, Long userNo) {
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
                .checked(schedule.getChecked())
                .color(schedule.getColor())
                .scheduledate(schedule.getScheduledate())
                .scheduletime(schedule.getScheduletime())
                .build();
        return scheduleDTO;
    }
}
