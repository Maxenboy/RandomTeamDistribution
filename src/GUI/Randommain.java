package GUI;


import javax.swing.JFrame;


public class Randommain {
public static void main(String[]args){
	mainFrame frame= new mainFrame("Randomize");
	frame.setVisible(true);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(600, 600);
	frame.setLocationRelativeTo(null);
}
}
