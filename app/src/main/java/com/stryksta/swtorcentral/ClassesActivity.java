package com.stryksta.swtorcentral;


import java.util.ArrayList;

import com.stryksta.swtorcentral.adapters.ClassesAdapter;
import com.stryksta.swtorcentral.data.AdvancedClassesItem;
import com.stryksta.swtorcentral.data.ClassItem;
import com.stryksta.swtorcentral.util.database.ClassesDatabase;

import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class ClassesActivity extends Fragment{
	
	ListView listView;
	ArrayList<ClassItem> classesArray = new ArrayList<ClassItem>();
    ArrayList<AdvancedClassesItem> AdvancedClassesArray = new ArrayList<AdvancedClassesItem>();
	ClassesAdapter adapter;
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_NAME = "name";
    public static final String APC_NAME = "apc";
    public static final String COLUMN_DESC = "description";
	public static final String COLUMN_RESOURCE = "resource";
	
	private ClassesDatabase db;
	private Cursor classesDB;
	private Cursor advClassesCursor;
	String advClassName1;
	String advClassName2;

    String advClassDesc1;
    String advClassDesc2;

	int advClassimg1;
	int advClassimg2;
	int advClassID1;
	int advClassID2;
	View vw_layout;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		
		if (container == null) {
			// We have different layouts, and in one of them this
			// fragment's containing frame doesn't exist. The fragment
			// may still be created from its saved state, but there is
			// no reason to try to create its view hierarchy because it
			// won't be displayed. Note this is not needed -- we could
			// just run the code below, where we would create and return
			// the view hierarchy; it would just never be used.
			return null;
		}
		
        vw_layout = inflater.inflate(R.layout.class_main, container, false);
        
        listView = (ListView) vw_layout.findViewById(R.id.listView1);
        
        //getActivity().setTitle("Classes");
        
        db = new ClassesDatabase(getActivity());
        classesDB = db.getClasses();
        
        
        classesArray.clear();
        AdvancedClassesArray.clear();

		if (classesDB.moveToFirst()) {
			
			do {
				Integer classID = classesDB.getInt(classesDB.getColumnIndex(COLUMN_ID));
            	String className = classesDB.getString(classesDB.getColumnIndex(COLUMN_NAME));
                String classDescription = classesDB.getString(classesDB.getColumnIndex(COLUMN_DESC));
            	String classResource = classesDB.getString(classesDB.getColumnIndex(COLUMN_RESOURCE));
                String classAPC = classesDB.getString(classesDB.getColumnIndex(APC_NAME));
            	int classIcon = getResources().getIdentifier(classesDB.getString(classesDB.getColumnIndex("class_icon")), "drawable", getActivity().getPackageName());

                //
            	classesArray.add(new ClassItem(className, classIcon, classID, classResource, classDescription, classAPC));

                advClassesCursor = db.getAdvancedClasses(classID);


                advClassesCursor.moveToPosition(0);
            		advClassID1 = advClassesCursor.getInt(advClassesCursor.getColumnIndex(COLUMN_ID));
                    advClassName1 = advClassesCursor.getString(advClassesCursor.getColumnIndex("class"));
                    advClassDesc1 = advClassesCursor.getString(advClassesCursor.getColumnIndex("description"));
                	advClassimg1 = getResources().getIdentifier(advClassesCursor.getString(advClassesCursor.getColumnIndex("advanced_class_icon")), "drawable", getActivity().getPackageName());

                advClassesCursor.moveToPosition(1);
                	advClassID2 = advClassesCursor.getInt(advClassesCursor.getColumnIndex(COLUMN_ID));
                    advClassName2 = advClassesCursor.getString(advClassesCursor.getColumnIndex("class"));
                    advClassDesc2 = advClassesCursor.getString(advClassesCursor.getColumnIndex("description"));
                	advClassimg2 = getResources().getIdentifier(advClassesCursor.getString(advClassesCursor.getColumnIndex("advanced_class_icon")), "drawable", getActivity().getPackageName());

                    AdvancedClassesArray.add(new AdvancedClassesItem(advClassID1, advClassimg1, advClassName1, advClassDesc1, advClassID2, advClassimg2, advClassName2, advClassDesc2));
                    advClassesCursor.close();

			} while (classesDB.moveToNext());
		}

		classesDB.close();
		
        
		
		adapter = new ClassesAdapter(getActivity(), classesArray, AdvancedClassesArray);
		listView.setAdapter(adapter);
		
		return vw_layout;
	}
	
	@Override
	public void onDestroyView() {
	    super.onDestroyView();
	    //getActivity().getActionBar().setTitle("Previous Title");
	}
	
}
