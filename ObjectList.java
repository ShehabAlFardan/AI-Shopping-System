import java.awt.*;
import java.util.*;

// This class is a special version of a scrollable list that
// associates an object with each element in the list.

public class ObjectList extends List
{
	Vector objects;	// the objects that correspond to list entries

	public ObjectList()
	{
		objects = new Vector();
	}

	public ObjectList(int items, boolean multiSelect)
	{
		super(items, multiSelect);
		objects = new Vector();
	}

	public synchronized void addObject(Object ob)
	{
// add a string version of the object to the list
		super.addItem(ob.toString());

// add the object itself to the object vector
		objects.addElement(ob);
	}

	public synchronized void addObject(Object ob, int position)
	{
// add a string version of the object to the list
		super.addItem(ob.toString(), position);

// add the object itself to the object vector
		if (position >= objects.size()) {
			objects.addElement(ob);
		} else {
			objects.insertElementAt(ob.toString(),
				position);
		}
	}

	public synchronized void addObject(String label, Object ob)
	{
// Allow the object to be assigned a label independently of the object
		super.addItem(label);
		objects.addElement(ob);
	}

	public synchronized void addObject(String label, Object ob,
		int position)
	{
// Allow the object to be assigned a label independently of the object
		super.addItem(label, position);
		if (position >= objects.size()) {
			objects.addElement(ob);
		} else {
			objects.insertElementAt(ob.toString(),
				position);
		}
	}

	public synchronized void delObject(Object ob)
	{
// See if the object is in the vector
		int index = objects.indexOf(ob);

// If not, just return
		if (index < 0) return;

// Remove the object from the vector
		objects.removeElementAt(index);

// Remove the list entry
		super.delItem(index);
	}

	public synchronized Object getSelectedObject()
	{
// Get the index of the current selection
		int i = getSelectedIndex();

		if (i == -1) return null;

// Return the object currently selected
		return objects.elementAt(i);
	}

	public synchronized Object[] getSelectedObjects()
	{
// Get the indices of all the selected objects
		int[] selectedItems = getSelectedIndexes();

// Create an array of all the selected objects
		Object[] whichObjects = new Object[
			selectedItems.length];

		for (int i=0; i < selectedItems.length; i++) {
			whichObjects[i] = objects.elementAt(i);
		}

		return whichObjects;
	}

	public int indexOf(Object ob)
	{
// Locate a particular object
		return objects.indexOf(ob);
	}

	public Object objectAt(int index)
	{
// Return the object at a particular index
		return objects.elementAt(index);
	}

	public void replaceObject(Object ob, int index)
	{
// Change a specific entry in the vector
		replaceItem(ob.toString(), index);

// Change a specific entry in the list
		objects.setElementAt(ob, index);
	}

	public void replaceObject(String label, Object ob, int index)
	{
// Change a specific entry in the vector
		replaceItem(label, index);

// Change a specific entry in the list
		objects.setElementAt(ob, index);
	}
}