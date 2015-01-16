package com.stryksta.swtorcentral;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.ActionBar;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;
import com.stryksta.swtorcentral.adapters.CompanionClassAdapter;
import com.stryksta.swtorcentral.adapters.ExpandableListAdapter;
import com.stryksta.swtorcentral.data.CompanionItem;
import com.stryksta.swtorcentral.util.BackdropImageView;
import com.stryksta.swtorcentral.util.NonScrollListView;
import com.stryksta.swtorcentral.util.database.CompanionDatabase;
import com.stryksta.swtorcentral.util.database.CompanionGiftsDatabase;

public class TestActivity extends ActionBarActivity implements ObservableScrollViewCallbacks {
    private static final float MAX_TEXT_SCALE_DELTA = 0.3f;
    private static final boolean TOOLBAR_IS_STICKY = true;

    private Toolbar mToolbar;

    private View mImageView;
    private View mOverlayView;
    private ObservableScrollView mScrollView;
    private TextView mTitleView;
    private int mActionBarSize;
    private int mFlexibleSpaceImageHeight;
    private int mToolbarColor;

    //Companion Info from prev
    private int ClassPos;
    private int ClassID;
    private CompanionDatabase db;
    private Cursor companions;
    private CompanionGiftsDatabase db2;
    ArrayList<CompanionItem> companionItems;

    int actionBarHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_main);


        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if (!TOOLBAR_IS_STICKY) {
            mToolbar.setBackgroundColor(Color.TRANSPARENT);
        }

        //get bundle info
        Bundle bundle = getIntent().getExtras();

        if ( bundle != null ) {
            ClassPos = bundle.getInt("position");
            ClassID = bundle.getInt("class_id");
        }

        ClassID = 2;

        mFlexibleSpaceImageHeight = getResources().getDimensionPixelSize(R.dimen.flexible_space_image_height);

        //get actionbarheight
        TypedValue tv = new TypedValue();
        int attr = android.support.v7.appcompat.R.attr.actionBarSize;
        if (getTheme().resolveAttribute(attr, tv, true)){
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,getResources().getDisplayMetrics());
        }

        mActionBarSize = actionBarHeight;
        mToolbarColor = getResources().getColor(R.color.swtor_blue);

        mImageView = findViewById(R.id.image);
        mOverlayView = findViewById(R.id.overlay);
        mScrollView = (ObservableScrollView) findViewById(R.id.scroll);
        mScrollView.setScrollViewCallbacks(this);
        mTitleView = (TextView) findViewById(R.id.title);
        mTitleView.setText("Jedi Sage");
        setTitle(null);

        NonScrollListView companionsListView = (NonScrollListView) findViewById(R.id.companionsListView);

        companionItems = new ArrayList<CompanionItem>();

        db = new CompanionDatabase(TestActivity.this);
        db2 = new CompanionGiftsDatabase(TestActivity.this);
        companions = db.getCompanions(ClassID);
        if (companions.moveToFirst())
        {
            do
            {
                String txtName = companions.getString(companions.getColumnIndex("companion_name"));
                String txtRole = companions.getString(companions.getColumnIndex("role"));
                String txtBonus = companions.getString(companions.getColumnIndex("crew_skill_bonus"));
                String txtRomance = companions.getString(companions.getColumnIndex("romance"));
                String txtPrimaryStat = companions.getString(companions.getColumnIndex("primarystat"));
                String txtSecondaryStat = companions.getString(companions.getColumnIndex("secondarystat"));
                String txtPrimaryWeapon = companions.getString(companions.getColumnIndex("primaryweapon"));
                String txtSecondaryWeapon = companions.getString(companions.getColumnIndex("secondaryweapon"));
                String txtGender = companions.getString(companions.getColumnIndex("gender"));
                String txtRace = companions.getString(companions.getColumnIndex("race"));
                String txtFound = companions.getString(companions.getColumnIndex("found"));
                String txtArmor = companions.getString(companions.getColumnIndex("armor"));
                String txtDescription = companions.getString(companions.getColumnIndex("description"));
                String txtGifts = db2.getGifts(companions.getString(companions.getColumnIndex("companion_name")));

                CompanionItem item = new CompanionItem(txtName, txtRole, txtBonus, txtRomance, txtPrimaryStat, txtSecondaryStat, txtPrimaryWeapon, txtSecondaryWeapon, txtGifts, txtGender, txtRace, txtFound, txtArmor, txtDescription);
                companionItems.add(item);
            } while (companions.moveToNext());
        }

        CompanionClassAdapter companionsAdapter = new CompanionClassAdapter(TestActivity.this, R.layout.companion_class_row, companionItems);
        companionsListView.setAdapter(companionsAdapter);

        ScrollUtils.addOnGlobalLayoutListener(mScrollView, new Runnable() {
            public void run() {
              // mScrollView.scrollTo(0, mFlexibleSpaceImageHeight - mActionBarSize);
               mScrollView.scrollTo(0, 1);
               mScrollView.scrollTo(0, 0);
            }
        });


        // Debug the thread name
        Log.d("SWTORCentral", Thread.currentThread().getName());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            this.finish();
        } else {
            getFragmentManager().popBackStack();
        }
    }

    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        // Translate overlay and image
        float flexibleRange = mFlexibleSpaceImageHeight - mActionBarSize;
        int minOverlayTransitionY = mActionBarSize - mOverlayView.getHeight();
        ViewHelper.setTranslationY(mOverlayView, ScrollUtils.getFloat(-scrollY, minOverlayTransitionY, 0));
        ViewHelper.setTranslationY(mImageView, ScrollUtils.getFloat(-scrollY / 2, minOverlayTransitionY, 0));

        // Change alpha of overlay
        ViewHelper.setAlpha(mOverlayView, ScrollUtils.getFloat((float) scrollY / flexibleRange, 0, 1));

        // Scale title text
        float scale = 1 + ScrollUtils.getFloat((flexibleRange - scrollY) / flexibleRange, 0, MAX_TEXT_SCALE_DELTA);
        ViewHelper.setPivotX(mTitleView, 0);
        ViewHelper.setPivotY(mTitleView, 0);
        ViewHelper.setScaleX(mTitleView, scale);
        ViewHelper.setScaleY(mTitleView, scale);

        // Translate title text
        int maxTitleTranslationY = (int) (mFlexibleSpaceImageHeight - mTitleView.getHeight() * scale);
        int titleTranslationY = maxTitleTranslationY - scrollY;
        if (TOOLBAR_IS_STICKY) {
            titleTranslationY = Math.max(0, titleTranslationY);
        }
        ViewHelper.setTranslationY(mTitleView, titleTranslationY);

        if (TOOLBAR_IS_STICKY) {
            // Change alpha of toolbar background
            if (-scrollY + mFlexibleSpaceImageHeight <= mActionBarSize) {
                mToolbar.setBackgroundColor(ScrollUtils.getColorWithAlpha(1, mToolbarColor));
            } else {
                mToolbar.setBackgroundColor(ScrollUtils.getColorWithAlpha(0, mToolbarColor));
            }
        } else {
            // Translate Toolbar
            if (scrollY < mFlexibleSpaceImageHeight) {
                ViewHelper.setTranslationY(mToolbar, 0);
            } else {
                ViewHelper.setTranslationY(mToolbar, -scrollY);
            }
        }
    }

    public void onDownMotionEvent() {
    }

    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
    }
}