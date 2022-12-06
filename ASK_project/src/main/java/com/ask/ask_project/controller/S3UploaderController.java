//package com.ask.ask_project.controller;
//
//import com.ask.ask_project.service.S3UploaderService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//
//@RequiredArgsConstructor
//@Controller
//public class S3UploaderController {
//    private final S3UploaderService s3UploaderService;
//
//    @RequestMapping("/image-upload")
//    @ResponseBody
//    public String imageUpload(@RequestParam("data") MultipartFile multipartFile) throws IOException {
//        return s3UploaderService.upload(multipartFile, "elasticbeanstalk-ap-northeast-2-299253517068", "receipt");
//    }
//
//}
