package com.codepalace.chatbot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.codepalace.chatbot.Constants.RECEIVE_ID
import com.codepalace.chatbot.Constants.SEND_ID
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private val messagesList = mutableListOf<Message>()
    private lateinit var adapter: MessagingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = MessagingAdapter()
        rv_messages.adapter = adapter
        rv_messages.layoutManager = LinearLayoutManager(applicationContext)


        btn_send.setOnClickListener {
            sendMessage()
        }
    }

    private fun sendMessage() {
        val message = et_message.text.toString()

        if (message.isNotEmpty()) {
            messagesList.add(Message(message, SEND_ID))
            et_message.setText("")

            adapter.insertMessage(Message(message, SEND_ID))
            rv_messages.scrollToPosition(messagesList.size - 1)

            botResponse(message)
        }
    }

    private fun botResponse(message: String) {

        GlobalScope.launch {
            //Fake response delay
            delay(1000)

            withContext(Dispatchers.Main) {
                //Gets the response
                val response = BotResponse.responses(message)
                //Adds it to our local list
                messagesList.add(Message(response, RECEIVE_ID))
                //Inserts our message into the adapter
                adapter.insertMessage(Message(response, RECEIVE_ID))
                //Scrolls us to the position of the latest message
                rv_messages.scrollToPosition(messagesList.size - 1)

            }
        }
    }
}