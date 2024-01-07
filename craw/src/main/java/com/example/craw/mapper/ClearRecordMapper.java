package com.example.craw.mapper;

import com.example.craw.model.ClearRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ClearRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClearRecord record);

    int insertSelective(ClearRecord record);

    ClearRecord selectByPrimaryKey(Long id);

    ClearRecord selectByclearingDate(@Param("clearingDate") String clearingDate);

    int updateByPrimaryKeySelective(ClearRecord record);

    int updateByPrimaryKey(ClearRecord record);

    int updateFinancingInterestStatus(@Param("clearingDate") String clearingDate, @Param("oldStatus")Integer oldStatus, @Param("newStatus") Integer newStatus);
    int updateStockBorrowInterestStatus(@Param("clearingDate") String clearingDate, @Param("oldStatus")Integer oldStatus, @Param("newStatus") Integer newStatus);
    int updateClearStatus(@Param("clearingDate") String clearingDate, @Param("oldStatus")Integer oldStatus, @Param("newStatus") Integer newStatus);
}