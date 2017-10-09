package com.paytpv.appjava.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.paytpv.androidsdk.Model.Basic.PTPVProduct;
import com.paytpv.appjava.R;

import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter{

    private Context mContext;
    private ArrayList<PTPVProduct> mDataSource;
    private LayoutInflater mInflater;

    public ProductAdapter(Context context, ArrayList<PTPVProduct> dataSource) {
        this.mContext = context;
        this.mDataSource = dataSource;
        this.mInflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mDataSource.size();
    }

    @Override
    public Object getItem(int i) {
        return mDataSource.get(i);
    }

    @Override
    public long getItemId(int i) {
        return (long) i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder mViewHolder;

        if(view == null) {
            mViewHolder = new ViewHolder();

            view = mInflater.inflate(R.layout.list_item_product, null);

            mViewHolder.productDescription = view.findViewById(R.id.product_description);
            mViewHolder.productOwner = view.findViewById(R.id.product_owner);
            mViewHolder.productScoring = view.findViewById(R.id.product_scoring);
            mViewHolder.productPrice = view.findViewById(R.id.product_price);

            view.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) view.getTag();
        }

        mViewHolder.productDescription.setText(mDataSource.get(i).getDescription());
        mViewHolder.productOwner.setText(mDataSource.get(i).getOwner());
        mViewHolder.productScoring.setText(mDataSource.get(i).getScoring());
        mViewHolder.productPrice.setText(String.valueOf((double) i + 0.99));

        return view;
    }

    private class ViewHolder {
        private TextView productDescription;
        private TextView productOwner;
        private TextView productScoring;
        private TextView productPrice;
    }
}
