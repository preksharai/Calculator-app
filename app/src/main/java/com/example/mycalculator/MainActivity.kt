package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    var lastNumeric:Boolean=false
    var lastDot:Boolean=false
    var flag=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun digit_pressed(view: View)
    {
        if(flag==1)
        {
            screen_tv.text=""
            flag=0
        }
        //whenever a button is pressed then view is called, so view.text gives text
        //but no since view has no text, button has it. So, convert view as button
        screen_tv.append((view as Button).text)

        //doing it bcz before decimal ,a number has to be there
        lastNumeric=true

    }

    fun clear_pressed(view: View)
    {
        screen_tv.text=""
        lastNumeric=false
        lastDot=false
    }

    fun decimalPressed(view: View)
    {
        if(lastNumeric==true && lastDot==false)
        {
            screen_tv.append(".")
            lastNumeric=false
            lastDot=true
        }
    }

    fun operatorPressed(view:View)
    {
        if(lastNumeric && !isOperatorAdded(screen_tv.text.toString()))
        {
            screen_tv.append((view as Button).text)
            //refreshing
            lastNumeric=false
            lastDot=false
        }
        if(screen_tv.text.isEmpty() && (view as Button).text=="-")
        {
            screen_tv.append((view as Button).text)
            lastNumeric=false
            lastDot=false
        }
    }

    fun isOperatorAdded(value:String): Boolean {
        when
        {
            value.startsWith("-")-> return false
            value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-")->{
                return true
            }
        }
        return false
    }

    /*fun isOperatorAdded(value:String):Boolean {
        if(value.startsWith("-")) {
            return false
        }
        else if(value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-")){
            return true
        }

    }*/

    fun equal_pressed(view: View)
    {
        //it will be pressed only if lastnumber is true
        if(lastNumeric==true)
        {
            var expr=screen_tv.text.toString()
            var em=""

            //exception handling in case of 10/0
            try{
                if(expr.startsWith("-"))
                {
                    expr=expr.substring(1)
                    em="-"
                }
                if(expr.contains("-"))
                {
                   val splitexpr= expr.split("-")   //gets stored in array
                    var one=splitexpr[0]
                    var two=splitexpr[1]
                    if(em=="-")
                        one=em+one
                    screen_tv.text=(one.toDouble()-two.toDouble()).toString()
                }
                else if(expr.contains("+"))
                {
                    val splitexpr= expr.split("+")   //gets stored in array
                    var one=splitexpr[0]
                    var two=splitexpr[1]
                    if(em=="-")
                        one=em+one
                    screen_tv.text=(one.toDouble()+two.toDouble()).toString()
                }
                else if(expr.contains("*"))
                {
                    val splitexpr= expr.split("*")   //gets stored in array
                    var one=splitexpr[0]
                    var two=splitexpr[1]
                    if(em=="-")
                        one=em+one
                    screen_tv.text=(one.toDouble()*two.toDouble()).toString()
                }
                else if(expr.contains("/"))
                {
                    val splitexpr= expr.split("/")   //gets stored in array
                    var one=splitexpr[0]
                    var two=splitexpr[1]
                    if(em=="-")
                        one=em+one
                    screen_tv.text=(one.toDouble()/two.toDouble()).toString()
                }
                flag=1
            }
            catch(e:ArithmeticException)
            {
                e.printStackTrace()
            }
        }
    }


}