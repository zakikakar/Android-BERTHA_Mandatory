package zakiexample.myfirstapp.bertha_mandatory

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlin.math.log
import android.widget.EditText
import android.content.SharedPreferences
import android.provider.Telephony.Carriers.PASSWORD
import android.provider.Telephony.Carriers.PASSWORD
import android.R.attr.password
import android.content.SharedPreferences.Editor
import android.widget.CheckBox
import android.R.id.edit
import android.net.Uri
import android.widget.TextView


class LoginActivity : AppCompatActivity() {

    val PREF_FILE_NAME = "loginPref"
    val USERNAME = "USERNAME"
    val PASSWORD = "PASSWORD"

    private var preferences: SharedPreferences? = null
    private var usernameField: EditText? = null
    private var passwordField: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        preferences = getSharedPreferences(PREF_FILE_NAME, MODE_PRIVATE);


            usernameField = findViewById(R.id.editText_username);
            passwordField = findViewById(R.id.editText_password);


        // '!!' tjekker om preferences er NULL
        var username = preferences!!.getString(USERNAME, "")
        var password = preferences!!.getString(PASSWORD, "")

        // '!!' tjekker om usernameField er NULL
        usernameField!!.setText(username);
        passwordField!!.setText(password);
    }

    override fun onStart() {
        super.onStart()

        login_button.setOnClickListener {
            loginButtonClicked()
        }

        fb_button.setOnClickListener {
            fbButtonClicked()
        }

        twit_button.setOnClickListener {
            twitButtonClicked()
        }
    }


    private fun loginButtonClicked() {

        var username = usernameField!!.getText().toString()
        var password = passwordField!!.getText().toString()

        val ok = PasswordChecker.Check(username, password)

        if (ok) {
            val checkBox = findViewById(R.id.login_remember_checkbox) as CheckBox;
            val editor = preferences!!.edit()
            if (checkBox.isChecked()) {
                editor.putString(USERNAME, username);
                editor.putString(PASSWORD, password);
            } else {
                editor.remove(USERNAME);
                editor.remove(PASSWORD);
            }
            editor.apply()
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(USERNAME, username)
            startActivity(intent);
        } else {
            usernameField!!.setError("Wrong username or password");
            val messageView = findViewById(R.id.login_message_textview) as TextView;
            messageView.setText("Username + password not valid");
        }
    }

    fun fbButtonClicked() {
            val url = "https://www.facebook.com/"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent);

    }

    fun twitButtonClicked() {
        val url = "https://twitter.com/?lang=da"
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent);

    }
}



