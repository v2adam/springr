package com.springr.first.controller;


import com.springr.first.service.storage.ProcessXls;
import com.springr.first.service.storage.StorageService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/my_api")
public class UploadFileController {


    private StorageService storageService;

    @Autowired
    public void setStorageService(StorageService storageService) {
        this.storageService = storageService;
    }


    private ProcessXls processXls;

    @Autowired
    public void setProcessXls(ProcessXls processXls) {
        this.processXls = processXls;
    }

    // fájl feltöltés fájérendszerbe
    @PostMapping("/files/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile uploadFile) {
        if (uploadFile.isEmpty()) {
            return new ResponseEntity("please select a file!", HttpStatus.OK);
        }

        storageService.store(uploadFile);

        return new ResponseEntity("Successfully uploaded - " + uploadFile.getOriginalFilename(), new HttpHeaders(), HttpStatus.OK);
    }


    @PostMapping("/files/upload/xls")
    public ResponseEntity<?> xlsUpload(@RequestParam("file") MultipartFile uploadFile) {
        if (uploadFile.isEmpty()) {
            return new ResponseEntity("please select a file!", HttpStatus.OK);
        }

        processXls.convertFileToDTO(uploadFile);

        return new ResponseEntity("Successfully uploaded - " + uploadFile.getOriginalFilename(), new HttpHeaders(), HttpStatus.OK);
    }


    // feltöltési mappában lévő fájlok listzázása
    @GetMapping("/files")
    public List<?> listUploadedFiles() {

        @Data
        class StorageDTO {
            public String fileName;
            public String path;
        }

        return storageService.loadAll()
                .map(path -> {
                    StorageDTO item = new StorageDTO();
                    item.setFileName(path.getFileName().toString());
                    item.setPath(MvcUriComponentsBuilder.fromMethodName(UploadFileController.class, "serveFile", path.getFileName().toString()).build().toString());
                    return item;
                })
                .collect(Collectors.toList());
    }

    // adott file letöltése
    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

}
