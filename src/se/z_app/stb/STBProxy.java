package se.z_app.stb;

import se.z_app.stb.STB.STBEnum;

public class STBProxy {
	public static STB getProxy(){
		STB stb = new STB();
		stb.setBoxName("STB Proxy");
		stb.setIP("130.236.248.226");
		stb.setType(STBEnum.ZENTERIO);
		stb.setMAC("00:07:67:9B:EB:33");
		return stb;
	}
}
