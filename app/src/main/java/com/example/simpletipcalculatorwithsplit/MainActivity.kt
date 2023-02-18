package com.example.simpletipcalculatorwithsplit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import org.w3c.dom.Text
private const val initial_tip_percent=20

class MainActivity : AppCompatActivity() {


//    private lateinit var tax_label:TextView
    private lateinit var total_shown:TextView
    private lateinit var seekBar:SeekBar
    private lateinit var tax_label:TextView
    private lateinit var tip_shown:TextView
    private lateinit var edit_base:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        total_shown=findViewById(R.id.total_shown)
        seekBar=findViewById(R.id.seekBar)
        tax_label=findViewById(R.id.tax_label)
        tip_shown=findViewById(R.id.tip_shown)
        edit_base=findViewById(R.id.edit_base)

        seekBar.progress= initial_tip_percent
        tax_label.text="$initial_tip_percent %"

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
               tax_label.text="$progress %"
                computevalues()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })
        edit_base.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
              computevalues()
            }

        })
    }

    private fun computevalues() {
        if(edit_base.text.isEmpty()){
            tip_shown.text=""
            total_shown.text=""
            return
        }
        val baseamt= edit_base.text.toString().toDouble()
        val percent=seekBar.progress

        val tipamt=baseamt*percent/100
        val totalamt=baseamt+tipamt

        tip_shown.text="%.2f".format(tipamt)
        total_shown.text="%.2f".format(totalamt)


    }
}