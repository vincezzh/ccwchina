package com.ccw.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class HtmlUtil {
	public static void generateHtml(String filePath, String fileName, String packageName, Object entity) {
		BufferedWriter writer = null;
		try {
			VelocityUtil vu = VelocityUtil.getInstance();
			String htmlContent = new String(vu.generate(packageName, entity).getBytes("UTF-8"), "UTF-8");
			
			File directory = new File(filePath);
			if(!directory.exists())
				directory.mkdirs();
			
			File file = new File(filePath + fileName);
			if (file.exists()) {
				file.delete();
			}
			OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
			writer = new BufferedWriter(write);
			writer.write(htmlContent);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void generateHtml(String filePathAndName, String packageName, Object entity) {
		int index = filePathAndName.lastIndexOf(File.separator) + 1;
		String filePath = filePathAndName.substring(0, index);
		String fileName = filePathAndName.substring(index);
		HtmlUtil.generateHtml(filePath, fileName, packageName, entity);
	}
	
//	public static void main(String[] args) {
//		  Tourproduct t = new Tourproduct();
//		  
//		  Trunktype trunk = new Trunktype();
//		  trunk.setTrunkTypeId(1);
//		  trunk.setTrunkTypeName("oversea");
//		  
//		  Branchtype branch = new Branchtype();
//		  branch.setBranchTypeId(1);
//		  branch.setBranchTypeName("sea");
//		  
//		  Tourproductnotes note1 = new Tourproductnotes();
//		  note1.setId(1l);
//		  note1.setNoteContent("Nice?");
//		  note1.setNoteAnswer("Yes");
//		  
//		  Tourproductnotes note2 = new Tourproductnotes();
//		  note2.setId(2l);
//		  note2.setNoteContent("Dirty?");
//		  note2.setNoteAnswer("No");
//		  
//		  Set<Tourproductnotes> notes = new HashSet<Tourproductnotes>();
//		  notes.add(note1);
//		  notes.add(note2);
//		  
//		  t.setAuthor("Author");
//		  t.setBookingnote("Booking Note");
//		  t.setBranchtype(branch);
//		  t.setByVehicle("By Vehicle");
//		  t.setDescription("Description");
//		  t.setDiscount("Discount");
//		  t.setFee("Fee");
//		  t.setReleaseTime(new Date());
//		  t.setFromCity("From City");
//		  t.setKeyword("Keyword");
//		  t.setKeywordDescription("Keyword Description");
//		  t.setLength("Length");
//		  t.setOthers("Others");
//		  t.setPayImmidiatly((byte)1);
//		  t.setPrice(5000d);
//		  t.setProductId("0000000001");
//		  t.setProductName("Product Name");
//		  t.setTourproductnoteses(notes);
//		  t.setTrunktype(trunk);
//		  t.setWarmNotification("Warm Notification");
//		  
//		  HtmlUtil.generateHtml(t.getProductId(), t);
//	}
}
