package com.example.easypoi.utils;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @description 
 * @author xiaobo
 * @date 2024/1/12 13:16
 */
public class TestMeg {

    public static void main(String[] args) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("sheet1");
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("姓名");
        headerRow.createCell(1).setCellValue("年龄");
        headerRow.createCell(2).setCellValue("性别");

        Row dataRow1 = sheet.createRow(1);
        dataRow1.createCell(0).setCellValue("张三");
        dataRow1.createCell(1).setCellValue("20");
        dataRow1.createCell(2).setCellValue("男");

        Row dataRow2 = sheet.createRow(2);
        dataRow2.createCell(0).setCellValue("李四");
        dataRow2.createCell(1).setCellValue("25");
      //  dataRow2.createCell(2).setCellValue("女");

        Row dataRow3 = sheet.createRow(3);
        dataRow3.createCell(0).setCellValue("王五");
        dataRow3.createCell(1).setCellValue("26");
       // dataRow3.createCell(2).setCellValue("女");

      //  sheet.removeMergedRegion(2);
        sheet.addMergedRegion(new CellRangeAddress(1,3,2,2));
    //    sheet.addMergedRegion(new CellRangeAddress(2,3,2,2));

      /*  CellStyle rowStyle = dataRow1.getRowStyle();
        rowStyle.setAlignment();
        rowStyle.setVerticalAlignment();
*/

        FileOutputStream outputStream = new FileOutputStream("test.xlsx");
        workbook.write(outputStream);
        outputStream.close();

    }

}
