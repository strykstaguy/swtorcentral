package com.stryksta.swtorcentral;


import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.stryksta.swtorcentral.util.database.DisciplinesDatabase;

public class Test2Fragment extends Fragment{

    private DisciplinesDatabase disciplinesDB;
    private Cursor disciplinesCursor;

    int advClassID;
    String ClassName;
    String advClassDesc;
    String advClassName;

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
        }

        //Toast.makeText(getActivity(), advClassID, Toast.LENGTH_SHORT).show();

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
            String disciplineName = disciplinesCursor.getString(disciplinesCursor.getColumnIndex("name"));
            String disciplineType = disciplinesCursor.getString(disciplinesCursor.getColumnIndex("type"));
            String disciplineDesc = disciplinesCursor.getString(disciplinesCursor.getColumnIndex("description"));

            //Set Discipline 1 Name
            TextView txtDiscipline1Title = (TextView) vw_layout.findViewById(R.id.txtDiscipline1Title);
            txtDiscipline1Title.setText(disciplineName);

            //Set Discipline 1 Type
            TextView txtDiscipline1Type = (TextView) vw_layout.findViewById(R.id.txtDiscipline1Type);
            txtDiscipline1Type.setText(disciplineType);

            //Set Discipline 1 Type
            TextView txtDiscipline1Description = (TextView) vw_layout.findViewById(R.id.txtDiscipline1Description);
            txtDiscipline1Description.setText(disciplineDesc);
        }

        //Second Discipline
        if (disciplinesCursor.getCount() > 0) {
            disciplinesCursor.moveToPosition(1);
            String disciplineName = disciplinesCursor.getString(disciplinesCursor.getColumnIndex("name"));
            String disciplineType = disciplinesCursor.getString(disciplinesCursor.getColumnIndex("type"));
            String disciplineDesc = disciplinesCursor.getString(disciplinesCursor.getColumnIndex("description"));

            //Set Discipline 2 Name
            TextView txtDiscipline2Title = (TextView) vw_layout.findViewById(R.id.txtDiscipline2Title);
            txtDiscipline2Title.setText(disciplineName);

            //Set Discipline 2 Type
            TextView txtDiscipline2Type = (TextView) vw_layout.findViewById(R.id.txtDiscipline2Type);
            txtDiscipline2Type.setText(disciplineType);

            //Set Discipline 2 Type
            TextView txtDiscipline2Description = (TextView) vw_layout.findViewById(R.id.txtDiscipline2Description);
            txtDiscipline2Description.setText(disciplineDesc);
        }

        //Third Discipline
        if (disciplinesCursor.getCount() > 0) {
            disciplinesCursor.moveToPosition(2);
            String disciplineName = disciplinesCursor.getString(disciplinesCursor.getColumnIndex("name"));
            String disciplineType = disciplinesCursor.getString(disciplinesCursor.getColumnIndex("type"));
            String disciplineDesc = disciplinesCursor.getString(disciplinesCursor.getColumnIndex("description"));

            //Set Discipline 3 Name
            TextView txtDiscipline3Title = (TextView) vw_layout.findViewById(R.id.txtDiscipline3Title);
            txtDiscipline3Title.setText(disciplineName);

            //Set Discipline 3 Type
            TextView txtDiscipline3Type = (TextView) vw_layout.findViewById(R.id.txtDiscipline3Type);
            txtDiscipline3Type.setText(disciplineType);

            //Set Discipline 3 Type
            TextView txtDiscipline3Description = (TextView) vw_layout.findViewById(R.id.txtDiscipline3Description);
            txtDiscipline3Description.setText(disciplineDesc);
        }

        disciplinesCursor.close();
        disciplinesDB.close();
     	return vw_layout;
	}
}
