package com.example.POIS.Controllers;

import com.example.POIS.service.DatabaseBackupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class BackupRestoreController {

    @Autowired
    private DatabaseBackupService databaseBackupService;


    @PostMapping("/backup")
    public String backupData() throws IOException {
        databaseBackupService.backupDatabase();
        return "pages/laba4";
    }

    @PostMapping("/restore")
    public String restoreData() throws IOException {
        databaseBackupService.restoreDataBase();
        return "pages/laba4";
    }
    // Обработка запроса на восстановление данных
//    @PostMapping("/restore")
//    public String restoreData(@RequestParam("backupFile") MultipartFile backupFile) {
//        boolean success = restoreService.restoreDatabase(backupFile);
//        if (success) {
//            return "redirect:/backup-restore/page?restoreSuccess=true";
//        } else {
//            return "redirect:/backup-restore/page?restoreSuccess=false";
//        }
//    }
}
