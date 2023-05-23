package gui;

import javax.swing.UIManager;

public class UIStyle {
	
	static void setUIStyle() {
		//https://docs.oracle.com/javase/tutorial/uiswing/lookandfeel/nimbus.html
				//Nimbus uses Java 2D vector graphics to draw the user interface (UI), rather than static bitmaps, so the UI can be crisply rendered at any resolution
				try {
		            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		                if ("Nimbus".equals(info.getName())) {
		                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
		                    break;
		                }
		            }
		        } catch (ClassNotFoundException ex) {
		            java.util.logging.Logger.getLogger(MainCalendar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		        } catch (InstantiationException ex) {
		            java.util.logging.Logger.getLogger(MainCalendar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		        } catch (IllegalAccessException ex) {
		            java.util.logging.Logger.getLogger(MainCalendar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
		            java.util.logging.Logger.getLogger(MainCalendar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		        }
	}
}
