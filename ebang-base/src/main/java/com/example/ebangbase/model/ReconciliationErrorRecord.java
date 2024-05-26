package com.example.ebangbase.model;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * tb_reconciliation_error_record
 * @author 
 */
@Data
public class ReconciliationErrorRecord implements Serializable {
    private Long id;


    private String clearingDate;


    private Long reconciliationId;


    private Boolean reconciliationType;


    private Date reconciliationDate;


    private Boolean errorType;


    private Boolean reportAType;


    private String reportAId;


    private String errorAInformation1;


    private String errorAInformation2;


    private String errorAInformation3;


    private String errorAContent;


    private Boolean reportBType;


    private String reportBId;


    private String errorBInformation1;


    private String errorBInformation2;


    private String errorBInformation3;


    private String errorBContent;

    private Long reportAUkId;

    private Long reportBUkId;


    private Date createdAt;

    private Date updatedAt;



    public ReconciliationErrorRecord(Long id, String clearingDate, Long reconciliationId, Boolean reconciliationType, Date reconciliationDate, Boolean errorType, Boolean reportAType, String reportAId, String errorAInformation1, String errorAInformation2, String errorAInformation3, String errorAContent, Boolean reportBType, String reportBId, String errorBInformation1, String errorBInformation2, String errorBInformation3, String errorBContent, Long reportAUkId, Long reportBUkId, Date createdAt, Date updatedAt) {
        this.id = id;
        this.clearingDate = clearingDate;
        this.reconciliationId = reconciliationId;
        this.reconciliationType = reconciliationType;
        this.reconciliationDate = reconciliationDate;
        this.errorType = errorType;
        this.reportAType = reportAType;
        this.reportAId = reportAId;
        this.errorAInformation1 = errorAInformation1;
        this.errorAInformation2 = errorAInformation2;
        this.errorAInformation3 = errorAInformation3;
        this.errorAContent = errorAContent;
        this.reportBType = reportBType;
        this.reportBId = reportBId;
        this.errorBInformation1 = errorBInformation1;
        this.errorBInformation2 = errorBInformation2;
        this.errorBInformation3 = errorBInformation3;
        this.errorBContent = errorBContent;
        this.reportAUkId = reportAUkId;
        this.reportBUkId = reportBUkId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public ReconciliationErrorRecord(Long id, Long reportAUkId, Date createdAt, Long reportBUkId,  Date updatedAt) {
        this.id = id;
        this.reportAUkId = reportAUkId;
        this.createdAt = createdAt;
        this.reportBUkId = reportBUkId;
        this.updatedAt = updatedAt;
    }
}