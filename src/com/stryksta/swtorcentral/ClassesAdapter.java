package com.stryksta.swtorcentral;

import java.util.ArrayList;

import com.stryksta.swtorcentral.data.ProgressionItem;
import com.stryksta.swtorcentral.data.ClassItem;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ClassesAdapter extends ArrayAdapter<ClassItem> {

		private final Context context;
		private final ArrayList<ClassItem> swtorClasses;
		int AdvancedPos1;
		int AdvancedPos2;

		public ClassesAdapter(Context context, ArrayList<ClassItem> swtorClasses) {
			super(context, R.layout.progression_item_single, swtorClasses);
			
			this.context = context;
			this.swtorClasses = swtorClasses;
		}
		
		@Override
		public View getView(int position, View convertView, final ViewGroup parent) {
		    
			// 1. Create inflater 
			LayoutInflater inflater = (LayoutInflater) context
		        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		    
			// 2. Get rowView from inflater
			
			View rowView = null;
			final ClassItem item = swtorClasses.get(position);
			
			if(item.isGroupHeader()){
				rowView = inflater.inflate(R.layout.class_header, parent, false);
				
				TextView txtHeader = (TextView) rowView.findViewById(R.id.txtClass);
				
				txtHeader.setText(item.gettxtClass());

			} else {
				rowView = inflater.inflate(R.layout.class_item, parent, false);
				
				// 3. Get icon,title & counter views from the rowView
				ImageView imgClass1 = (ImageView) rowView.findViewById(R.id.imgClass1); 
				TextView txtClass1 = (TextView) rowView.findViewById(R.id.txtClass1);
				
				ImageView imgClass2 = (ImageView) rowView.findViewById(R.id.imgClass2); 
				TextView txtClass2 = (TextView) rowView.findViewById(R.id.txtClass2);
				
		    	//imgPlanet.setTag(position);
		    	
			    // 4. Set the text for textView 
				imgClass1.setImageResource(item.getimgAdvancedClass1());
				txtClass1.setText(item.gettxtAdvancedClass1());
				
				imgClass2.setImageResource(item.getimgAdvancedClass2());
				txtClass2.setText(item.gettxtAdvancedClass2());
				
				LinearLayout AdvClass1Layout = (LinearLayout) rowView.findViewById(R.id.AdvClass1);
				LinearLayout AdvClass2Layout = (LinearLayout) rowView.findViewById(R.id.AdvClass2); 
				
				AdvClass1Layout.setOnClickListener(new View.OnClickListener() {
		            public void onClick(View view) {
		             
		              
		                Bundle bundle = new Bundle();
		                bundle.putString("class", item.gettxtClass());
		                bundle.putInt("class_id", item.getIdClass());
		                bundle.putString("resource", item.gettxtResource());
		                bundle.putInt("position", item.getAdvancedClassID1());
		                bundle.putString("advancedclass", item.gettxtAdvancedClass1());
		                
		                Intent intent = new Intent(context, AdvancedClassActivity.class);
		                intent.putExtras(bundle);
		                context.startActivity(intent);
		    	        
		            	
		            	//Log.d("SWTORCentral", String.valueOf(item.getAdvancedClassID1()));
		            }
		        });
				
				AdvClass2Layout.setOnClickListener(new View.OnClickListener() {
		            public void onClick(View view) {
		             
		              
		                Bundle bundle = new Bundle();
		                bundle.putString("class", item.gettxtClass());
		                bundle.putInt("class_id", item.getIdClass());
		                bundle.putString("resource", item.gettxtResource());
		                bundle.putInt("position", item.getAdvancedClassID2());
		                bundle.putString("advancedclass", item.gettxtAdvancedClass2());
		                
		                Intent intent = new Intent(context, AdvancedClassActivity.class);
		                intent.putExtras(bundle);
		                context.startActivity(intent);
		    	        
		            	
		            	//Log.d("SWTORCentral", String.valueOf(item.getAdvancedClassID2()));
		            }
		        });
			}
		   
		    // 5. retrn rowView
		    return rowView;
		}
}