package com.example.vchatai

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.RetryPolicy
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest

import com.android.volley.toolbox.Volley
import com.example.chatvai.messageAdapter
import com.example.chatvai.senderreceiver
import com.example.vchatai.databinding.ActivityChatBinding
import org.json.JSONObject

class chatActivity : AppCompatActivity() {
    lateinit var adapter: messageAdapter
    lateinit var binding: ActivityChatBinding
    lateinit var messageList:ArrayList<senderreceiver>
    val url="https://api.openai.com/v1/completions"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        messageList= ArrayList()
        binding.rcv.layoutManager= LinearLayoutManager(this)
        adapter=messageAdapter(this, messageList)
        binding.rcv.adapter=adapter

        binding.sentbtn.setOnClickListener{
                            if(binding.edttxt.text.toString().length>0){
                    messageList.add(senderreceiver(binding.edttxt.text.toString(),"user"))
                    adapter.notifyDataSetChanged()
                    getResponse(binding.edttxt.text.toString())
                }
                else{
                    Toast.makeText(this,"Please enter your text",Toast.LENGTH_SHORT).show()
                }
        }
    }
    fun getResponse(Query:String){
        binding.edttxt.setText("")
        val queue:RequestQueue=Volley.newRequestQueue(applicationContext)
        val jsonObject:JSONObject?= JSONObject()
        jsonObject?.put("model", "ext-davinci-003")
        jsonObject?.put("prompt",Query)
        jsonObject?.put("temperature",0)
        jsonObject?.put("max_tokens",100)
        jsonObject?.put("top_p",1)
        jsonObject?.put("frequency_penalty",0.0)
        jsonObject?.put("presence_penalty",0.0)
        val postRequest:JsonObjectRequest=object: JsonObjectRequest(Method.POST,url,jsonObject,Response.Listener { response ->
val responseMsg:String=response.getJSONArray("choices").getJSONObject(0).getString("text")
            messageList.add(senderreceiver(responseMsg,"bot"))
            adapter.notifyDataSetChanged()
        },Response.ErrorListener {
            Toast.makeText(applicationContext,"Failed to generate response..",Toast.LENGTH_SHORT).show()
        }){
            override fun getHeaders(): MutableMap<String, String> {
              val params:MutableMap<String,String> =HashMap()
                params["Content-Type"]=" application/json"
                params["Authorization"]="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"
                return params
            }
        }
        postRequest.setRetryPolicy(object :RetryPolicy{
            override fun getCurrentTimeout(): Int {
return 5000
            }

            override fun getCurrentRetryCount(): Int {
return 500
            }
            override fun retry(error: VolleyError?) {

            }

        })
queue.add(postRequest)
    }
}