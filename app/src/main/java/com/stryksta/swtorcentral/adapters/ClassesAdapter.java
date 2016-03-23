package com.stryksta.swtorcentral.adapters;

import java.util.ArrayList;

import com.stryksta.swtorcentral.AdvancedClassActivity;
import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.data.AdvancedClassesItem;
import com.stryksta.swtorcentral.data.ClassItem;

import android.content.Context;
import android.content.Intent;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ClassesAdapter extends ArrayAdapter<ClassItem> {

	private final Context context;
	private final ArrayList<ClassItem> swtorClasses;
    private final ArrayList<AdvancedClassesItem> swtorAdvancedClasses;
	int AdvancedPos1;
	int AdvancedPos2;

	public ClassesAdapter(Context context, ArrayList<ClassItem> swtorClasses, ArrayList<AdvancedClassesItem> swtorAdvancedClasses) {
		super(context, R.layout.class_item, swtorClasses);

		this.context = context;
		this.swtorClasses = swtorClasses;
        this.swtorAdvancedClasses = swtorAdvancedClasses;
	}

	@Override
	public View getView(int position, View convertView, final ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = null;
        rowView = inflater.inflate(R.layout.class_item, parent, false);

		final ClassItem classItem = swtorClasses.get(position);
        final AdvancedClassesItem advancedClassItem = swtorAdvancedClasses.get(position);

        //Class Information
        TextView txtHeader = (TextView) rowView.findViewById(R.id.txtClass);
        txtHeader.setText(classItem.getClassName());

        TextView txtDesc = (TextView) rowView.findViewById(R.id.txtDesc);
        txtDesc.setText(classItem.getClassDescription());

        //Advanced Class Information
        ImageButton imgClass1 = (ImageButton) rowView.findViewById(R.id.imgClass1);
        TextView txtClass1 = (TextView) rowView.findViewById(R.id.txtClass1);

        ImageButton imgClass2 = (ImageButton) rowView.findViewById(R.id.imgClass2);
        TextView txtClass2 = (TextView) rowView.findViewById(R.id.txtClass2);

        int iColor = ContextCompat.getColor(context, R.color.white);

        int red = (iColor & 0xFF0000) / 0xFFFF;
        int green = (iColor & 0xFF00) / 0xFF;
        int blue = iColor & 0xFF;

        float[] matrix = { 0, 0, 0, 0, red
                , 0, 0, 0, 0, green
                , 0, 0, 0, 0, blue
                , 0, 0, 0, 1, 0 };

        ColorFilter colorFilter = new ColorMatrixColorFilter(matrix);

        imgClass1.setImageResource(advancedClassItem.getimgAdvancedClass1());
        txtClass1.setText(advancedClassItem.gettxtAdvancedClass1());

        imgClass2.setImageResource(advancedClassItem.getimgAdvancedClass2());
        txtClass2.setText(advancedClassItem.gettxtAdvancedClass2());

        imgClass1.setColorFilter(colorFilter);
        imgClass2.setColorFilter(colorFilter);

        imgClass1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("class", classItem.getClassName());
                bundle.putInt("class_id", classItem.getClassID());
                bundle.putString("apc", classItem.geAPC());
                bundle.putString("node", classItem.getNode());
                bundle.putString("resource", classItem.getClassResource());
                bundle.putInt("position", advancedClassItem.getAdvancedClassID1());
                bundle.putString("advancedclass", advancedClassItem.gettxtAdvancedClass1());

                Intent intent = new Intent(context, AdvancedClassActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

        imgClass2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("class", classItem.getClassName());
                bundle.putInt("class_id", classItem.getClassID());
                bundle.putString("resource", classItem.getClassResource());
                bundle.putInt("position", advancedClassItem.getAdvancedClassID2());
                bundle.putString("apc", classItem.geAPC());
                bundle.putString("node", classItem.getNode());
                bundle.putString("advancedclass", advancedClassItem.gettxtAdvancedClass2());

                Intent intent = new Intent(context, AdvancedClassActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

		return rowView;
	}
}