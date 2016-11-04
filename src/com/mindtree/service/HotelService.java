package com.mindtree.service;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.*;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.security.auth.login.CredentialException;

import com.mindtree.dao.daoImpl;
import com.mindtree.entity.BookingDetails;
import com.mindtree.entity.Hotel;
import com.mindtree.entity.Users;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.services.sns.model.DeleteTopicRequest;

/**
 * @author M1035913
 * 
 *
 */
public class HotelService {

	/**
	 * @param searchString
	 * @return
	 */
	daoImpl dao = new daoImpl();

	public ArrayList<Hotel> SearchString(String searchString) {
		ArrayList<Hotel> list = new ArrayList<Hotel>();
		list = dao.SearchString(searchString);
		return list;
	}

	/**
	 * @param hotelid
	 * @return
	 */
	public Hotel hotelinfo(int hotelid) {
		Hotel hotel = new Hotel();
		hotel = dao.hotelInfo(hotelid);
		return hotel;
	}

	/**
	 * @param email
	 * @param pass
	 * @return
	 * @throws Exception
	 */
	public Users fetchUserService(String email, String pass) throws Exception {
		return dao.fetchUser(email, pass);

	}

	/**
	 * @param bo
	 */
	public void insertBookingDetails(BookingDetails bo) {
		dao.insertData(bo);
	}

	/**
	 * @param userid
	 * @return
	 */
	public List<BookingDetails> fetchBookingService(int userid) {
		return dao.fetchBooking(userid);

	}

	// **********************************************************************************
	public int forMail(String mesg, String email) {

		final String TO = "sourabh.sharma@mindtree.com"; // Replace with your
															// "From" address.
															// This address must
															// be verified.
		final String FROM = "mesourabhsharma@gmail.com"; // Replace with a "To"
															// address. If your
															// account is still
															// in the
															// sandbox, this
															// address must be
															// verified.

		final String BODY = "hiThis email was sent through the Amazon SES SMTP interface by using Java.";
		final String SUBJECT = "Amazon SES test (SMTP interface accessed using Java)";
		final String message = "mys name";

		// Supply your SMTP credentials below. Note that your SMTP credentials
		// are different from your AWS credentials.
		final String SMTP_USERNAME = ""; // Replace with
																// your SMTP
																// username.
		final String SMTP_PASSWORD = ""; // Replace
																						// with
																						// your
																						// SMTP
																						// password.

		// Amazon SES SMTP host name. This example uses the US West (Oregon)
		// region.
		final String HOST = "email-smtp.us-west-2.amazonaws.com";

		// The port you will connect to on the Amazon SES SMTP endpoint. We are
		// choosing port 25 because we will use
		// STARTTLS to encrypt the connection.
		final int PORT = 25;

		try {
			// Create a Properties object to contain connection configuration
			// information.
			Properties props = System.getProperties();
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.port", PORT);

			// Set properties indicating that we want to use STARTTLS to encrypt
			// the connection.
			// The SMTP session will begin on an unencrypted connection, and
			// then the client
			// will issue a STARTTLS command to upgrade to an encrypted
			// connection.
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.starttls.required", "true");

			// Create a Session object to represent a mail session with the
			// specified properties.
			Session session = Session.getDefaultInstance(props);

			// Create a message with the specified information.
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(FROM));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
			msg.setSubject(SUBJECT);
			msg.setContent(BODY + "\n message is : " + message, "text/plain");

			// Create a transport.
			Transport transport = session.getTransport();

			// Send the message.
			// try
			// {
			System.out.println("Attempting to send an email through the Amazon SES SMTP interface...");

			// Connect to Amazon SES using the SMTP username and password you
			// specified above.
			transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
			// Send the email.
			transport.sendMessage(msg, msg.getAllRecipients());
			System.out.println("Email sent!");
			return 1;
			// }
		} catch (Exception ex) {
			System.out.println("The email was not sent.");
			System.out.println("Error message: " + ex.getMessage());

			ex.printStackTrace();
			return 0;

		}
		/*
		 * finally { // Close and terminate the connection. transport.close(); }
		 */
	}
	// ***************************************************************************

	public int ses(String email) {

		AWSCredentials credentials = null;
		// BasicAWSCredentials awsCreds=new BasicAWSCredentials(accessKey,
		// secretKey)

		credentials = new AWSCredentials() {
			@Override
			public String getAWSSecretKey() {
				String k = "";
				return k;
			}

			@Override
			public String getAWSAccessKeyId() {
				String kk = "";
				return kk;
			}
		};

		// credentials = new
		// ProfileCredentialsProvider("default").getCredentials();

		// create a new SNS client and set endpoint
		AmazonSNSClient snsClient = new AmazonSNSClient(credentials);
		snsClient.setRegion(Region.getRegion(Regions.AP_SOUTHEAST_2));

		// create a new SNS topic
		/*
		 * CreateTopicRequest createTopicRequest = new
		 * CreateTopicRequest("MyNewTopicSorbhs"); CreateTopicResult
		 * createTopicResult = snsClient.createTopic(createTopicRequest);
		 * //print TopicArn System.out.println(createTopicResult); //get request
		 * id for CreateTopicRequest from SNS metadata System.out.println(
		 * "CreateTopicRequest - " +
		 * snsClient.getCachedResponseMetadata(createTopicRequest));
		 */
		// subscribe to an SNS topic
		/*
		 * SubscribeRequest subRequest = new SubscribeRequest(
		 * "arn:aws:sns:ap-southeast-2:543064653246:MyNewTopicSorbhs", "email",
		 * "mesourabhsharma@gmail.com"); snsClient.subscribe(subRequest); //get
		 * request id for SubscribeRequest from SNS metadata System.out.println(
		 * "SubscribeRequest - " +
		 * snsClient.getCachedResponseMetadata(subRequest)); System.out.println(
		 * "Check your email and confirm subscription.");
		 */
		// publish to an SNS topic
		String msg = "Hotel successfully booked.Thank you!!!";
		PublishRequest publishRequest = new PublishRequest("arn:aws:sns:ap-southeast-2:543064653246:MyNewTopicSorbhs",
				msg);
		PublishResult publishResult = snsClient.publish(publishRequest);
		// print MessageId of message published to SNS topic
		System.out.println("MessageId - " + publishResult.getMessageId());
		return 1;
	}
	
	
	public String upload()
	{
		PutObjectResult result;
		String etag="yes";
		String bucketName     = "hoteldocs";
		String keyName        = "key";
		//String uploadFileName = "C:/Users/M1035913/Desktop/sss.txt";
		String uploadFileName = "/usr/share/tomcat7/sss.txt";
	
		/*	
		ClientConfiguration clientConfig = new ClientConfiguration();
			clientConfig.setProxyHost("172.22.218.218");
			clientConfig.setProxyPort(8085);
			clientConfig.withProtocol(Protocol.HTTP);*/
		
			AWSCredentials credentials = new BasicAWSCredentials("",
						"");			
			
			
			AmazonS3 s3client = new AmazonS3Client(credentials);
			//AmazonS3 s3client=new AmazonS3Client(credentials, clientConfig);
	        try {
	            System.out.println("Uploading a new object to S3 from a file\n");
	            File file = new File(uploadFileName);
	            result=s3client.putObject(new PutObjectRequest(
	            		                 bucketName, keyName, file));
	            etag=result.getETag();

	         } catch (AmazonServiceException ase) {
	            System.out.println("Caught an AmazonServiceException, which " +
	            		"means your request made it " +
	                    "to Amazon S3, but was rejected with an error response" +
	                    " for some reason.");
	            System.out.println("Error Message:    " + ase.getMessage());
	            System.out.println("HTTP Status Code: " + ase.getStatusCode());
	            System.out.println("AWS Error Code:   " + ase.getErrorCode());
	            System.out.println("Error Type:       " + ase.getErrorType());
	            System.out.println("Request ID:       " + ase.getRequestId());
	        } catch (AmazonClientException ace) {
	            System.out.println("Caught an AmazonClientException, which " +
	            		"means the client encountered " +
	                    "an internal error while trying to " +
	                    "communicate with S3, " +
	                    "such as not being able to access the network.");
	            System.out.println("Error Message: " + ace.getMessage());
	        }

	return etag;
	}
		
	public String upload2() {
		File file;
		String err;
		AmazonS3Client s3 ;
		AWSCredentials credentials; 
		try{
		 //file = new File("/usr/sss.txt");
		  file = new File("C:/Users/M1035913/Desktop/sss.txt");
		/////////////////////////////////
		}catch(Exception e){
			System.out.println("here 1");
			err=e.getMessage();
		//	err=e.getLocalizedMessage();
//			throw new Exception(e);
			return err;
			//e.printStackTrace();
		}
		
		//AWSCredentials credentials = null;
	//	BasicAWSCredentials awsCreds =null;
		try {
			//Properties prop = new Properties();
			//InputStream input = null;
			
			/*input =getClass().getClassLoader().getResourceAsStream("/HotelReservation/credentialsAWS.properties");
			System.out.println("-----------------------"+input.hashCode());
			System.out.println("-----------------------"+input.hashCode());
			try{
			prop.load(input);
			// Create a Properties object to contain connection configuration
			// information.
			}
			catch(Exception exception){
				exception.printStackTrace();
			}
			System.out.println("size of prop"+prop.size());
			*/
			//System.out.println("1");
		//	 Replace
																		// with
			// your SMTP
			// username.
		//	System.out.println("2");
		//	System.out.println("3"); 
		//	 System.out.println("4");
		///credentials = new ProfileCredentialsProvider("default").getCredentials();*/
			 credentials = new BasicAWSCredentials("",
					"");
		} catch (Exception e){
			
			err=e.getMessage();
			return err;
			/*throw new AmazonClientException("Cannot load the credentials from the credential profiles file. "
					+ "Please make sure that your credentials file is at the correct "
					+ "location (C:\\Users\\M103\\.aws\\credentials), and is in valid format.", e);
*/
		
		}
		
		try{
		s3 = new AmazonS3Client(credentials);		
		}catch(Exception e)
		{
			err=e.getMessage();
			return err;
			
		}
		///////////////////////////////////////
		
		//File file = new File("/home/ec2-user/sss.txt");
		
	//   File file = new File("C:/Users/M1035913/Desktop/sss.txt");
		
		//File file = new File("C:/Users/Administrator/Downloads/sss.txt");		
		
		//AmazonS3 s3;
		String etag=null;
		String bucketName = "hoteldocs";
	//	ClientConfiguration clientConfig = new ClientConfiguration();
	//	clientConfig.setProxyHost("172.22.218.218");
		//clientConfig.setProxyHost("172.31.27.72");
	//	clientConfig.setProxyPort(8085);
	//	clientConfig.withProtocol(Protocol.HTTP);
		/*BasicAWSCredentials awsCreds = null;
		try {
		} catch (Exception e) {
			throw new AmazonClientException("Cannot load the credentials from the credential profiles file. "
					+ "Please make sure that your credentials file is at the correct "
					+ "location (C:\\Users\\M1036004\\.aws\\credentials), and is in valid format.", e);
		}*/
	//	s3 = new AmazonS3Client(credentials, clientConfig);
		//s3=new AmazonS3Client(awsCreds);
		try{
		String key = "Bucketkey1";
		System.out.println("Uploading object in a bucket through program......................");
		System.out.println("Bucket name is :" + bucketName);
		System.out.println("Key of bucket is :" + key);
		System.out.println("value of file :" + file.getAbsolutePath());
		}catch(Exception e)
		{
			err=e.getMessage();
			return err;			
		}
		try {
			System.out.println("before metadata");
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentLength(0);	
			String keyName = "key";
			System.out.println("before put object");
			PutObjectResult result = s3.putObject(new PutObjectRequest(bucketName, keyName, file));
			System.out.println("after putobject");
			System.out.println(result.getETag());
			etag=result.getETag();
		
		} catch(Exception e) {
			err=e.getMessage();
			//System.out.println("before stack");
			System.out.println(err);
			//ex.printStackTrace();
			System.out.println("after printstack");
			return err;
		}
		return etag;
	}
}