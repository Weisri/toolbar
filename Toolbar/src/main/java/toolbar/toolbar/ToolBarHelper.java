package com.example.qintainshop;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class ToolBarHelper {
	/* �����ģ�����view��ʱ����Ҫ�õ� */ private Context mContext;
	/* base view */ private FrameLayout mContentView;
	/* �û������view */ private View mUserView;
	/* toolbar */ private Toolbar mToolBar;
	/* ��ͼ������ */ private LayoutInflater mInflater;
	/*
	 * �������� 1��toolbar�Ƿ������ڴ���֮�� 2��toolbar�ĸ߶Ȼ�ȡ
	 */ private static int[] ATTRS = { R.attr.windowActionBarOverlay, R.attr.actionBarSize };

	public ToolBarHelper(Context context, int layoutId) {
		this.mContext = context;
		mInflater = LayoutInflater.from(mContext);
		/* ��ʼ���������� */ 
		initContentView();
		/* ��ʼ���û�����Ĳ��� */
		initUserView(layoutId);
		/* ��ʼ��toolbar */ 
		initToolBar();
	}

	private void initContentView() {
		/* ֱ�Ӵ���һ��֡���֣���Ϊ��ͼ�����ĸ����� */ 
		mContentView = new FrameLayout(mContext);
		ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT);
		mContentView.setLayoutParams(params);

	}

	private void initToolBar() {
		/* ͨ��inflater��ȡtoolbar�Ĳ����ļ� */
		View toolbar = mInflater.inflate(R.layout.activity_tool_bar, mContentView);
		mToolBar = (Toolbar) toolbar.findViewById(R.id.toolbar);
	}

	private void initUserView(int id) {
		mUserView = mInflater.inflate(id, null);
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT);
		TypedArray typedArray = mContext.getTheme().obtainStyledAttributes(ATTRS);
		/* ��ȡ�����ж����������־ */
		boolean overly = typedArray.getBoolean(0, false);
		/* ��ȡ�����ж����toolbar�ĸ߶� */ 
		int toolBarSize = (int) typedArray.getDimension(1,
				(int) mContext.getResources().getDimension(R.dimen.abc_action_bar_default_height_material));
		typedArray.recycle();
		/* ���������״̬������Ҫ���ü�� */
		params.topMargin = overly ? 0 : toolBarSize;
		mContentView.addView(mUserView, params);

	}

	public FrameLayout getContentView() {
		return mContentView;
	}

	public Toolbar getToolBar() {
		return mToolBar;
	}

}