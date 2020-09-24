package com.pdf.service.impl;

import com.pdf.dao.PdfDao;
import com.pdf.pojo.FileContent;
import com.pdf.service.PdfSeervice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PdfServiceImpl implements PdfSeervice {
    @Autowired
    private PdfDao pdfDao;

    @Override
    public void saveFileInfo(FileContent fileContent) throws Exception {
        pdfDao.saveFileInfo(fileContent);
    }

    @Override
    public FileContent getFileContent(FileContent fileContent) throws Exception {
        return pdfDao.getFileContent(fileContent);
    }
}
