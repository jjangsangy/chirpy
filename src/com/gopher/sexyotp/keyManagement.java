package com.example.test;

import java.io.RandomAccessFile;

public class keyManagement {
	public static byte[] getByteArray(String file){

		try {
			RandomAccessFile raf = new RandomAccessFile(file, "rw");
			byte[] key = new byte[132];
			raf.read(key, (int)raf.length()-132-1, 132);
			raf.setLength(raf.length()-132);
			raf.close();
			return key;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
}
