package com.example.mariatrapicyna.smart_fridge

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import java.util.*

class ProductAdapter :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private val product = Product("milk", Date(2019, 7, 2), Date(2019, 7, 2), 2)
    private val productList: List<Product> = listOf(product)

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ProductViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.product_card, viewGroup, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productList.count()
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.bind(product)
    }

    class ProductViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(product: Product) {
            view.findViewById<TextView>(R.id.productName).text = product._name
            view.findViewById<TextView>(R.id.shelf_life).text = product._shelfLife.toString()
        }
    }
}