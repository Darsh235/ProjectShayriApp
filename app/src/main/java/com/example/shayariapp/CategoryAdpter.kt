package com.example.shayariapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shayariapp.MyDatabase.ShayariShow
import java.util.ArrayList

class CategoryAdpter( var context: Context ,var  list: ArrayList<ModalClass>) : RecyclerView.Adapter<CategoryAdpter.MyViewHolder>() {
    class MyViewHolder(view: View) :RecyclerView.ViewHolder(view)
    {
       var txcategoty=view.findViewById<TextView>(R.id.txcategoty)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        var view =LayoutInflater.from(parent.context).inflate(R.layout.catrgory_list,parent,false)

        return  MyViewHolder(view)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.txcategoty.setText(list.get(position).Category)

        holder.txcategoty.setOnClickListener {
            ShayariShow=list.get(position).ID

            var intent=Intent(context,ShayariShowActivity::class.java)
            intent.putExtra("cate",position)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}