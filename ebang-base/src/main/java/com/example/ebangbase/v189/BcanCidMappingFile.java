package com.example.ebangbase.v189;

import org.apache.commons.lang3.StringUtils;

/**
 * @description 
 * @author xiaobo
 * @date 2024/3/18 15:10
 */
public class BcanCidMappingFile {

    /**
     *
     */
    private String recordType = "H";
    private String fileID = "HBCNMAPP";
    private String fileFormatVersion = "1";
    private String ceNumber;
    private String submissionDate;
    private String submissionSequenceNumber;

    public static void main(String[] args) {
        String s = StringUtils.appendIfMissing("1223.563", ",");
        System.out.println(s);
    }

}
