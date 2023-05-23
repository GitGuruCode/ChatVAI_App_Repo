package com.example.chatvai

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.vchatai.R

class messageAdapter(val context: Context, var messageList:ArrayList<senderreceiver>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    class userMessageViewHolder(ItemView: View):RecyclerView.ViewHolder(ItemView){
        val userTV:TextView=itemView.findViewById(R.id.sentsmallid)
    }
    class BotMessageViewHolder(ItemView: View):RecyclerView.ViewHolder(ItemView){
        val botTV:TextView=itemView.findViewById(R.id.receivesmallid)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
val view:View
if(viewType==0) {
    view=LayoutInflater.from(parent.context).inflate(R.layout.sentsmallpart,parent,false)
    return userMessageViewHolder(view)
}else{
    view=LayoutInflater.from(parent.context).inflate(R.layout.receivesmallpart,parent,false)
    return BotMessageViewHolder(view)
}
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
         val sender=messageList.get(position).sender
        when(sender){
            "user"->(holder as userMessageViewHolder).userTV.setText(messageList.get(position).message)
            "bot"->(holder as BotMessageViewHolder).botTV.setText(messageList.get(position).message)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(messageList.get(position).sender){
             "user"->0
            "bot"->1
            else->1
        }
    }
    override fun getItemCount(): Int {
        return messageList.size
    }
}