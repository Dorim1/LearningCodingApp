package ru.anlyashenko.learningcodingapp


data class Word(
    val original: String,
    val translate: String,
    var learned: Boolean = false,
)

data class Question(
    val variants: List<Word>,
    val correctAnswer: Word,
)

class LearnWordsTrainer {

    private val dictionary = listOf(
        Word("Towel", "Полотенце"),
        Word("Apple", "Яблоко"),
        Word("Dog", "Собака"),
        Word("Cat", "Кошка"),
        Word("Book", "Книга"),
        Word("Table", "Стол"),
        Word("Chair", "Стул"),
        Word("Window", "Окно"),
        Word("Pen", "Ручка"),
        Word("Phone", "Телефон"),
        Word("Computer", "Компьютер"),
        Word("Water", "Вода"),
        Word("Food", "Еда"),
        Word("Car", "Машина"),
        Word("Door", "Дверь"),
        Word("Shoe", "Туфля / Обувь"),
        Word("Hat", "Шляпа / Головной убор"),
        Word("Sun", "Солнце"),
        Word("Moon", "Луна"),
        Word("Star", "Звезда")
    )

    private var currentQuestion: Question? = null

    fun getNextQuestion(): Question? {

        val notLearnedList = dictionary.filter { !it.learned }
        if (notLearnedList.isEmpty()) return null

        val questionWords =
            if (notLearnedList.size < NUMBER_OF_ANSWER) {
                val learnedList = dictionary.filter { it.learned }.shuffled()
                notLearnedList.shuffled().take(NUMBER_OF_ANSWER) + learnedList.take(NUMBER_OF_ANSWER - notLearnedList.size)
            } else {
                notLearnedList.shuffled().take(NUMBER_OF_ANSWER)
            }.shuffled()

        val correctAnswer = questionWords.random()

        currentQuestion = Question(
            variants = questionWords,
            correctAnswer = correctAnswer
        )
        return currentQuestion

    }

    fun checkAnswer(userAnswerIndex: Int?): Boolean {
        return currentQuestion?.let {
            val correctAnswerId = it.variants.indexOf(it.correctAnswer)
            if (correctAnswerId == userAnswerIndex) {
                it.correctAnswer.learned = true
                true
            } else {
                false
            }
        } ?: false
    }
}

const val NUMBER_OF_ANSWER: Int = 4