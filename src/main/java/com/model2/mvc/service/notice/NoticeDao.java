package com.model2.mvc.service.notice;

import java.util.List;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Notice;

public interface NoticeDao {
	
	public void insertNotice(Notice notice) throws Exception;
	
	public List<Notice> getNoticeList(Search search) throws Exception;
	
	public Notice detailNotice(int noticeNo) throws Exception;
	
	public void updateNotice(Notice notice) throws Exception;
	
	public Notice deleteNotice(int noticeNo) throws Exception;
	
	public int getTotalCount(Search search) throws Exception;
	
	public String getPwdNotice(int noticeNo,String noticePwd) throws Exception;
}
