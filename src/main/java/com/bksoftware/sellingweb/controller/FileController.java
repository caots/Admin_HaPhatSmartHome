package com.bksoftware.sellingweb.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


@RestController
@RequestMapping("/api/v1/public")
public class FileController {

    private final static Logger LOGGER = Logger.getLogger(FileController.class.getName());


    private static final String URL_UPLOAD_FILE = "https://image.bksoftwarevn.com/data/public/resources/img/haphatsmarthome/";

    private static final String UPLOAD_DIRECTORY = "/home/tomcat/webapps/data/public/resources/img/haphatsmarthome";

//    private static final String UPLOAD_DIRECTORY = "C:\\Users\\Admin\\IdeaProjects\\haphatsmarthome-admin\\src\\main\\resources\\static\\img\\product";

    @PostMapping(value = "/upload-file")
    public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) {
        multiPartFile(file);
        System.out.println(file);
        String rs = URL_UPLOAD_FILE + file.getOriginalFilename();
        System.out.println(rs);
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }

    private void multiPartFile(@RequestParam("file") MultipartFile file) {
        File uploadedFile = new File(UPLOAD_DIRECTORY, file.getOriginalFilename());
        OutputStream stream;
        try {
            uploadedFile.createNewFile();
            stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
            stream.write(file.getBytes());
            stream.flush();
            stream.close();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "upload-file-error : {0}", e.getMessage());
        }
    }


    //method for uploading multiple files
    @PostMapping(value = "/upload-multi-files", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<String>> uploadMultiFile(@RequestParam("files") MultipartFile[] files) {
        List<String> nameImageNews = new ArrayList<>();
        for (MultipartFile multipartFile : files) {
            multiPartFile(multipartFile);
            if (multipartFile.getOriginalFilename().isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            String rs = URL_UPLOAD_FILE + multipartFile.getOriginalFilename();
            System.out.println(rs);
            nameImageNews.add(rs);
        }
        return new ResponseEntity<>(nameImageNews, HttpStatus.OK);
    }


}
