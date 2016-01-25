package global;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class TeamUtil {

	public static void main(String[] args) {
		UtilController controller = new UtilController();
		controller.populatedata();
		SimWindow window = new SimWindow(controller);
		window.build();
		window.show();
		controller.setWindow(window);
	}

}
