import { useState, useEffect } from 'react'
import { getQuestions, saveResult } from '../api'

export default function Quiz({ token, userName }) {
  const [questions, setQuestions] = useState([])
  const [current, setCurrent] = useState(0)
  const [selected, setSelected] = useState(null)
  const [score, setScore] = useState(0)
  const [finished, setFinished] = useState(false)
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState('')

  useEffect(() => {
    getQuestions(token)
      .then(setQuestions)
      .catch((err) => setError(err.message))
      .finally(() => setLoading(false))
  }, [token])

  const handleNext = async () => {
    const q = questions[current]
    const options = [q.optionA, q.optionB, q.optionC, q.optionD]
    const newScore = options[selected] === q.correctAnswer ? score + 1 : score
    if (options[selected] === q.correctAnswer) setScore(newScore)

    if (current + 1 < questions.length) {
      setCurrent(current + 1)
      setSelected(null)
    } else {
      try { await saveResult(token, newScore, questions.length) } catch (e) { console.error('Failed to save result', e) }
      setFinished(true)
    }
  }

  if (loading) return <div className="quiz-page"><div className="quiz-box"><p className="quiz-count">Loading questions...</p></div></div>
  if (error) return <div className="quiz-page"><div className="quiz-box"><p className="error-msg">{error}</p></div></div>
  if (questions.length === 0) return <div className="quiz-page"><div className="quiz-box"><p className="quiz-count">No questions found.</p></div></div>

  if (finished) {
    return (
      <div className="quiz-page">
        <div className="quiz-box">
          <h2>Quiz Completed!</h2>
          <p className="quiz-user">{userName}</p>
          <p className="score">Your Score: {score} / {questions.length}</p>
          <p className="quiz-count">{score === questions.length ? 'Perfect Score!' : score >= questions.length / 2 ? 'Good Job!' : 'Keep Practicing!'}</p>
        </div>
      </div>
    )
  }

  const q = questions[current]
  const options = [q.optionA, q.optionB, q.optionC, q.optionD]

  return (
    <div className="quiz-page">
      <div className="quiz-box">
        <p className="quiz-user">{userName}</p>
        <p className="quiz-count">Question {current + 1} of {questions.length}</p>
        <h2>{q.questionText}</h2>
        <ul className="options">
          {options.map((opt, i) => (
            <li
              key={i}
              className={`option ${selected === i ? 'selected' : ''}`}
              onClick={() => setSelected(i)}
            >
              {opt}
            </li>
          ))}
        </ul>
        <button className="next-btn" onClick={handleNext} disabled={selected === null}>
          {current + 1 === questions.length ? 'Finish' : 'Next'}
        </button>
      </div>
    </div>
  )
}
