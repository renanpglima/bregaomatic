package gui;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Rectangle;
import java.rmi.RemoteException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import communication.ClientCommunicationManager;
import communication.ICommunicationManager;
import communication.LocalCommunicationManager;
import communication.ServerCommunicationManager;

public class ConnectDlg extends JDialog {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JButton okBtn = null;

	private JButton cancelBtn = null;

	private JLabel jLabel = null;

	private JRadioButton serverRB = null;

	private JRadioButton clientSB = null;

	private JRadioButton localRB = null;

	private JLabel backgroundLbl = null;

	/**
	 * This method initializes okBtn	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getOkBtn() {
		if (okBtn == null) {
			okBtn = new JButton();
			okBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
			okBtn.setText("OK");
			okBtn.setBounds(new Rectangle(95, 132, 100, 20));
			okBtn.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ConnectDlg.this.onOK();
				}
			});
		}
		return okBtn;
	}

	protected void onOK() {
		try {
			ICommunicationManager com = null;
			if (serverRB.isSelected()){				
				com = new ServerCommunicationManager();
			}else if (clientSB.isSelected()){
				String hostName = JOptionPane.showInputDialog("Informe o IP do servidor:");
				if(hostName.equals(""))
					hostName = "localhost";
				com = new ClientCommunicationManager(hostName);
			}else if (localRB.isSelected()){
				com = new LocalCommunicationManager();
			}
			CommunicationHandler.getInstance().setCommunicationManager(com);
			int userID = com.connect();
			
			
			FMain frame = new FMain(userID);
			frame.setLocationRelativeTo(null);		
			frame.setVisible(true);	
			
			this.setVisible(false);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method initializes cancelBtn	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getCancelBtn() {
		if (cancelBtn == null) {
			cancelBtn = new JButton();
			cancelBtn.setText("Cancelar");
			cancelBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
			cancelBtn.setBounds(new Rectangle(205, 132, 100, 20));
			cancelBtn.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ConnectDlg.this.setVisible(false);
				}
			});
		}
		return cancelBtn;
	}

	/**
	 * This method initializes serverRB	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getServerRB() {
		if (serverRB == null) {
			serverRB = new JRadioButton();
			serverRB.setText("Quero ser o Servidor!");
			serverRB.setLocation(new Point(30, 47));
			serverRB.setFont(new Font("Tahoma", Font.BOLD, 14));
			serverRB.setOpaque(false);
			serverRB.setForeground(Color.white);
			serverRB.setActionCommand("Quero ser o servidor!");
			serverRB.setSelected(false);
			serverRB.setSize(new Dimension(300, 21));
			serverRB.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ConnectDlg.this.serverRB.setSelected(true);
					ConnectDlg.this.clientSB.setSelected(false);
					ConnectDlg.this.localRB.setSelected(false);
				}
			});
		}
		return serverRB;
	}

	/**
	 * This method initializes clientSB	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getClientSB() {
		if (clientSB == null) {
			clientSB = new JRadioButton();
			clientSB.setText("Quero me conectar a um servidor!");
			clientSB.setLocation(new Point(30, 77));
			clientSB.setSize(new Dimension(300, 21));
			clientSB.setFont(new Font("Tahoma", Font.BOLD, 14));
			clientSB.setOpaque(false);
			clientSB.setForeground(Color.white);
			clientSB.setSize(new Dimension(158, 21));
			clientSB.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ConnectDlg.this.serverRB.setSelected(false);
					ConnectDlg.this.clientSB.setSelected(true);
					ConnectDlg.this.localRB.setSelected(false);
				}
			});
		}
		return clientSB;
	}

	/**
	 * This method initializes localRB	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getLocalRB() {
		if (localRB == null) {
			localRB = new JRadioButton();
			localRB.setText("Vou brincar sozinho!");
			localRB.setLocation(new Point(30, 107));
			localRB.setSize(new Dimension(300, 21));
			localRB.setSize(new Dimension(352, 21));
			localRB.setFont(new Font("Tahoma", Font.BOLD, 14));
			localRB.setOpaque(false);
			localRB.setForeground(Color.white);
			localRB.setSize(new Dimension(158, 21));
			localRB.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ConnectDlg.this.serverRB.setSelected(false);
					ConnectDlg.this.clientSB.setSelected(false);
					ConnectDlg.this.localRB.setSelected(true);
				}
			});
		}
		return localRB;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ConnectDlg dlg = new ConnectDlg(null);
		dlg.setVisible(true);
		dlg.setLocationRelativeTo(null);

	}

	/**
	 * @param owner
	 */
	public ConnectDlg(Frame owner) {
		super(owner);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setBounds(new Rectangle(0, 0, 400, 188));
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			backgroundLbl = new JLabel();
			backgroundLbl.setIcon(new ImageIcon(getClass().getResource("/gui/images/connectBG.PNG")));
			backgroundLbl.setBounds(new Rectangle(0, 0, 400, 300));
			backgroundLbl.setText("JLabel");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(30, 17, 347, 20));
			jLabel.setText("Escolha como será sua participação:");
			jLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
			jLabel.setOpaque(false);
			jLabel.setForeground(Color.white);
			jLabel.setSize(new Dimension(300, 21));
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(getServerRB(), null);
			jContentPane.add(getClientSB(), null);
			jContentPane.add(getLocalRB(), null);
			jContentPane.add(getOkBtn(), null);
			jContentPane.add(getCancelBtn(), null);
			jContentPane.add(backgroundLbl, null);
			
		}
		return jContentPane;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
