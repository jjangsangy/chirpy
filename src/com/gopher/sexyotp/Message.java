package com.gopher.sexyotp;

import java.io.RandomAccessFile;

public class Message {

    String ef,df;
    public Message(String encryptionFile, String decryptionFile){
        ef=encryptionFile;
        df=decryptionFile;
    }
    
    int sendMessagesLeft = RandomAccessFile(ef,"r")/132;
    int receiveMessagesLeft = RandomAccessFile(df,"r")/132;


    
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
            byte[] key = keyManagement.getByteArray(df);
            String encryptedString = GET_DAT_TWEET(key);
            String decryptedString = Encryption.decrypt(encryptedString,key);
            return decryptedString;
        }
        else {
            // your key ran out
            return "=(";
        }
    }

}
