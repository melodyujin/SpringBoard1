package org.sp.springapp.model.gallery;

import java.util.List;

import org.sp.springapp.domain.Gallery;

//컨트롤러가 이 서비스 객체를 보유할 때, 결합도를 낮추기 위해..
public interface GalleryService {
	public void regist(Gallery gallery); //insert
	public List selectAll();
	public Gallery select(int gallery_idx);
	public void update(Gallery gallery);
	public void delete(int gallery_idx);
}
