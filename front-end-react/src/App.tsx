import { Routes, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import { Home } from "./components/Home";
import { ExamList } from "./components/exam/ExamList";
import { ExamForm } from "./components/exam/ExamForm";
import { ExamCard } from "./components/exam/ExamCard";

const title = "Online Test";
const description = "Aplicación web para la automatización de cuestionarios en línea";

const App: React.FC = () => {
  return (
    <div>
      <nav className="navbar navbar-expand navbar-dark bg-dark">        
        <Link to={"/"}  className="navbar-brand">
          NRC 6515
        </Link>
        <div className="navbar-nav mr-auto">
          <li className="nav-item">
            <Link to={"/exams"} className="nav-link">
              Exámenes
            </Link>
          </li>          
        </div>
      </nav>
      <div className="container mt-3">
        <Routes>
          <Route path="/" element={<Home title={title} description={description} />} />          
          <Route path="/exams" element={<ExamList />} />          
          <Route path="/exams/create" element={<ExamForm />} />    
          <Route path="/exams/retrieve/:id" element={<ExamCard/>} />      
          <Route path="/exams/update/:id" element={<ExamForm />} />    
        </Routes>
      </div>
    </div>
  );
}
export default App;
