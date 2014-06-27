package com.example.test;

import java.io.RandomAccessFile;

public class keyManagement {
	public static byte[] getByteArray(String file){

		try {
			RandomAccessFile raf = new RandomAccessFile(file, "rw");
			byte[] key = new byte[192];
			raf.read(key, (int)raf.length()-129, 128);
			raf.setLength(raf.length()-128);
			raf.close();
			return key;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
}
