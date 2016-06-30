package com.dsinnovators.aauth.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.resource.instance.Message;

@Service
public class SmsSender {

	/* Find your sid and token at twilio.com/user/account */
	public static final String ACCOUNT_SID = "ACd817ad59f1b14521fed9b4ee233001e6";
	public static final String AUTH_TOKEN = "fc0a55500acf0aeb35501cf5f220b743";

	// Replace with a valid phone number for your account.
	public static final String SENDER_NUMBER = "+12054172059";

	public void send(String number, String msg) {

		TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);

		Account account = client.getAccount();

		MessageFactory messageFactory = account.getMessageFactory();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("To", number));
		params.add(new BasicNameValuePair("From", SENDER_NUMBER));
		params.add(new BasicNameValuePair("Body", msg));
		Message sms = null;
		try {
			sms = messageFactory.create(params);
		} catch (TwilioRestException e) {
			e.printStackTrace();
		}
		if (sms != null) {
			System.out.println(sms.getStatus());
		}
	}
}