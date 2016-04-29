package com.stryksta.swtorcentral;


import java.util.ArrayList;

import com.stryksta.swtorcentral.adapters.ClassesAdapter;
import com.stryksta.swtorcentral.data.ClassItem;
import com.stryksta.swtorcentral.util.RecyclerItemClickListener;
import com.stryksta.swtorcentral.util.database.ClassesDatabase;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ClassesFragment extends Fragment{

    private RecyclerView mRecyclerView;
    private GridLayoutManager mLayoutManager;

    private ClassesDatabase classDB;
    ArrayList<ClassItem> classItems;
    private ClassesAdapter mRecycleAdapter;

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
		
        vw_layout = inflater.inflate(R.layout.class_fragment_main, container, false);

        getActivity().setTitle("Classes");

        //Set RecyclerView
        mRecyclerView = (RecyclerView) vw_layout.findViewById(R.id.classesList);

        if (mRecyclerView != null) {
            mLayoutManager = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
            mRecyclerView.setLayoutManager(mLayoutManager);
        }

        classDB = new ClassesDatabase(getActivity());
        classItems = classDB.getClasses();

        //Set Adapter
        mRecycleAdapter = new ClassesAdapter(classItems);
        mRecyclerView.setAdapter(mRecycleAdapter);
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), mRecyclerView, new RecyclerItemClickListener.OnItemClickListener()
        {
            public void onItemClick(View view, int position)
            {
                Bundle bundle = new Bundle();
                bundle.putString("clsName", classItems.get(position).getClassName());
                Intent intent = new Intent(getActivity(), ClassActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }

            public void onItemLongClick(View view, int position)
            {

            }
        }));
        //Close DB
        classDB.close();
		
		return vw_layout;
	}
	
	@Override
	public void onDestroyView() {
	    super.onDestroyView();
	    //getActivity().getActionBar().setTitle("Previous Title");
	}
	
}
