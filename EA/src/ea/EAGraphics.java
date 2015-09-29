package ea;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Parameters;
public class EAGraphics {
	public static void showMutateControl(){
		ChangeListener mutateSliderListener;
		
		final JSlider mutateSlider = new JSlider(JSlider.HORIZONTAL, 0,10,2);
		mutateSlider.setPaintTicks(true);
		mutateSlider.setMajorTickSpacing(2);
		mutateSlider.setMinorTickSpacing(1);
		mutateSlider.setPaintLabels(true);
		
		JFrame frame2  = new JFrame();
		frame2.setSize(300,75);
		//frame2.setLocation(frame.getWidth(), 0);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		frame2.setLocation(0, (int)height - frame2.getSize().height);
		frame2.setTitle("Slider window");
		frame2.setVisible(true);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.add(mutateSlider);
		
		mutateSliderListener = new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				Parameters.MUTATION_RATE = ((double)mutateSlider.getValue())/10.0;
				//System.out.println("Parameters.Delay " + Parameters.DELAY);
				
			}
		};
		
		mutateSlider.addChangeListener(mutateSliderListener);
	}
	
}
