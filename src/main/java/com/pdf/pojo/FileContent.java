package com.pdf.pojo;

public class FileContent {
    private Long id;
    private String fileName;
    private String path;
    private byte[] content;
    private String suffix;
    private String fileDate;
    private String fileNo;
    private String remark;
    private String fileCapitalize;
    private String fileAmout;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileDate() {
        return fileDate;
    }

    public void setFileDate(String fileDate) {
        this.fileDate = fileDate;
    }

    public String getFileNo() {
        return fileNo;
    }

    public void setFileNo(String fileNo) {
        this.fileNo = fileNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFileCapitalize() {
        return fileCapitalize;
    }

    public void setFileCapitalize(String fileCapitalize) {
        this.fileCapitalize = fileCapitalize;
    }

    public String getFileAmout() {
        return fileAmout;
    }

    public void setFileAmout(String fileAmout) {
        this.fileAmout = fileAmout;
    }
}
