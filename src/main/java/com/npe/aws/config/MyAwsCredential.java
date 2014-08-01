package com.npe.aws.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;

import java.io.IOException;

/**
 * Created by Vikranth on 7/23/2014.
 */
public class MyAwsCredential {
    public static AWSCredentials MyAWSCredential(){
        AWSCredentials credentials = null;
        try {
               credentials = new PropertiesCredentials(
                    MyAwsCredential.class.getResourceAsStream("AwsCredentials.properties"));
                return credentials;
            } catch (IOException ioex){
                ioex.printStackTrace();
            }finally {
            return credentials;
        }
      }
   /* public static AmazonS3 MyAWSS3Credential() {
        AmazonS3 s3credential = null;
        try {
            s3credential = new AmazonS3Client(new PropertiesCredentials(
                    MyAwsCredential.class.getResourceAsStream("AwsCredentials.properties")));
            System.out.println("reading properties file" );
            return s3credential;
        }catch (IOException ioex) {
            ioex.printStackTrace();
        }
        finally {
            return s3credential;
        }

    }*/
}
