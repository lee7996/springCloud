package com.javbus.common.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class ReadFilesRecursion {
	
	public static void readFiles(String path, List<File> fileList) {
//		FileInputStream ios = null;
		try {
			File file = new File(path);
			if (file.isDirectory()) { // 路径为文件夹
				File[] files = file.listFiles();
				for (File file2 : files) {
//					ios = new FileInputStream(file2);
//					Properties prop=new Properties();  
//					prop.load(ios);
//					String encoding = prop.getProperty(""); 
					// 如果当前file2是文件夹则递归
					if (file2.isDirectory()) {
//						FileOutputStream fos = new FileOutputStream(file2);
//						OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");
						readFiles(file2.getPath(), fileList);
					} else {
						fileList.add(file2);
					}
				}
			} else { // 路径为文件
				fileList.add(file);
			}
		} finally {
//			if (ios != null) {
//				ios.close();
//			}
		}
	}
}
