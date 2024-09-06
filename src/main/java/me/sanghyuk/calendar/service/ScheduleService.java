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

    //일정을 저장하는 메서드
    public Schedule save(ScheduleDTO scheduleDTO) {
        return scheduleRepository.save(scheduleDTO.toEntity());
    }

    //변경할 일정이 사용자의 일정이 맞는 지 확인 후 변경하는 메서드
    public Schedule modify(ScheduleDTO scheduleDTO) {
        separator(scheduleDTO.getUserNo(), scheduleDTO.getSno());
        return scheduleRepository.save(scheduleDTO.toEntity());
    }

    //삭제할 일정이 사용자의 일정이 맞는 지 확인 후 삭제하는 메서드
    public void delete(Long userNo, Long sno) {
        if (separator(userNo, sno)) {
            scheduleRepository.deleteById(sno);
        }
    }

    //사용자의 일정을 검색하는 메서드
    public List<Schedule> findByUser(Long userNo) {
        return scheduleRepository.findByUser(userNo);
    }

    //날짜에 해당하는 일정을 검색하는 메서드
    public List<Schedule> findByDate(Long userNo, Date date) {
        return scheduleRepository.findByScheduledate(userNo, date);
    }

    //기간에 속하는 일정을 검색하는 메서드
    public List<Schedule> findByPeriod(Long userNo, Date start, Date end) {
        return scheduleRepository.findByPeriod(userNo, start, end);
    }

    //type으로 제목 / 내용 / 제목 + 내용 중 어느 것에 검색할 지 받은 후 키워드로 일정을 검색하는 메서드
    public List<Schedule> findByKeyword(Long userNo, String keyword, int type) {
        if (type == 1) {
            return scheduleRepository.findByTitle(userNo, keyword);
        } else if (type == 2) {
            return scheduleRepository.findByContent(userNo, keyword);
        } else {
            return scheduleRepository.findByTitleAndContent(userNo, keyword);
        }
    }

    //상태명에 따라 일정을 검색하는 메서드
    public List<Schedule> findByCheckedName(Long userNo, String checkedName) {
        return scheduleRepository.findByCheckedName(userNo, checkedName);
    }

    //색상명에 따라 일정을 검색하는 메서드
    public List<Schedule> findByColorName(Long userNo, String colorName) {
        return scheduleRepository.findByColorName(userNo, colorName);
    }

    /*
    일정을 공유하는 메서드
        일정 번호를 받아 실제 존재하는 일정인 지 확인한 후
        해당 일정을 불러와 일정 번호를 지우고 새로운 사용자에게 일정을 저장
     */
    public Schedule shareSchcedule(Long userNo, Long sno) {
        Schedule schedule = existSchedule(sno);
        ScheduleDTO scheduleDTO = entituToDTO(schedule);
        scheduleDTO.setUserNo(userNo);
        scheduleDTO.setSno(null);
        return scheduleRepository.save(scheduleDTO.toEntity());
    }

    //일정이 사용자의 일정인 지 확인하는 메서드
    public boolean separator(Long userNo, Long sno) {
        Schedule schedule = existSchedule(sno);
        return schedule.getUserNo() == userNo;
    }

    //일정이 실제로 존재하는 일정인 지 확인하는 메서드
    public Schedule existSchedule(Long sno) {
        return scheduleRepository.findById(sno).orElseThrow(() -> new IllegalStateException("Schedule not found"));
    }

    /*
    Entity DTO로 변환해주는 메서드
        Entity의 은닉, 데이터 베이스 안정 등의 이유로 DTO 활용을 위해 사용한 메서드
     */
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
