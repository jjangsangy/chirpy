package com.example.test;

import java.io.UnsupportedEncodingException;

import android.util.Base64;

public class Encryption{

	public static String encrypt(String message, byte[] key){
		try {
			byte[] byteMessage = message.getBytes("US-ASCII");
	        for(int i = 0; i < byteMessage.length; i++)
	        {
	            byteMessage[i] = (byte) (byteMessage[i]^key[i]);
	        }
	        
	        return Base64.encodeToString(byteMessage, 0);
		 
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static String decrypt(String tweet, byte[] key){
		byte[] decodedTweet = Base64.decode(tweet, 0);
        for(int i = 0; i < decodedTweet.length; i++)
        {
        	decodedTweet[i] = (byte) (decodedTweet[i]^key[i]);
        }
        String decryptedMessage;
		try {
			decryptedMessage = new String(decodedTweet,"US-ASCII");
	        return decryptedMessage;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
    

}
