import { useEffect, useState } from "react";
import { FaArrowLeft, FaTrash } from "react-icons/fa";
import { Link } from 'react-router-dom';
import { useParams } from "react-router-dom";
import IExamModel from "../../models/Exam";
import ExamService from "../../services/ExamServices";

export const ExamCard = () => {
  const { id }= useParams();

  const [exam, setExam] = useState<IExamModel>();

  useEffect(() => {
    if (id)
      getExam(id);
  }, [id]);


  const getExam = (id: any) => {
    ExamService.retrieve(id)
      .then((response: any) => {
        setExam(response.data); //Víncula el resultado del servicio con la función del Hook useState
        console.log(response.data);
      })
      .catch((e: Error) => {
        console.log(e);
      });
 };

    return (
      <div>
      { 
        exam ? (
          <div>          
          <h2>{exam.title}</h2>
          <p>{exam.description}</p>
          <ul>
            <li> <strong>Tiempo límite (mins) :</strong>  {exam.timeLimit} minutos</li>
            <li>Nota mínima : {exam.minimumPassingScore} /20</li>
            <li>Número de preguntas : {exam.numberOfQuestions}</li>
            <li>Instrucciones : {exam.instructions}</li>
          </ul>
          <br />
							<div className="btn-group" role="group">								
                <Link to={"/exams"} className="btn btn-primary">
                    <FaArrowLeft /> Volver
                </Link>
								<button type="button" className="btn btn-danger">
                  <FaTrash />Eliminar
                </button>
							</div>
          </div>

        ) : 
        ( 
          <h1>No hay un examen activo</h1>
        )
      }
      </div>
    );
}