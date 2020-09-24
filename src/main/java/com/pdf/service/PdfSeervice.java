package com.pdf.service;

import com.pdf.pojo.FileContent;

public interface PdfSeervice {
    void saveFileInfo(FileContent fileContent) throws Exception;

    FileContent getFileContent(FileContent fileContent);
}
