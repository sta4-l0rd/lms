package com.sta4l0rd.lms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sta4l0rd.lms.entity.BorrowingHistory;

public interface BorrowingHistoryRepo extends JpaRepository<BorrowingHistory, Long> {

}
