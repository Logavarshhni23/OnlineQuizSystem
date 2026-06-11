import { useState } from 'react'
import Login from './components/Login'
import Quiz from './components/Quiz'
import AdminDashboard from './components/AdminDashboard'
import './App.css'

function App() {
  const [page, setPage] = useState('welcome')
  const [token, setToken] = useState(null)
  const [userName, setUserName] = useState('')

  const handleLogin = (jwtToken, role, name) => {
    setToken(jwtToken)
    setPage(role === 'ADMIN' ? 'admin' : 'quiz')
    setUserName(name)
  }

  if (page === 'login') return <Login onLogin={handleLogin} />
  if (page === 'quiz') return <Quiz token={token} userName={userName} />
  if (page === 'admin') return <AdminDashboard token={token} />

  return (
    <div className="welcome-page">
      <h1 className="welcome-title">Welcome to Online Quiz</h1>
      <p className="welcome-sub">Test your knowledge and challenge yourself!</p>
      <button className="start-btn" onClick={() => setPage('login')}>Start Quiz</button>
    </div>
  )
}

export default App
