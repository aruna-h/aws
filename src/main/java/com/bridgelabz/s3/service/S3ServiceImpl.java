package com.bridgelabz.s3.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.bridgelabz.s3.controller.S3Controller;
import com.bridgelabz.s3.exceptionservice.ToDoException;
@Service
public class S3ServiceImpl implements IS3Service {
	@Value("${bucketName}")
	String bucketName;
	static final String SUFFIX = "/";
	//String imagePath = "/home/bridgelabz/Desktop/s3Images/";
	//String imageName = "butterfly.jpeg";
	public static final Logger logger = LoggerFactory.getLogger(S3ServiceImpl.class);
	static String REQ_ID = "IN_Note";
	static String RESP_ID = "OUT_Note";
	@SuppressWarnings("unused")
	public String s3AddImage(String imageName, String path, String folderName) throws AmazonServiceException, AmazonClientException, IOException {
		logger.info("adding image into s3 started");

		//Note note = noteElasticRepository.getByNoteId(noteId).get();

		//logger.info(note.getUserId());
		//String folder = note.getUserId();
		System.out.println(folderName);
		String folder = folderName;
		AWSCredentials credentials = new BasicAWSCredentials("key",
				"value");

		// create a client connection based on credentials
		AmazonS3 s3client = new AmazonS3Client(credentials);

		// create bucket - name must be unique for all S3 users
		// String bucketName = "todo-image-s3-bucket";
		s3client.createBucket(bucketName);

		// list buckets
		for (Bucket bucket : s3client.listBuckets()) {
			logger.info(" - " + bucket.getName());
		}
 File newFile=new File(path);
		// create folder into bucket
	   // String folder = "s3Images";
		createFolder(bucketName, folder, s3client);

		// upload file to folder and set it to public
		String fileName = folder + SUFFIX + newFile.getName();
	
		ObjectMetadata metadata = new ObjectMetadata();
		s3client.putObject(new PutObjectRequest(bucketName, fileName,newFile));
		String url = "https://s3.amazonaws.com/" + bucketName + fileName;

		List<String> listofurl = new ArrayList<>();
		listofurl.add(url);
		System.out.println(listofurl);
	
		return url;

	
	}

	public static void createFolder(String bucketName, String folderName, AmazonS3 client) {
		// create meta-data for your folder and set content-length to 0
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(0);
		// create empty content
		InputStream emptyContent = new ByteArrayInputStream(new byte[0]);
		// create a PutObjectRequest passing the folder name suffixed by /
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, folderName + SUFFIX, emptyContent,
				metadata);
		// send request to S3 to create folder
		client.putObject(putObjectRequest);
	}

}
