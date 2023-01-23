package kz.sfizfaka.geoquiz_2

import androidx.lifecycle.SavedStateHandle
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class QuizViewModelTest {
    @Test
    fun providesExpectedQuestionText(){
        val savedStateHandle = SavedStateHandle()
        val quizViewModel = QuizViewModel(savedStateHandle)
    assertEquals(R.string.question_australia,quizViewModel.currentQuestionText)
    }
    @Test
    fun providesExpectedQuestionAnswers(){
        val savedStateHandle = SavedStateHandle()
        val quizViewModel = QuizViewModel(savedStateHandle)
    assertTrue("",quizViewModel.currentQuestionAnswer)
    }
}