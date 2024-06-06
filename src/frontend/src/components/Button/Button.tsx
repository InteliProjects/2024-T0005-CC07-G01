import React from 'react';
import './styles.scss';


// Propriedades do botão
interface ButtonProps {
  onClick: () => void;
  label: string;
  className?: string;
}

// Componente de botão
const Button: React.FC<ButtonProps> = ({ onClick, label, className }) => {
  return (
    <button onClick={onClick} className={`button ${className || ''}`}>
      {label}
    </button>
  );
};

export default Button;