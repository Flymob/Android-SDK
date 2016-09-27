package com.flymob.sample.samples.native_ad.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flymob.sample.R;
import com.flymob.sample.samples.native_ad.recycler.elements.ElementBase;
import com.flymob.sample.samples.native_ad.recycler.elements.NativeAdElement;
import com.flymob.sample.samples.native_ad.recycler.elements.NewsElement;
import com.flymob.sample.samples.native_ad.recycler.holders.NativeAdViewHolder;
import com.flymob.sample.samples.native_ad.recycler.holders.NewsViewHolder;

import java.util.List;

/**
 * Created by a.baskakov on 13/04/16.
 */
public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected static final int VIEW_TYPE_NEWS = 0;
    protected static final int VIEW_TYPE_NATIVE_AD = 1;

    Context mContext;
    List<ElementBase> mItemList;

    public NewsAdapter(Context context, List<ElementBase> itemList) {
        mContext = context;
        mItemList = itemList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_NEWS: {
                View view = LayoutInflater.from(mContext).inflate(R.layout.news_item, parent, false);
                return new NewsViewHolder(view);
            }
            case VIEW_TYPE_NATIVE_AD: {
                View view = LayoutInflater.from(mContext).inflate(R.layout.native_ad_item, parent, false);
                return new NativeAdViewHolder(view);
            }
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        ElementBase elementBase = getItem(position);
        if (elementBase instanceof NewsElement) {
            return VIEW_TYPE_NEWS;
        }
        return VIEW_TYPE_NATIVE_AD;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        switch (itemViewType) {
            case VIEW_TYPE_NEWS: {
                NewsViewHolder h = (NewsViewHolder) holder;
                NewsElement element = (NewsElement) getItem(position);
                h.setItem(element);
            }
            break;
            case VIEW_TYPE_NATIVE_AD: {
                NativeAdViewHolder h = (NativeAdViewHolder) holder;
                NativeAdElement element = (NativeAdElement) getItem(position);
                h.setItem(element);
            }
            break;
        }
    }

    public ElementBase getItem(int position) {
        return mItemList.get(position);
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }
}
