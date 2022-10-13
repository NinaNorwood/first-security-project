import React, {useState} from 'react';
import './App.css';
import axios from "axios";

function App() {

    const [welcome, setWelcome] = useState("");

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [me, setMe] = useState("");

    function fetchWelcome() {
        axios.get("/hello")
            .then(response => {return response.data})
            .then(data => setWelcome(data))
    }

    function handleLogin() {
        axios.get("/user/login", {auth: {username, password}})
            .then(response => {return response.data})
            .then(() => setMe(username))
            .then(() => setUsername(""))
            .then(() => setPassword(""))
            .catch(() => ("Sorry, User oder Passwort war falsch!"))
    }

    function handleLogout() {
        axios.get("/user/logout")
            .then(() => setMe(""))
    }

  return (
    <div className="App">
      <header className="App-header">

          { !me ?
          <>
              <h3>Login</h3>
              <input value={username} onChange={event => setUsername(event.target.value)}/>
              <input type="password" value={password} onChange={event => setPassword(event.target.value)}/>
              <button onClick={handleLogin}>Login</button>
          </> :
              <>
                  <p>Du bist eingeloggt als {me}</p>
                  <button onClick={handleLogout}>Logout</button>
                 <p>{welcome}</p>
                <button onClick={fetchWelcome}>Say Hello</button>
              </> }

      </header>
    </div>
  );
}

export default App;
