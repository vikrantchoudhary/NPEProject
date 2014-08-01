package com.npe.aws.s3;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3EncryptionClient;
import com.amazonaws.services.s3.model.*;
import com.npe.aws.config.MyAwsCredential;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.File;
import java.lang.Exception;
import java.lang.System;
import java.security.spec.KeySpec;
import java.util.UUID;

/**
 * Created by Vikranth on 7/23/2014.
 *
 */
public class CopyFileToS3 {

    public static void main(String args[]) {
        try {
            byte[] salt = {
                    (byte)0xA9, (byte)0x9B, (byte)0xC8, (byte)0x32,
                    (byte)0x56, (byte)0x34, (byte)0xE3, (byte)0x03
            };
            String passPhrase = "password";

            int iterationCount = 19;

            KeySpec keySpec = new PBEKeySpec(passPhrase.toCharArray(), salt, iterationCount);
            SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);

            AmazonS3 s3 = new AmazonS3EncryptionClient(new PropertiesCredentials(
                    CopyFileToS3.class.getResourceAsStream("AwsCredentials.properties")), new EncryptionMaterials(key));
            String bucketName = "s3-bucket-" + UUID.randomUUID();
            String key1 = "CDAKey";
            System.out.println("===========================================");
            System.out.println("Getting Started with Amazon S3");
            System.out.println("===========================================\n");
            try {
                /**
                 * final String username = "LGLFHJDMAL6BG1TXBYBAL";
                 final String password = "cqTdTJB7tb1pDYRsjjtF0Pm0tvebKSfCZDDlFNJv";
                 BasicAWSCredentials credentials = new BasicAWSCredentials(username,
                 password);

                 AmazonS3 s3 = new AmazonS3Client(credentials);
                 final String serviceurl = "http://10.1.112.43:8773/services/Walrus";
                 s3.setEndpoint(serviceurl);
                 S3ClientOptions s3ClientOptions = new S3ClientOptions();
                 s3ClientOptions.setPathStyleAccess(true);
                 s3.setS3ClientOptions(s3ClientOptions);
                 */
                //create
                System.out.println("creating bucket " + bucketName + "n");
                //AmazonS3 credit = MyAwsCredential.MyAWSS3Credential();
                System.out.println(s3);

                //credit.createBucket(bucketName);
                //list
                s3.createBucket(bucketName);
                System.out.println("Listing buckets");
                for (Bucket bucket : s3.listBuckets()) {
                    System.out.println(" - " + bucket.getName());
                }
                System.out.println();
                //upload
                System.out.println("Uploading a new object to S3 from a file\n");
                //
                String filename = "src/main/java/static.war";
                File file = new File(filename);
                s3.putObject(new PutObjectRequest(bucketName, key1, file));

                /*//download
                System.out.println("Downloading an object");
                S3Object object = MyAwsCredential.MyAWSS3Credential().getObject(new GetObjectRequest(bucketName, key));
                System.out.println("Content-Type: "  + object.getObjectMetadata().getContentType());
                //displayTextInputStream(object.getObjectContent());

                //Listing all files
                System.out.println("Listing objects");
                ObjectListing objectListing = MyAwsCredential.MyAWSS3Credential().listObjects(new ListObjectsRequest()
                        .withBucketName(bucketName)
                        .withPrefix("My"));
                for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
                    System.out.println(" - " + objectSummary.getKey() + "  " +
                            "(size = " + objectSummary.getSize() + ")");
                }
                System.out.println();
                //delete
                *//*System.out.println("Deleting an object\n");
                s3.deleteObject(bucketName, key);*//*

                //delete the container
                *//*System.out.println("Deleting an object\n");
                s3.deleteObject(bucketName, key);
*/
            }catch (AmazonServiceException ase) {
                ase.printStackTrace();
            }

        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
