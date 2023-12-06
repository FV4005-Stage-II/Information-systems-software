package com.example.POIS.service;

import com.example.POIS.data.Student;
import com.example.POIS.repos.StudentRepos;
import com.thoughtworks.xstream.XStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.apache.commons.io.IOUtils;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class DatabaseBackupService {

    @Autowired
    StudentRepos studentRepos;

    public void backupDatabase() throws IOException {
        OutputStream os = new FileOutputStream("C:\\Users\\FV4005_Stage_II\\Desktop\\backup.xml");
        saveXml(os);
    }

    public void restoreDataBase() throws IOException {
        InputStream is = new FileInputStream("C:\\Users\\FV4005_Stage_II\\Desktop\\backup.xml");
        loadXml(is);
    }
    private void loadXml(InputStream is) throws IOException {
        XStream xstream = new XStream();
        String xmlData = IOUtils.toString(is); // , "UTF-8" если вставить кодировка слетает
        studentRepos.deleteAll();
        xstream.allowTypesByWildcard(new String[] { "com.example.POIS.**" });
        studentRepos.saveAll((List<Student>)xstream.fromXML(xmlData));
    }
    private void saveXml(OutputStream os) throws IOException {
        OutputStreamWriter osw = new OutputStreamWriter(os);
        XStream xstream = new XStream();
        String dataXml =  xstream.toXML(studentRepos.findAll());
        osw.write(dataXml);
        osw.flush();
        osw.close();
    }

}

