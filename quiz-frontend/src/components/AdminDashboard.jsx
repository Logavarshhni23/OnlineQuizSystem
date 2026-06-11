import { useState, useEffect } from 'react'
import { getQuestions, addQuestion, deleteQuestion, getUsers } from '../api'

const empty = { questionText: '', optionA: '', optionB: '', optionC: '', optionD: '', correctAnswer: '' }

export default function AdminDashboard({ token }) {
  const [tab, setTab] = useState('questions')
  const [questions, setQuestions] = useState([])
  const [users, setUsers] = useState([])
  const [form, setForm] = useState(empty)
  const [showForm, setShowForm] = useState(false)
  const [error, setError] = useState('')
  const [success, setSuccess] = useState('')

  const fetchQuestions = () => getQuestions(token).then(setQuestions).catch(() => {})
  const fetchUsers = () => getUsers(token).then(setUsers).catch(() => {})

  useEffect(() => {
    fetchQuestions()
    fetchUsers()
    const interval = setInterval(() => {
      fetchQuestions()
      fetchUsers()
    }, 5000)
    return () => clearInterval(interval)
  }, [])

  const handleChange = (e) => setForm({ ...form, [e.target.name]: e.target.value })

  const handleAdd = async (e) => {
    e.preventDefault()
    setError('')
    setSuccess('')
    try {
      await addQuestion(token, form)
      setSuccess('Question added successfully!')
      setForm(empty)
      setShowForm(false)
      fetchQuestions()
    } catch {
      setError('Failed to add question')
    }
  }

  const handleDelete = async (id) => {
    try {
      await deleteQuestion(token, id)
      setQuestions(questions.filter(q => q.id !== id))
    } catch {
      setError('Failed to delete question')
    }
  }

  return (
    <div className="admin-page">
      <div className="admin-header">
        <h1 className="welcome-title">Admin Dashboard</h1>
        {tab === 'questions' && (
          <button className="start-btn" onClick={() => setShowForm(!showForm)}>
            {showForm ? 'Close' : '+ Add Question'}
          </button>
        )}
      </div>

      {/* Tabs */}
      <div className="admin-tabs">
        <button className={`tab-btn ${tab === 'questions' ? 'active' : ''}`} onClick={() => setTab('questions')}>
          Questions ({questions.length})
        </button>
        <button className={`tab-btn ${tab === 'users' ? 'active' : ''}`} onClick={() => setTab('users')}>
          Registered Users ({users.length})
        </button>
      </div>

      {/* Questions Tab */}
      {tab === 'questions' && (
        <>
          {showForm && (
            <div className="question-form-box">
              <h2>Add New Question</h2>
              {error && <p className="error-msg">{error}</p>}
              {success && <p className="success-msg">{success}</p>}
              <form onSubmit={handleAdd}>
                <input name="questionText" placeholder="Question" value={form.questionText} onChange={handleChange} required />
                <input name="optionA" placeholder="Option A" value={form.optionA} onChange={handleChange} required />
                <input name="optionB" placeholder="Option B" value={form.optionB} onChange={handleChange} required />
                <input name="optionC" placeholder="Option C" value={form.optionC} onChange={handleChange} required />
                <input name="optionD" placeholder="Option D" value={form.optionD} onChange={handleChange} required />
                <input name="correctAnswer" placeholder="Correct Answer (e.g. OptionA text)" value={form.correctAnswer} onChange={handleChange} required />
                <button type="submit">Add Question</button>
              </form>
            </div>
          )}
          <div className="question-list">
            <h2>All Questions</h2>
            {questions.length === 0 && <p className="quiz-count">No questions yet.</p>}
            {questions.map((q) => (
              <div key={q.id} className="question-card">
                <p className="q-text">{q.questionText}</p>
                <ul className="q-options">
                  <li>A: {q.optionA}</li>
                  <li>B: {q.optionB}</li>
                  <li>C: {q.optionC}</li>
                  <li>D: {q.optionD}</li>
                </ul>
                <p className="q-answer">Answer: {q.correctAnswer}</p>
                <button className="delete-btn" onClick={() => handleDelete(q.id)}>Delete</button>
              </div>
            ))}
          </div>
        </>
      )}

      {/* Users Tab */}
      {tab === 'users' && (
        <div className="question-list">
          <h2>Registered Users</h2>
          {users.length === 0 && <p className="quiz-count">No users registered yet.</p>}
          {users.map((u) => (
            <div key={u.id} className="question-card">
              <p className="q-text">{u.name}</p>
              <p className="q-options" style={{color: '#9B30FF'}}>{u.email}</p>
              <p className="q-answer">Role: {u.role}</p>
            </div>
          ))}
        </div>
      )}
    </div>
  )
}
