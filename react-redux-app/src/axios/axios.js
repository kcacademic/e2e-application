import axios from 'axios'

export default axios.create({
    baseURL: 'http://localhost:8085/api',
    auth: {
        username: 'admin',
        password: 'password'
    }
})