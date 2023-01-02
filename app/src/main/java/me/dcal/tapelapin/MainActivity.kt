package me.dcal.tapelapin

import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.os.CountDownTimer
import me.dcal.tapelapin.MainActivity
import android.os.Bundle
import me.dcal.tapelapin.R
import android.graphics.drawable.Drawable
import androidx.core.content.res.ResourcesCompat
import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import me.dcal.tapelapin.FinDuJeu
import java.util.*

class MainActivity : AppCompatActivity() {
    //private ResultProfileBinding binding;
    private var LstButtons: MutableList<Button>? = null
    private var PositionTaupe = 0
    private var PositionBombe = 0 //mode expert
    private var nbScore = 0
    private var nbVie = 3
    private var mode: String? = ""
    private var typeFin = ""
    var nbAnimaux = 0
    private var heart1: ImageView? = null
    private var heart2: ImageView? = null
    private var heart3: ImageView? = null
    private var Bombe: Button? = null
    private var TimerView: TextView? = null
    private var CountDownTimer: CountDownTimer? = null
    private var TimerLeft = TEMPS_TIMER // on commence avec le timer plein
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //gestion choix nv
        val extras = intent.extras
        if (extras != null) {
            //score
            val ExtraMode = extras.getString("mode")
            /*TextView TextmodeJeu = (TextView) findViewById(R.id.modeJeu);
            TextmodeJeu.setText("mode : " + ExtraMode);*/mode = ExtraMode // je met le mode
            when (mode) {
                "deb" -> nbAnimaux = 4
                "inter" -> nbAnimaux = 5
                "expert" -> nbAnimaux = 6
                else -> {}
            }
        }
        TimerView = findViewById(R.id.timer)
        startTimer()
        heart1 = findViewById(R.id.heart1)
        heart2 = findViewById(R.id.heart2)
        heart3 = findViewById(R.id.heart3)
        addButton()
        Jeu()
    }

    private fun addButton() {
        //je garde de coté les boutons, ca c'est les boutons de bases
        LstButtons = ArrayList<Button>()
        (LstButtons as ArrayList<Button>).add(findViewById<View>(R.id.button1) as Button)
        (LstButtons as ArrayList<Button>).add(findViewById<View>(R.id.button2) as Button)
        (LstButtons as ArrayList<Button>).add(findViewById<View>(R.id.button3) as Button)
        (LstButtons as ArrayList<Button>).add(findViewById<View>(R.id.button4) as Button)
        when (mode) {
            "deb" -> {}
            "inter" -> {
                val b5 = findViewById<View>(R.id.button5) as Button
                b5.visibility = View.VISIBLE
                (LstButtons as ArrayList<Button>).add(b5)
            }
            "expert" -> {
                val bt5 = findViewById<View>(R.id.button5) as Button
                bt5.visibility = View.VISIBLE
                (LstButtons as ArrayList<Button>).add(bt5)
                val bt6 = findViewById<View>(R.id.button6) as Button
                bt6.visibility = View.VISIBLE
                (LstButtons as ArrayList<Button>).add(bt6)
            }
            else -> {}
        }
    }

    private fun Jeu() {
        updateCountDownText()
        NewRound()
        for (b in LstButtons!!) {
            b.setOnClickListener { v -> //Toast.makeText(getApplicationContext(),"button1", Toast.LENGTH_SHORT).show();
                OnCLickButton(v)
            }
        }
    }

    private fun NewRound() {
        setAutre()
        setTaupe()
    }

    private fun setAutre() {
        for (b in LstButtons!!) {
            val res = this.resources
            val myImage = ResourcesCompat.getDrawable(res, R.drawable.lievre_t, null)
            b.foreground = myImage

            //b.setText("Autre");
        }
        when (mode) {
            "deb" -> {
                //le mode deb est celui de base
                val mode_deb = findViewById<ImageView>(R.id.mode_deb)
                mode_deb.visibility = View.VISIBLE
            }
            "inter" -> {
                val mode_inter = findViewById<ImageView>(R.id.mode_inter)
                mode_inter.visibility = View.VISIBLE
                //ajout d'un rat
                setRat()
            }
            "expert" -> {
                val mode_expert = findViewById<ImageView>(R.id.mode_expert)
                mode_expert.visibility = View.VISIBLE
                //ajout d'une bombe
                setRat()
                setBombe()
            }
            else -> {}
        }
    }

    private fun setRat() {
        //on utilise le hasard pour decider uqel bouton sera le bon
        val seed = Random()
        val PositionRat = seed.nextInt(nbAnimaux)
        val b = LstButtons!![PositionRat]
        val res = this.resources
        val myImage = ResourcesCompat.getDrawable(res, R.drawable.souris_tr, null)
        b.foreground = myImage
        //b.setText("Rat");
    }

    private fun setBombe() {

        //on utilise le hasard pour decider uqel bouton sera le bon
        val seed = Random()
        PositionBombe = seed.nextInt(nbAnimaux)
        val b = LstButtons!![PositionBombe]
        val res = this.resources
        val myImage = ResourcesCompat.getDrawable(res, R.drawable.bombe, null)
        b.foreground = myImage
        //Toast.makeText(getApplicationContext(),"setBombe : "+PositionBombe, Toast.LENGTH_SHORT).show();
        //b.setText("Bombe");
    }

    private fun setTaupe() {

        //on utilise le hasard pour decider uqel bouton sera le bon
        val seed = Random()
        PositionTaupe = seed.nextInt(nbAnimaux)
        val b = LstButtons!![PositionTaupe]
        //b.setText("Taupe");
        val res = this.resources
        val myImage = ResourcesCompat.getDrawable(res, R.drawable.taupe_trapp, null)
        b.foreground = myImage
    }

    private val isFailGame: Boolean
        private get() {
            if (nbVie <= 0) {
                typeFin = "Mort"
                return true
            }
            return false
        }

    private fun OnCLickButton(view: View) {
        //Toast.makeText(getApplicationContext(),"onCllicj", Toast.LENGTH_SHORT).show();
        val Taupe = LstButtons!![PositionTaupe]
        if (mode == "expert") {
            Bombe = LstButtons!![PositionBombe]
        }
        if (view === Taupe) {
            //OK c'est la taupe
            nbScore++
            val TextScore = findViewById<View>(R.id.score) as TextView
            TextScore.text = "Score : $nbScore"
        } else {
            if (mode == "expert") {
                if (view === Bombe) {
                    typeFin = "Boom"
                    //Toast.makeText(getApplicationContext(), "BOOM", Toast.LENGTH_SHORT).show();
                    val ImageBoom = findViewById<ImageView>(R.id.imageboom)
                    ImageBoom.visibility = View.VISIBLE
                    startEndActivity()
                }
            }
            //FAUX
            nbVie--
            when (nbVie) {
                3 -> {}
                2 -> heart3!!.visibility = View.INVISIBLE
                1 -> {
                    heart2!!.visibility = View.INVISIBLE
                    heart3!!.visibility = View.INVISIBLE
                }
                else -> {}
            }
            //TextView TextFail = (TextView) findViewById(R.id.fail);
            //TextFail.setText("Fail : "+nbFail);
        }
        //Log.i("Timer Left : ", TimerLeft + "");
        if (true == isFailGame) {
            startEndActivity()
        } else {
            NewRound()
        }
    }

    private fun startTimer() {
        CountDownTimer = object : CountDownTimer(TEMPS_TIMER, 1000) {
            override fun onTick(l: Long) {
                TimerLeft = l
                updateCountDownText()
            }

            override fun onFinish() {
                if (typeFin == "") {
                    typeFin = "Timer"
                    startEndActivity()
                }
            }
        }.start()
    }

    private fun updateCountDownText() {
        val minutes = (TimerLeft / 1000).toInt() / 60
        val secondes = (TimerLeft / 1000).toInt() % 60
        val TimerLeftFormat = String.format(Locale.getDefault(), "%02d:%02d", minutes, secondes)
        TimerView!!.text = TimerLeftFormat
    }

    private fun startEndActivity() {
        val intent = Intent(this, FinDuJeu::class.java)
        intent.putExtra("Score", Integer.toString(nbScore)) //Optional parameters
        intent.putExtra("mode", mode) //Optional parameters
        intent.putExtra("Type", typeFin) //Optional parameters
        //Log.i("SCOREMAINActivity", " : "+ nbScore);
        startActivity(intent)
    }

    companion object {
        private const val TEMPS_TIMER: Long = 60000 // en milliseconde soit 6 sec
    }
}