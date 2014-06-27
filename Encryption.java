import java.io.*;
import java.util.Base64.*;

public class Encryption{


    public String encrypt(String message, byte [] key)
    {
        // Pad message to 128 (which is divisble by 4!)
        int len = message.length();
        for(int i = len; i < 127; i++)
        {
            message = message.concat("/");
        }

        // From base64 string to bytes
        // This should given a 192=(128*6)/4 byte array
        byte [] decoded = Base64.Decoder.decode(message);

        // Encryption process
        byte[] encrypted = new byte[192];
        for(int i = 0; i < 192; i++)
        {
            encrypted[i] = decoded[i]^key[i];
        }

        String encryptMessage = Base64.Encoder.encodeToString(encrypted);

        return encryptMessage;
    }

    

}
