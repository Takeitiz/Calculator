package com.example.calculator

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private lateinit var display: TextView
    private lateinit var button0: Button
    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button
    private lateinit var button5: Button
    private lateinit var button6: Button
    private lateinit var button7: Button
    private lateinit var button8: Button
    private lateinit var button9: Button
    private lateinit var buttonC: Button
    private lateinit var buttonCE: Button
    private lateinit var buttonBS: Button
    private lateinit var buttonPlus: Button
    private lateinit var buttonDiv: Button
    private lateinit var buttonMul: Button
    private lateinit var buttonSub: Button
    private lateinit var buttonEqual: Button
    private var calculation = ""
    private var calculation1 = ""
    private var calculation2 = ""
    private var state = 1
    private var state1 = 0
    private var op1 = 0
    private var op2 = 0
    private var cal = 0
    private var result = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.output)

        button0 = findViewById(R.id.btn0);
        button1 = findViewById(R.id.btn1);
        button2 = findViewById(R.id.btn2);
        button3 = findViewById(R.id.btn3);
        button4 = findViewById(R.id.btn4);
        button5 = findViewById(R.id.btn5);
        button6 = findViewById(R.id.btn6);
        button7 = findViewById(R.id.btn7);
        button8 = findViewById(R.id.btn8);
        button9 = findViewById(R.id.btn9);

        buttonCE = findViewById(R.id.clearEntry)
        buttonC = findViewById(R.id.clear)
        buttonBS = findViewById(R.id.backSpace)
        buttonDiv = findViewById(R.id.divide)
        buttonMul = findViewById(R.id.multiply)
        buttonSub = findViewById(R.id.subtract)
        buttonPlus = findViewById(R.id.plus)
        buttonEqual = findViewById(R.id.equal)

        button0.setOnClickListener { onClick(it) }
        button1.setOnClickListener { onClick(it) }
        button2.setOnClickListener { onClick(it) }
        button3.setOnClickListener { onClick(it) }
        button4.setOnClickListener { onClick(it) }
        button5.setOnClickListener { onClick(it) }
        button6.setOnClickListener { onClick(it) }
        button7.setOnClickListener { onClick(it) }
        button8.setOnClickListener { onClick(it) }
        button9.setOnClickListener { onClick(it) }
        buttonPlus.setOnClickListener { onClick(it) }
        buttonSub.setOnClickListener { onClick(it) }
        buttonDiv.setOnClickListener { onClick(it) }
        buttonMul.setOnClickListener { onClick(it) }
        buttonEqual.setOnClickListener { onClick(it) }
        buttonC.setOnClickListener { onClick(it) }
        buttonCE.setOnClickListener { onClick(it) }
        buttonBS.setOnClickListener { onClick(it) }




    }
    fun onClick(v: View?) {
        v?.let {
            val id = it.id
            when (id) {
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5,
                R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9 -> {
                    state1 = 0
                    calculation += (it as Button).text
                    calculation1 += it.text
                    result += it.text
                    display.text = calculation
                }

                R.id.plus, R.id.subtract, R.id.multiply, R.id.divide -> {
                    cal = when (id) {
                        R.id.plus -> 1
                        R.id.subtract -> 2
                        R.id.multiply -> 3
                        R.id.divide -> 4
                        else -> 0
                    }

                    if (state == 1) {
                        if (calculation == "" && state1 != 1) {
                            return
                        } else if (state1 == 1) {
                            calculation = display.text.toString() + (it as Button).text
                            display.text = calculation
                            return
                        } else {
                            state = 2
                            op1 = calculation1.toInt()
                        }
                    }

                    calculation1 = ""
                    calculation += (it as Button).text
                    calculation2 = calculation
                    display.text = calculation
                }

                R.id.equal -> {
                    if (calculation == "+" || calculation == "-" || calculation == "*" || calculation == "/") {
                        return
                    }

                    if (state == 1) {
                        display.text = display.text.toString()
                    }
                    if (calculation == "" && state == 1) {
                        display.text = calculation
                        return
                    }
                    if (display.text.toString() == "" && state == 1) {
                        display.text = calculation
                        return
                    }
                    if (state == 2 && calculation1 == "") {
                        return
                    }

                    op2 = calculation1.toInt()
                    when (cal) {
                        1 -> {
                            display.text = (op1 + op2).toString()
                            op1 += op2
                        }
                        2 -> {
                            display.text = (op1 - op2).toString()
                            op1 -= op2
                        }
                        3 -> {
                            display.text = (op1 * op2).toString()
                            op1 *= op2
                        }
                        4 -> {
                            if (op2 == 0) {
                                display.text = "Syntax error"
                                return
                            }
                            display.text = (op1.toDouble() / op2).toString()
                            op1 /= op2
                        }
                    }
                    state = 1
                    state1 = 1
                    calculation = ""
                    calculation1 = ""
                }

                R.id.clear -> {
                    if (state == 1) {
                        calculation = ""
                        calculation1 = ""
                        display.text = calculation
                    }
                    if (state == 2) {
                        calculation = calculation2
                        calculation1 = ""
                        display.text = calculation
                    }
                }

                R.id.clearEntry-> {
                    calculation = ""
                    calculation1 = ""
                    calculation2 = ""
                    state1 = 0
                    state = 1
                    op1 = 0
                    display.text = calculation
                }

                R.id.backSpace -> {
                    if (state == 1) {
                        if (calculation == "") {
                            display.text = calculation
                            return
                        } else if (display.text.toString() == "") {
                            display.text = calculation
                            return
                        } else {
                            calculation = calculation.substring(0, calculation.length - 1)
                            calculation1 = calculation1.substring(0, calculation1.length - 1)
                            display.text = calculation
                        }
                    } else if (state == 2) {
                        if (calculation1 == "") {
                            state = 1
                            calculation = calculation.substring(0, calculation.length - 1)
                            calculation1 = calculation
                            display.text = calculation
                        } else {
                            calculation = calculation.substring(0, calculation.length - 1)
                            calculation1 = calculation1.substring(0, calculation1.length - 1)
                            display.text = calculation
                        }
                    }
                }
            }
        }
    }

}


