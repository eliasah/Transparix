package gui;

import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
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
import tools.Couple;

public class StationSelectionCombo extends JComboBox<String> {

	private Collection<Couple<Integer, Station>> stations;
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
	public StationSelectionCombo(LinkedList<Couple<Integer, Station>> stations) {
		super();
		this.stations = stations;
		// generation du modele de donnees a�partir des noms de stations
		Vector<String> values = new Vector<String>();
		Iterator<Couple<Integer, Station>> it = stations.iterator();

		for (Couple<Integer, Station> c : stations) {
			values.addElement(c.second().getName());
		}

		// while (it.hasNext())
		// values.addElement(it.next().getName());
		Collections.sort(values, this.comp);
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(
				values);
		// ajout du modele de donnees a� afficher
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
	 * Met à jour le modèle de données à afficher en fonction des lettres
	 * entrées par l'utilisateur. Affiche en priorité les noms commençant par la
	 * chaîne entrée, puis les noms contenant la chaîne entrée.
	 */
	public void updateModel() {
		Vector<String> starting = new Vector<String>();
		Vector<String> containing = new Vector<String>();
		Document document = this.jtf.getDocument();
		int length = document.getLength();
		String text = "";
		try {
			text = document.getText(0, length);
			// récupération des stations qui correspondent à la chaîne
			// entrée
			for (Couple<Integer, Station> c : this.stations) {
				String name = c.second().getName();
				if (name.toLowerCase().startsWith(text.toLowerCase()))
					starting.add(name);
				else if (name.toLowerCase().contains(text.toLowerCase()))
					containing.add(name);
			}
			// tri des données obtenues qui commencent par la chaîne entrée
			Collections.sort(starting, this.comp);
			// tri des données obtenues qui contiennent la chaîne entrée
			Collections.sort(containing, this.comp);
			// concaténation des résultats
			Iterator<String> it = containing.iterator();
			while (it.hasNext())
				starting.add(it.next());
			// mise à jour du modèle de données à afficher
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

}
