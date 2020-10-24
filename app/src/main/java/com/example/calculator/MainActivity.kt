package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    var lastNumeric : Boolean = false
    var lastdot : Boolean = false
    fun onDigit(view : View){
        textView.append((view as Button).text)
        lastNumeric = true
    }
    fun onClear(view : View){
        textView.text= ""
        lastNumeric = false
        lastdot = false
    }

    fun onDecimal(view : View){
        if(lastNumeric && !lastdot){
            textView.append(".")
            lastdot = true
        }
    }

    fun onOperator(view : View){
        if(lastNumeric && !isOperatorThere(textView.text.toString())){
            textView.append((view as Button).text)
            lastNumeric = false
            lastdot = false
        }
    }

    fun onEqual(view : View){

        if(lastNumeric){
            var valuetext = textView.text.toString()
            var prefix = ""
            try{
                if(valuetext.startsWith("-")){
                    prefix = "-"
                    valuetext = valuetext.substring(1)
                }
                if(valuetext.contains("-")){
                    val valuesplit = valuetext.split("-")
                    var one = valuesplit[0]
                    val two = valuesplit[1]
                    if(prefix!=""){
                        one = prefix + one
                    }
                    textView.text = removeZeroafterPoint((one.toDouble() - two.toDouble()).toString())
                }
                else if(valuetext.contains("+")){
                    val valuesplit = valuetext.split("+")
                    var one = valuesplit[0]
                    val two = valuesplit[1]
                    if(prefix!=""){
                        one = prefix + one
                    }
                    textView.text = removeZeroafterPoint((one.toDouble() + two.toDouble()).toString())
                }
                else if(valuetext.contains("*")){
                    val valuesplit = valuetext.split("*")
                    var one = valuesplit[0]
                    val two = valuesplit[1]
                    if(prefix!=""){
                        one = prefix + one
                    }
                    textView.text = removeZeroafterPoint((one.toDouble() * two.toDouble()).toString())
                }
                else{
                    val valuesplit = valuetext.split("/")
                    var one = valuesplit[0]
                    val two = valuesplit[1]
                    if(prefix!=""){
                        one = prefix + one
                    }
                    textView.text = removeZeroafterPoint((one.toDouble() / two.toDouble()).toString())
                }
            }
            catch(e : ArithmeticException){
                println(e.printStackTrace())
            }
        }
    }

    private fun isOperatorThere(value : String) : Boolean{
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("+")||value.contains("-")||value.contains("*")||value.contains("/")
        }
    }

    private fun removeZeroafterPoint(result : String) : String{
        var value = result
        if(result.contains(".0")) {
            value = result.substring(0,result.length - 2)
        }
        return value
    }
}