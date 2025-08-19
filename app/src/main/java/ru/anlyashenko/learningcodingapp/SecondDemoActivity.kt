package ru.anlyashenko.learningcodingapp

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.anlyashenko.learningcodingapp.FirstDemoActivity.ExtraWord
import ru.anlyashenko.learningcodingapp.databinding.ActivityFirstDemoBinding
import ru.anlyashenko.learningcodingapp.databinding.ActivitySecondDemoBinding

class SecondDemoActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondDemoBinding

    private val locationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                Log.d("SecondDemoActivity", "Разрешение на локацию получено")
            } else {
                Log.d("SecondDemoActivity", "Разрешение на локацию отклонено")
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.i("!!!", "${this.componentName.shortClassName} Выполняется метод onCreate() ")

        val btnLocation = binding.btnRequestPermission
        btnLocation.setOnClickListener {
            locationPermissionLauncher.launch(ACCESS_FINE_LOCATION)
        }

        with(binding) {
            btnOpenFirst.setOnClickListener {
                val intent = Intent(this@SecondDemoActivity, FirstDemoActivity::class.java)
                startActivity(intent)
            }

//            val text = intent.getStringExtra("EXTRA_KEY_TEXT")
//            val number = intent.getIntExtra("EXTRA_KEY_NUM", 0)
//            val word = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//                intent.getSerializableExtra("EXTRA_KEY_WORD", ExtraWord::class.java) as ExtraWord
//            } else {
//                intent.getSerializableExtra("EXTRA_KEY_WORD") as ExtraWord // deprecated
//            }

//            val word2 = intent.extras?.getSerializable("EXTRA_KEY_WORD", ExtraWord::class.java)
//            val word3 = intent.extras?.get("EXTRA_KEY_WORD")

//            val word = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//                intent.getParcelableExtra("EXTRA_KEY_WORD", ExtraWord::class.java)
//            } else {
//                intent.getParcelableExtra("EXTRA_KEY_WORD")
//            }

            val bundle = intent.extras
            val text = bundle?.getString("EXTRA_KEY_TEXT")
            val number = bundle?.getInt("EXTRA_KEY_NUM")
            val word = bundle?.getSerializable("EXTRA_KEY_WORD", ExtraWord::class.java)

            tvText.text = text
            tvNumber.text = number.toString()
            tvWord.text = word?.original
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i("!!!", "${this.componentName.shortClassName} Выполняется метод onStart() ")
    }

    override fun onResume() {
        super.onResume()
        Log.i("!!!", "${this.componentName.shortClassName} Выполняется метод onResume() ")

    }

    override fun onPause() {
        super.onPause()
        Log.i("!!!", "${this.componentName.shortClassName} Выполняется метод onPause() ")

    }

    override fun onStop() {
        super.onStop()
        Log.i("!!!", "${this.componentName.shortClassName} Выполняется метод onStop() ")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("!!!", "${this.componentName.shortClassName} Выполняется метод onDestroy() ")

    }
}