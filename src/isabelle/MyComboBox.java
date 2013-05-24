package isabelle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class MyComboBox extends JComboBox<String> {

	private Collection<Station> stations;
	private JTextField jtf;
	private DefaultComboBoxModel<String> model;

	public MyComboBox(Collection<Station> stations) {
		super();
		this.stations = stations;
		this.model = new DefaultComboBoxModel<String>();
		this.setEditable(true);
		this.jtf = (JTextField) this.getEditor().getEditorComponent();
		this.jtf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					setModel(model);
				} catch (Exception ex) {
				}
			}
		});

		DocumentListener documentListener = new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent documentEvent) {
			}

			@Override
			public void insertUpdate(DocumentEvent documentEvent) {
				updateValues();
			}

			@Override
			public void removeUpdate(DocumentEvent documentEvent) {
				updateValues();
			}
		};
		jtf.getDocument().addDocumentListener(documentListener);
	}

	private void updateValues() {
		Document document = this.jtf.getDocument();
		int length = document.getLength();
		String text = "";
		try {
			text = document.getText(0, length);
			for (Station s : this.stations) {
				if (s.getName().startsWith(text))
					continue;
				else if (s.getName().contains(text))
					continue;
			}
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		// FIXME
		model.addElement("a");
		model.addElement("b");
		model.addElement("c");
	}

	public static void main(String[] args) {
		// récuparation de la liste des stations
		Collection<Station> stations = Extraction.extractStations(
				"data/data_v1/stations.txt").values();

		// création du vecteur contenant le nom des stations
		Iterator<Station> it = stations.iterator();
		Vector<String> values = new Vector<String>();
		while (it.hasNext())
			values.addElement(it.next().getName());
		Collections.sort(values, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});

		// création de la combobox
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(
				values);
		MyComboBox combo = new MyComboBox(stations);
		combo.setModel(model);

		// création de la fenêtre
		JFrame frame = new JFrame("MyComboBox");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(combo);
		frame.pack();
		frame.setVisible(true);
	}

}
