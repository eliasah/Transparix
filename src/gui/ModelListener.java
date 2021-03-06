package gui;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import structure.Graph;

/**
 * ModelLister for the main Model
 * 
 * @author Abou Haydar Elias - Univ. Paris Denis Diderot
 *
 */
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