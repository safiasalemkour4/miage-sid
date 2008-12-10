package agentFreedom;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


import agentIntern.BankerAgent;
import agentIntern.StockManagerAgent;
import agentIntern.StrategyAgent;

public class InfosUI extends JFrame{
	
	public InfosUI() {
		
		super();
		this.setTitle("Projet Agent - Informations");

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.update();

		this.pack();
		this.setVisible(true);
	}
	
	public void update() {
		
		System.out.println("f2");
		
		//this.removeAll();
		this.getContentPane().removeAll();
		
		JPanel content = new JPanel();
		content.setLayout(new GridLayout(6,2));
		
		content.add(new JLabel("Banque :"));
		content.add(new JLabel(BankerAgent.getMoney()+"e"));
		content.add(new JLabel());
		content.add(new JLabel());
		content.add(new JLabel("Stock CD :"));
		content.add(new JLabel(StockManagerAgent.nosStockCD+""));
		content.add(new JLabel("Prix CD :"));
		content.add(new JLabel(StrategyAgent.prixCD+"e"));
		content.add(new JLabel("Stock DVD :"));
		content.add(new JLabel(StockManagerAgent.nosStockDVD+""));
		content.add(new JLabel("Prix DVD :"));
		content.add(new JLabel(StrategyAgent.prixDVD+"e"));

		this.setContentPane(content);
		
		System.out.println("f3");
	}

}
