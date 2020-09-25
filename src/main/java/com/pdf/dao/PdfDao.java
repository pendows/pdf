package com.pdf.dao;

import com.pdf.pojo.FileContent;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PdfDao {
    @Insert({"INSERT INTO `pdf_exchange`.`pdf_infomation` " +
            "(`ID`, `FILE_NAME`, `FILE_PATH`, `FILE_DATE`, `FILE_TICKET_TAX_NO`, `FILE_AMOUT`, `FILE_AMOUNT_CAPITALIZE`, " +
            "`FILE_REMARK`, `FILE_TICKET_TAX_CODE`, `FILE_TICKET_TAX_TYPE`) " +
            "VALUES (#{id}, #{fileName}, #{path}, #{fileDate}, #{fileNo}, #{fileAmout}, #{fileCapitalize}, #{remark}, null, null);"})
    void saveFileInfo(FileContent fileContent) throws Exception;

    @Select({"<script>",
            "select ID as id,FILE_NAME as fileName,FILE_AMOUNT_CAPITALIZE as fileCapitalize FILE_AMOUT as fileAmout,FILE_DATE as fileDate,FILE_PATH as path,FILE_TICKET_TAX_NO as fileNo",
                    " from pdf_infomation ",
                    "where ID = #{id}",
                    "<when test='fileDate !=null'>",
                    " AND FILE_DATE = #{fileDate}",
                    "</when>",
                    "<when test='fileAmout !=null'>",
                    " AND FILE_AMOUT = #{fileAmout}",
                    "</when>",
                    "</script>"})
    FileContent getFileContent(FileContent fileContent) throws Exception;
}
