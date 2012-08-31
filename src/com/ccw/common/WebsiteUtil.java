package com.ccw.common;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;

import com.ccw.dao.CommonDaoInf;
import com.ccw.dao.CourseDaoInf;
import com.ccw.dao.ItemDaoInf;
import com.ccw.vm.command.BaseCommand;
import com.ccw.vm.command.BottomfootCommand;
import com.ccw.vm.command.CourseVMCommand;
import com.ccw.vm.command.ItemVMCommand;
import com.ccw.vm.command.NewsVMCommand;
import com.ccw.vm.command.ToptitleCommand;

public class WebsiteUtil {
	public void createAllWebPages(String basicPath, CourseDaoInf courseDao, CommonDaoInf commonDao, ItemDaoInf itemDao) {
		ArrayList<BaseCommand> commands = new ArrayList<BaseCommand>();
		
		ToptitleCommand tc = new ToptitleCommand(basicPath, commonDao);
		commands.add(tc);
		BottomfootCommand bc = new BottomfootCommand(basicPath, courseDao);
		commands.add(bc);
		CourseVMCommand cc = new CourseVMCommand(basicPath, courseDao);
		commands.add(cc);
		NewsVMCommand nc = new NewsVMCommand(basicPath, commonDao);
		commands.add(nc);
		ItemVMCommand ic = new ItemVMCommand(basicPath, itemDao);
		commands.add(ic);
		
		Iterator<BaseCommand> i = commands.iterator();
		BaseCommand command = null;
		while(i.hasNext()) {
			command = i.next();
			command.preDo();
			command.generateHtml();
			command.postDo();
		}
	}
	
	public void copyWebPages(String from, String to) throws Exception {
		FileUtils.copyDirectory(new File(from), new File(to));
	}
	
	public void deleteDirectory(String location) throws Exception {
		FileUtils.deleteDirectory(new File(location));
	}
	
	public void deleteDirectories(String context, ArrayList<String> folderNames) throws Exception {
		for(String name : folderNames) {
			FileUtils.deleteDirectory(new File(context + File.separator + name));
		}
	}
}
