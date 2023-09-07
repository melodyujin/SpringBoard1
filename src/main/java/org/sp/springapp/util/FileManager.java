package org.sp.springapp.util;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

//scan에 의해 자동 인스턴스 생성..
@Component
public class FileManager {

	//확장자 구하기  432432432.jpg
	public static String getExt(String path) {
		int index = path.lastIndexOf(".");
		return path.substring(index+1, path.length()); //exclusive 
	}
	
	//최종적인 파일명 만들기	789788.jpg 
	public static String createFilename(String filename) {
		long time = System.currentTimeMillis();
		
		return time+"."+getExt(filename);
	}
	
	//파일 저장
	public String save(String path, String filename, MultipartFile mf) {
		
		System.out.println(filename);
		
		//파일명 만들기
		String newName = FileManager.createFilename(filename); //4234234325.jpg
		
		File file = new File(path+newName);
		
		//photo[i].transferTo(파일객체);
		
		try {
			mf.transferTo(file);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newName;
	}
}
