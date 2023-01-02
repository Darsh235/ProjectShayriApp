package com.example.shayariapp

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ShayariApdter(var context: Context, var list: ArrayList<ShayariModalClass>) :
    RecyclerView.Adapter<ShayariApdter.MyViewHolder>() {
    var colourRoll= arrayOf(R.color.l1,R.color.l2,R.color.l3)
    var c=0
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var txtShayari = itemView.findViewById<TextView>(R.id.txtShayari)
        var imgcopy = itemView.findViewById<ImageView>(R.id.imgcopy)
        var imgshare = itemView.findViewById<ImageView>(R.id.imgshare)
        var imgWhatappShare = itemView.findViewById<ImageView>(R.id.imgWhatappShare)
        var imgEmtyHeart = itemView.findViewById<ImageView>(R.id.imgEmtyHeart)
        var imgFillHeart = itemView.findViewById<ImageView>(R.id.imgFillHeart)
        var lnrBg = itemView.findViewById<LinearLayout>(R.id.lnrBg)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        var view = LayoutInflater.from(context).inflate(R.layout.shayari_box_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if(c==  3){
            c=0
        }
        holder.txtShayari.setText(list.get(position).Shayari)
        holder.lnrBg.setBackgroundResource(colourRoll.get(c))
        c++

///// copy code
        holder.imgcopy.setOnClickListener(View.OnClickListener { v: View? ->
            val clipboard =
                context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip =
                ClipData.newPlainText("label", holder.txtShayari.getText().toString())
            clipboard.setPrimaryClip(clip)
            Toast.makeText(context, "Shayari Copy Suceessfully", Toast.LENGTH_SHORT).show()
        })

////// Share code

        holder.imgshare.setOnClickListener(View.OnClickListener { v: View? ->

            /*Create an ACTION_SEND Intent*/
            val intent = Intent(Intent.ACTION_SEND)
            val ff: String = holder.txtShayari.getText().toString()
            intent.type = "text/plain"
            intent.putExtra(
            Intent.EXTRA_SUBJECT,
            context.getString(R.string.l)
        )
            intent.putExtra(Intent.EXTRA_TEXT, ff)
            /*Fire!*/context.startActivity(
            Intent.createChooser(
                intent,
                context.getString(R.string.l)
            )
        )
        })

        holder.imgWhatappShare.setOnClickListener(View.OnClickListener { v: View? ->

            /*Create an ACTION_SEND Intent*/
            val intent = Intent(Intent.ACTION_SEND)
            val ff: String = holder.txtShayari.getText().toString()
            intent.type = "text/plain"
            intent.setPackage("com.whatsapp")
            /*Applying information Subject and Body.*/intent.putExtra(
            Intent.EXTRA_SUBJECT,
            context.getString(R.string.l)
        )
            intent.putExtra(Intent.EXTRA_TEXT, ff)
            /*Fire!*/context.startActivity(
            Intent.createChooser(
                intent, context.getString(R.string.l)
            )
        )
        })

        if (list.get(position).Status==0){
            holder.imgFillHeart.setVisibility(View.GONE)
            holder.imgEmtyHeart.setVisibility(View.VISIBLE)
        }else{
            holder.imgEmtyHeart.setVisibility(View.GONE)
            holder.imgFillHeart.setVisibility(View.VISIBLE)
        }

        holder.imgEmtyHeart.setOnClickListener(View.OnClickListener { v: View? ->
            holder.imgEmtyHeart.setVisibility(View.GONE)
            holder.imgFillHeart.setVisibility(View.VISIBLE)
            MyDatabase(context).editstatus(1,list.get(position).ShayariID)

        })

        holder.imgFillHeart.setOnClickListener(View.OnClickListener { v: View? ->
            holder.imgFillHeart.setVisibility(View.GONE)
            holder.imgEmtyHeart.setVisibility(View.VISIBLE)
            MyDatabase(context).editstatus(0,list.get(position).ShayariID)
        })


        holder.lnrBg.setOnClickListener{

            val intent = Intent(context,ImageActivity::class.java)
            intent.putExtra("quoteKey",list.get(position).Shayari)
            context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}