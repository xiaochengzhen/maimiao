package com.example.easypoi.controller;
import com.example.easypoi.model.Student;
import com.example.easypoi.utils.ExcelUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * @description
 * @author xiaobo
 * @date 2023/5/12 11:14
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @PostMapping("/import")
    public void importStudent(@RequestParam MultipartFile multipartFile) throws Exception {
        InputStream inputStream = multipartFile.getInputStream();
        List<Student> list = ExcelUtils.importExcel(inputStream, Student.class, null);
        for (Student student : list) {
            System.out.println(student.toString());
        }
    }

    @PostMapping("/import1")
    public void importStudent1(@RequestParam MultipartFile multipartFile) throws Exception {
        File file = File.createTempFile("test",".xlsx");
        multipartFile.transferTo(file);
        List<Student> list = ExcelUtils.importExcel(file, Student.class, null);
        for (Student student : list) {
            System.out.println(student.toString());
        }
    }
}
