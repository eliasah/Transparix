package examples;

import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;

/**
 * Cette classe cree un JPanel pour la selection de station dans un combobox
 * @author Abou Haydar Elias - Univ. Paris Denis Diderot
 *
 */
public class PanelStation extends JPanel {

	public PanelStation(String t,ArrayList<String> stations) {
		setLayout(new FlowLayout());
		Object[] o = stations.toArray();
		
		JLabel lbltitre = new JLabel(t);
		lbltitre.setBounds(22, 12, 86, 15);
		add(lbltitre);
		
		JLabel lblligne = new JLabel("Ligne");
		lblligne.setBounds(22, 38, 39, 15);
		add(lblligne);
		
		JComboBox cbligne = new JComboBox(new Object[]{"1","2","3","3bis","4","5","6","7","7bis","8","9","10","11","12","13","14"});
		cbligne.setBounds(92, 33, 75, 24);
		add(cbligne);
		
		JLabel lblstation = new JLabel("Station");
		lblstation.setBounds(324, 38, 70, 15);
		add(lblstation);
		
		JComboBox cbstation = new JComboBox(o);
		cbstation.setBounds(412, 33, 236, 24);
		// FIXME AutoCompletion.enable(cbstation);
		add(cbstation);
	}
}
