package beertracker;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.BeerCellContent;
import model.Parameters;

public class BeerGraphics extends JPanel{
	BeerBoard currentBoard;
	int scale = 40;	
	ChangeListener delaySliderListener;
	public BeerGraphics(){
	
		JFrame frame = new JFrame();
		frame.setSize(Parameters.BT_SIZE_X*scale,Parameters.BT_SIZE_Y*scale+100);
		frame.setTitle("Agent");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		//currentBoard = board;
		
		final JSlider delaySlider = new JSlider(JSlider.HORIZONTAL, 0,200,50);
		delaySlider.setPaintTicks(true);
		delaySlider.setMajorTickSpacing(5);
		delaySlider.setMinorTickSpacing(1);
		
		JFrame frame2  = new JFrame();
		frame2.setSize(200,200);
		frame2.setTitle("Slider window");
		frame2.setVisible(true);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.add(delaySlider);
		
		delaySliderListener = new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				Parameters.DELAY = delaySlider.getValue();
				System.out.println("Parameters.Delay " + Parameters.DELAY);
				
			}
		};
		
		delaySlider.addChangeListener(delaySliderListener);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for(int i=0; i<Parameters.BT_SIZE_X; i++){
			for(int j=0; j<Parameters.BT_SIZE_Y; j++){
				g.setColor(Color.gray);
				g.fillRect(i*scale, j*scale, scale, scale);
				if(currentBoard.getCells()[i][j] == BeerCellContent.SENSOR){
					if(currentBoard.getPulledDown()){
						g.setColor(Color.green);
					}else{
						g.setColor(Color.blue);
					}
					g.fillOval(i*scale, j*scale, scale, scale);
				}
				if(currentBoard.getCells()[i][j] == BeerCellContent.BLOCK){
					g.setColor(Color.red);
					g.fillOval(i*scale, j*scale, scale, scale);
				}
			}
		}
	}
	
	public void setBoard(BeerBoard board){
		currentBoard = board;
		try {
			Thread.sleep(Parameters.DELAY);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//drawBoard();
		this.repaint();
	}
	
}
