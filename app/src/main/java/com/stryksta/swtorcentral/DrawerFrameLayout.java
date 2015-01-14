/*
 * Copyright 2014 Heinrich Reimer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.stryksta.swtorcentral;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import com.stryksta.swtorcentral.drawer.DrawerItem;
import com.stryksta.swtorcentral.drawer.DrawerProfile;

import java.util.List;

public class DrawerFrameLayout extends DrawerLayout {

    private DrawerView mDrawer;

    public DrawerFrameLayout(Context context) {
        this(context, null);
    }

    public DrawerFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawerFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        inflate(context, R.layout.md_drawer_view, this);
        mDrawer = (DrawerView) findViewById(R.id.mdDrawer);

        setDrawerShadow(R.drawable.md_drawer_shadow, Gravity.START);


        int colorPrimaryDark = getResources().getColor(android.R.color.black);
        TypedArray a = getContext().getTheme().obtainStyledAttributes(new int[]{R.attr.colorPrimaryDark});
        try {
            colorPrimaryDark = a.getColor(0, 0);
        } finally {
            a.recycle();
        }

        setStatusBarBackgroundColor(colorPrimaryDark);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        super.addView(child, 0, params);
    }

    /**
     * Sets a profile to the md_drawer
     *
     * @param profile Profile to set
     */
    public DrawerFrameLayout setProfile(DrawerProfile profile) {
        mDrawer.setProfile(profile);
        return this;
    }

    /**
     * Gets the profile of the md_drawer
     *
     * @return Profile of the md_drawer
     */
    public DrawerProfile getProfile() {
        return mDrawer.getProfile();
    }

    /**
     * Removes the profile from the md_drawer
     */
    public DrawerFrameLayout removeProfile() {
        mDrawer.removeProfile();
        return this;
    }


    /**
     * Adds items to the md_drawer
     *
     * @param items Items to add
     */
    public DrawerFrameLayout addItems(List<DrawerItem> items) {
        mDrawer.addItems(items);
        return this;
    }

    /**
     * Adds an item to the md_drawer
     *
     * @param item Item to add
     */
    public DrawerFrameLayout addItem(DrawerItem item) {
        mDrawer.addItem(item);
        return this;
    }

    /**
     * Adds a divider to the md_drawer
     */
    public DrawerFrameLayout addDivider() {
        mDrawer.addDivider();
        return this;
    }

    /**
     * Gets all items from the md_drawer
     *
     * @return Items from the md_drawer
     */
    public List<DrawerItem> getItems() {
        return mDrawer.getItems();
    }

    /**
     * Gets an item from the md_drawer
     *
     * @param position The item position
     * @return Item from the md_drawer
     */
    public DrawerItem getItem(int position) {
        return mDrawer.getItem(position);
    }

    /**
     * Gets an item from the md_drawer
     *
     * @param id The item ID
     * @return Item from the md_drawer
     */
    public DrawerItem findItemById(int id) {
        mDrawer.findItemById(id);
        return null;
    }

    /**
     * Selects an item from the md_drawer
     *
     * @param position The item position
     */
    public void selectItem(int position) {
        mDrawer.selectItem(position);
    }

    /**
     * Gets the selected item position of the md_drawer
     *
     * @return Position of the selected item
     */
    public int getSelectedPosition(){
        return mDrawer.getSelectedPosition();
    }

    /**
     * Selects an item from the md_drawer
     *
     * @param id The item ID
     */
    public void selectItemById(int id) {
        mDrawer.selectItemById(id);
    }

    /**
     * Removes an item from the md_drawer
     *
     * @param item Item to remove
     */
    public DrawerFrameLayout removeItem(DrawerItem item) {
        mDrawer.removeItem(item);
        return this;
    }

    /**
     * Removes an item from the md_drawer
     *
     * @param position Position to remove
     */
    public DrawerFrameLayout removeItem(int position) {
        mDrawer.removeItem(position);
        return this;
    }

    /**
     * Removes all items from the md_drawer
     */
    public DrawerFrameLayout clearItems() {
        mDrawer.clearItems();
        return this;
    }


    /**
     * Sets an item click listener to the md_drawer
     *
     * @param listener Listener to set
     */
    public DrawerFrameLayout setOnItemClickListener(DrawerItem.OnItemClickListener listener) {
        mDrawer.setOnItemClickListener(listener);
        return this;
    }

    /**
     * Gets the item click listener of the md_drawer
     *
     * @return Item click listener of the md_drawer
     */
    public DrawerItem.OnItemClickListener getOnItemClickListener() {
        return mDrawer.getOnItemClickListener();
    }

    /**
     * Gets whether the md_drawer has an item click listener set to it
     *
     * @return True if the md_drawer has an item click listener set to it, false otherwise.
     */
    public boolean hasOnItemClickListener() {
        return mDrawer.hasOnItemClickListener();
    }

    /**
     * Removes the item click listener from the md_drawer
     */
    public DrawerFrameLayout removeOnItemClickListener() {
        mDrawer.removeOnItemClickListener();
        return this;
    }


    /**
     * Opens the md_drawer
     */
    public void openDrawer(){
        openDrawer(mDrawer);
    }

    /**
     * Closes the md_drawer
     */
    public void closeDrawer(){
        closeDrawer(mDrawer);
    }
}
