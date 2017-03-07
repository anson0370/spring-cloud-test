const express = require('express');
const app = express();

app.get('/square', (req, res) => {
  const n = parseInt(req.query.n);
  console.log(`square ${n}`);
  res.json(n * n);
});

app.get('/health', (req, res) => {
  console.log('health checked');
  res.send({
    status: 'UP'
  });
});

app.get('/', (req, res) => {
  res.send('Home page');
});

app.listen(5555);
console.log('Listen at port 5555');