package com.paytpv.paytpv.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.paytpv.androidsdk.Model.Basic.PTPVProduct
import com.paytpv.paytpv.R

class ProductAdapter(private var mContext: Context, private var mDataSource: ArrayList<PTPVProduct>) : BaseAdapter() {

    private var mInflater: LayoutInflater = this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val row = mInflater.inflate(R.layout.list_item_product, parent, false)

        val productDescription = row.findViewById(R.id.product_description) as TextView
        val productOwner = row.findViewById(R.id.product_owner) as TextView
        val productScoring = row.findViewById(R.id.product_scoring) as TextView
        val productPrice = row.findViewById(R.id.product_price) as TextView

        productDescription.text = mDataSource[position].description
        productOwner.text = mDataSource[position].owner
        productScoring.text = mDataSource[position].scoring
        productPrice.text = (position.toDouble() + 0.99).toString()

        return row
    }

    override fun getItem(position: Int): Any = mDataSource[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = mDataSource.size
}