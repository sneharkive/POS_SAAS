package com.saas.pos.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.saas.pos.dto.RefundDto;
import com.saas.pos.service.RefundService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/refunds")
@RequiredArgsConstructor
public class RefundController {

  private final RefundService refundService;

  @PostMapping()
  public ResponseEntity<RefundDto> createRefund(@RequestBody RefundDto refundDto) throws Exception {

    return ResponseEntity.ok(refundService.createRefund(refundDto));
  }

  @GetMapping()
  public ResponseEntity<List<RefundDto>> getAllRefund() throws Exception {

    return ResponseEntity.ok(refundService.getAllRefund());
  }

  @GetMapping("/cashier/{cashierId}")
  public ResponseEntity<List<RefundDto>> getRefundByCashier(@PathVariable Long cashierId) throws Exception {

    return ResponseEntity.ok(refundService.getRefundByCashier(cashierId));
  }

  @GetMapping("/shift/{shiftReportId}")
  public ResponseEntity<List<RefundDto>> getRefundByShiftReport(@PathVariable Long shiftReportId) throws Exception {

    return ResponseEntity.ok(refundService.getRefundByShiftReport(shiftReportId));
  }

  @GetMapping("/cashier/{cashierId}/range")
  public ResponseEntity<List<RefundDto>> getRefundByCashierAndDateRange(@PathVariable Long cashierId,
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) throws Exception {

    return ResponseEntity.ok(refundService.getRefundByCashierAndDateRange(cashierId, startDate, endDate));
  }

  @GetMapping("/branch/{branchId}")
  public ResponseEntity<List<RefundDto>> getRefundByBranch(@PathVariable Long branchId) throws Exception {

    return ResponseEntity.ok(refundService.getRefundByBranch(branchId));
  }

  @GetMapping("/{id}")
  public ResponseEntity<RefundDto> getRefundByID(@PathVariable Long id) throws Exception {

    return ResponseEntity.ok(refundService.getRefundByID(id));
  }

}
