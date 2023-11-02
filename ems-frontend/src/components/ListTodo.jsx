import React, { useEffect, useState } from 'react'
import { completeTodoByIdAPI, deleteTodoByIdAPI, getAllTodosAPI, inCompleteTodoByIdAPI } from '../services/TodoService';
import { Link, useNavigate } from 'react-router-dom';


const ListTodo = () => {

    const [todos, setTodos] = useState([]);
    const [searchTerm, setSearchTerm] = useState('');
    const navigator = useNavigate();



    useEffect(() => {
        listTodos();
    }, []);

    const listTodos = () => {
        getAllTodosAPI().then(response => {
            setTodos(response.data);
        }).catch(err => console.error(err));
    }

    const updateTodo = (todoId) => {
        navigator("/edit-todo/" + todoId)
    }

    const deleteTodo = (todoId) => {
        deleteTodoByIdAPI(todoId).then(() => {
            listTodos();
        }).catch(err => console.error(err));
    }

    const markCompleteTodo = (todoId) => {
        completeTodoByIdAPI(todoId).then(() => {
            listTodos();
        }).catch(err => console.error(err));
    }
    const markInCompleteTodo = (todoId) => {
        inCompleteTodoByIdAPI(todoId).then(() => {
            listTodos();
        }).catch(err => console.error(err));
    }

    const filteredData = todos.filter(todo => todo.title.toLowerCase().includes(searchTerm.toLowerCase()));

    return (
        <div className='container'>
            <h2 className='text-center'>List of Todos</h2>
            <Link to="/add-todo" className='btn btn-primary mb-2'>Add Todo</Link>
            <input type='text' placeholder='Ara...' value={searchTerm} onChange={(e) => setSearchTerm(e.target.value)} />
            <table className='table table-striped table-bordered'>
                <thead>
                    <tr>
                        <td className='d-flex justify-content-center align-items-center fw-bold'>Id</td>
                        <td className='text-center fw-bold'>Title</td>
                        <td className='text-center fw-bold'>Description</td>
                        <td className='text-center fw-bold'>Complete</td>
                        <td className='text-center fw-bold'>Actions</td>
                    </tr>
                </thead>

                <tbody>
                    { searchTerm.length === 0 ? (
                        todos.map(todo => (
                            <tr key={todo.id}>
                                <td className='text-center align-middle'>{todo.id}</td>
                                <td className='text-center align-middle'>{todo.title}</td>
                                <td className='text-center align-middle'>{todo.description}</td>
                                <td className='text-center align-middle'>{todo.completed ? "YES" : "NO"}</td>
                                <td>
                                    <div className='container'>
                                        <div className='row' style={{display:'flex', justifyContent:'center'}}>
                                            <button className='btn btn-info' style={{ margin: '5px', width: '8rem' }} onClick={() => updateTodo(todo.id)}>Update</button>
                                            <button className='btn btn-danger' style={{ margin: '5px', width: '8rem' }} onClick={() => deleteTodo(todo.id)}>Delete</button>
                                        </div>
                                    </div>

                                    <div className='container'>
                                        <div className='row' style={{display:'flex', justifyContent:'center'}}>
                                            <button className='btn btn-success' style={{ margin: '5px', width: '8rem' }} onClick={() => markCompleteTodo(todo.id)}>Complete</button>
                                            <button className='btn btn-warning' style={{ margin: '5px', width: '8rem' }} onClick={() => markInCompleteTodo(todo.id)}>In-Complete</button>
                                        </div>
                                    </div>

                                </td>
                            </tr>
                        ))
                        ): (filteredData.map( todo => (
                            <tr key={todo.id}>
                                 <td className='text-center align-middle'>{todo.id}</td>
                                <td className='text-center align-middle'>{todo.title}</td>
                                <td className='text-center align-middle'>{todo.description}</td>
                                <td className='text-center align-middle'>{todo.completed ? "YES" : "NO"}</td>
                                <td>
                                    <div className='container'>
                                        <div className='row' style={{display:'flex', justifyContent:'center'}}>
                                            <button className='btn btn-info' style={{ margin: '5px', width: '8rem' }} onClick={() => updateTodo(todo.id)}>Update</button>
                                            <button className='btn btn-danger' style={{ margin: '5px', width: '8rem' }} onClick={() => deleteTodo(todo.id)}>Delete</button>
                                        </div>
                                    </div>

                                    <div className='container'>
                                        <div className='row' style={{display:'flex', justifyContent:'center'}}>
                                            <button className='btn btn-success' style={{ margin: '5px', width: '8rem' }} onClick={() => markCompleteTodo(todo.id)}>Complete</button>
                                            <button className='btn btn-warning' style={{ margin: '5px', width: '8rem' }} onClick={() => markInCompleteTodo(todo.id)}>In-Complete</button>
                                        </div>
                                    </div>

                                </td>
                            </tr>
                        )))

                    }
                </tbody>
            </table>
        </div>
    )
}

export default ListTodo