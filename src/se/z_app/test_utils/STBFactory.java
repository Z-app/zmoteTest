package se.z_app.test_utils;

import se.z_app.stb.STB;
import se.z_app.stb.STB.STBEnum;

public class STBFactory {
	public static STB getProxy(){
		STB stb = new STB();
		stb.setBoxName("STB Proxy");
		stb.setIP("130.236.248.226");
		stb.setType(STBEnum.ZENTERIO);
		stb.setMAC("00:07:67:9B:EB:33");
		return stb;
	}
	public static STB get1(){
		STB stb = new STB();
		stb.setBoxName("Zenterio227");
		stb.setIP("130.236.248.227");
		stb.setType(STBEnum.ZENTERIO);
		stb.setMAC("00:07:67:9B:EB:34");
		return stb;
	}
	public static STB get2(){
		STB stb = new STB();
		stb.setBoxName("Zenterio228");
		stb.setIP("130.236.248.228");
		stb.setType(STBEnum.ZENTERIO);
		stb.setMAC("00:07:67:9B:EB:35");
		return stb;
	}
	public static STB getLocal(){
		STB stb = new STB();
		stb.setBoxName("Zenterio228");
		stb.setIP("127.0.0.1");
		stb.setType(STBEnum.ZENTERIO);
		stb.setMAC("00:07:67:9B:EB:32");
		return stb;
	}
}
