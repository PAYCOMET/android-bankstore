package com.paytpv.paytpv.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.paytpv.androidsdk.Model.PTPVRequests.Purchase.PTPVPurchaseDetails
import com.paytpv.paytpv.R

class PurchaseAdapter(private var mContext: Context, private var mDataSource: ArrayList<PTPVPurchaseDetails>) : BaseAdapter() {

    private var mInflater: LayoutInflater = this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val row = mInflater.inflate(R.layout.list_item_purchase, parent, false)

        val purchaseOrder = row.findViewById(R.id.purchase_order) as TextView
        val purchaseAmount = row.findViewById(R.id.purchase_amount) as TextView

        purchaseOrder.text = mDataSource[position].order
        purchaseAmount.text = (mDataSource[position].amount.toDouble()/100).toString()

        return row
    }

    override fun getItem(position: Int): Any = mDataSource[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = mDataSource.size

}