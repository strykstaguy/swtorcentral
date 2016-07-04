package com.stryksta.swtorcentral.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.data.AchievementsItem;
import com.stryksta.swtorcentral.data.CodexItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CodexAdapter extends RecyclerView.Adapter<CodexAdapter.ViewHolder>{

	private ArrayList<CodexItem> cdxItems;

	public CodexAdapter(ArrayList<CodexItem> cdxItems) {
		super();
		this.cdxItems = cdxItems;
	}

    public void updateItems(ArrayList<CodexItem> cdxItems) {
        this.cdxItems = cdxItems;
    }

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
		View view = View.inflate(viewGroup.getContext(), R.layout.codex_row, null);
		ViewHolder holder = new ViewHolder(view);
		return holder;
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, int position) {
		CodexItem codexRow = cdxItems.get(position);

		if (codexRow != null) {
			viewHolder.cdxTitle.setText(codexRow.getTitle());
			viewHolder.cdxDescription.setText(codexRow.getDescription());
			viewHolder.cdxFaction.setText(codexRow.getFaction());

            if (codexRow.getLevel() != null && !codexRow.getLevel().equals("null")) {
                viewHolder.cdxLevel.setText("Level: " + codexRow.getLevel());
            } else {
                viewHolder.cdxLevel.setVisibility(View.GONE);
            }

			if (codexRow.getPlanets() != null && !codexRow.getPlanets().equals("")) {
                viewHolder.cdxPlants.setText("Planet(s): " + codexRow.getPlanets());
                //String planetNames = getPlanet(codexRow.getPlanets());
                //viewHolder.cdxPlants.setText("Planet(s): " + planetNames);
			} else {
                viewHolder.cdxPlants.setVisibility(View.GONE);
            }
		}
	}

	@Override
	public int getItemCount() {
		return (null != cdxItems ? cdxItems.size() : 0);
	}

	public static class ViewHolder extends RecyclerView.ViewHolder{

		public TextView cdxTitle;
		public TextView cdxDescription;
		public TextView cdxLevel;
		public TextView cdxFaction;
		public TextView cdxPlants;

		public ViewHolder(View itemView) {
			super(itemView);
			cdxTitle = (TextView) itemView.findViewById(R.id.cdxTitle);
			cdxDescription = (TextView) itemView.findViewById(R.id.cdxDescription);
			cdxLevel = (TextView) itemView.findViewById(R.id.cdxLevel);
			cdxPlants = (TextView) itemView.findViewById(R.id.cdxPlants);
			cdxFaction = (TextView) itemView.findViewById(R.id.cdxFaction);
		}
	}

    public String getPlanet(String planets) {
        Map<String,String> planetMap = new HashMap<>();
        planetMap.put("16141076066187801147", "Alderaan");
        planetMap.put("16140903155296015570", "Balmorra (Imperial)");
        planetMap.put("16140924047723196937", "Balmorra (Republic)");
        planetMap.put("16141121725666440689", "Belsavis");
        planetMap.put("16140915876594649023", "Black Hole");
        planetMap.put("16141060140100412063", "Corellia");
        planetMap.put("16141154995358231178", "Coruscant");
        planetMap.put("16140969352659235187", "Dromund Kaas");
        planetMap.put("16141109882649941585", "Hoth");
        planetMap.put("16141158871960526176", "Hutta");
        planetMap.put("16141145871965102174", "Illum");
        planetMap.put("16140949671682761916", "Imperial Fleet");
        planetMap.put("16140965352798472550", "Korriban");
        planetMap.put("16140905763631380450", "Odessen");
        planetMap.put("16140922522269102216", "Zakuul");
        planetMap.put("16141112529810539905", "Makeb");
        planetMap.put("16141148416747135524", "Manaan");
        planetMap.put("16140948956820683845", "Nar Shaddaa");
        planetMap.put("16141091541170885060", "Ord Mantell");
        planetMap.put("16140926619724173051", "Oricon");
        planetMap.put("16141165625065288506", "Quesh");
        planetMap.put("16141064934392624126", "Rakata Prime");
        planetMap.put("16141153476086825476", "Republic Fleet");
        planetMap.put("16141088354346344157", "Rishi");
        planetMap.put("16141112478558425996", "Section X");
        planetMap.put("16141165292934571748", "Taris (Imperial)");
        planetMap.put("16141008063347613517", "Taris (Republic)");
        planetMap.put("16141178175742985098", "Tatooine");
        planetMap.put("16140933910212274443", "Tython");
        planetMap.put("16141116506689843478", "Voss");
        planetMap.put("16141006993103609455", "Yavin 4");
        planetMap.put("16140988326615547422", "Ziost");

        List<String> planetNames = new ArrayList<>();
        //Split Planet ID's
        String[] items = planets.split(",");

        //Iterate through strings
        for (String item : items)
        {
            //Get String and Replace
            String str = planetMap.get(item);
            planetNames.add(str);
        }

        String joinedPlanets = planetNames.toString();
        return joinedPlanets;
    }
}