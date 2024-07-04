package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.amazonaws.services.simpleemail.model.SendEmailResult;
import com.example.demo.exception.InvalidEmailException;
import com.example.demo.model.entity.User;

@Service
public class EmailServiceImpl implements EmailService {
	
	private static final String FROM = "olojedechristopher24@gmail.com";
	
	private static final String SUBJECT = "Email Verification for Complete Registration with Yokoto";
	
	private static final String HTML_BODY = """
			<p>
				Welcome to Yokoto APP, to experience our full services you will have to click 
				the link below for complete registration.
				<a href="http://localhost:8080/customers/verify-email?token=$tokenValue">Click this link for email verification</a> 
			<p>
			<h3>Yesterday was the best day to join yokoto, today is another day.</h3> 
			""";
	
	private static final String TEXT_BODY = """
			
			Welcome to Yokoto APP, to experience our full services you will have to click 
			the link below for complete registration.
			Click this link for email verification
			http://localhost:8080/customers/verify-email?token=$tokenValue" 
			
			
			Yesterday was the best day to join yokoto, today is another day.
			""";

	@Override
	public void sendEmailVerification(User user) {
		
		try {
			
			AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder
					.standard()
					.withRegion(Regions.US_EAST_2)
					.build();
			
			String htmlBodyWithToken = HTML_BODY.replace("$tokenValue", user.getEmailVerificationToken());
			String textBodyWithToken = TEXT_BODY.replace("$tokenValue", user.getEmailVerificationToken());
			
			SendEmailRequest request = new SendEmailRequest()
					.withDestination(new Destination().withToAddresses(user.getEmail()))
					.withMessage(new Message()
							.withBody(new Body()
									.withHtml(new Content()
											.withCharset("UTF-8")
											.withData(htmlBodyWithToken)
											)
									.withText(new Content()
											.withCharset("UTF-8")
											.withData(textBodyWithToken)
											)
									)
									.withSubject(new Content()
											.withCharset("UTF-8")
											.withData(SUBJECT)
											)
							)
					.withSource(FROM);
					
					SendEmailResult sendEmail = client.sendEmail(request);
					
					if (sendEmail.getMessageId() == null) {
						throw new InvalidEmailException();
					}
		}
		catch (Exception ex) {
			throw new RuntimeException("An error has occurred!");
		}
	}
}
