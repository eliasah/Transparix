package gui;

import java.util.Collection;
import java.util.Comparator;
import java.util.Vector;
import java.util.Iterator;
import java.util.Collections;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import structure.Graph;
import structure.Station;

public class StationSelectionCombo extends JComboBox<String> {

	private Collection<Station> stations;
	private JTextField jtf;
	private Comparator<String> comp = new Comparator<String>() {
		@Override
		public int compare(String o1, String o2) {
			return o1.compareTo(o2);
		}
	};

	/**
	 * Constructeur.
	 * 
	 * @param stations
	 *            L'ensemble des stations.
	 */
	public StationSelectionCombo(Collection<Station> stations) {
		super();
		this.stations = stations;
		// generation du modele de donnees a partir des noms de stations
		Vector<String> values = new Vector<String>();
		Iterator<Station> it = stations.iterator();
		while (it.hasNext())
			values.addElement(it.next().getName());
		Collections.sort(values, this.comp);
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(
				values);
		// ajout du modele de donnees a afficher
		this.setModel(model);
		// aucune station n'est selectionnee au depart
		this.setSelectedIndex(-1);
		// rend la combobox editable
		this.setEditable(true);
		// recuperation de l'editeur de la combobox
		this.jtf = (JTextField) this.getEditor().getEditorComponent();
		// ajout d'un listener sur la combobox
		jtf.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent documentEvent) {
			}

			@Override
			public void insertUpdate(DocumentEvent documentEvent) {
				updateModel();
			}

			@Override
			public void removeUpdate(DocumentEvent documentEvent) {
				updateModel();
			}
		});
	}

	/**
	 * Met a jour le modele de donnees a afficher en fonction des lettres
	 * entrees par l'utilisateur. Affiche en priorite les noms commençant par la
	 * chaine entree, puis les noms contenant la chaine entree.
	 */
	public void updateModel() {
		Vector<String> starting = new Vector<String>();
		Vector<String> containing = new Vector<String>();
		Document document = this.jtf.getDocument();
		int length = document.getLength();
		String text = "";
		try {
			text = document.getText(0, length);
			// recuperation des stations qui correspondent a la chaine entree
			for (Station s : this.stations) {
				String name = s.getName();
				if (name.toLowerCase().startsWith(text.toLowerCase()))
					starting.add(name);
				else if (name.toLowerCase().contains(text.toLowerCase()))
					containing.add(name);
			}
			// tri des donnees obtenues qui commencent par la chaine entree
			Collections.sort(starting, this.comp);
			// tri des donnees obtenues qui contiennent la chaine entree
			Collections.sort(containing, this.comp);
			// concatenation des resultats
			Iterator<String> it = containing.iterator();
			while (it.hasNext())
				starting.add(it.next());
			// mise a jour du modele de donnees a afficher
			try {
				this.setModel(new DefaultComboBoxModel<String>(starting));
			} catch (Exception e) {
			}
			try {
				this.showPopup();
			} catch (Exception e) {
			}
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// recuparation de la liste des stations
		Graph g = new Graph();
		Collection<Station> stations = g.stationsToHashtable().values();

		// creation de la combobox
		StationSelectionCombo combo = new StationSelectionCombo(stations);

		// creation de la fenetre
		JFrame frame = new JFrame("MyComboBox");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(combo);
		frame.pack();
		frame.setVisible(true);
	}

}
