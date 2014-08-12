package com.stryksta.swtorcentral;

import java.util.ArrayList;

import com.stryksta.swtorcentral.data.AbilitiesItem;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AbilityAdapter extends ArrayAdapter<AbilitiesItem> {

		private final Context context;
		private final ArrayList<AbilitiesItem> swtorAbilities;
		int AdvancedPos1;
		int AdvancedPos2;

		public AbilityAdapter(Context context, ArrayList<AbilitiesItem> swtorAbilities) {
			super(context, R.layout.progression_item_single, swtorAbilities);
			
			this.context = context;
			this.swtorAbilities = swtorAbilities;
		}
		
		@Override
		public View getView(int position, View convertView, final ViewGroup parent) {
		    
			LayoutInflater inflater = (LayoutInflater) context
		        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			View rowView = null;
			final AbilitiesItem item = swtorAbilities.get(position);
			
			if(item.isGroupHeader()){
				rowView = inflater.inflate(R.layout.advanced_class_tab3_header, parent, false);
				
				TextView txtHeader = (TextView) rowView.findViewById(R.id.txtAbilityTitle);
				
				txtHeader.setText(item.gettxtHeader());

			} else {
				rowView = inflater.inflate(R.layout.advanced_class_tab3_row, parent, false);
				
				//LinearLayout abilityLinearLayout = (LinearLayout) rowView.findViewById(R.id.abilitylinearlayout);
				
				TextView txtViewName = (TextView) rowView.findViewById(R.id.txtName);
				TextView txtViewSummary = (TextView) rowView.findViewById(R.id.txtSummary);
				
				if (item != null) {
					txtViewName.setText(item.gettxtName());
					txtViewSummary.setText(item.getsummary());
				}
				
				/*abilityLinearLayout.setOnClickListener(new View.OnClickListener() {
		            public void onClick(View view) {
		                Toast.makeText(context, "Ability: " + item.gettxtName() + " - ID: " + item.getabilityID(), Toast.LENGTH_SHORT).show();
		            }
		        });
		        */
			}
		    return rowView;
		}
}