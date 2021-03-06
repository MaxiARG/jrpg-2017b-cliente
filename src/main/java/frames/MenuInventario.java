package frames;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.google.gson.Gson;

import cliente.Cliente;
import inventario.Inventario;
import juego.Pantalla;
import mensajeria.Comando;

/**
 * Clase para el menu de inventario.
 * @author Lucas
 *
 */
public class MenuInventario extends JFrame {
	/**
	 * El buen serial ID.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Boton para cancelar.
	 */
	private JButton cancelar = new JButton("Exit");

	/**
	 * Constructor del menu de inventario.
	 * @param cliente es del tipo cliente
	 */
	public MenuInventario(final Cliente cliente) {
		cancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				try {
					Gson gson = new Gson();
					cliente.getPaquetePersonaje().setComando(Comando.ACTUALIZARINVENTARIO);
					cliente.getSalida().writeObject(gson.toJson(cliente.getPaquetePersonaje()));
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Error al actualizar inventario");
				}
				Pantalla.menuInventario = null;
				dispose();
			}
		});
		this.setTitle("Inventario");
		this.setUndecorated(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		try {
			this.setLayout(new BorderLayout());
			this.add(new Inventario(cliente.getPaquetePersonaje()));
			this.add(cancelar, BorderLayout.AFTER_LAST_LINE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Falló al iniciar el inventario");

		}
		this.setBounds(600, 600, 600, 600);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setLocation(900, 140);
		this.setResizable(false);
		this.setVisible(true);
	}
}