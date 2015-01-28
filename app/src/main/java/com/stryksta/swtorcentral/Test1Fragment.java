package com.stryksta.swtorcentral;


import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.stryksta.swtorcentral.adapters.AchievementCategoryAdapter;
import com.stryksta.swtorcentral.adapters.ClassesAdapter;
import com.stryksta.swtorcentral.adapters.TestAdapter;
import com.stryksta.swtorcentral.data.AchievementCategoryItem;
import com.stryksta.swtorcentral.data.ClassItem;
import com.stryksta.swtorcentral.util.AutoMeasureGridView;
import com.stryksta.swtorcentral.util.FragmentUtils;
import com.stryksta.swtorcentral.util.SessionManager;
import com.stryksta.swtorcentral.util.database.AchievementsDatabase;
import com.stryksta.swtorcentral.util.database.ClassesDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class Test1Fragment extends Fragment{

    ListView classesListView;
    ArrayList<ClassItem> classesArray = new ArrayList<ClassItem>();
    TestAdapter testAdapter;

    private ClassesDatabase classesDB;
    private Cursor classesCursor;
    private Cursor advClassesCursor;
    String advClassName1;
    String advClassName2;
    String advClassDesc1;
    String advClassDesc2;
    int advClassIcon1;
    int advClassIcon2;
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

        classesListView = (ListView) vw_layout.findViewById(R.id.listView1);

        classesDB = new ClassesDatabase(getActivity());
        classesCursor = classesDB.getClasses();


        classesArray.clear();

        if (classesCursor.moveToFirst()) {

            do {
                Integer id = classesCursor.getInt(classesCursor.getColumnIndex("_id"));
                String name = classesCursor.getString(classesCursor.getColumnIndex("name"));
                String resource = classesCursor.getString(classesCursor.getColumnIndex("resource"));
                String apc = classesCursor.getString(classesCursor.getColumnIndex("apc"));
                //int classicon = getResources().getIdentifier(classesDB.getString(classesDB.getColumnIndex("class_icon")), "drawable", getActivity().getPackageName());

                classesArray.add(new ClassItem(name));

                advClassesCursor = classesDB.getAdvancedClasses(id);

                //Get Advanced Class #1
                advClassesCursor.moveToPosition(0);
                advClassID1 = advClassesCursor.getInt(advClassesCursor.getColumnIndex("_id"));
                advClassName1 = advClassesCursor.getString(advClassesCursor.getColumnIndex("class"));
                advClassDesc1 = advClassesCursor.getString(advClassesCursor.getColumnIndex("description"));
                advClassIcon1 = getResources().getIdentifier(advClassesCursor.getString(advClassesCursor.getColumnIndex("advanced_class_icon")), "drawable", getActivity().getPackageName());

                //Get Advanced Class #2
                advClassesCursor.moveToPosition(1);
                advClassID2 = advClassesCursor.getInt(advClassesCursor.getColumnIndex("_id"));
                advClassName2 = advClassesCursor.getString(advClassesCursor.getColumnIndex("class"));
                advClassDesc2 = advClassesCursor.getString(advClassesCursor.getColumnIndex("description"));
                advClassIcon2 = getResources().getIdentifier(advClassesCursor.getString(advClassesCursor.getColumnIndex("advanced_class_icon")), "drawable", getActivity().getPackageName());

                classesArray.add(new ClassItem(name, id, resource, advClassID1, advClassIcon1, advClassName1, advClassDesc1, advClassID2, advClassIcon2, advClassName2, advClassDesc2, apc));
                advClassesCursor.close();
            } while (classesCursor.moveToNext());
        }

        classesCursor.close();
        //classesDB.close();


        testAdapter = new TestAdapter(getActivity(), classesArray);
        classesListView.setAdapter(testAdapter);
        /*
        classesListView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                Category2Fragment category2frag = new Category2Fragment();

                Bundle args = new Bundle();
                args.putString("category1", testAdapter.getItem(position).gettxtClass());
                category2frag.setArguments(args);

                FragmentUtils.addFragmentsInActivity(getActivity(), R.id.achievementframe, category2frag, "Category2");

            }});
        */
     	return vw_layout;
	}
}
