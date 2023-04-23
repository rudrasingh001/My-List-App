import React, { useState } from 'react';
import './App.css';


import List from './List';

function App() {
  const [items, setItems] = useState([
    { text: "Item 1" },
    { text: "Item 2" },
    { text: "Item 3" },
  ]);

  const addNewItem = () => {
    const newItem = { text: `Item ${items.length + 1}` };
    setItems([...items, newItem]);
  };

  return (
    <div className="App">
      <h1>My List App</h1>
      <List items={items} updateItems={setItems} />
      <button onClick={addNewItem} className="add-new-item-btn">Add New Event</button>
    </div>
  );
}


export default App;







