package me.sanghyuk.calendar.service;

import lombok.RequiredArgsConstructor;
import me.sanghyuk.calendar.dto.ScheduleDTO;
import me.sanghyuk.calendar.entity.Schedule;
import me.sanghyuk.calendar.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public Schedule save(ScheduleDTO scheduleDTO) {
        return scheduleRepository.save(scheduleDTO.toEntity());
    }

    public Schedule modify(ScheduleDTO scheduleDTO) {
        scheduleRepository.findById(scheduleDTO.getSno()).orElseThrow(() -> new IllegalStateException("Schedule not found"));
        return scheduleRepository.save(scheduleDTO.toEntity());
    }

    public void delete(ScheduleDTO scheduleDTO) {
        scheduleRepository.findById(scheduleDTO.getSno()).orElseThrow(() -> new IllegalStateException("Schedule not found"));
        scheduleRepository.delete(scheduleDTO.toEntity());
    }

    public Schedule findById(long sno) {
        return scheduleRepository.findById(sno).orElseThrow(() -> new IllegalStateException("Schedule not found"));
    }

    public List<Schedule> findByUser(Long user) {
        return scheduleRepository.findByUser(user);
    }

    public List<Schedule> findByDate(Date date, Long user) {
        return scheduleRepository.findByScheduledate(date, user);
    }

    public List<Schedule> findByPeriod(Date start, Date end, Long user) {
        return scheduleRepository.findByPeriod(start,end,user);
    }
    public Schedule shareSchcedule(Long sno, Long user){
        Schedule schedule = scheduleRepository.findById(sno).orElseThrow(() -> new IllegalStateException("Schedule not found"));
        ScheduleDTO scheduleDTO = entituToDTO(schedule);
        scheduleDTO.setUser(user);
        scheduleDTO.setSno(null);
        return scheduleRepository.save(scheduleDTO.toEntity());
    }
    public ScheduleDTO entituToDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = ScheduleDTO.builder()
                .sno(schedule.getSno())
                .user(schedule.getUser())
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
