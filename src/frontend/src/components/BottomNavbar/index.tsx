// Importando as bibliotecas e arquivos necessários
import React from 'react';
import './style.scss'; // Make sure this path is correct and the file exists
import { Link } from "react-router-dom";
import { CgNotes } from "react-icons/cg";
import { CgProfile } from "react-icons/cg";

// Componente de barra de navegação inferior
const BottomNavbar: React.FC = () => {
  return (
    <nav className="bottom-navbar">
      <Link to="/home" className="nav-item">
        <svg width="20" height="20" viewBox="0 0 20 20" fill="none" xmlns="http://www.w3.org/2000/svg">
          <path d="M4.34713 16.6806V10.5139H2.2915L9.99994 3.31921L17.7083 10.5137H15.6528V16.6805H11.5416V12.0555H8.45827V16.6805L4.34713 16.6806Z" stroke="#A1A1A1" strokeWidth="2.4375" strokeMiterlimit="5" strokeLinecap="round" strokeLinejoin="round"/>
        </svg>
        <span>Início</span>
      </Link>
      <Link to="/plano" className="nav-item">
        {/* Ícone do Meu Plano usando react-icons */}
        <CgNotes className="icon-notes" />
        <span>Meu Plano</span>
      </Link>
      <Link to="/perfil" className="nav-item">
        {/* Ícone de perfil do react-icons */}
        <CgProfile className="icon-profile" />
        <span>Perfil</span>
      </Link>
    </nav>
  );
};

export default BottomNavbar;
