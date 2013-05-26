package test;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import structure.Graph;

import gui.Model;

class ModelListner extends Model implements ChangeListener {

	private Model sourceModel;

	public ModelListner(Graph graph) {
		super(graph);
        sourceModel.addChangeListener(this);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		fireStateChanged();		
	}
	
}