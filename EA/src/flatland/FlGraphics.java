package flatland;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.CellContent;
import model.Parameters;

public class FlGraphics extends JPanel{
	Board currentBoard;
	int scale = 40;
	ChangeListener delaySliderListener;
	public FlGraphics(){
	
		JFrame frame = new JFrame();
		frame.setSize(Parameters.FL_MAPSIZE*scale+20,Parameters.FL_MAPSIZE*scale+40);
		frame.setTitle("Agent");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		//currentBoard = board;
		
		final JSlider delaySlider = new JSlider(JSlider.HORIZONTAL, 0,100,50);
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
		for(int i=0; i<Parameters.FL_MAPSIZE; i++){
			for(int j=0; j<Parameters.FL_MAPSIZE; j++){
				//g.fillRect(x, y, width, height);
				g.setColor(Color.gray);
				g.fillRect(i*scale, j*scale, scale, scale);
				if(currentBoard.getCells()[i][j] == CellContent.AGENT){
					g.setColor(Color.blue);
					//g.fillOval(x, y, width, height);
					g.fillOval(i*scale, j*scale, scale, scale);
				}
				if(currentBoard.getCells()[i][j] == CellContent.FOOD){
					g.setColor(Color.red);
					g.fillOval(i*scale, j*scale, scale/2, scale/2);
				}
				if(currentBoard.getCells()[i][j] == CellContent.POISON){
					g.setColor(Color.green);
					g.fillOval(i*scale, j*scale, scale/2, scale/2);
				}
			}
		}
	}
	
	public void setBoard(Board board){
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
	/*
	public void drawBoard(){
		currentBoard.getCells();
		for(int i=0; i<Parameters.FL_MAPSIZE; i++){
			for(int j=0; j<Parameters.FL_MAPSIZE; j++){
				
				//this.repaint();
			}
		}
	}*/
	
}
