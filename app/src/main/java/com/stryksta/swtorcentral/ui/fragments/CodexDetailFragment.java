package com.stryksta.swtorcentral.ui.fragments;


import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.models.CodexItem;
import com.stryksta.swtorcentral.ui.adapters.CodexAdapter;
import com.stryksta.swtorcentral.util.database.CodexDatabase;

import java.util.ArrayList;

public class CodexDetailFragment extends Fragment{

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;

    private CodexDatabase codexDB;
    ArrayList<CodexItem> cdxItems;
    private CodexAdapter mRecycleAdapter;
    String codexCategory;

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
		
        vw_layout = inflater.inflate(R.layout.codex_detail_main, container, false);

        if ( getArguments().getString("codexCategory") != null ) {
            codexCategory = getArguments().getString("codexCategory");
        }

        //Set RecyclerView
        mRecyclerView = (RecyclerView) vw_layout.findViewById(R.id.codexDetailList);

        /*
        cdxCategoryItems = codexDB.getCategories();

        ChipCloud chipCloud = (ChipCloud) findViewById(R.id.chip_cloud);
        for (String mString : cdxCategoryItems) {
            chipCloud.addChip(mString);
        }

        chipCloud.setSelectedChip(0);
        chipCloud.setChipListener(new ChipListener() {
            @Override
            public void chipSelected(int index, String text) {
                //Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                new CodexActivityOld.GetCodexes().execute(text);
            }

            @Override
            public void chipDeselected(int index) {

            }
        });
*/


        //Get Codex Categories
        codexDB = new CodexDatabase(getActivity());
        new CodexDetailFragment.GetCodexes().execute(codexCategory);

        //Set Codex Adapter
        if (mRecyclerView != null) {
            mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            mRecyclerView.setLayoutManager(mLayoutManager);
        }
		
		return vw_layout;
	}
	
	@Override
	public void onDestroyView() {
	    super.onDestroyView();
	    //getActivity().getActionBar().setTitle("Previous Title");
	}

    private class GetCodexes extends AsyncTask<String, Void, ArrayList<CodexItem>> {

        protected void onPreExecute() {
            super.onPreExecute();
            cdxItems = new ArrayList<>();
        }

        @Override
        protected ArrayList<CodexItem> doInBackground(String... urls) {

            try {
                cdxItems = codexDB.getCodexes(urls[0]);
            } catch (Exception e) {
                if(e.getMessage() != null) {
                    Log.e("SWTORCentral", e.getMessage());
                }
            }
            return null;
        }

        protected void onPostExecute(ArrayList<CodexItem> result) {
            //Set Codex Adapter
            mRecycleAdapter = new CodexAdapter(cdxItems);
            mRecyclerView.setNestedScrollingEnabled(false);
            mRecyclerView.setAdapter(mRecycleAdapter);
        }
    }
	
}
