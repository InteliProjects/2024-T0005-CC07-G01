// InputField.tsx
import React from 'react';
import './styles.scss';

// Propriedades do campo de entrada
interface InputFieldProps {
  name: string;
  type: string;
  placeholder: string;
  handleBlur?: (e: React.FocusEvent<HTMLInputElement, Element>) => void;
  value: string;
  touched?: boolean;
  errors?: string;
  onChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
}

// Componente de campo de entrada
const InputField: React.FC<InputFieldProps> = ({ name, type, placeholder, onChange, handleBlur, value, touched, errors }) => {
  return (
    <>
      <input
        className="input-field"
        id={name}
        name={name}
        type={type}
        placeholder={placeholder}
        onChange={onChange}
        onBlur={handleBlur}
        value={value}
      />
      {touched && errors && <div className="error">{errors}</div>}
    </>
  );
};

export default InputField;