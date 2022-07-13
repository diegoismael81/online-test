import { FaPen, FaEye, FaTrash, FaPlus } from "react-icons/fa";
import React, { useState, useEffect } from 'react'
import { Link } from 'react-router-dom';
import IExamModel from '../../models/Exam';
import ExamService from '../../services/ExamServices';
import Swal from "sweetalert2";
import ReactPaginate from "react-paginate";

export const ExamList = () => {

    //Hook: Define un atributo y la función que lo va a actualizar
    const [exams, setExams] = useState<Array<IExamModel>>([]);
    const [itemsCount, setItemsCount] = useState<number>(0);
    const [pageCount, setPageCount] = useState(0);
    const [itemsPerPage, setItemsPerPage] = useState(5);
    
    //Hook para llamar a la Web API
    useEffect(() => {
      getItems();  
      listExams(0, itemsPerPage);           
      }, []);

    const handlePageClick = (event: any) => {        
      const numberPage = event.selected;                   
      listExams(numberPage, itemsPerPage);
    };

    //Función que llama al Service para listar los datos desde la Web API
    const listExams = (page: number, size: number) => {
       ExamService.list(page, size)
         .then((response: any) => {
           setExams(response.data); //Víncula el resultado del servicio con la función del Hook useState
           console.log(response.data);
         })
         .catch((e: Error) => {
           console.log(e);
         });
    };

    const getItems = () => {
      ExamService.count().then((response: any) =>{
        var itemsCount = response;
        setItemsCount(itemsCount);
        setPageCount(Math.ceil(itemsCount/ itemsPerPage));           
        setItemsPerPage(5)
        console.log(response);
      }).catch((e : Error)=> {
        console.log(e);
      });
    }

    const removeExam = (id: number) => {
        Swal.fire({
            title: '¿Desea eliminar el examen?',
            showDenyButton: true,
            confirmButtonText: 'Si',
            denyButtonText: 'No',
          }).then((result) => {            
            if (result.isConfirmed) {
                ExamService.remove(id)
                .then((response: any) => {
                  listExams(0,itemsPerPage);
                  console.log(response.data);
                })
                .catch((e: Error) => {
                  console.log(e);
                });      

            }
          });        
     };
   
    return ( 
        <div className='list row'>
            <h1>Hay {itemsCount} exámenes</h1>
            <div className='col-md-12'>
                <table className='table'>
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Título</th>
                            <th>Descripción</th>
                            <th>Tiempo límite</th>
                            <th># Preguntas</th>
                            <th>
                              <Link to={"/exams/create"} className="btn btn-success">
                                  <FaPlus /> Agregar
                              </Link>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        {exams && exams.map((Exam, index) => (
                            <tr key={index}>
                                <td>{++index}</td>
                                <td>{Exam.title}</td>
                                <td>{Exam.description}</td>
                                <td>{Exam.timeLimit} mins</td>
                                <td>{Exam.numberOfQuestions}</td>
                                <td>
                        
                                <div className="btn-group" role="group">
                                <Link to={"/exams/retrieve/" + Exam.id} className="btn btn-warning">
                                    <FaEye /> Ver
                                  </Link>                                  
                                  <Link to={"/exams/update/" + Exam.id} className="btn btn-primary">
                                      <FaPen /> Editar
                                  </Link>

                                  <button className="btn btn-danger" onClick={() => removeExam(Exam.id!)}>
                                    <FaTrash /> Eliminar
                                  </button>

                                  
                                </div>
                                    
                                </td>
                            </tr>                        
                        ))}
                    </tbody>
                </table>

                <ReactPaginate
                  className="pagination"
                  breakLabel="..."
                  nextLabel="siguiente >"
                  onPageChange={handlePageClick}
                  pageRangeDisplayed={5}
                  pageCount={pageCount}
                  previousLabel="< anterior"/>

            </div>            
        </div>
     );
}