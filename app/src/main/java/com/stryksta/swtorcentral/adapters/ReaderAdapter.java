package com.stryksta.swtorcentral.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.data.RssItem;

import java.util.ArrayList;

public class ReaderAdapter extends RecyclerView.Adapter<ReaderAdapter.ViewHolder>{

    private final Context mContext;
    private ArrayList<RssItem> readerItems;

    public ReaderAdapter(Context context, ArrayList<RssItem> readerItems) {
        super();
        mContext = context;
        this.readerItems = readerItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = View.inflate(viewGroup.getContext(), R.layout.reader_row, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        RssItem readerRow = readerItems.get(position);

        if (readerRow != null) {
            viewHolder.txtTitle.setText(readerRow.getTitle());
            viewHolder.txtNewsCategory.setText(readerRow.getCategory());
            viewHolder.txtDescription.setText(readerRow.getDescription());
        }
            /*
        if (readerRow.getTitle().toString() == "Press Release") {
            viewHolder.txtTitle.setBackgroundColor(mContext.getResources().getColor(R.color.swtor_blue));
            viewHolder.txtNewsCategory.setBackgroundColor(mContext.getResources().getColor(R.color.swtor_blue));
            viewHolder.txtDescription.setText(readerRow.getDescription());
        } if (readerRow.getTitle().toString() == "News Article") {

        }  if (readerRow.getTitle().toString() == "Developer Blog") {

        }*/
        //Log.d("SWTORCentral", readerRow.getCategory());
        //viewHolder.imgBackground.setImageResource(R.drawable.placeholder);
        /*
        Picasso.with(mContext)
                .load(readerRow.getImage())
                .into(viewHolder.imgBackground);

        viewHolder.imgBackground.setTag(readerRow.getImage());
        */
    }

    @Override
    public int getItemCount() {
        return (null != readerItems ? readerItems.size() : 0);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView txtTitle;
        public TextView txtNewsCategory;
        public TextView txtDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            txtNewsCategory = (TextView) itemView.findViewById(R.id.txtNewsCategory);
            txtDescription = (TextView) itemView.findViewById(R.id.txtDescription);
        }
    }
}