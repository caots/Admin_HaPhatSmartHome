/*
package com.bksoftware.sellingweb.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/upload")
public class FileUploadController {
    private final static Logger LOGGER = Logger.getLogger(FileController.class.getName());


    private static final String URL_UPLOADFILE = "https://image.bksoftwarevn.com/data/public/resources/img";

    private static final String UPLOAD_DIRECTORY = "/home/tomcat/webapps/data/public/resources/img";

//    private static final String UPLOAD_DIRECTORY = "C:\\Users\\Admin\\IdeaProjects\\haphatsmarthome-admin\\src\\main\\resources\\static\\img\\product";

    @RequestMapping(value = "/singleSave", method = RequestMethod.POST,
            headers = "Content-Type=multipart/form-data", produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<Object> singleSave(@RequestParam("file") MultipartFile[] file) {

        String fileName = null;
        if (file != null && !file[0].isEmpty()) {
               try {
                   fileName = file[0].getOriginalFilename().replaceAll(".\\.+", String.valueOf(System.currentTimeMillis()) + ".");
                   String dirFile = UPLOAD_DIRECTORY;
                   System.out.println(dirFile);
                   File fileDir = new File(dirFile);
                   if (!fileDir.exists()) {
                       fileDir.mkdir();
                   }
                   byte[] bytes = file[0].getBytes();
                   try (BufferedOutputStream buffStream = new BufferedOutputStream(
                           new FileOutputStream(new File(fileDir.getAbsolutePath() + File.separator + fileName)))) {
                       buffStream.write(bytes);
                   }

               }catch (Exception e){

               }
        }
        return new ResponseEntity<>(fileName, HttpStatus.OK);
    }
}*/
