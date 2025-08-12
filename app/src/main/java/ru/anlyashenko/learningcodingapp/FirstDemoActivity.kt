package ru.anlyashenko.learningcodingapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.anlyashenko.learningcodingapp.databinding.ActivityFirstDemoBinding
import java.io.Serializable

class FirstDemoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirstDemoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFirstDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. без передачи данных
        // 2. с передачей данных
        // 3. с ожиданием результата

        val word = ExtraWord(
            "galaxy",
            "галактика",
        )

        binding.btnOpenSecond.setOnClickListener {
            val intent = Intent(this@FirstDemoActivity, SecondDemoActivity::class.java)
            intent.putExtra("EXTRA_KEY_TEXT", "don't panic")
            intent.putExtra("EXTRA_KEY_NUM", 42)
            intent.putExtra("EXTRA_KEY_WORD", word)
            startActivity(intent)
        }
    }

    data class ExtraWord(
        val original: String,
        val translate: String,
        var learned: Boolean = false,
    ) : Serializable

}