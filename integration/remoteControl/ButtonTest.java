package remoteControl;

import se.z_app.stb.api.RemoteControl;
import se.z_app.stb.api.RemoteControl.Button;


public class ButtonTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RemoteControl.instance().sendButton(Button.UP);
		
		//130.236.248.57
	}

}
