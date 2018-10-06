package com.bridgelabz.sqs.service;

/**
 * @author bridgelabz
 * @since 2/9/2018 <br>
 *        <p>
 *        a business entity giving info about aws sqs service
 *        </p>
 */
public interface IAwsSQSService {
	public void createSqs(String queueName);

}
