package com.bridgelabz.s3.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;

public interface IS3Service {
	String s3AddImage(String imageName,String path,String folderName) throws AmazonServiceException, AmazonClientException, IOException; 
}
