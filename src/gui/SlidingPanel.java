package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.Timer;

/* this class is using jpanel to make a sliding panel class that 
 * animates the transition between two components in a sliding fashion, either to the left or to the right.
 */
public class SlidingPanel extends JPanel {

    public int getAnimate() {
        return animate;
    }

    public void setAnimate(int animate) {
        this.animate = animate;
    }

    public SlidingPanel() {
        initComponents();
        setLayout(null);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent ce) {
                comShow.setSize(getSize());
            }

        });
        timer = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                animate();
            }
        });

    }

    private final Timer timer;
    private Component comExit;
    private Component comShow;
    private AnimateType animateType;
    private int animate = 70;

    public void show(Component com, AnimateType animateType) {
        if (!timer.isRunning()) {
            this.animateType = animateType;
            this.comShow = com;
            com.setSize(getSize()); // sets the size of the new component (comShow) to match the size of the SlidingPanel.
            if (getComponentCount() == 0) { // checking that there are no added components aldready added.
                add(com);
                comExit = com;
                repaint();
                revalidate();
            } else {

                if (animateType == AnimateType.TO_RIGHT) {
                    comShow.setLocation(-comShow.getWidth(), 0);// placing it to the left of the panel's visible area, with the y-coordinate set to 0.
                } else {
                    comShow.setLocation(getWidth(), 0); //placing it to the right of the panel's visible area, with the y-coordinate set to 0.
                }
                add(com);
                repaint();
                revalidate();
                timer.start();

            }
        }
    }

    private void animate() {
        if (animateType == AnimateType.TO_RIGHT) { // If it is set to TO_RIGHT, the method will slide the components to the right. If it is set to TO_LEFT, the method will slide the components to the left.
            if (comShow.getLocation().x < 0) {
                comShow.setLocation(comShow.getLocation().x + animate, 0); //updates the x-coordinates of both comShow and comExit components 
                comExit.setLocation(comExit.getLocation().x + animate, 0);
            } else {
                //  Stop animate
                comShow.setLocation(0, 0);//resets the x-coordinate of comShow
                timer.stop();
                remove(comExit);
                comExit = comShow;// sets comExit to the current comShow
            }
        } else {
            if (comShow.getLocation().x > 0) {
                comShow.setLocation(comShow.getLocation().x - animate, 0);
                comExit.setLocation(comExit.getLocation().x - animate, 0);
            } else {
                comShow.setLocation(0, 0);
                timer.stop();
                remove(comExit);
                comExit = comShow;
            }
        }
    }
    
    public void setAnimationSpeed(int speed) {
        this.animate = speed;
    }

    private void initComponents() {

       GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 319, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 192, Short.MAX_VALUE)
        );
    }

    // sets up TO_RIGHT or TO_LEFT enum, which is used in the animate method
    public static enum AnimateType {
        TO_RIGHT, TO_LEFT
    }
}