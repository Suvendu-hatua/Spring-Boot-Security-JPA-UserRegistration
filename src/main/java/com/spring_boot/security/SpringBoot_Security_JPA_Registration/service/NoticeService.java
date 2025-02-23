package com.spring_boot.security.SpringBoot_Security_JPA_Registration.service;

import com.spring_boot.security.SpringBoot_Security_JPA_Registration.dao.NoticeDao;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.entity.Notice;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeDao noticeDao;

    @Transactional
    public void saveNotice(Notice notice) {
        LocalDateTime dateTime = LocalDateTime.now();
        // Define a custom format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yy hh:mm a");
        // Format and print
        System.out.println(dateTime.format(formatter));
        //setting current date
        notice.setNoticeDate(dateTime.format(formatter));
        noticeDao.save(notice);
    }

    public List<Notice> getAllNotices() {
        return noticeDao.findAll();
    }
}
