package com.example.stopwatchapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import com.example.stopwatchapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    var count = 0
    var flag = true
    var handler = Handler(Looper.getMainLooper())

    var runnable = object:Runnable{
        override fun run() {
            count++
            val hour = count/3600
            val min = (count%3600)/60
            val sec = count%60
            var time = String.format("%02d:%02d:%02d",hour,min,sec)

            binding.display.text = time
                handler.postDelayed(this, 1000)
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.startBtn.setOnClickListener{
            if(flag){
                handler.postDelayed(runnable,1000)
                flag=false
                binding.startBtn.isEnabled = false
                binding.pauseBtn.isEnabled = true
                binding.resetBtn.isEnabled = false
            }
        }

        binding.pauseBtn.setOnClickListener{
            if(!flag){
                handler.removeCallbacks(runnable)
                flag=true
                binding.startBtn.text = "Resume"
                binding.startBtn.isEnabled = true
                binding.pauseBtn.isEnabled = false
                binding.resetBtn.isEnabled = true
            }
        }

        binding.resetBtn.setOnClickListener{
                handler.removeCallbacks(runnable)
                count = 0

            binding.display.text = "00:00:00"

                binding.startBtn.isEnabled = true
                binding.pauseBtn.isEnabled = false
                binding.resetBtn.isEnabled = false
        }
    }
}