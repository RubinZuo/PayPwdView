package com.rubin.paypwdview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import com.rubin.view.PayPwdView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var passWord:String=""//密码
    val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pay_pwd_view.setInputCompleteListener(object : PayPwdView.InputCompleteListener {
            override fun inputComplete() {
                when {
                    passWord.isBlank() -> {
                        passWord = pay_pwd_view.editContent
                        showMessage(pay_pwd_view.editContent)
                        showRepeatInput()
                    }
                    passWord == pay_pwd_view.editContent -> {
                        showMessage("密码设置成功")
                    }
                    else -> {
                        showMessage("输入的密码不一致")
                    }
                }
            }

            override fun invalidContent() {
            }

        })
    }

    /**
     * 展示再次输入
     */
    private fun showRepeatInput() {
        handler.postDelayed(Runnable {
            tv_set_pwd_tip_1.text = "请再次输入"
            tv_set_pwd_tip_2.visibility = View.GONE
            pay_pwd_view.clearPwd()
        }, 200)

    }

    private fun showMessage(text: String) {
        Toast.makeText(this@MainActivity,text,Toast.LENGTH_LONG).show()
    }
}