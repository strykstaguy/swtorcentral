package com.stryksta.swtorcentral;


import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.stryksta.swtorcentral.util.FragmentUtils;
import com.stryksta.swtorcentral.util.database.DisciplinesDatabase;

public class Test2Fragment extends Fragment{

    private DisciplinesDatabase disciplinesDB;
    private Cursor disciplinesCursor;

    int advClassID;
    String ClassName;
    String advClassDesc;
    String advClassName;

    String ClassAPC;

    String disciplineName1;
    String disciplineType1;
    String disciplineDesc1;
    String disciplineAPC1;

    String disciplineName2;
    String disciplineType2;
    String disciplineDesc2;
    String disciplineAPC2;

    String disciplineName3;
    String disciplineType3;
    String disciplineDesc3;
    String disciplineAPC3;

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

        vw_layout = inflater.inflate(R.layout.test_main_discplines, container, false);

        Bundle bundle = this.getArguments();

        if (bundle != null ) {
            advClassID = getArguments().getInt("advClassID");
            advClassName = getArguments().getString("advClassName");
            advClassDesc = getArguments().getString("advClassDesc");
            ClassAPC = getArguments().getString("ClassAPC");
        }

        Toast.makeText(getActivity(), ClassAPC, Toast.LENGTH_SHORT).show();

        //Advanced Class Title
        TextView txtClassTitle = (TextView) vw_layout.findViewById(R.id.txtClassTitle);
        txtClassTitle.setText(advClassName);

        //Advanced Class Title
        TextView txtClassDesc = (TextView) vw_layout.findViewById(R.id.txtClassDesc);
        txtClassDesc.setText(advClassDesc);

        //Disciplines
        disciplinesDB = new DisciplinesDatabase(getActivity());
        disciplinesCursor = disciplinesDB.getDisciplines(advClassID);

        //First Discipline
        if (disciplinesCursor.getCount() > 0) {
            disciplinesCursor.moveToPosition(0);
            disciplineName1 = disciplinesCursor.getString(disciplinesCursor.getColumnIndex("name"));
            disciplineType1 = disciplinesCursor.getString(disciplinesCursor.getColumnIndex("type"));
            disciplineDesc1 = disciplinesCursor.getString(disciplinesCursor.getColumnIndex("description"));
            disciplineAPC1 = disciplinesCursor.getString(disciplinesCursor.getColumnIndex("apc"));

            //Set Discipline 1 Name
            TextView txtDiscipline1Title = (TextView) vw_layout.findViewById(R.id.txtDiscipline1Title);
            txtDiscipline1Title.setText(disciplineName1);

            //Set Discipline 1 Type
            TextView txtDiscipline1Type = (TextView) vw_layout.findViewById(R.id.txtDiscipline1Type);
            txtDiscipline1Type.setText(disciplineType1);

            //Set Discipline 1 Type
            TextView txtDiscipline1Description = (TextView) vw_layout.findViewById(R.id.txtDiscipline1Description);
            txtDiscipline1Description.setText(disciplineDesc1);
        }

        //OnClick for Discipline 1
        LinearLayout txtDiscipline1 = (LinearLayout) vw_layout.findViewById(R.id.txtDiscipline1);
        txtDiscipline1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("ClassAPC", ClassAPC);
                bundle.putString("AdvAPC", disciplineAPC1);

                Test3Fragment test3frag = new Test3Fragment();
                test3frag.setArguments(bundle);
                FragmentUtils.addFragmentsInActivity(getActivity(), R.id.testFrame, test3frag, "Test3");
            }
        });

        //Second Discipline
        if (disciplinesCursor.getCount() > 0) {
            disciplinesCursor.moveToPosition(1);
            disciplineName2 = disciplinesCursor.getString(disciplinesCursor.getColumnIndex("name"));
            disciplineType2 = disciplinesCursor.getString(disciplinesCursor.getColumnIndex("type"));
            disciplineDesc2 = disciplinesCursor.getString(disciplinesCursor.getColumnIndex("description"));
            disciplineAPC2 = disciplinesCursor.getString(disciplinesCursor.getColumnIndex("apc"));

            //Set Discipline 2 Name
            TextView txtDiscipline2Title = (TextView) vw_layout.findViewById(R.id.txtDiscipline2Title);
            txtDiscipline2Title.setText(disciplineName2);

            //Set Discipline 2 Type
            TextView txtDiscipline2Type = (TextView) vw_layout.findViewById(R.id.txtDiscipline2Type);
            txtDiscipline2Type.setText(disciplineType2);

            //Set Discipline 2 Type
            TextView txtDiscipline2Description = (TextView) vw_layout.findViewById(R.id.txtDiscipline2Description);
            txtDiscipline2Description.setText(disciplineDesc2);
        }

        //OnClick for Discipline 2
        LinearLayout txtDiscipline2 = (LinearLayout) vw_layout.findViewById(R.id.txtDiscipline2);
        txtDiscipline2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getActivity(), disciplineAPC2, Toast.LENGTH_SHORT).show();
            }
        });

        //Third Discipline
        if (disciplinesCursor.getCount() > 0) {
            disciplinesCursor.moveToPosition(2);
            disciplineName3 = disciplinesCursor.getString(disciplinesCursor.getColumnIndex("name"));
            disciplineType3 = disciplinesCursor.getString(disciplinesCursor.getColumnIndex("type"));
            disciplineDesc3 = disciplinesCursor.getString(disciplinesCursor.getColumnIndex("description"));
            disciplineAPC3 = disciplinesCursor.getString(disciplinesCursor.getColumnIndex("apc"));

            //Set Discipline 3 Name
            TextView txtDiscipline3Title = (TextView) vw_layout.findViewById(R.id.txtDiscipline3Title);
            txtDiscipline3Title.setText(disciplineName3);

            //Set Discipline 3 Type
            TextView txtDiscipline3Type = (TextView) vw_layout.findViewById(R.id.txtDiscipline3Type);
            txtDiscipline3Type.setText(disciplineType3);

            //Set Discipline 3 Type
            TextView txtDiscipline3Description = (TextView) vw_layout.findViewById(R.id.txtDiscipline3Description);
            txtDiscipline3Description.setText(disciplineDesc3);
        }

        //OnClick for Discipline 3
        LinearLayout txtDiscipline3 = (LinearLayout) vw_layout.findViewById(R.id.txtDiscipline3);
        txtDiscipline3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getActivity(), disciplineAPC3, Toast.LENGTH_SHORT).show();
            }
        });

        disciplinesCursor.close();
        disciplinesDB.close();
     	return vw_layout;
	}
}
