package com.example.mariatrapicyna.smart_fridge

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class ProductAdapter(private val ProductList: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ProductViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.product_card, viewGroup, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return ProductList.count()
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = ProductList[position]
        holder.bind(product)
    }

    class ProductViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(product: Product) {
            view.findViewById<TextView>(R.id.productName).text = product._name
            view.findViewById<TextView>(R.id.shelf_life).text = product._shelfLife.toString()
        }
    }
}