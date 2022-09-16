 package com.ishak.hareketiyakalakt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.util.rangeTo
import com.ishak.hareketiyakalakt.databinding.ActivityMainBinding
import kotlin.random.Random

 class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var score=0
     var handler=Handler()
     var runnable= Runnable {  }
     var arrayList=ArrayList<ImageButton>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        arrayList.add(binding.imageButton1)
        arrayList.add(binding.imageButton2)
        arrayList.add(binding.imageButton3)
        arrayList.add(binding.imageButton5)
        arrayList.add(binding.imageButton6)
        arrayList.add(binding.imageButton7)
        arrayList.add(binding.imageButton9)
        arrayList.add(binding.imageButton10)
        arrayList.add(binding.imageButton11)
        arrayList.add(binding.imageButton13)
        arrayList.add(binding.imageButton14)
        arrayList.add(binding.imageButton15)
        changeImage()
        object :CountDownTimer(10100,1000){
            override fun onTick(p0: Long) {
                binding.TimeScore.text="time:"+p0/1000
            }

            override fun onFinish() {
                handler.removeCallbacks(runnable)
                for (image in arrayList){
                    image.visibility=View.INVISIBLE
                }
                binding.TimeScore.text="time:0"
                var alert=AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game Over")
                alert.setMessage("are you want to play the game?")
                alert.setPositiveButton("yes"){dialogue,which->
                    ///oyun baştan başlatılacak
                    val intent=intent
                    finish()
                    startActivity(intent)
                }
                alert.setNegativeButton("No"){dialogue,which->
                    Toast.makeText(this@MainActivity,"game finished",Toast.LENGTH_LONG).show()
                    finish()
                }
                alert.show()

            }

        }.start()

    }

     fun changeImage(){
     runnable= object :Runnable {
         override fun run() {
             for (image in arrayList){
                 image.visibility=View.INVISIBLE
             }

             var randomNumber=(0..11).random()///0 ile 10 arasında sayı üretir
             arrayList[randomNumber].visibility=View.VISIBLE

             handler.postDelayed(runnable,500)

         }
     }
         handler.post(runnable)

     }



     fun onClick(view: View){///resime bastığı anda score 1 artıyor
         score++;
         binding.ScoreText.text="score:"+score
     }
}