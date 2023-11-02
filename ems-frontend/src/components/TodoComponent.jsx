import React, { useState, useEffect }  from 'react'
import {useParams, useNavigate} from 'react-router-dom';
import { getTodoByIdAPI, saveTodoAPI, updateTodoByIdAPI } from '../services/TodoService';


const TodoComponent = () => {

    const {id} = useParams();

    const navigator = useNavigate();

    const [todo, setTodo] = useState({
        'title': '',
        'description': '',
        'completed': false
    });

    const saveOrUpdateTodo = (event) => {
        event.preventDefault();

        console.log(todo);

        if(id){
            updateTodoByIdAPI(id, todo).then(response => {
                console.log(response.data);
                navigator("/todos");
            }).catch(err => console.error(err));
        }
        else{
            saveTodoAPI(todo).then(response => {
                console.log(response.data);
                navigator("/todos");
            }).catch(err => console.error(err));    
        }
    }

    useEffect(()=> {
        if(id){
            getTodoByIdAPI(id).then(response => {
                console.log(response.data);
                setTodo(response.data);
            }).catch(err => console.error(err));
        }
    }, [id])

    return (
        <div className='container'>
            <br /><br />
            <div className='row'>
                <div className='card col-md-6 offset-md-3'>
                    <h2 className='text-center'>Add Todo</h2>
                    <div className='card-body'>
                        <form>
                            <div className='form-group mb-2'>
                                <label className='form-label'>Title:</label>
                                <input
                                    type='text'
                                    placeholder='Enter title'
                                    name='title'
                                    value={todo.title}
                                    className='form-control'
                                    onChange={(e) => setTodo({ ...todo, title: e.target.value })} />
                            </div>

                            <div className='form-group mb-2'>
                                <label className='form-label'>Description:</label>
                                <input
                                    type='text'
                                    placeholder='Enter description'
                                    name='description'
                                    value={todo.description}
                                    className='form-control'
                                    onChange={(e) => setTodo({ ...todo, description: e.target.value })} />
                            </div>

                            <div className='form-group mb-2'>
                                <label className='form-label'>Complete:</label>
                                <select className='form-control' name='completed' value={todo.completed} onChange={(e) => setTodo({...todo, completed:e.target.value})} >
                                    <option value="complete"  disabled={true} >complete</option>
                                    <option value={Boolean(false)}>NO</option>
                                    <option value={Boolean(true)}>YES</option>
                                </select>
                            </div>

                            <button className='btn btn-success' onClick={saveOrUpdateTodo}>Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default TodoComponent