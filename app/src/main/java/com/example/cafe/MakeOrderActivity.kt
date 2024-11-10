package com.example.cafe

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.ComponentActivity

class MakeOrderActivity : ComponentActivity(){

    private lateinit var textViewGreetings: TextView
    private lateinit var textViewAdditives: TextView

    private lateinit var radioGroupDrinks: RadioGroup
    private lateinit var radioButtonTea: RadioButton
    private lateinit var radioButtonCoffee: RadioButton

    private lateinit var checkBoxSugar: CheckBox
    private lateinit var checkBoxMilk: CheckBox
    private lateinit var checkBoxLemon: CheckBox

    private lateinit var spinnerTea: Spinner
    private lateinit var spinnerCoffee: Spinner

    private lateinit var buttonMakeOrder: Button

    private lateinit var drink: String
    private lateinit var userName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_order)
        Log.d("MakeOrderActivity", "OnCreate")

        initView()
        setupUserName()
        radioGroupDrinks.setOnCheckedChangeListener { _, id ->
            if (id == radioButtonTea.id) {
                onUserChooseTea()
            } else if (id == radioButtonCoffee.id) {
                onUserChooseCoffee()
            }
        }
        radioButtonTea.isChecked = true

        buttonMakeOrder.setOnClickListener {
            onUserMadeOrder()
        }

    }

    private fun launchNextScreen(userName: String, drink: String, drinkType: String, checkBox: String) {
        val intent = OrderDetailActivity.newIntent(this, userName, drink, drinkType, checkBox)
        startActivity(intent)
    }

    private fun onUserMadeOrder(){
        var additives: ArrayList<String> = ArrayList()
        if (checkBoxSugar.isChecked){
            additives.add(checkBoxSugar.text.toString())
        }
        if (checkBoxMilk.isChecked){
            additives.add(checkBoxMilk.text.toString())
        }
        if (radioButtonTea.isChecked && checkBoxLemon.isChecked){
            additives.add(checkBoxLemon.text.toString())
        }

        var drinkType: String = ""
        if (radioButtonTea.isChecked){
            drinkType = spinnerTea.selectedItem.toString()
        } else if (radioButtonCoffee.isChecked) {
            drinkType = spinnerCoffee.selectedItem.toString()
        }

        launchNextScreen(userName, drink, drinkType, additives.toString())
    }

    private fun onUserChooseTea(){
        drink = getString(R.string.tea)
        val additivesLabel = getString(R.string.additives, drink)
        textViewAdditives.text = additivesLabel
        checkBoxLemon.visibility = View.VISIBLE
        spinnerTea.visibility = View.VISIBLE
        spinnerCoffee.visibility = View.INVISIBLE
    }

    private fun onUserChooseCoffee(){
        drink = getString(R.string.coffee)
        val additivesLabel = getString(R.string.additives, drink)
        textViewAdditives.text = additivesLabel
        checkBoxLemon.visibility = View.INVISIBLE
        spinnerCoffee.visibility = View.VISIBLE
        spinnerTea.visibility = View.INVISIBLE
    }

    private fun setupUserName(){
        userName = intent.getStringExtra(EXTRA_USER_NAME).toString()
        val greetings: String = getString(R.string.greetings, userName)
        textViewGreetings.text = greetings
    }

    //называется фабричный метод (фабрика интендов)
    companion object {
        fun newIntent(context: Context, userName: String): Intent {
            return Intent(context, MakeOrderActivity::class.java).apply {
                putExtra(EXTRA_USER_NAME, userName)
            }
        }

        private const val EXTRA_USER_NAME: String = "userName"
    }

    private fun initView() {
        textViewGreetings = findViewById(R.id.textViewGreetings)
        radioGroupDrinks = findViewById(R.id.radioGroupDrinks)
        radioButtonTea = findViewById(R.id.radioButtonTea)
        radioButtonCoffee = findViewById(R.id.radioButtonCoffee)
        textViewAdditives = findViewById(R.id.textViewAdditives)
        checkBoxSugar = findViewById(R.id.checkBoxSugar)
        checkBoxMilk = findViewById(R.id.checkBoxMilk)
        checkBoxLemon = findViewById(R.id.checkBoxLemon)
        spinnerTea = findViewById(R.id.spinnerTea)
        spinnerCoffee = findViewById(R.id.spinnerCoffee)
        buttonMakeOrder = findViewById(R.id.buttonMakeOrder)
    }
}