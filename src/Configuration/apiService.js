import axios from 'axios'

const jwtToken = localStorage.getItem("token");

const api=axios.create({
    baseURL:'http://localhost:1111/',
    headers: {
        'Content-Type': 'application/json',
        "Authorization": `Bearer ${jwtToken}`,

      },
});

export default api