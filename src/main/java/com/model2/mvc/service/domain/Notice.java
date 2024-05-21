package com.model2.mvc.service.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;

@Data
public class Notice {
	
	private int noticeNo;
	private String noticeName,noticeSubject,noticeContent,noticePwd;
	private Date noticeRegDate;

	public String getnoticeRegDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(noticeRegDate);
	}
}
