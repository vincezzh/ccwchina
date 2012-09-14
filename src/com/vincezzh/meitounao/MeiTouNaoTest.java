package com.vincezzh.meitounao;

import java.util.List;

import javapns.Push;

public class MeiTouNaoTest {
	public static String myIphoneToken = "962f0ac3e44bdcc567d6bc9f6bf2e6a4685cc5d18ac08316534db645ee8b2def";
	public static String sukieIphoneToken = "c83f054122c0ecdc3d96e3e0b62fe48089c90d3a057f8cb6a5da9f7a0678b958";
	
	public static void send(List<String> contents, String certificatePath) {
		try {
			for(String content : contents) {
				Push.combined(content, 1, "default", certificatePath, "devZHEhan330", false, MeiTouNaoTest.sukieIphoneToken);
				Push.combined(content, 1, "default", certificatePath, "devZHEhan330", false, MeiTouNaoTest.myIphoneToken);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
//	public static void main(String[] args) {
//		try {
//			Push.combined("MeiTouNao BuGaoXing", 1, "default", "/Users/vince/Desktop/Certificates.p12", "devZHEhan330", false, MeiTouNaoTest.myIphoneToken);
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
	
//	public static void main(String[] args) {
//		ApnsService service =
//		    APNS.newService()
//		    .withCert("/Users/vince/Desktop/aps_developer_identity.p12", "devZHEhan330")
//		    .withSandboxDestination()
//		    .build();
//		String payload = APNS.newPayload().alertBody("Can't be simpler than this!").build();
//		String token = MeiTouNaoTest.myIphoneToken;
//		service.push(token, payload);
//	}
}
