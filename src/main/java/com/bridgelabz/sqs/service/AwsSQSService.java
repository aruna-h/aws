package com.bridgelabz.sqs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;

/**
 * @author bridgelabz
 * @since 2/9/2018 <br>
 *        <p>
 *        a business entity giving info about Aws SQS Service implementation
 *        </p>
 */
@Service
public class AwsSQSService implements IAwsSQSService {
	@Value("${accessKey}")
	String accessKey;
	@Value("${secretKey}")
	String secretKey;

	public void createSqs(String queueName) {
		AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
		AmazonSQS sqs = AmazonSQSClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withRegion(Regions.US_EAST_1).build();
		CreateQueueRequest createStandardQueueRequest = new CreateQueueRequest(queueName);
		String standardQueueUrl = sqs.createQueue(createStandardQueueRequest).getQueueUrl();

		Map<String, MessageAttributeValue> messageAttributes = new HashMap<>();
		messageAttributes.put("AttributeOne",
				new MessageAttributeValue().withStringValue("This is an attribute").withDataType("String"));

		SendMessageRequest sendMessageStandardQueue = new SendMessageRequest().withQueueUrl(standardQueueUrl)
				.withMessageBody("A simple message.").withDelaySeconds(30).withMessageAttributes(messageAttributes);

		sqs.sendMessage(sendMessageStandardQueue);

		ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(standardQueueUrl).withWaitTimeSeconds(1)
				.withMaxNumberOfMessages(1);
		List<Message> sqsMessages = sqs.receiveMessage(receiveMessageRequest).getMessages();
	}
}