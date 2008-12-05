package agentFreedom;


import java.awt.Dimension;
import java.awt.Toolkit;


/**
 *
 * @author  Romain
 */
public class LogUI extends javax.swing.JFrame {
	
	private ClientAgent agentFreedom;

	/** Creates new form Log */
	public LogUI(ClientAgent agentFreedom) {
		this.agentFreedom = agentFreedom;
		initComponents();
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	private void initComponents() {

		NouvellePhase = new javax.swing.JButton();
		FinSimulation = new javax.swing.JButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		console = new javax.swing.JTextPane();
		labelNumeroPhase = new javax.swing.JLabel();
		numeroPhase = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		NouvellePhase.setText("Lancer un nouvelle phase");
		NouvellePhase.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				NouvellePhaseActionPerformed(evt);
			}
		});

		FinSimulation.setText("Fin de la simulation");
		FinSimulation.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				FinSimulationActionPerformed(evt);
			}
		});

		jScrollPane1.setViewportView(console);

		labelNumeroPhase.setText("numero de phase");

		numeroPhase.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
		numeroPhase.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		numeroPhase.setText("0");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(layout.createSequentialGroup()
														.addGap(13, 13, 13)
														.addComponent(labelNumeroPhase))
														.addGroup(layout.createSequentialGroup()
																.addGap(41, 41, 41)
																.addComponent(numeroPhase, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
																.addGap(86, 86, 86)
																.addComponent(NouvellePhase, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGap(18, 18, 18)
																.addComponent(FinSimulation, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
																.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 758, Short.MAX_VALUE))
																.addContainerGap())
		);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGap(20, 20, 20)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(NouvellePhase, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(FinSimulation, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(layout.createSequentialGroup()
												.addComponent(labelNumeroPhase)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(numeroPhase, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
												.addContainerGap())
		);

		numeroPhase.getAccessibleContext().setAccessibleName("numeroPhase");

		pack();
		this.setTitle("Log | MIAGe SID 2008");		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) (screenSize.getWidth() / 2 - getWidth() / 2);
		int y = (int) (screenSize.getHeight() / 2 - getHeight() / 2);
		this.setLocation(x,y);		
		this.setVisible(true);
	}

	private void NouvellePhaseActionPerformed(java.awt.event.ActionEvent evt) {
		// on lance une nouvelle phase
		numeroPhase.setText(String.valueOf(Integer.valueOf(numeroPhase.getText()) + 1));
		agentFreedom.nouvellePhase();
	}

	private void FinSimulationActionPerformed(java.awt.event.ActionEvent evt) {                                         
		// TODO add your handling code here:
		addText("Fin de la simulation. (TODO: affichage des stats?)");
	}

	public synchronized void addText(String text){
		if(console.getText().length()>0)
			console.setText(console.getText() + "\n" + text);
		else
			console.setText(console.getText() + text);
	}
	
	public void setNumeroPhase(int numeroPhase){
		labelNumeroPhase.setText(String.valueOf(numeroPhase));
	}


	// Variables declaration - do not modify
	private javax.swing.JButton FinSimulation;
	private javax.swing.JButton NouvellePhase;
	private javax.swing.JTextPane console;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JLabel labelNumeroPhase;
	private javax.swing.JLabel numeroPhase;
	// End of variables declaration

}
