package com.example.ebangbase.mapper;

import com.example.ebangbase.model.ReconciliationErrorRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ReconciliationErrorRecordMapper {

    @Select({"<script>",
            "select id,  report_a_uk_id reportAUkId, report_b_uk_id reportBUkId, created_at createdAt, updated_at updatedAt  from tb_reconciliation_error_record order by id desc limit 10",
            "</script>"})
    List<ReconciliationErrorRecord> list();

}