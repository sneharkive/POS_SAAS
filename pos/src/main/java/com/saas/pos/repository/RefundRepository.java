package com.saas.pos.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saas.pos.model.Refund;

@Repository
public interface RefundRepository extends JpaRepository<Refund, Long> {
  
  List<Refund> findByCashierIdAndCreatedAtBetween(Long cashierId, LocalDateTime from, LocalDateTime to);

  List<Refund> findByCashierId(Long cashierId);
  List<Refund> findByShiftReportId(Long shiftReportId);
  List<Refund> findByBranchId(Long branchId);

}
