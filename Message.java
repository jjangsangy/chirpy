package com.example.test;

import java.io.RandomAccessFile;

public class Message {

    String ef,df;
    public Message(String encryptionFile, String decryptionFile){
        ef=encryptionFile;
        df=decryptionFile;
    }
    
    int sendMessagesLeft = RandomAccessFile(ef,"r")/128;
    int receiveMessagesLeft = RandomAccessFile(df,"r")/128;


    
    public int sendMessage(String message, String ef){
        if (sendMessagesLeft > 0){
            byte[] key = keyManagement.getByteArray(ed);
            String encryptedString = Encryption.encrypt(message, key);
            TWEET IT;
            sendMessagesLeft--;
            return 1;
        }
        else {
            // your key ran out 
            return 0;
        }
    }

    public String recieveMessage(String df){
        if (receiveMessagesLeft > 0){
            String encryptedString = GET_DAT_TWEET;
            byte[] key = keyManagement.getByteArray(df);
            String decryptedString = Encryption.decrypt(encryptedString,key);
            return decryptedString;
        }
        else {
            // your key ran out
            return "=(";
        }
    }

}
