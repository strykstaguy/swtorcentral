package com.stryksta.swtorcentral.util;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;


import java.lang.ref.WeakReference;

import com.stryksta.swtorcentral.R;

public class MaterialDialog extends Dialog{

	public MaterialDialog(Context context, boolean cancelable,
                             OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
	}

	public MaterialDialog(Context context, int theme) {
		super(context, theme);
	}

	public MaterialDialog(Context context) {
		super(context);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//LayoutParams params = getWindow().getAttributes();
		//params.horizontalMargin = 20;
		//getWindow().setAttributes(params);
	}

	public static class Builder{
		private Context mContext;
		private MaterialDialog mDialog;
		private CharSequence mTitle;
		private CharSequence mMessage;
		private Button mBtnPositive, mBtnNegative, mBtnNeutral;
		private Message mButtonPositiveMessage, mButtonNegativeMessage, mButtonNeutralMessage;
		private CharSequence mPositiveButtonText;
		private CharSequence mNegativeButtonText;
		private CharSequence mNeutralButtonText;
		private OnClickListener
			mPositiveButtonOnClickListener, mNegativeButtonOnClickListener, mNeutralButtonOnClickListener;
		private Handler mHandler;
		private OnCancelListener mOnCancelListener;
		
		private ListView mListView;
		private ImageView mSeperator;
		private ListAdapter mListAdapter;
		private CharSequence[] mItemArray;
		private int mSelectedItemtIndex = 0;
		private OnClickListener mItemClickListener;
		private boolean mIsSingleChoice = false;
		private View mDialogTemplate;
		
		private View mViewContent;
		private boolean mCancelable = true;
		
		private int mLayout = 0;

        //private float mDensity;
		
		public Builder (Context context){
			mContext = context;
           // mDensity = mContext.getResources().getDisplayMetrics().density;
		}

        public Context getContext(){
            return mContext;
        }
		
		View.OnClickListener mButtonHandler = new View.OnClickListener() {
	        public void onClick(View v) {
	            Message m = null;
	            if (v == mBtnPositive && mButtonPositiveMessage != null) {
	                m = Message.obtain(mButtonPositiveMessage);
	            } else if (v == mBtnNegative && mButtonNegativeMessage != null) {
	                m = Message.obtain(mButtonNegativeMessage);
	            } else if (v == mBtnNeutral && mButtonNeutralMessage != null) {
	                m = Message.obtain(mButtonNeutralMessage);
	            }
	            if (m != null) {
	                m.sendToTarget();
	            }

	            // Post a message so we dismiss after the above handlers are executed
	            mHandler.obtainMessage(ButtonHandler.MSG_DISMISS_DIALOG, mDialog)
	                    .sendToTarget();
	        }
	    };
		
	    private static final class ButtonHandler extends Handler {
	        // Button clicks have Message.what as the BUTTON{1,2,3} constant
	        private static final int MSG_DISMISS_DIALOG = 1;
	        
	        private WeakReference<DialogInterface> mDialog;

	        public ButtonHandler(DialogInterface dialog) {
	            mDialog = new WeakReference<DialogInterface>(dialog);
	        }

	        @Override
	        public void handleMessage(Message msg) {
	            switch (msg.what) {
	                
	                case DialogInterface.BUTTON_POSITIVE:
	                case DialogInterface.BUTTON_NEGATIVE:
	                case DialogInterface.BUTTON_NEUTRAL:
	                    ((OnClickListener) msg.obj).onClick(mDialog.get(), msg.what);
	                    break;
	                    
	                case MSG_DISMISS_DIALOG:
	                    ((DialogInterface) msg.obj).dismiss();
	            }
	        }
	    }
		
		public Builder setTitle(CharSequence title){
			mTitle = title;
			return this;
		}
		
		public Builder setTitle(int resId){
			mTitle = mContext.getString(resId);
			return this;
		}
		
		public Builder setMessage(CharSequence msg){
			mMessage = msg;
			return this;
		}
		
		public Builder setMessage(int resId){
			mMessage = mContext.getString(resId);
			return this;
		}
		
		public Builder setPositiveButton(CharSequence text, OnClickListener listener){
			mPositiveButtonText = text;
			mPositiveButtonOnClickListener = listener;
			//setButton(DialogInterface.BUTTON_POSITIVE, text, listener, mButtonPositiveMessage);
			return this;
		}
		
		public Builder setPositiveButton(int resId, OnClickListener listener){
			mPositiveButtonText = mContext.getString(resId);
			mPositiveButtonOnClickListener = listener;
			//setButton(DialogInterface.BUTTON_POSITIVE, mPositiveButtonText, listener, mButtonPositiveMessage);
			return this;
		}
		
		public Builder setNegativeButton(CharSequence text, OnClickListener listener){
			mNegativeButtonText = text;
			mNegativeButtonOnClickListener = listener;
			//setButton(DialogInterface.BUTTON_NEGATIVE, mNegativeButtonText, listener, mButtonNegativeMessage);
			return this;
		}
		
		public Builder setNegativeButton(int resId, OnClickListener listener){
			mNegativeButtonText = mContext.getString(resId);
			mNegativeButtonOnClickListener = listener;
			//setButton(DialogInterface.BUTTON_NEGATIVE, mNegativeButtonText, listener, mButtonNegativeMessage);
			return this;
		}
		
		public Builder setNeutralButton(CharSequence text, OnClickListener listener){
			mNeutralButtonText = text;
			mNeutralButtonOnClickListener = listener;
			//setButton(DialogInterface.BUTTON_NEGATIVE, mNeutralButtonText, listener, mButtonNeutralMessage);
			return this;
		}
		
		public Builder setNeutralButton(int resId, OnClickListener listener){
			mNeutralButtonText = mContext.getString(resId);
			mNeutralButtonOnClickListener = listener;
			
			return this;
		}
		
	    public void setButton(int whichButton, 
	            OnClickListener listener, Message msg) {

	        if (msg == null && listener != null) {
	            msg = mHandler.obtainMessage(whichButton, listener);
	        }
	        
	        switch (whichButton) {

	            case DialogInterface.BUTTON_POSITIVE:
	            	//mPositiveButtonText = text;
	                mButtonPositiveMessage = msg;
	                break;
	                
	            case DialogInterface.BUTTON_NEGATIVE:
	            	//mNegativeButtonText = text;
	                mButtonNegativeMessage = msg;
	                break;
	                
	            case DialogInterface.BUTTON_NEUTRAL:
	            	//mNeutralButtonText = text;
	                mButtonNeutralMessage = msg;
	                break;
	                
	            default:
	                throw new IllegalArgumentException("Button does not exist");
	        }
	    }

        public Button getButton(int whichButton){
            switch (whichButton) {
                case DialogInterface.BUTTON_POSITIVE:
                    return mBtnPositive;
                case DialogInterface.BUTTON_NEGATIVE:
                    return mBtnNegative;
                case DialogInterface.BUTTON_NEUTRAL:
                    return mBtnNeutral;
                default:
                    return null;
            }
        }
		
		public Builder setView(View v){
			mViewContent = v;
			return this;
		}
		
		public Builder setItems(int itemsId, OnClickListener listener){
			mItemArray = mContext.getResources().getStringArray(itemsId);
			mItemClickListener = listener;
			return this;
		}
		
		public Builder setItems(CharSequence[] items, OnClickListener listener){
			mItemArray = items;
			mItemClickListener = listener;
			return this;
		}
		
        public Builder setAdapter(final ListAdapter adapter, final OnClickListener listener) {
            mListAdapter = adapter;
            mItemClickListener = listener;
            return this;
        }
        
        public Builder setSingleChoiceItems(CharSequence[] items, int checkedItem, final OnClickListener listener) {
            mItemArray = items;
            mItemClickListener = listener;
            mSelectedItemtIndex = checkedItem;
            mIsSingleChoice = true;
            return this;
        } 
        
        public Builder setCancelable(boolean cancelable){
        	mCancelable = cancelable;
        	return this;
        }
        
        public Builder setOnCancelListener(OnCancelListener listener){
        	mOnCancelListener = listener;
        	return this;
        }
        
        /**
         * @param layout
         */
        public void setLayoutResource(int layout){
        	mLayout = layout;
        }
        
    	@SuppressWarnings("unused")
        public MaterialDialog create(){
        	LayoutInflater infl = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        	View layout = infl.inflate(mLayout == 0 ? R.layout.material_dialog_layout : mLayout, null);
        	layout.setMinimumWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, mContext.getResources().getDisplayMetrics()));
        	if (layout == null){
        		return null;
        	}
        	
        	//layout.setMinimumWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, mContext.getResources().getDisplayMetrics()));
        	
        	mDialogTemplate = layout;
        	
			boolean hasTitle = setupTitle();
        	boolean hasMessage = setupMessage();
        	boolean hasList = setupList();
        	boolean hasButton = setupButton();
        	boolean hasView = setupView();
        	
        	if (hasList){
        		FrameLayout content = (FrameLayout) mDialogTemplate.findViewById(R.id.contentPanel);
        		content.setVisibility(View.VISIBLE);
        	}
        	
        	if (hasView){
        		FrameLayout content = (FrameLayout) mDialogTemplate.findViewById(R.id.contentPanel);
        		//content.removeAllViews();
        	}else{
        	}
        	
        	if (!hasButton){
        		View buttonPanel = mDialogTemplate.findViewById(R.id.buttonPanel);
        		buttonPanel.setVisibility(View.GONE);
        		((ViewGroup) mDialogTemplate).removeView(buttonPanel);
        	}
        	
        	MaterialDialog customAlertDialog = new MaterialDialog(mContext, R.style.SWTORMaterialDialog);
        	customAlertDialog.setContentView(mDialogTemplate);
        	mDialog = customAlertDialog;
        	mDialog.setCancelable(mCancelable);
        	mHandler = new ButtonHandler(mDialog);
        	setupButtonListener();
        	if (mOnCancelListener != null){
        		mDialog.setOnCancelListener(mOnCancelListener);
        	}
        	return mDialog;
        }
        
        private void setupButtonListener() {
        	setButton(BUTTON_POSITIVE, mPositiveButtonOnClickListener, mButtonPositiveMessage);
        	setButton(BUTTON_NEGATIVE, mNegativeButtonOnClickListener, mButtonNegativeMessage);
        	setButton(BUTTON_NEUTRAL, mNeutralButtonOnClickListener, mButtonNeutralMessage);
		}

		private boolean setupView() {
        	if (mViewContent == null){
        		return false;
        	}else{
        		FrameLayout custom = (FrameLayout) mDialogTemplate.findViewById(R.id.contentPanel);
        		custom.addView(mViewContent, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        	}
			return true;
		}

		private boolean setupButton() {
        	int BIT_BUTTON_POSITIVE = 1;
            int BIT_BUTTON_NEGATIVE = 2;
            int BIT_BUTTON_NEUTRAL = 4;
            int whichButtons = 0;
            mBtnPositive = (Button) mDialogTemplate.findViewById(R.id.button1);
            mBtnPositive.setOnClickListener(mButtonHandler);
            Typeface tf = FontLoader.getTypeface(getContext(), 1);
    		
            if (TextUtils.isEmpty(mPositiveButtonText)) {
            	mBtnPositive.setVisibility(View.GONE);
            } else {
            	mBtnPositive.setTypeface(tf);
                mBtnPositive.setText(mPositiveButtonText);
                mBtnPositive.setVisibility(View.VISIBLE);
                whichButtons = whichButtons | BIT_BUTTON_POSITIVE;
            }

            mBtnNegative = (Button) mDialogTemplate.findViewById(R.id.button2);
            mBtnNegative.setOnClickListener(mButtonHandler);

            if (TextUtils.isEmpty(mNegativeButtonText)) {
                mBtnNegative.setVisibility(View.GONE);
            } else {
            	mBtnNegative.setTypeface(tf);
                mBtnNegative.setText(mNegativeButtonText);
                mBtnNegative.setVisibility(View.VISIBLE);

                whichButtons = whichButtons | BIT_BUTTON_NEGATIVE;
            }

            mBtnNeutral = (Button) mDialogTemplate.findViewById(R.id.button3);
            mBtnNeutral.setOnClickListener(mButtonHandler);

            if (TextUtils.isEmpty(mNeutralButtonText)) {
                mBtnNeutral.setVisibility(View.GONE);
            } else {
            	mBtnNeutral.setTypeface(tf);
                mBtnNeutral.setText(mNeutralButtonText);
                mBtnNeutral.setVisibility(View.VISIBLE);

                whichButtons = whichButtons | BIT_BUTTON_NEUTRAL;
            }

            if (whichButtons == BIT_BUTTON_POSITIVE) {
                centerButton(mBtnPositive);
            } else if (whichButtons == BIT_BUTTON_NEGATIVE) {
                centerButton(mBtnNeutral);
            } else if (whichButtons == BIT_BUTTON_NEUTRAL) {
                centerButton(mBtnNeutral);
            }else {
                //setButtonDividers(whichButtons);
            }
            
            return whichButtons != 0;
		}
        
        private void centerButton(Button button) {
        	View leftSpacer = mDialogTemplate.findViewById(R.id.leftSpacer);
            leftSpacer.setVisibility(View.VISIBLE);
        }

		private boolean setupList() {
			int layout = mIsSingleChoice ? R.layout.material_singlechoice
					: R.layout.material_list_item;
        	ListAdapter adapter;
        	if (mListAdapter == null){
        		if (mItemArray == null){
        			return false;
        		}
        		adapter = new ArrayAdapter<CharSequence>(mContext, layout, android.R.id.text1, mItemArray){
        			
        			@Override
        			public View getView(int position, View convertView,
        					ViewGroup parent) {
        				View v = super.getView(position, convertView, parent);
//        				if (v instanceof TextView){
//        					TextView txt = (TextView) v;
//        					//txt.setMinimumHeight((int) (18 * mDensity));
//        					//txt.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16.0f);
//        				}
        				return v;
        			}
        		};
        	}else{
        		adapter = mListAdapter;
        	}
        	createListView(adapter);
			return true;
		}
        

		private void createListView(ListAdapter adapter) {
			FrameLayout content = (FrameLayout) mDialogTemplate.findViewById(R.id.contentPanel);
			content.removeView(mDialogTemplate.findViewById(R.id.scrollView));
			
			
			mSeperator = (ImageView) mDialogTemplate.findViewById(R.id.mSeperator);
			mSeperator.setVisibility(View.VISIBLE);
			
            LayoutInflater inflater = LayoutInflater.from(mContext);
			ListView lst = (ListView) inflater.inflate(R.layout.material_listview, content, false);
			//ListView lst = (ListView) inflater.inflate(R.layout.material_listview, null);
			mListView = lst;
			int choiceMode = mIsSingleChoice ? ListView.CHOICE_MODE_SINGLE : ListView.CHOICE_MODE_NONE;
			mListView.setChoiceMode(choiceMode);
			mListView.setCacheColorHint(Color.TRANSPARENT);
			content.addView(mListView, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
			content.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1.0f));
			mListView.setAdapter(adapter);
			mListView.setItemChecked(mSelectedItemtIndex, true);
			if (mItemClickListener != null){
				mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						mItemClickListener.onClick(mDialog, arg2);
						if (!mIsSingleChoice){
							mDialog.dismiss();
						}
					}
				});
					
			}
		}

		private boolean setupMessage() {
        	if (!TextUtils.isEmpty(mMessage)){
        		TextView txtMessage = (TextView) mDialogTemplate.findViewById(R.id.message);
        		Typeface tf = FontLoader.getTypeface(getContext(), 2);
        		
        		txtMessage.setTypeface(tf);
        		txtMessage.setText(mMessage);
        		return true;
        	}else{
        		View messagePanel = mDialogTemplate.findViewById(R.id.contentPanel);
        		//messagePanel.setVisibility(View.GONE);
        	}
			return false;
		}

		private boolean setupTitle() {
        	if (!TextUtils.isEmpty(mTitle)){
        		TextView txtTitle = (TextView) mDialogTemplate.findViewById(R.id.title_message);
        		
        		Typeface tf = FontLoader.getTypeface(getContext(), 1);
        		
        		txtTitle.setTypeface(tf);
        		txtTitle.setText(mTitle);
        		return true;
        	}else{
        		//View titlePanel = mDialogTemplate.findViewById(R.id.title_panel);
        		//titlePanel.setVisibility(View.GONE);
        	}
			return false;
		}

		public MaterialDialog show(){
			MaterialDialog dialog = create();
			dialog.show();
        	return dialog;
        }
		
	}
	
}
