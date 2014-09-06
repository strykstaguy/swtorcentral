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
			holder.txtViewLegacy = (TextView) v.findViewById(R.id.txtLegacy);
			holder.txtViewDescription = (TextView) v.findViewById(R.id.txtDescription);
			holder.txtViewLevel = (TextView) v.findViewById(R.id.txtLevel);
			holder.txtViewClass = (TextView) v.findViewById(R.id.txtClass);
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
			
			//Required
	        holder.txtViewName.setText(characterItem.getName());
			holder.txtViewLevel.setText("Level " + characterItem.getLevel());
			
			//Optional
			if (characterItem.getDescription() == null) {
	        	holder.txtViewDescription.setVisibility(View.GONE);
	        } else {
	        	holder.txtViewDescription.setText(characterItem.getDescription());
	        }
			
			if (characterItem.getLegacy().equals("")) {
				holder.txtViewLegacy.setText("No Legacy");
	        } else {
	        	holder.txtViewLegacy.setText("The " + characterItem.getLegacy() + " Legacy");
	        }
			
			if (characterItem.getAdvancedClass() == null) {
				holder.txtViewClass.setVisibility(View.GONE);
	        } else {
	        	holder.txtViewClass.setText(characterItem.getAdvancedClass());
	        }
			
			if (characterItem.getGender() == null && characterItem.getRace() == null) {
				holder.txtViewRace.setVisibility(View.GONE);
	        } else if(characterItem.getGender() == null && characterItem.getRace() != null) {
	        	holder.txtViewRace.setText(characterItem.getRace());
	        } else if(characterItem.getGender() != null && characterItem.getRace() == null) {
	        	holder.txtViewRace.setText(characterItem.getGender());
	        } else {
	        	holder.txtViewRace.setText(characterItem.getGender() + " " + characterItem.getRace());
	        }
			
			if (characterItem.getAlignment() == null) {
				holder.txtViewAlignment.setVisibility(View.GONE);
	        } else {
	        	holder.txtViewAlignment.setText("Alignment: " + characterItem.getAlignment());
	        }
			
			if (characterItem.getCrewSkill_1() == null) {
				holder.txtViewCrewSkill1.setVisibility(View.GONE);
	        } else {
	        	holder.txtViewCrewSkill1.setText("Crew Skill 1: " + characterItem.getCrewSkill_1());
	        }
			
			if (characterItem.getCrewSkill_2() == null) {
				holder.txtViewCrewSkill2.setVisibility(View.GONE);
	        } else {
	        	holder.txtViewCrewSkill2.setText("Crew Skill 2: " + characterItem.getCrewSkill_2());
	        }
			
			if (characterItem.getCrewSkill_3() == null) {
				holder.txtViewCrewSkill3.setVisibility(View.GONE);
	        } else {
	        	holder.txtViewCrewSkill3.setText("Crew Skill 3: " + characterItem.getCrewSkill_3());
	        }
		}
		
		return v;
	}
	
	private static class ViewHolder {
		public TextView txtViewName;
		public TextView txtViewLegacy;
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