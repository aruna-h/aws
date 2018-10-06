package com.bridgelabz.s3.controller;

import java.io.IOException;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.bridgelabz.s3.dto.ResponseDto;
import com.bridgelabz.s3.exceptionservice.ToDoException;
import com.bridgelabz.s3.service.IS3Service;

@RestController
@RequestMapping("/s3service")
@RefreshScope
public class S3Controller {
	public static final Logger logger = LoggerFactory.getLogger(S3Controller.class);
	static String REQ_ID = "IN_s3";
	static String RESP_ID = "OUT_s3";
	@Autowired IS3Service is3service;
	//@PostMapping(value = "/s3service/uploadImage")
	//public ResponseEntity<?> s3AddImage( @RequestParam("imageName") String imageName,@RequestParam("path") String path,@RequestParam("folderName") String folderName);

	@RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
	public String s3AddImage( @RequestParam("imageName") String imageName,@RequestParam("path") String path,@RequestParam("folderName") String folderName) throws AmazonServiceException, AmazonClientException, ToDoException, IOException  {
//		String userId = request.getHeader("token");
//		System.out.println(userId);
		logger.info(REQ_ID+"inside s3 controller");
		is3service.s3AddImage(imageName,path,folderName);
		logger.info(RESP_ID);
		return " IMAGE sucessfully added to s3";
	}
}
