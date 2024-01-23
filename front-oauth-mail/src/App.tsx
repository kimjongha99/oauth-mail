import React, {ChangeEvent, useState} from 'react';
import './App.css';
import InputBox from "./components/inputBox/input-box";

function App() {

  const [id, setId] = useState<string>('');
  const  onIdChangeHandler = (event:ChangeEvent<HTMLInputElement>)=>{
    const {value} =event.target;
    setId(value);
  }

  return (
    <div className="App">

    <InputBox title='아이디' placeholder='아이디를 입력해주세요' type='text' value={id} isErrorMessage={false} onChange={onIdChangeHandler} />
    </div>
  );
}

export default App;
