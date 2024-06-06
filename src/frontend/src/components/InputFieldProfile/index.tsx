import React from 'react';
import './styles.scss'; 

// Propriedades do campo de entrada
interface InputFieldProfileProps {
    label: string; 
    value: string;
}

// Componente de campo de entrada para o perfil do usuário
const InputFieldProfile: React.FC<InputFieldProfileProps> = ({ label, value }) => {
    return (
        <div className='inputfield-container'>
            <p className='inputfield-label'>{label}</p> 
            <p className='inputfield-value'>{value}</p>
        </div>
    );
}

export default InputFieldProfile;
