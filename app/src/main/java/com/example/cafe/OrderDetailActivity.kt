package com.example.cafe

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cafe.MakeOrderActivity.Companion

class OrderDetailActivity : ComponentActivity() {
    private lateinit var textViewName: TextView
    private lateinit var textViewDrink: TextView
    private lateinit var textViewDrinkType: TextView
    private lateinit var textViewAdditives: TextView

    private lateinit var drink: String
    private lateinit var userName: String
    private lateinit var drinkType: String
    private lateinit var additives: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_detail)

        initView()
        setupUserName()
        setupDrink()
        setupDrinkType()
        setupAdditives()
    }

    private fun setupUserName(){
        userName = intent.getStringExtra(EXTRA_USER_NAME).toString()
        textViewName.text = userName
    }

    private fun setupDrink(){
        drink = intent.getStringExtra(EXTRA_DRINK).toString()
        textViewDrink.text = drink
    }

    private fun setupDrinkType(){
        drinkType = intent.getStringExtra(EXTRA_DRINK_TYPE).toString()
        textViewDrinkType.text = drinkType
    }

    private fun setupAdditives(){
        additives = intent.getStringExtra(EXTRA_CHECK_BOX).toString()
        textViewAdditives.text = additives
    }

    private fun initView(){
        textViewName = findViewById(R.id.textViewName)
        textViewDrink = findViewById(R.id.textViewDrink)
        textViewDrinkType = findViewById(R.id.textViewDrinkType)
        textViewAdditives = findViewById(R.id.textViewAdditives)
    }


    //называется фабричный метод (фабрика интендов)
    companion object {
        fun newIntent(
            context: Context,
            userName: String,
            drink: String,
            drinkType: String,
            checkBox: String
        ): Intent {
            return Intent(context, OrderDetailActivity::class.java).apply {
                putExtra(EXTRA_USER_NAME, userName)
                putExtra(EXTRA_DRINK, drink)
                putExtra(EXTRA_DRINK_TYPE, drinkType)
                putExtra(EXTRA_CHECK_BOX, checkBox)
            }
        }

        private const val EXTRA_USER_NAME: String = "userName"
        private const val EXTRA_DRINK: String = "drink"
        private const val EXTRA_CHECK_BOX: String = "checkBox"
        private const val EXTRA_DRINK_TYPE: String = "drinkType"
    }
}