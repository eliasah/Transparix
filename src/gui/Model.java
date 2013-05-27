package gui;

import itinerary.BFS;

import java.util.LinkedList;

import javax.swing.BoundedRangeModel;
import javax.swing.DefaultBoundedRangeModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;

import structure.Graph;
import structure.Station;

/**
 * Model du Programme
 * @author Abou Haydar Elias - Univ. Paris Denis Diderot
 *
 * TODO impletementer
 */
public class Model {
	protected ChangeEvent changeEvent = null;
	protected EventListenerList listenerList = new EventListenerList();

	private Graph graph;
	private BFS parcours;
	private LinkedList<Integer> path;
	private Station sStart;
	private Station sEnd;

	public Model(Graph graph) {
		super();
		this.graph = graph;
	}

	public Graph getGraph() {
		return graph;
	}

	public void setGraph(Graph graph) {
		this.graph = graph;
	}

	public BFS getParcours() {
		return parcours;
	}

	public void setParcours(BFS parcours) {
		this.parcours = parcours;
	}

	public LinkedList<Integer> getPath() {
		return path;
	}

	public void setPath(LinkedList<Integer> path) {
		this.path = path;
	}

	public Station getsStart() {
		return sStart;
	}

	public void setsStart(Station sStart) {
		this.sStart = sStart;
	}

	public Station getsEnd() {
		return sEnd;
	}

	public void setsEnd(Station sEnd) {
		this.sEnd = sEnd;
	}
	
    public void addChangeListener(ChangeListener l) {
        listenerList.add(ChangeListener.class, l);
    }

    public void removeChangeListener(ChangeListener l) {
        listenerList.remove(ChangeListener.class, l);
    }

    protected void fireStateChanged() {
        Object[] listeners = listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -=2 ) {
            if (listeners[i] == ChangeListener.class) {
                if (changeEvent == null) {
                    changeEvent = new ChangeEvent(this);
                }
                ((ChangeListener)listeners[i+1]).stateChanged(changeEvent);
            }
        }
    }
}
