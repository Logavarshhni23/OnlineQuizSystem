import axios from 'axios'

const api = axios.create({
  baseURL: 'http://localhost:8080',
})

function authHeader(token) {
  return { Authorization: 'Bearer ' + token }
}

export async function loginUser(email, password) {
  const res = await api.post('/api/auth/login', { email, password })
  return res.data
}

export async function registerUser(name, email, password) {
  const res = await api.post('/api/auth/register', { name, email, password })
  return res.data
}

export async function getQuestions(token) {
  const res = await api.get('/questions', { headers: authHeader(token) })
  return res.data
}

export async function getUsers(token) {
  const res = await api.get('/api/auth/users', { headers: authHeader(token) })
  return res.data
}

export async function saveResult(token, score, totalQuestions) {
  const res = await api.post('/api/results', { score, totalQuestions, submittedAt: new Date().toISOString() }, { headers: authHeader(token) })
  return res.data
}

export async function getAllResults(token) {
  const res = await api.get('/api/results', { headers: authHeader(token) })
  return res.data
}

export async function addQuestion(token, question) {
  const res = await api.post('/questions', question, { headers: authHeader(token) })
  return res.data
}

export async function deleteQuestion(token, id) {
  const res = await api.delete('/questions/' + id, { headers: authHeader(token) })
  return res.data
}
