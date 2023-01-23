package kz.sfizfaka.geoquiz_2
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import kz.sfizfaka.geoquiz_2.databinding.ActivityMainBinding


private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val quizViewModel: QuizViewModel by viewModels()

    private var rightAnswer = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "Got a QuizViewModel: $quizViewModel")

        binding.trueButton.setOnClickListener {
            checkAnswer(true)
            binding.trueButton.isClickable = false
            binding.falseButton.isClickable = false
        }
        binding.falseButton.setOnClickListener {
            checkAnswer(false)
            binding.trueButton.isClickable = false
            binding.falseButton.isClickable = false
        }
        binding.nextButton.setOnClickListener {
            quizViewModel.moveToNext()
            updateQuestion()
            binding.trueButton.isClickable = true
            binding.falseButton.isClickable = true
        }
         binding.prevButton.setOnClickListener {
            try {
                quizViewModel.moveToPrevious()
            }
            catch (ex: ArrayIndexOutOfBoundsException) {
                // Регистрация сообщения с уровнем регистрации "error" с трассировкой стека исключений
                Log.e(TAG, "Index was out of bounds", ex)
            }
             updateQuestion()
            binding.trueButton.isClickable = true
            binding.falseButton.isClickable = true
        }
        updateQuestion()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,"onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"onDestroy called")
    }
    private fun updateQuestion() {
        val questionTextResId = quizViewModel.currentQuestionText
        binding.questionTextView.setText(questionTextResId)
    }
    private fun checkAnswer(userAnswer: Boolean){
        val correctAnswer = quizViewModel.currentQuestionAnswer
        if (userAnswer == correctAnswer)
            rightAnswer += 1
        val total = rightAnswer.toDouble()/quizViewModel.questionBankSize.toDouble() * 100
        val tl = String.format("%.2f",total)
        val messageResId = if (userAnswer == correctAnswer)
            R.string.correct_toast
        else
            R.string.incorrect_toast
        Toast.makeText(this,messageResId, Toast.LENGTH_SHORT).show()
        if (quizViewModel.questionCurrentIndex == 5)
            Toast.makeText(this,"Your result is $tl %", Toast.LENGTH_LONG).show()

    }
}