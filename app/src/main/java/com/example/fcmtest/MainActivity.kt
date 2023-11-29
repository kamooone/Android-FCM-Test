package com.example.fcmtest

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        updateFCMToken()
    }

    private fun updateFCMToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("FIREBASE", "getInstanceId failed", task.exception)
                return@OnCompleteListener
            }
            val token = task.result

            // 以下通知テスト用にFCMトークンを表示、コピーする為の記述
            Toast.makeText(baseContext,token,Toast.LENGTH_LONG).show()
            val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText("text", token)
            clipboardManager.setPrimaryClip(clipData)
        })
    }
}