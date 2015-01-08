package com.stryksta.swtorcentral;

import java.util.ArrayList;
import java.util.HashMap;

import com.stryksta.swtorcentral.adapters.AbilityAdapter;
import com.stryksta.swtorcentral.data.AbilitiesItem;
import com.stryksta.swtorcentral.util.database.AbilitiesDatabase;
import com.stryksta.swtorcentral.util.NonScrollListView;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;


public class AdvancedClassTab3 extends Fragment {
	private int ClassPos;
	private int ClassID;
	private int skillAbilityID1;
	private int skillAbilityID2;
	private int skillAbilityID3;
	private String advancedclass;
	private String ClassResource;
	private String ClassText;
    private AbilitiesDatabase db;
    ArrayList<AbilitiesItem> abilitiesClassItems = new ArrayList<AbilitiesItem>();
    ArrayList<AbilitiesItem> abilitiesAdvancedItems = new ArrayList<AbilitiesItem>();
    HashMap<String, Integer> abilityItem = new HashMap<String, Integer>();
    ArrayList<AbilitiesItem> abilitiesSkill1Items = new ArrayList<AbilitiesItem>();
    ArrayList<AbilitiesItem> abilitiesSkill2Items = new ArrayList<AbilitiesItem>();
    ArrayList<AbilitiesItem> abilitiesSkill3Items = new ArrayList<AbilitiesItem>();
    AbilityAdapter classAdapter;
    AbilityAdapter advancedAdapter;
    AbilityAdapter Skill1Adapter;
    AbilityAdapter Skill2Adapter;
    AbilityAdapter Skill3Adapter;

	
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
		
        vw_layout = inflater.inflate(R.layout.advanced_class_tab3, container, false);
        NonScrollListView classabilitylist = (NonScrollListView) vw_layout.findViewById(R.id.classabilitylist);
        ListView advancedclassabilitylist = (ListView) vw_layout.findViewById(R.id.advancedclassabilitylist);
        ListView skill1abilitylist = (ListView) vw_layout.findViewById(R.id.skillabilitylist1);
        ListView skill2abilitylist = (ListView) vw_layout.findViewById(R.id.skillabilitylist2);
        ListView skill3abilitylist = (ListView) vw_layout.findViewById(R.id.skillabilitylist3);
        TextView classAbilityTitle = (TextView) vw_layout.findViewById(R.id.txtClassAbility);
        TextView advancedAbilityTitle = (TextView) vw_layout.findViewById(R.id.txtAdvancedClassAbility);
        TextView skill1AbilityTitle = (TextView) vw_layout.findViewById(R.id.txtSkillAbility1);
        TextView skill2AbilityTitle = (TextView) vw_layout.findViewById(R.id.txtSkillAbility2);
        TextView skill3AbilityTitle = (TextView) vw_layout.findViewById(R.id.txtSkillAbility3);
        
        Bundle bundle = getActivity().getIntent().getExtras();
		
        if ( bundle != null ) {
        	ClassPos = bundle.getInt("position");
        	ClassID = bundle.getInt("class_id");
        	ClassResource = bundle.getString("resource");
        	ClassText = bundle.getString("class");
        	advancedclass = bundle.getString("advancedclass");
        	
        }
        
        classAbilityTitle.setText(ClassText);
        advancedAbilityTitle.setText(advancedclass);
        
        abilitiesClassItems.clear();
        abilitiesAdvancedItems.clear();
        abilityItem.clear();
 
      //Open Database
		db = new AbilitiesDatabase(getActivity());
		abilitiesClassItems = db.getClassAbilities(ClassID);
		//abilitiesAdvancedItems = db.getAdvancedClassAbilities(ClassPos);
		//abilityItem = db.getSkills(ClassPos);
		
		//skill1AbilityTitle.setText(abilityItem.keySet().toArray()[0].toString());
        //skill2AbilityTitle.setText(abilityItem.keySet().toArray()[1].toString());
        //skill3AbilityTitle.setText(abilityItem.keySet().toArray()[2].toString());
        
		//skillAbilityID1 = Integer.valueOf(abilityItem.values().toArray()[0].toString());
        //skillAbilityID2 = Integer.valueOf(abilityItem.values().toArray()[1].toString());
        //skillAbilityID3 = Integer.valueOf(abilityItem.values().toArray()[2].toString());

		//abilitiesSkill1Items = db.getSkillAbilities(skillAbilityID1);
		//abilitiesSkill2Items = db.getSkillAbilities(skillAbilityID2);
		//abilitiesSkill3Items = db.getSkillAbilities(skillAbilityID3);
		/*
		for (Map.Entry<String,String> entry : map.entrySet()) {
		    System.out.printf("%s -> %s%n", entry.getKey(), entry.getValue());
		}
		*/
		//get first items
		//abilityItem.keySet();
		//get second
		//abilityItem.values();
		
		
				
		db.close();
		
	    classAdapter = new AbilityAdapter(getActivity(), abilitiesClassItems);
		classabilitylist.setAdapter(classAdapter);
		classabilitylist.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) 
			    {
					Bundle bundle = new Bundle();
					bundle.putString("class", ClassText);
					bundle.putString("class_resource", ClassResource);
					bundle.putInt("class_id", classAdapter.getItem(position).getclassID());
					bundle.putInt("advanced_class_id", ClassPos);
					bundle.putString("ability_name", classAdapter.getItem(position).gettxtName());
					bundle.putString("type", "class");
					bundle.putInt("skill_id", classAdapter.getItem(position).getSkillTreeID());
					
					Intent intent = new Intent(getActivity(), AbilityDetailActivity.class);
					intent.putExtras(bundle);
					getActivity().startActivity(intent);
			    }});
		/*
		advancedAdapter = new AbilityAdapter(getActivity(), abilitiesAdvancedItems);
		advancedclassabilitylist.setAdapter(advancedAdapter);
		advancedclassabilitylist.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) 
			    {
					Bundle bundle = new Bundle();
					bundle.putString("class", ClassText);
					bundle.putString("class_resource", ClassResource);
					bundle.putInt("class_id", advancedAdapter.getItem(position).getclassID());
					bundle.putInt("advanced_class_id", ClassPos);
					bundle.putString("ability_name", advancedAdapter.getItem(position).gettxtName());
					bundle.putString("type", "advanced");
					bundle.putInt("skill_id", advancedAdapter.getItem(position).getSkillTreeID());
					
					Intent intent = new Intent(getActivity(), AbilityDetailActivity.class);
					intent.putExtras(bundle);
					getActivity().startActivity(intent);
			    }});
		
		Skill1Adapter = new AbilityAdapter(getActivity(), abilitiesSkill1Items);
		skill1abilitylist.setAdapter(Skill1Adapter);
		skill1abilitylist.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) 
			    {
					Bundle bundle = new Bundle();
					bundle.putString("class", ClassText);
					bundle.putString("class_resource", ClassResource);
					bundle.putInt("class_id", Skill1Adapter.getItem(position).getclassID());
					bundle.putInt("advanced_class_id", ClassPos);
					bundle.putString("ability_name", Skill1Adapter.getItem(position).gettxtName());
					bundle.putString("type", "skill");
					bundle.putInt("skill_id", Skill1Adapter.getItem(position).getSkillTreeID());
					
					Intent intent = new Intent(getActivity(), AbilityDetailActivity.class);
					intent.putExtras(bundle);
					getActivity().startActivity(intent);
					
					//Toast.makeText(getActivity(), String.valueOf(Skill1Adapter.getItem(position).getSkillTreeID()), Toast.LENGTH_LONG).show();
			    }});
		
		Skill2Adapter = new AbilityAdapter(getActivity(), abilitiesSkill2Items);
		skill2abilitylist.setAdapter(Skill2Adapter);
		skill2abilitylist.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) 
			    {
					Bundle bundle = new Bundle();
					bundle.putString("class", ClassText);
					bundle.putString("class_resource", ClassResource);
					bundle.putInt("class_id", Skill2Adapter.getItem(position).getclassID());
					bundle.putInt("advanced_class_id", ClassPos);
					bundle.putString("ability_name", Skill2Adapter.getItem(position).gettxtName());
					bundle.putString("type", "skill");
					bundle.putInt("skill_id", Skill2Adapter.getItem(position).getSkillTreeID());
					
					Intent intent = new Intent(getActivity(), AbilityDetailActivity.class);
					intent.putExtras(bundle);
					getActivity().startActivity(intent);
			    }});
		
		Skill3Adapter = new AbilityAdapter(getActivity(), abilitiesSkill3Items);
		skill3abilitylist.setAdapter(Skill3Adapter);
		skill3abilitylist.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) 
			    {
					Bundle bundle = new Bundle();
					bundle.putString("class", ClassText);
					bundle.putString("class_resource", ClassResource);
					bundle.putInt("class_id", Skill3Adapter.getItem(position).getclassID());
					bundle.putInt("advanced_class_id", ClassPos);
					bundle.putString("ability_name", Skill3Adapter.getItem(position).gettxtName());
					bundle.putString("type", "skill");
					bundle.putInt("skill_id", Skill3Adapter.getItem(position).getSkillTreeID());
					
					Intent intent = new Intent(getActivity(), AbilityDetailActivity.class);
					intent.putExtras(bundle);
					getActivity().startActivity(intent);
			    }});
		*/
     // Debug the thread name
     	Log.d("SWTORCentral", Thread.currentThread().getName());
     		
		return vw_layout;
	}
}