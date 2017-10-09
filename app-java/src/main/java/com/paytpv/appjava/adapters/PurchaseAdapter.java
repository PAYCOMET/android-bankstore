package com.paytpv.appjava.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.paytpv.androidsdk.Model.PTPVRequests.Purchase.PTPVPurchaseDetails;
import com.paytpv.appjava.R;

import java.util.ArrayList;

public class PurchaseAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private Context mContext;
    private ArrayList<PTPVPurchaseDetails> mDataSource;

    public PurchaseAdapter(Context context, ArrayList<PTPVPurchaseDetails> dataSource) {
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

            view = mInflater.inflate(R.layout.list_item_purchase, null);

            mViewHolder.purchaseOrder = view.findViewById(R.id.purchase_order);
            mViewHolder.purchaseAmount = view.findViewById(R.id.purchase_amount);

            view.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) view.getTag();
        }

        mViewHolder.purchaseOrder.setText(mDataSource.get(i).getOrder());
        mViewHolder.purchaseAmount.setText(String.valueOf(Double.parseDouble(mDataSource.get(i).getAmount())/100));

        return view;
    }

    private class ViewHolder {
        private TextView purchaseOrder;
        private TextView purchaseAmount;
    }
}
