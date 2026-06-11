import axios from 'axios'

const api = axios.create({
  baseURL: 'http://localhost:8080',
})

export async function loginUser(email, password) {
  const res = await api.post('/api/auth/login', { email, password })
  return res.data
}

export async function registerUser(name, email, password) {
  const res = await api.post('/api/auth/register', { name, email, password })
  return res.data
}

export async function getQuestions(token) {
  const res = await api.get('/questions', {
    headers: { Authorization: `Bearer ${token}` },
  })
  return res.data
}

export async function getUsers(token) {
  const res = await api.get('/api/auth/users', {
    headers: { Authorization: `Bearer ${token}` },
  })
  return res.data
}

export async function addQuestion(token, question) {
  const res = await api.post('/questions', question, {
    headers: { Authorization: `Bearer ${token}` },
  })
  return res.data
}

export async function deleteQuestion(token, id) {
  const res = await api.delete(`/questions/${id}`, {
    headers: { Authorization: `Bearer ${token}` },
  })
  return res.data
}
