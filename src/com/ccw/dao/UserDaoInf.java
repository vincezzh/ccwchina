package com.ccw.dao;

import java.util.ArrayList;

import com.ccw.bean.Admindetail;
import com.ccw.bean.Confirmtime;
import com.ccw.bean.Infomaillist;
import com.ccw.bean.Peopletitle;
import com.ccw.bean.Userdetail;

public interface UserDaoInf {
	public Admindetail adminLogin(Admindetail admin) throws Exception;
	
	public Userdetail getDetailUser(String userId) throws Exception;
	
	public ArrayList<Peopletitle> getAllPeopletitle() throws Exception;
	
	public ArrayList<Confirmtime> getAllConfirmTime() throws Exception;
	
	public Userdetail login(String username, String password) throws Exception;
	
	public void register(Userdetail user) throws Exception;
	
	public void updateUserInformation(Userdetail user) throws Exception;
	
	public ArrayList<Userdetail> getAllUserByPageNumber(Integer pageNumber) throws Exception;
	
	public Long getAllUserCount() throws Exception;
	
	public Long getAllNamedUserCount(String nameFormat) throws Exception;
	
	public Peopletitle getDetailPeopletitle(Integer peopleTitleId) throws Exception;
	
	public ArrayList<Userdetail> getAllUser() throws Exception;
	
	public ArrayList<Infomaillist> getAllInfomaillist() throws Exception;
}
