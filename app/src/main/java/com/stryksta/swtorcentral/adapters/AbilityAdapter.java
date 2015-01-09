package com.stryksta.swtorcentral.adapters;

import java.util.ArrayList;

import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.data.AbilitiesItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AbilityAdapter extends ArrayAdapter<AbilitiesItem> {

		private final Context context;
		private final ArrayList<AbilitiesItem> swtorAbilities;
		int AdvancedPos1;
		int AdvancedPos2;

		public AbilityAdapter(Context context, ArrayList<AbilitiesItem> swtorAbilities) {
			super(context, R.layout.achievement_row, swtorAbilities);
			
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
				
				txtHeader.setText(item.getAblHeader());

			} else {
				rowView = inflater.inflate(R.layout.advanced_class_tab3_row, parent, false);
				
				//LinearLayout abilityLinearLayout = (LinearLayout) rowView.findViewById(R.id.abilitylinearlayout);
				
				TextView txtViewName = (TextView) rowView.findViewById(R.id.txtName);
				TextView txtViewSummary = (TextView) rowView.findViewById(R.id.txtSummary);
				
				if (item != null) {
					txtViewName.setText(item.getablName());
					txtViewSummary.setText(item.getablDesc());
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