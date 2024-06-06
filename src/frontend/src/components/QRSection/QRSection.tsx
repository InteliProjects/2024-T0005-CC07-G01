// QRSection.tsx
// Componente reutilizável para a seção de QR Code.
import React from 'react';
import qr from '../../assets/qr-code-vivo.svg';

// Seção de QR Code
const QRSection: React.FC = () => {
  return (
    <div className="qr-container">
      <h2 className="qr-title">Baixe o app Vivo</h2>
      <p className="qr-text">
        Acesse as informações do seu celular, TV, internet e telefone
        fixo, pague faturas e muito mais!
      </p>
      <div className="qr-image-container">
        <img className="qr-image" src={qr} alt="QR Code Vivo"></img>
      </div>
    </div>
  );
};

export default QRSection;
