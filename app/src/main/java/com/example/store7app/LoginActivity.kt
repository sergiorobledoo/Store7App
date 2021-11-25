package com.example.store7app

import android.content.Intent
import android.graphics.Color
import android.graphics.Color.RED
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.SignInButton
import androidx.core.app.ActivityCompat.startActivityForResult





class LoginActivity : AppCompatActivity() {

    private var user: EditText? = null
    private var password: EditText? = null
    private var robot: CheckBox? = null
    private var vLayout:LinearLayout?=null
    private var vResult: TextView?=null
    private lateinit var googleSignInClient: GoogleSignInClient
    private val RC_SIGN_IN = 0

    override fun onStart() {
        super.onStart()
        val account = GoogleSignIn.getLastSignedInAccount(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            val marker = Intent(this,MarkerActivity::class.java)
            startActivity(marker)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        googleSignInClient  =GoogleSignIn.getClient(this,gso)
        var user: EditText = findViewById(R.id.txtUser)
        var password: EditText = findViewById(R.id.txtPassword)
        var robot: CheckBox = findViewById(R.id.chRobot)
        var button: Button = findViewById(R.id.ingreso)
        var vista:TextView=findViewById(R.id.vResult)
        var buttonGoogle: SignInButton=findViewById(R.id.sign_in_button)
        buttonGoogle.setOnClickListener { view ->
            val signInIntent: Intent = googleSignInClient.signInIntent

            startActivityForResult(signInIntent, RC_SIGN_IN)
        }

        // Register the onClick listener with the implementation above
        button.setOnClickListener { view ->
            // do something when the button is clicked
            var usuario = user!!.text.toString()
            var contrasena = password!!.text.toString()

            if (usuario.isEmpty() || contrasena.isEmpty()) {
                Toast.makeText(
                    applicationContext, "LOS CAMPOS ESTAN VACIOS",
                    Toast.LENGTH_LONG).show()
                vista.text="ERROR DE LOGUEO"
                vista.setTextColor( Color.RED )
            } else {
                if (usuario == "giovanny" && contrasena=="1234") {
                    if(robot.isChecked){
                        vista.text="OK USUARIO LOGUEADO"
                        vista.setTextColor( Color.GREEN)
                        Toast.makeText(applicationContext, "ok", Toast.LENGTH_LONG).show()

                        val marker = Intent(this,MarkerActivity::class.java)
                        startActivity(marker)
                    }else{
                        Toast.makeText(
                            applicationContext, "CHEQUEA QUE NO ERES UN ROBOT",
                            Toast.LENGTH_LONG).show()
                        vista.text="ERROR DE LOGUEO"
                        vista.setTextColor( Color.RED )

                    }
                } else {
                    Toast.makeText(
                        applicationContext,"USUARIO NO EXISTE O PASSWORD INCORRECTO",
                        Toast.LENGTH_SHORT).show()
                    vista.text="ERROR DE LOGUEO"
                    vista.setTextColor( Color.RED )
                }
            }
        }
    }
}






