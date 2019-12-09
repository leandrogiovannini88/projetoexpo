package projetoexpo;

import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.AWTEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

//import projetoexpo.ExportacaoExcel;

public class MaisOpcoes extends TelaPrincipal {

	private JPanel contentPane;
	private static Timer timer;
	private String leitura = "";
	private AWTEventListener listener;
	private int IdPedido;
	static TelaPrincipal frame2;
	static MaisOpcoes frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new MaisOpcoes(0, new TelaPrincipal());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public MaisOpcoes(int idPedido, TelaPrincipal telaPrincipal) {
		
		this.IdPedido = idPedido;
		
		setTitle("Mais Opções");
		
//	    Toolkit.getDefaultToolkit().addAWTEventListener(
//	            new AWTEventListener() {
//	              @Override
//	              public void eventDispatched(AWTEvent event) {
//	                Object source = event.getSource();
//	                if (source instanceof Component) {
//	                  Component comp = (Component) source;
//	                  Window win = null;
//	                  if (comp instanceof Window) {
//	                    win = (Window) comp;
//	                  } else {
//	                    win = SwingUtilities.windowForComponent(comp);
//	                  }
//	                  if (win == win) {
//	                    timer.restart();
//	                    }
//	                }
//	              }
//	            },
//	            AWTEvent.KEY_EVENT_MASK | AWTEvent.MOUSE_EVENT_MASK
//	                | AWTEvent.MOUSE_MOTION_EVENT_MASK
//	                | AWTEvent.MOUSE_WHEEL_EVENT_MASK);
//
//	        timer = new Timer(10000, new ActionListener() {
//	          @Override
//	          public void actionPerformed(ActionEvent e) {
//	            dispose();
//	          }
//	        });
//	        timer.start();
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 538, 660);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnCadastrar = new JButton("");
		btnCadastrar.setBounds(21, 22, 214, 83);
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String codigo = "";
				JFrame cadastro = new Cadastro(codigo,false, telaPrincipal);
				cadastro.setVisible(true);
				Toolkit.getDefaultToolkit().removeAWTEventListener(listener);
				dispose();
				
			}
		});
		contentPane.setLayout(null);
		btnCadastrar.setIcon(new ImageIcon(MaisOpcoes.class.getResource("/ImageLib/CADASTRO (99999).jpg")));
		contentPane.add(btnCadastrar);
		
		JButton btnDesativar = new JButton("");
		btnDesativar.setBounds(21, 139, 214, 83);
		btnDesativar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame desativa = new DesativaProduto(telaPrincipal);
				desativa.setVisible(true);
				Toolkit.getDefaultToolkit().removeAWTEventListener(listener);
				dispose();
			}
		});
		btnDesativar.setIcon(new ImageIcon(MaisOpcoes.class.getResource("/ImageLib/DESATIVAR PRODUTO (88888).jpg")));
		contentPane.add(btnDesativar);
		
		JButton btnCancelarPedido = new JButton("");
		btnCancelarPedido.setBounds(21, 253, 214, 83);
		btnCancelarPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame cancelapedido = new CancelaPedido(idPedido, telaPrincipal);
				cancelapedido.setVisible(true);
				Toolkit.getDefaultToolkit().removeAWTEventListener(listener);
				dispose();
			}
		});
		btnCancelarPedido.setIcon(new ImageIcon(MaisOpcoes.class.getResource("/ImageLib/CANCELAR COMPRA (77777).jpg")));
		contentPane.add(btnCancelarPedido);
		
		JButton btnAlterarPreco = new JButton("");
		btnAlterarPreco.setBounds(21, 371, 214, 83);
		btnAlterarPreco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame alterar = new AlteraPreco();
				alterar.setVisible(true);
				Toolkit.getDefaultToolkit().removeAWTEventListener(listener);
				dispose();
			}
		});
		btnAlterarPreco.setIcon(new ImageIcon(MaisOpcoes.class.getResource("/ImageLib/ALTERAR PRE\u00C7O (66666).jpg")));
		contentPane.add(btnAlterarPreco);
		
		JLabel lblCadastrar = new JLabel("Cadastrar");
		lblCadastrar.setBounds(279, 49, 133, 29);
		lblCadastrar.setFont(new Font("Arial", Font.PLAIN, 15));
		contentPane.add(lblCadastrar);
		
		JLabel lblDesativar = new JLabel("Desativar Produto");
		lblDesativar.setBounds(279, 166, 214, 29);
		lblDesativar.setFont(new Font("Arial", Font.PLAIN, 15));
		contentPane.add(lblDesativar);
		
		JLabel lblCancelarPedido = new JLabel("Cancelar Compra");
		lblCancelarPedido.setBounds(279, 277, 214, 29);
		lblCancelarPedido.setFont(new Font("Arial", Font.PLAIN, 15));
		contentPane.add(lblCancelarPedido);
		
		JLabel lblAlterarPreco = new JLabel("Alterar Pre\u00E7o");
		lblAlterarPreco.setBounds(279, 398, 209, 29);
		lblAlterarPreco.setFont(new Font("Arial", Font.PLAIN, 15));
		contentPane.add(lblAlterarPreco);
		
		JLabel lblCode = new JLabel("");
		lblCode.setBounds(299, 22, 46, 14);
		contentPane.add(lblCode);
		
		JButton btnExel = new JButton("");
		btnExel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame excel = new ExportacaoExcel();
				excel.setVisible(true);
//				Toolkit.getDefaultToolkit().removeAWTEventListener(listener);
				dispose();
			}
		});
		btnExel.setIcon(new ImageIcon(MaisOpcoes.class.getResource("/ImageLib/EXPORTAR EXCEL (55555).jpg")));
		btnExel.setBounds(21, 486, 214, 83);
		contentPane.add(btnExel);
		
		JLabel lblImportarBancoPro = new JLabel("Exportar para o Excel");
		lblImportarBancoPro.setFont(new Font("Arial", Font.PLAIN, 15));
		lblImportarBancoPro.setBounds(279, 512, 209, 29);
		contentPane.add(lblImportarBancoPro);
		
		listener = new AWTEventListener() {
				  public void eventDispatched(AWTEvent event) {
				    try {
				      KeyEvent evt = (KeyEvent)event;
				      if(evt.getID() == KeyEvent.KEY_PRESSED) {
					      System.out.println("Tecla: "+evt.getKeyChar());
					      leitura += evt.getKeyChar();
					      if(leitura.length()==5) {
//					    	  lblCode.setText(leitura);
					    	  if(leitura.equals("99999")) {
					    		  System.out.println("Cadastra");
					    		  btnCadastrar.doClick();
					    	  }else if(leitura.equals("88888")) {
					    		  System.out.println("Desativa");
					    		  btnDesativar.doClick();
					    	  }else if(leitura.equals("77777")) {
					    		  System.out.println("Cancela produto");
					    		  btnCancelarPedido.doClick();
					    	  }else if(leitura.equals("66666")) {
					    		  System.out.println("Altera preço");
					    		  btnAlterarPreco.doClick();
					    	  }else if(leitura.equals("55555")) {
					    		  System.out.println("EXCEL");
					    		  btnExel.doClick();
					    	  }
					    	  leitura = "";
					      }
					      
				      }
				     
				    }
				    catch(Exception e) {
				      e.printStackTrace();
				    }
				  }
				};
				
				Toolkit.getDefaultToolkit().addAWTEventListener(listener, AWTEvent.KEY_EVENT_MASK);

	}
}
