package com.stryksta.swtorcentral;

import java.util.ArrayList;

import com.stryksta.swtorcentral.data.CharacterItem;
import com.stryksta.swtorcentral.data.DatacronItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;


public class CharacterAdapter extends ArrayAdapter<CharacterItem> {
	private final ArrayList<CharacterItem> results;
	Context mContext;
	public CharacterAdapter(Context mContext, int resource, int textViewResourceId, final ArrayList<CharacterItem> results) {

		super(mContext, textViewResourceId, results);
        this.results = results;
        this.mContext = mContext;
	}
	@Override public View getView(int position, View convertView, ViewGroup parent) {
		
		View v = convertView;
		ViewHolder holder;

		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.character_main_row, parent, false);
			holder = new ViewHolder();

			holder.txtViewName = (TextView) v.findViewById(R.id.txtName);
			holder.txtViewDescription = (TextView) v.findViewById(R.id.txtDescription);
			holder.txtViewLevel = (TextView) v.findViewById(R.id.txtLevel);
			holder.txtViewClass = (TextView) v.findViewById(R.id.txtClass);
			holder.txtViewGender = (TextView) v.findViewById(R.id.txtGender);
			holder.txtViewRace = (TextView) v.findViewById(R.id.txtRace);
			holder.txtViewAlignment = (TextView) v.findViewById(R.id.txtAlignment);
			holder.txtViewCrewSkill1 = (TextView) v.findViewById(R.id.txtCrewSkill1);
			holder.txtViewCrewSkill2 = (TextView) v.findViewById(R.id.txtCrewSkill2);
			holder.txtViewCrewSkill3 = (TextView) v.findViewById(R.id.txtCrewSkill3);
			
			v.setTag(holder);

		} else {
			holder = (ViewHolder) v.getTag();
		}
		
		CharacterItem characterItem = results.get(position);
		
		if (characterItem != null) {
			holder.txtViewName.setText(characterItem.getName());
			holder.txtViewDescription.setText(characterItem.getDescription());
			holder.txtViewLevel.setText("Level " + characterItem.getLevel());
			holder.txtViewClass.setText(characterItem.getAdvancedClass());
			holder.txtViewGender.setText("Gender: " + characterItem.getGender());
			holder.txtViewRace.setText("Race: " + characterItem.getRace());
			holder.txtViewAlignment.setText("Alignment: " + characterItem.getAlignment());
			holder.txtViewCrewSkill1.setText("Crew Skill 1: " + characterItem.getCrewSkill_1());
			holder.txtViewCrewSkill2.setText("Crew Skill 2: " + characterItem.getCrewSkill_2());
			holder.txtViewCrewSkill3.setText("Crew Skill 3: " + characterItem.getCrewSkill_3());
		}
		
		return v;
	}
	
	private static class ViewHolder {
		public TextView txtViewName;
		public TextView txtViewDescription;
		public TextView txtViewLevel;
		public TextView txtViewClass;
		public TextView txtViewGender;
		public TextView txtViewRace;
		public TextView txtViewAlignment;
		public TextView txtViewCrewSkill1;
		public TextView txtViewCrewSkill2;
		public TextView txtViewCrewSkill3;
	}
}