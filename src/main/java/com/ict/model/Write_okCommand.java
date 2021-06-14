package com.ict.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ict.db.DAO;
import com.ict.db.VO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class Write_okCommand implements Command {
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
			VO vo = new VO();
			vo.setName(request.getParameter("name"));
			vo.setSubject(request.getParameter("subject"));
			vo.setContent(request.getParameter("content"));
			vo.setEmail(request.getParameter("email"));
			vo.setPwd(request.getParameter("pwd"));

			// 첨부파일이 있을 때와 첨부파일이 없을 때을 구분하자
			int result = DAO.getInsert(vo);
			if (result > 0) {
				return "MyController?cmd=list";
			}
		return null;

	}
}
