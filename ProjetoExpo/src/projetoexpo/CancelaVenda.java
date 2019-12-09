package projetoexpo;

import java.awt.AWTEvent;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import java.awt.Font;

public class CancelaVenda extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private static Timer timer;
	private String leitura = "";
	private AWTEventListener listener;
	private Mercadoria mercadoria;
	private Pedido pedido;
	private Venda venda;
	private int idPedido;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CancelaVenda frame = new CancelaVenda(0, new TelaPrincipal());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public int getIdPedido() {
		return idPedido;
	}



	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
		System.out.println(idPedido);
	}
	
	/**
	 * Create the frame.
	 */
	public CancelaVenda(int idPed, TelaPrincipal telaPrincipal ) {
		
		this.idPedido = idPed;
		
		Connection conn = Conexao.getConnection();
		
		setTitle("Cancelar venda");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 556, 409);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCodigoDeBarras = new JLabel("Codigo de barras");
		lblCodigoDeBarras.setFont(new Font("Arial", Font.PLAIN, 14));
		lblCodigoDeBarras.setBounds(20, 29, 150, 20);
		contentPane.add(lblCodigoDeBarras);
		
		JLabel lblPro = new JLabel("Produto");
		lblPro.setFont(new Font("Arial", Font.PLAIN, 14));
		lblPro.setBounds(20, 77, 132, 20);
		contentPane.add(lblPro);
		
		JLabel lblPreo = new JLabel("Pre\u00E7o");
		lblPreo.setFont(new Font("Arial", Font.PLAIN, 14));
		lblPreo.setBounds(20, 122, 132, 20);
		contentPane.add(lblPreo);
		
		JLabel lblProduto = new JLabel("");
		lblProduto.setFont(new Font("Arial", Font.PLAIN, 14));
		lblProduto.setBounds(200, 77, 293, 20);
		contentPane.add(lblProduto);
		
		JLabel lblPreco = new JLabel("");
		lblPreco.setFont(new Font("Arial", Font.PLAIN, 14));
		lblPreco.setBounds(200, 122, 150, 20);
		contentPane.add(lblPreco);
		
		JLabel lblNao = new JLabel("Cancelar");
		lblNao.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNao.setBounds(307, 184, 132, 27);
		contentPane.add(lblNao);
		
		JLabel lblSim = new JLabel("Confirmar");
		lblSim.setFont(new Font("Arial", Font.PLAIN, 14));
		lblSim.setBounds(20, 184, 150, 27);
		contentPane.add(lblSim);
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conn.setAutoCommit(false);
					mercadoria = new Mercadoria();
					mercadoria.setCodigo(textField.getText());
					mercadoria.carregar(conn);
					
					
					if (mercadoria.getId() != -1) {
						lblProduto.setText(mercadoria.getDescricao());
						lblPreco.setText(mercadoria.getPreco() + "");
						textField.setEditable(false);
						lblSim.requestFocus();
					}
					
				} catch (SQLException erro){
					JOptionPane.showMessageDialog(null, erro + "erro1");
			         if(conn != null){
			            try{
			               conn.rollback();
			            } 
			            catch(SQLException e1){
			            	JOptionPane.showMessageDialog(null, e1 + "erro2");
			            }
			         }
			      } 
//			      finally{
//			         if(conn != null){
//			            try{
//			               conn.close();
//			            } 
//			            catch(SQLException e1){
//			            	JOptionPane.showMessageDialog(null, e1 + "erro3");
//			            }
//			         }      
//			      }
			}
		});
		textField.setBounds(200, 26, 257, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnConfirma = new JButton("");
		btnConfirma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					conn.setAutoCommit(false);
					mercadoria = new Mercadoria();
					pedido = new Pedido();
					venda = new Venda();
					mercadoria.setCodigo(textField.getText());
					mercadoria.carregar(conn);
					
					if (mercadoria.getId() != -1) {
						System.out.println("pedido   " + idPed);
						System.out.println(mercadoria.getCodigo());
						venda.deleteVenda(conn, idPed, mercadoria.getCodigo());
						
						
						venda.trazTotal(conn, idPed);
						venda.trazQuantidade(conn, idPed);
						System.out.println("as  " + venda.getQuantidade());
						
						pedido.setTotalItens(venda.getQuantidade());
						pedido.setTotalPedido(venda.getPreco());
						pedido.atualizar(conn, idPed);
						
						conn.commit();
						telaPrincipal.cancelaVenda();
						telaPrincipal.repopulaTextAreaVenda(idPed);
						telaPrincipal.atualizaTotal(idPed);
						
						
						
						JOptionPane.showMessageDialog(null, "Produto removido do carrinho");
					} else {
						JOptionPane.showMessageDialog(null, "Não existe esse produto");
					}
					
					Toolkit.getDefaultToolkit().removeAWTEventListener(listener);
					
					dispose();
					
				} catch (SQLException erro){
					JOptionPane.showMessageDialog(null, erro + "erro1");
			         if(conn != null){
			            try{
			               conn.rollback();
			            } 
			            catch(SQLException e1){
			            	JOptionPane.showMessageDialog(null, e1 + "erro2");
			            }
			         }
			      } 
			      finally{
			         if(conn != null){
			            try{
			               conn.close();
			            } 
			            catch(SQLException e1){
			            	JOptionPane.showMessageDialog(null, e1 + "erro3");
			            }
			         }      
			      }
			}
		});
		btnConfirma.setIcon(new ImageIcon(CancelaVenda.class.getResource("/ImageLib/SIM.jpg")));
		btnConfirma.setBounds(20, 224, 219, 85);
		contentPane.add(btnConfirma);
		
		JButton btnCancelar = new JButton("");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Toolkit.getDefaultToolkit().removeAWTEventListener(listener);
				dispose();
			}
		});
		btnCancelar.setIcon(new ImageIcon(CancelaVenda.class.getResource("/ImageLib/NAO.jpg")));
		btnCancelar.setBounds(307, 224, 219, 85);
		contentPane.add(btnCancelar);
		
		listener = new AWTEventListener() {
			  public void eventDispatched(AWTEvent event) {
			    try {
			      KeyEvent evt = (KeyEvent)event;
			      if(evt.getID() == KeyEvent.KEY_PRESSED) {
				      System.out.println("Tecla: "+evt.getKeyChar());
				      leitura += evt.getKeyChar();
				      if(leitura.length()==5) {
//				    	  lblCode.setText(leitura);
				    	  if(leitura.equals("11111")) {
				    		  System.out.println("sim");
				    		  btnConfirma.doClick();
				    	  } else if(leitura.equals("00000")) {
				    		  System.out.println("nao");
				    		  btnCancelar.doClick();
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
