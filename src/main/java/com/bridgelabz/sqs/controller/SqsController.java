package com.bridgelabz.sqs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.sqs.dto.ResponseDto;
import com.bridgelabz.sqs.service.IAwsSQSService;

@RestController
@RequestMapping("/sqsservice")
public class SqsController {
	public static final Logger logger = LoggerFactory.getLogger(SqsController.class);
	static String REQ_ID = "IN_sqs";
	static String RESP_ID = "OUT_sqs";
	@Autowired
	IAwsSQSService iSqsService;

	@RequestMapping(value = "/UseSqs", method = RequestMethod.POST)
	public ResponseEntity<ResponseDto> createSqs(@RequestParam String queueName) {
		logger.info(REQ_ID);
		iSqsService.createSqs(queueName);
		logger.info(RESP_ID);
		return new ResponseEntity(" message sent through sqs", HttpStatus.OK);
	}
}