package com.sta4l0rd.lms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sta4l0rd.lms.entity.BorrowHistory;

public interface BorrowHistoryRepo extends JpaRepository<BorrowHistory, Long> {
    public List<BorrowHistory> findByStudentIdAndBookId(Long studentId, Long bookId);
}
