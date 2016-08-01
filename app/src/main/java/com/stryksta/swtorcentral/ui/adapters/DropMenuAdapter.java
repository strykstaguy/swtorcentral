package com.stryksta.swtorcentral.ui.adapters;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import com.afollestad.materialdialogs.MaterialDialog;
import com.baiiu.filter.adapter.MenuAdapter;
import com.baiiu.filter.adapter.SimpleTextAdapter;
import com.baiiu.filter.interfaces.OnFilterDoneListener;
import com.baiiu.filter.interfaces.OnFilterItemClickListener;
import com.baiiu.filter.typeview.SingleListView;
import com.baiiu.filter.util.UIUtil;
import com.baiiu.filter.view.FilterCheckedTextView;
import com.stryksta.swtorcentral.R;

import java.util.ArrayList;
import java.util.List;

public class DropMenuAdapter implements MenuAdapter {
    private final Context mContext;
    private OnFilterDoneListener onFilterDoneListener;
    private String[] titles;
    private String selectedText;
    private List<String> cdxCategoryList;
    public DropMenuAdapter(Context context, String[] titles, OnFilterDoneListener onFilterDoneListener, List<String> cdxCategoryList) {
        this.mContext = context;
        this.titles = titles;
        this.cdxCategoryList = cdxCategoryList;
        this.onFilterDoneListener = onFilterDoneListener;
    }

    @Override
    public int getMenuCount() {
        return titles.length;
    }

    @Override
    public String getMenuTitle(int position) {
        return titles[position];
    }

    @Override
    public int getBottomMargin(int position) {
        if (position == 3) {
            return 0;
        }

        return UIUtil.dp(mContext, 140);
    }

    @Override
    public View getView(int position, FrameLayout parentContainer) {
        View view = parentContainer.getChildAt(position);

        switch (position) {
            case 0:
                view = createSingleListView();
                break;
            case 1:
                view = createFactionListView();
                break;
        }

        return view;
    }

    private View createSingleListView() {
        SingleListView<String> singleListView = new SingleListView<String>(mContext)
                .adapter(new SimpleTextAdapter<String>(null, mContext) {
                    @Override
                    public String provideText(String string) {
                        return string;
                    }

                    @Override
                    protected void initCheckedTextView(FilterCheckedTextView checkedTextView) {
                        int dp = UIUtil.dp(mContext, 15);
                        checkedTextView.setPadding(dp, dp, 0, dp);
                    }
                })
                .onItemClick(new OnFilterItemClickListener<String>() {
                    @Override
                    public void onItemClick(String item) {
                        selectedText = item;
                        /*
                        new MaterialDialog.Builder(mContext)
                                .title(R.string.faction_main)
                                .content(item)
                                .positiveText("Yes")
                                .negativeText("No")
                                .show();
                                */
                        onFilterDone(0);
                    }
                });

        /*
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            list.add("" + i);
        }
        */
        singleListView.setList(cdxCategoryList, -1);

        return singleListView;
    }

    private View createFactionListView() {
        SingleListView<String> singleListView = new SingleListView<String>(mContext)
                .adapter(new SimpleTextAdapter<String>(null, mContext) {
                    @Override
                    public String provideText(String string) {
                        return string;
                    }

                    @Override
                    protected void initCheckedTextView(FilterCheckedTextView checkedTextView) {
                        int dp = UIUtil.dp(mContext, 15);
                        checkedTextView.setPadding(dp, dp, 0, dp);
                    }
                })
                .onItemClick(new OnFilterItemClickListener<String>() {
                    @Override
                    public void onItemClick(String item) {
                        selectedText = item;
                        /*
                        new MaterialDialog.Builder(mContext)
                            .title(R.string.faction_main)
                            .content(item)
                            .positiveText("Yes")
                            .negativeText("No")
                            .show();
                        */
                        onFilterDone(1);
                    }
                });

        List<String> factionList = new ArrayList<>();
        factionList.add("All");
        factionList.add("Both");
        factionList.add("Republic");
        factionList.add("Empire");

        singleListView.setList(factionList, -1);

        return singleListView;
    }

    private void onFilterDone(int position) {
        if (onFilterDoneListener != null) {
            //int selectedIndex = cdxCategoryList.indexOf(selectedText);
            onFilterDoneListener.onFilterDone(position, selectedText, "");
            /*
            new MaterialDialog.Builder(mContext)
                    .title(R.string.faction_main)
                    .content("test" + selectedIndex)
                    .positiveText("Yes")
                    .negativeText("No")
                    .show();
            */
        }
    }

}