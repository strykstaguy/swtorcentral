package com.stryksta.swtorcentral;

import com.stryksta.swtorcentral.util.database.ClassesDatabase;

import android.support.v4.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class AdvancedClassTab1 extends Fragment {
	private Cursor advancedclass;
	private String ClassPos;
	private ClassesDatabase db;
	View vw_layout;
	private int AdvClassImgid;
	
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
		
        vw_layout = inflater.inflate(R.layout.advanced_class_tab1, container, false);
        
        Bundle bundle = getActivity().getIntent().getExtras();
		
        if ( bundle != null ) {
        	ClassPos = bundle.getString("advancedclass");
        }

        db = new ClassesDatabase(getActivity());
        advancedclass = db.getAdvancedClass(ClassPos);
		
        ImageView imgClass = (ImageView) vw_layout.findViewById(R.id.imgAdvancedClass);
		TextView txtRole = (TextView) vw_layout.findViewById(R.id.txtRole);
		TextView txtArmor = (TextView) vw_layout.findViewById(R.id.txtArmor);
		TextView txtWeapons = (TextView) vw_layout.findViewById(R.id.txtWeapons);
		TextView txtAttribute = (TextView) vw_layout.findViewById(R.id.txtAttribute);
		TextView txtAdvDesc = (TextView) vw_layout.findViewById(R.id.txtAdvDesc);

		if (advancedclass.moveToFirst()) {
			AdvClassImgid = getResources().getIdentifier(advancedclass.getString(advancedclass.getColumnIndex("advanced_class_icon")), "drawable", getActivity().getPackageName());
			imgClass.setImageResource(AdvClassImgid);
			txtRole.setText("Role: " + advancedclass.getString(advancedclass.getColumnIndex("role")));
			txtArmor.setText("Armor: " + advancedclass.getString(advancedclass.getColumnIndex("armor")));
			txtWeapons.setText("Weapon(s): " + advancedclass.getString(advancedclass.getColumnIndex("weapons")));
			txtAttribute.setText("Primary Attribute: " + advancedclass.getString(advancedclass.getColumnIndex("priattribute")));
			txtAdvDesc.setText(advancedclass.getString(advancedclass.getColumnIndex("description")));
		}
		
		advancedclass.close();
		
     // Debug the thread name
     	Log.d("SWTORCentral", Thread.currentThread().getName());
     		
		return vw_layout;
	}

}