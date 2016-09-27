package com.flymob.sample.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.flymob.sample.R;

/**
 * Created by alexey on 06/11/15.
 */
public class MenuElementViewHolder extends RecyclerView.ViewHolder {
    TextView mTitle;
    MenuElement mMenuElement;

    public MenuElementViewHolder(View view) {
        super(view);
        mTitle = (TextView) view.findViewById(R.id.title);
    }

    public void setMenuItem(MenuElement menuElement) {
        mMenuElement = menuElement;
        mTitle.setText(menuElement.getTitle());
    }

}
