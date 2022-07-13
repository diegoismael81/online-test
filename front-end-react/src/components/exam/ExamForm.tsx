import { ChangeEvent, useEffect, useState } from "react";
import { FaArrowLeft, FaSave } from "react-icons/fa";
import { Link, useNavigate, useParams } from "react-router-dom";
import IExamModel from "../../models/Exam";
import ExamService from "../../services/ExamServices";

export const ExamForm = () => {
	
  const { id }= useParams();
  let navigate = useNavigate();

    //Model vacío
    const initialExamModel : IExamModel = {
        id: null,
        title: "",
        description: "",
        timeLimit: 60,
        minimumPassingScore: 14.01,
        numberOfQuestions: 10,
        instructions: ""
    };

    //Hooks para gestionar el modelo
    const [exam, setExam] = useState<IExamModel>(initialExamModel);
    
    //Escucha los cambios en cada control Input y los asigna a los valores del Modelo
    const handleInputChange = (event: ChangeEvent<HTMLInputElement>) => {
        const { name, value } = event.target;
        setExam({ ...exam, [name]: value });
    };

		const handleTextAreaChange = (event: ChangeEvent<HTMLTextAreaElement>) => {
			const { name, value } = event.target;
			setExam({ ...exam, [name]: value });
	};

    const saveExam = () => {        
      if(exam.id !== null)
      {
        ExamService.update(exam)
        .then((response: any) => {
          navigate("/exams");
          console.log(response.data);
        })
        .catch((e: Error) => {
          console.log(e);
        });
      }
      else
      {
			  ExamService.create(exam)
          .then((response: any) => {    
            navigate("/exams");
            console.log(response.data);
          })
          .catch((e: Error) => {
            console.log(e);
          });
      }
    };

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


		return ( //JSX
			<div className="submit-form">				
					<div>
						{ exam.id !== null ? (<h1>Actualizado examen {exam.title}</h1>) : (<h1>Registro de nuevo examen</h1>) }            
						<div className="form-group">
						<label htmlFor="title">Título</label>
            <input
              type="text"
							placeholder="Ingrese el título del examen"
              className="form-control"
              id="title"
              required
              value={exam.title}
              onChange={handleInputChange}
              name="title"
            />
						<label htmlFor="description">Descripción</label>
            <input						
              type="text"
              className="form-control"
							placeholder="Ingrese la descripción del examen"
              id="description"
              required
              value={exam.description}
              onChange={handleInputChange}
              name="description"
            />
						<label htmlFor="timeLimit">Tiempo límite (Minutos)</label>
            <input						
              type="number"
              className="form-control"
              id="timeLimit"
							max="180"
							min="30"
              required
              value={exam.timeLimit}
              onChange={handleInputChange}
              name="timeLimit"
            />
						<label htmlFor="minimumPassingScore">Nota mínima de aprobación</label>
            <input						
              type="number"
              className="form-control"
              id="minimumPassingScore"
							max="20"							
							step="0.01"
              value={exam.minimumPassingScore!}
              onChange={handleInputChange}
              name="minimumPassingScore"
            />
						<label htmlFor="numberOfQuestions">Número de preguntas</label>
            <input						
              type="number"
              className="form-control"
              id="numberOfQuestions"							
							min="1"
              required
              value={exam.numberOfQuestions!}
              onChange={handleInputChange}
              name="numberOfQuestions"
            />
						<label htmlFor="instructions">Instrucciones</label>
            <textarea	
              className="form-control"
							placeholder="Ingrese las instrucciones para el examen"
              id="instructions"              
              value={exam.instructions!}							
              onChange={handleTextAreaChange}
              name="instructions"
            />
						<br />
							<div className="btn-group" role="group">								
                <Link to={"/exams"} className="btn btn-primary">
                    <FaArrowLeft /> Volver
                </Link>
								<button type="button" onClick={saveExam} className="btn btn-success">
                  <FaSave />Guardar
                </button>
							</div>
						</div>
					</div>				
			</div>        
    );

}