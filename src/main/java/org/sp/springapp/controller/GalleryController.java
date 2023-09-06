package org.sp.springapp.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.sp.springapp.domain.Gallery;
import org.sp.springapp.util.FileManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

//갤러리와 관련된 요청을 처리하는 하위 컨트롤러
@Controller
public class GalleryController {
	
	//게시판 목록 요청  처리
	@RequestMapping(value="/gallery/list",method=RequestMethod.GET)
	public ModelAndView getList() {
		//3단계 : 일 시키기
		
		//4단계 : 목록 저장
		ModelAndView mav = new ModelAndView("gallery/list");
		
		return mav;
	}

	//글스기 폼 요청
	@RequestMapping(value="/gallery/registform", method=RequestMethod.GET)
	public String getForm() {
		return "gallery/regist";
		
	}
	
	//글쓰기 요청 처리
	@RequestMapping(value="/gallery/regist", method=RequestMethod.POST)
	public ModelAndView regist(Gallery gallery, HttpServletRequest request) {
		//3단계 : 오라클에 글등록 + 파일 업로드 + 
		System.out.println("title = "+gallery.getTitle());
		System.out.println("writer = "+gallery.getWriter());
		System.out.println("content = "+gallery.getContent());
		
		MultipartFile[] photo = gallery.getPhoto();
		System.out.println("넘겨받은 파일의 수는 "+photo.length);
		
		//jsp의 application 내장객체는 서블릿 api에서 ServletContext 이다.
		//따라서 이 객체를 얻기 위해 HttpSession을 얻어야 한다.
		ServletContext context=request.getSession().getServletContext();
		String path=context.getRealPath("/resources/data/");
		System.out.println("파일이 저장될 풀 경로는 "+path);
		
		for(int i=0;i<photo.length; i++) {
			String filename=photo[i].getOriginalFilename();
			System.out.println(filename);
			
			//파일명 만들기
			String newName=FileManager.createFilename(filename);
			
			File file = new File(path+newName);
			
			//photo[i].transferTo(파일객체);
			
			try {
				photo[i].transferTo(file);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		//메모리상에 올라온 파일들을 서버의 지정된 디레고리에 저장하기
		
		
		return null;
	}
}
