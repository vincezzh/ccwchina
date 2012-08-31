package com.ccw.vm.command;

public abstract class BaseCommand {
	public abstract void preDo();
	
	public abstract void generateHtml();
	
	public abstract void postDo();
}
