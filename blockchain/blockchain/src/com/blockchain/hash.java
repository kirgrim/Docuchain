package com.docupace.blockchain;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class hash {
    public static String getHash(String pathToFile) throws IOException, NoSuchAlgorithmException {
            FileInputStream fis = new FileInputStream(pathToFile);
            MessageDigest shaDigest= MessageDigest.getInstance("SHA-512");
            byte[] byteArray = new byte[256];

            int bytesCount = 0;


            //Read file data and update in message digest

            while ((bytesCount = fis.read(byteArray)) != -1) {
                shaDigest.update(byteArray,0, bytesCount);

            }

            //	close the stream; We don't need it now.
            fis.close();

            //Get the hash's bytes
            StringBuilder sb = new StringBuilder();
            byte[] bytes = shaDigest.digest();

            //This bytes[] has bytes in decimal format;
            //	Convert it to hexadecimal format
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //return complete hash
            //System.out.println(sb.toString());
            return sb.toString();
        }
    }
