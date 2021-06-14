package com.ict.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ict.db.DAO;
import com.ict.db.VO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class Update_okCommand implements Command{
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		
		//세션에 있는 vo를 꺼내자/
		VO v = (VO)request.getSession().getAttribute("vo");
		
		
		VO vo = new VO();
	    vo.setIdx(v.getIdx());
	    //안고치는 애들만 세션에서 받음. 고쳐지는 애들은 바뀌기떄문에 세션에서 불러오기어렵.
	    vo.setName(request.getParameter("name"));
		vo.setSubject(request.getParameter("subject"));
		vo.setContent(request.getParameter("content"));
		vo.setEmail(request.getParameter("email"));
		vo.setPwd(request.getParameter("pwd"));
		
		int result = DAO.getUpdate(vo);
		if(result>0){ 
			return "MyController?cmd=onelist&idx="+vo.getIdx();
		}
		return null;
	}
}
